package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PilotBasicRepositoryQueryInterface extends JpaRepository<PilotBasicQueryModel, Long> {
}
