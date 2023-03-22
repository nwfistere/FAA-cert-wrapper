package com.nickfistere.airmendatabase.airmencertification.importDb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nickfistere.airmendatabase.airmencertification.BasicModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pilot_Basic")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PilotBasicModel extends BasicModel {}
