package com.nickfistere.airmendatabase.airmencertification.importDb;

import com.nickfistere.airmendatabase.airmencertification.PilotBasic.PilotBasicModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PilotBasicRepositoryInterface extends JpaRepository<PilotBasicModel, Long> {
}

