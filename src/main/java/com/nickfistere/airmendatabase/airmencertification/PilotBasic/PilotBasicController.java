package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

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
public class PilotBasicController {

    static ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    @Autowired
    PilotBasicService pilotBasicService;

    @GetMapping("/pilots")
    ResponseEntity<Page<PilotBasicQueryModel>> getPilotsAdvanced(
            PilotBasicQueryModel pilot,
            Pageable page
    ) {
        Example<PilotBasicQueryModel> example = Example.of(pilot, matcher);
        return new ResponseEntity<>(pilotBasicService.findPilotsByExample(example, page), HttpStatus.OK);
    }

}
