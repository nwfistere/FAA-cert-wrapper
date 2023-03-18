package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PilotBasicRepositoryInterface extends JpaRepository<PilotBasicModel, Long> {
}
