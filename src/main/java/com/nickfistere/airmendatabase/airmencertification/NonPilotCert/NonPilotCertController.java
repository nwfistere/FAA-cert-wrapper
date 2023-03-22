package com.nickfistere.airmendatabase.airmencertification.NonPilotCert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NonPilotCertController {

    static ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    @Autowired
    NonPilotCertRepositoryInterface pilotCertRepository;

    @GetMapping("/non-pilot-certificates")
    ResponseEntity<Page<NonPilotCertModel>> getPilotCertificates(
            NonPilotCertModel nonPilotCertModel,
            Pageable page
    ) {
        Example<NonPilotCertModel> example = Example.of(nonPilotCertModel, matcher);
        return new ResponseEntity<>(pilotCertRepository.findAll(example, page), HttpStatus.OK);
    }
}
