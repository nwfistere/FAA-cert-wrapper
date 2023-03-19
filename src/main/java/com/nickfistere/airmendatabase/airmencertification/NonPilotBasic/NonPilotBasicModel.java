package com.nickfistere.airmendatabase.airmencertification.NonPilotBasic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nickfistere.airmendatabase.airmencertification.BasicModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Non_Pilot_Basic")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NonPilotBasicModel extends BasicModel {}
