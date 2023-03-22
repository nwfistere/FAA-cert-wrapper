package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import com.nickfistere.airmendatabase.airmencertification.importDb.*;
import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertRepositoryInterface;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertRepositoryInterface;
import com.nickfistere.airmendatabase.airmencertification.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
public class ImportController {

    @Autowired
    ImportService importService;

    @PostMapping("/import")
    ResponseEntity<String> importDb(@RequestBody ImportRequest importRequest) throws IOException {
        importService.importDb(importRequest);
        return new ResponseEntity<>("Successfully started import.", HttpStatus.OK);
    }
}
