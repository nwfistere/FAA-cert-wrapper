package com.nickfistere.airmendatabase.airmencertification.importDb;

import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertRepositoryInterface;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertRepositoryInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ImportRepositoryFactory {
    @Autowired
    PilotBasicRepositoryInterface pilotBasicRepository;

    @Autowired
    PilotCertRepositoryInterface pilotCertRepository;

    @Autowired
    NonPilotBasicRepositoryInterface nonPilotBasicRepository;

    @Autowired
    NonPilotCertRepositoryInterface nonPilotCertRepository;

    private Map<Class, JpaRepository<?, Long>> repoMap;

    @PostConstruct
    private void makeMap() {
        repoMap = Map.of(
                NonPilotBasicModel.class, nonPilotBasicRepository,
                NonPilotCertModel.class, nonPilotCertRepository,
                PilotBasicModel.class, pilotBasicRepository,
                PilotCertModel.class, pilotCertRepository
        );
    }

    private static final Map<String, Class> fileNameToClassMap = Map.of(
            "NONPILOT_BASIC.csv", NonPilotBasicModel.class,
            "NONPILOT_CERT.csv", NonPilotCertModel.class,
            "PILOT_BASIC.csv", PilotBasicModel.class,
            "PILOT_CERT.csv", PilotCertModel.class
    );

    public JpaRepository<?, Long> get(Class<?> clazz) {
        return repoMap.get(clazz);
    }
}
