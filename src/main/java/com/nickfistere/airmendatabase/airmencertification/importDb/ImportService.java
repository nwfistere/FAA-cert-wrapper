package com.nickfistere.airmendatabase.airmencertification.importDb;

import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.util.ApplicationProperties;
import com.nickfistere.airmendatabase.airmencertification.util.CsvUtil;
import com.nickfistere.airmendatabase.airmencertification.util.UnclosableInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ImportService {

    private static final String URL_FORMAT = "https://registry.faa.gov/database/CS%s.zip";
    private static final String URL_DATE_FORMAT = "MMyyyy";

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
        List<String> filepaths = new ArrayList<>();

        try {
            if (importRequest.getHref().isPresent()) {
                href = importRequest.getHref().get();
            } else {
                // If href is empty, come up with the url ourselves.
                DateFormat df = new SimpleDateFormat(URL_DATE_FORMAT);
                String urlString = String.format(URL_FORMAT, df.format(new Date()));
                href = new URL(urlString);
            }
        } catch (MalformedURLException malformedURLException) {
            results.add(new ImportResult(malformedURLException,"Href is invalid."));
            completableFuture.complete(results);
            return completableFuture;
        }

        try {
            downloadStream(href, filepaths::add, (f, e) -> results.add(new ImportResult(f, e, "Failed to import file.")));
            if (filepaths.isEmpty()) {
                throw new IOException("No files were unzipped.");
            }
            filepaths.forEach((f) -> results.add(new ImportResult(f, true)));
        } catch (IOException ioException) {
            results.add(new ImportResult(ioException,"Failed to download and unzip files."));
            completableFuture.complete(results);
            return completableFuture;
        }

        completableFuture.complete(results);

        return completableFuture;
    }

    private void importCsv(InputStream in, String filepath) throws IOException {
        File file = new File(filepath);
        if (!fileNameToClassMap.containsKey(file.getName())) {
            throw new RuntimeException("Filename:" + file.getName() + " is unexpected.");
        }

        List<?> models;

        // If the file is a cert file, we need to process the csv differently.
        if (file.getName().contains("CERT")) {
            models = CsvUtil.readAsArray(fileNameToClassMap.get(file.getName()), in);
        } else {
            models = CsvUtil.read(fileNameToClassMap.get(file.getName()), in);
        }

        JpaRepository repo = importRepositoryFactory.get(fileNameToClassMap.get(file.getName()));

        repo.saveAll(models);
    }

    private void downloadStream(URL url, Consumer<String> onSuccess, BiConsumer<String, Exception> onFailure)
            throws IOException {

        ZipInputStream zipInputStream = new ZipInputStream(url.openStream());
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            // Only process csv files.
            if (!zipEntry.isDirectory() && zipEntry.getName().endsWith("csv")) {
                try {
                    importCsv(new UnclosableInputStream(zipInputStream), zipEntry.getName());
                    onSuccess.accept(zipEntry.getName());
                } catch (Exception e) {
                    onFailure.accept(zipEntry.getName(), e);
                }
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
    }

}
