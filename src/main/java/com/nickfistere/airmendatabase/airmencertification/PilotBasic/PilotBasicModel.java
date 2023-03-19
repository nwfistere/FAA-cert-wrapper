package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nickfistere.airmendatabase.airmencertification.BasicModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pilot_Basic")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PilotBasicModel extends BasicModel {

//    @OneToMany
//    @JoinColumn(name="uniqueId")
//    @JsonProperty
//    private Set<PilotCertModel> certificates = new HashSet<>();
}
