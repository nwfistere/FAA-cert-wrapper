package com.nickfistere.airmendatabase.airmencertification.NonPilotBasic;

import com.nickfistere.airmendatabase.airmencertification.PilotBasic.NonPilotBasicService;
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
public class NonPilotBasicController {

    @Autowired
    NonPilotBasicService nonPilotBasicService;

    @GetMapping("/non-pilots")
    ResponseEntity<Page<NonPilotBasicQueryModel>> getPilotsAdvanced(
            NonPilotBasicQueryModel nonPilot,
            Pageable page
    ) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<NonPilotBasicQueryModel> example = Example.of(nonPilot, matcher);

        return new ResponseEntity<>(nonPilotBasicService.findNonPilotsByExample(example, page), HttpStatus.OK);
    }

}
