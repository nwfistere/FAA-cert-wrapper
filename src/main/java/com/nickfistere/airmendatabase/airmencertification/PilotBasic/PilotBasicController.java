package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class PilotBasicController {

    @Autowired
    PilotBasicService pilotBasicService;

    @GetMapping("/pilots")
    ResponseEntity<Page<PilotBasicQueryModel>> getPilotsAdvanced(
            PilotBasicQueryModel pilot,
            Pageable page
    ) {
        ExampleMatcher matcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<PilotBasicQueryModel> example = Example.of(pilot, matcher);

        return new ResponseEntity<>(pilotBasicService.findPilotsByExample(example, page), HttpStatus.OK);
    }

}
