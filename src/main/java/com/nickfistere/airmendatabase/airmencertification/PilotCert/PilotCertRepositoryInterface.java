package com.nickfistere.airmendatabase.airmencertification.PilotCert;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PilotCertRepositoryInterface extends JpaRepository<PilotCertModel, Long> {}
