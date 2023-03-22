package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import com.nickfistere.airmendatabase.airmencertification.NonPilotBasic.NonPilotBasicQueryModel;
import com.nickfistere.airmendatabase.airmencertification.NonPilotBasic.NonPilotBasicRepositoryQueryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NonPilotBasicService {
    @Autowired
    NonPilotBasicRepositoryQueryInterface nonPilotBasicQueryRepository;

    public Page<NonPilotBasicQueryModel> findNonPilotsByExample(Example<NonPilotBasicQueryModel> example, Pageable page) {
        return nonPilotBasicQueryRepository.findAll(example, page);
    }

}
