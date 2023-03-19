package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class PilotBasicService {
    @Autowired
    PilotBasicRepositoryQueryInterface pilotBasicQueryRepository;

    Page<PilotBasicQueryModel> findPilotsByExample(Example<PilotBasicQueryModel> example, Pageable page) {
        return pilotBasicQueryRepository.findAll(example, page);
    }

}
