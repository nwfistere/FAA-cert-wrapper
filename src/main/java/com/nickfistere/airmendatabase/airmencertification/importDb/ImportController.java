package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import com.nickfistere.airmendatabase.airmencertification.NonPilotBasic.NonPilotBasicModel;
import com.nickfistere.airmendatabase.airmencertification.NonPilotBasic.NonPilotBasicRepositoryInterface;
import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertRepositoryInterface;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertRepositoryInterface;
import com.nickfistere.airmendatabase.airmencertification.util.CsvUtil;
import com.nickfistere.airmendatabase.airmencertification.importDb.ImportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
public class ImportController {

    @Autowired
    PilotBasicRepositoryInterface pilotBasicRepository;

    @Autowired
    PilotCertRepositoryInterface pilotCertRepository;

    @Autowired
    NonPilotBasicRepositoryInterface nonPilotBasicRepository;

    @Autowired
    NonPilotCertRepositoryInterface nonPilotCertRepository;

    @PostMapping("/import")
    ResponseEntity<List<PilotBasicModel>> importDb(@RequestBody ImportRequest importRequest) throws IOException {
        String pilotBasicPath = importRequest.getPilotBasicPath();
        String pilotCertPath = importRequest.getPilotCertPath();
        String nonPilotBasicPath = importRequest.getNonPilotBasicPath();
        String nonPilotCertPath = importRequest.getNonPilotCertPath();
        if (pilotBasicPath != null && !pilotBasicPath.isEmpty()) {
            InputStream in = new FileInputStream(pilotBasicPath);
            List<PilotBasicModel> models = CsvUtil.read(PilotBasicModel.class, in);
            pilotBasicRepository.saveAll(models);
        }
        if (pilotCertPath != null && !pilotCertPath.isEmpty()) {
            InputStream in = new FileInputStream(pilotCertPath);
            List<PilotCertModel> models = CsvUtil.readAsArray(PilotCertModel.class, in);
            pilotCertRepository.saveAll(models);
        }
        if (nonPilotBasicPath != null && !nonPilotBasicPath.isEmpty()) {
            InputStream in = new FileInputStream(nonPilotBasicPath);
            List<NonPilotBasicModel> models = CsvUtil.read(NonPilotBasicModel.class, in);
            nonPilotBasicRepository.saveAll(models);
        }
        if (nonPilotCertPath != null && !nonPilotCertPath.isEmpty()) {
            InputStream in = new FileInputStream(nonPilotCertPath);
            List<NonPilotCertModel> models = CsvUtil.readAsArray(NonPilotCertModel.class, in);
            nonPilotCertRepository.saveAll(models);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
