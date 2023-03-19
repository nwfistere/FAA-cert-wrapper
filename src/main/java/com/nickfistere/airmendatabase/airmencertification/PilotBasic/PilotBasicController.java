package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PilotBasicController {

    @Autowired
    PilotBasicRepositoryQueryInterface pilotBasicQueryRepository;

    @GetMapping("/pilots")
    ResponseEntity<Page<PilotBasicQueryModel>> getPilots(
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        @RequestParam Optional<String> sort
    ) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10),
            Sort.by(sort.orElse("uniqueId")));
        return new ResponseEntity<>(pilotBasicQueryRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/pilots-advanced")
    ResponseEntity<Page<PilotBasicQueryModel>> getPilots(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sort
    ) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10),
                Sort.by(sort.orElse("uniqueId")));
        return new ResponseEntity<>(pilotBasicQueryRepository.findAll(pageable), HttpStatus.OK);
    }

}
