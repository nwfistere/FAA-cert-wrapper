package com.nickfistere.airmendatabase.airmencertification.importDb;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface NonPilotBasicRepositoryInterface extends JpaRepository<NonPilotBasicModel, Long> {}
