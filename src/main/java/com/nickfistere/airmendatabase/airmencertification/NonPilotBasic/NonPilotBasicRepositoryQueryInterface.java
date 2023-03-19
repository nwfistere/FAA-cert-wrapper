package com.nickfistere.airmendatabase.airmencertification.NonPilotBasic;

import com.nickfistere.airmendatabase.airmencertification.PilotBasic.PilotBasicQueryModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface NonPilotBasicRepositoryQueryInterface extends JpaRepository<NonPilotBasicQueryModel, Long> {}
