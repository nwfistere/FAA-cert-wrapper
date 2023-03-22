package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PilotBasicService {
    @Autowired
    PilotBasicRepositoryQueryInterface pilotBasicQueryRepository;

    public Page<PilotBasicQueryModel> findPilotsByExample(Example<PilotBasicQueryModel> example, Pageable page) {
        return pilotBasicQueryRepository.findAll(example, page);
    }

}
