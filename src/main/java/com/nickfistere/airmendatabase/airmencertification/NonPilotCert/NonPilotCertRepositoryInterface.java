package com.nickfistere.airmendatabase.airmencertification.NonPilotCert;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface NonPilotCertRepositoryInterface extends JpaRepository<NonPilotCertModel, Long> {
}
