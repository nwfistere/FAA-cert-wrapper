package com.nickfistere.airmendatabase.airmencertification.importDb;

import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.util.ApplicationProperties;
import com.nickfistere.airmendatabase.airmencertification.util.CsvUtil;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ImportService {

    @Autowired
    ImportRepositoryFactory importRepositoryFactory;

    @Autowired
    ApplicationProperties applicationProperties;

    private static final Map<String, Class> fileNameToClassMap = Map.of(
            "NONPILOT_BASIC.csv", NonPilotBasicModel.class,
            "NONPILOT_CERT.csv", NonPilotCertModel.class,
            "PILOT_BASIC.csv", PilotBasicModel.class,
            "PILOT_CERT.csv", PilotCertModel.class
    );

    @Async
    public Future<List<ImportResult>> importDb(ImportRequest importRequest) {
        List<ImportResult> results = new ArrayList<>();
        CompletableFuture<List<ImportResult>> completableFuture = new CompletableFuture<>();
        URL href;
        List<String> filepaths;

        try {
            href = importRequest.getHref();
        } catch (MalformedURLException malformedURLException) {
            results.add(new ImportResult(malformedURLException,"Href is invalid."));
            completableFuture.complete(results);
            return completableFuture;
        }

        try {
            filepaths = downloadAndUnzipFiles(href, applicationProperties.getDestination());
            if (filepaths.isEmpty()) {
                throw new IOException("No files were unzipped.");
            }
        } catch (IOException ioException) {
            results.add(new ImportResult(ioException,"Failed to download and unzip files."));
            completableFuture.complete(results);
            return completableFuture;
        }

        for (String filepath : filepaths) {
            boolean success = true;
            try {
                importCsv(filepath);
            } catch (Exception e) {
                success = false;
                results.add(new ImportResult(filepath, e, "Failed to import file."));
            }
            if (success) {
                results.add(new ImportResult(filepath, true));
            }
        }

        completableFuture.complete(results);

        return completableFuture;
    }

    private void importCsv(String filepath) throws IOException {
        File file = new File(filepath);
        if (!fileNameToClassMap.containsKey(file.getName())) {
            throw new RuntimeException("Filename:" + file.getName() + " is unexpected.");
        }

        List<?> models;
        InputStream in = new FileInputStream(filepath);

        // If the file is a cert file, we need to process the csv differently.
        if (file.getName().contains("CERT")) {
            models = CsvUtil.readAsArray(fileNameToClassMap.get(file.getName()), in);
        } else {
            models = CsvUtil.read(fileNameToClassMap.get(file.getName()), in);
        }

        JpaRepository repo = importRepositoryFactory.get(fileNameToClassMap.get(file.getName()));

        repo.saveAll(models);
    }

    private List<String> downloadAndUnzipFiles(URL url, String destinationDirectory) throws IOException {
        List<String> outputFiles = new ArrayList<>();
        File destination = new File(destinationDirectory);

        ZipInputStream zipInputStream = new ZipInputStream(url.openStream());
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            // Only process csv files.
            if (!zipEntry.isDirectory() && zipEntry.getName().endsWith("csv")) {
                String outputFile = destination.getAbsolutePath() + File.separator + zipEntry.getName();
                extractFile(zipInputStream, outputFile);
                outputFiles.add(outputFile);
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
        return outputFiles;
    }

    private void extractFile(ZipInputStream zipInputStream, String filePath) throws IOException {
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytes = new byte[1024];
        int read;
        while ((read = zipInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        outputStream.close();
    }
}
