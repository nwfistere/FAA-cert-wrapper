package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nickfistere.airmendatabase.airmencertification.BasicModel;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pilot_Basic")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PilotBasicQueryModel extends BasicModel {
    @OneToMany
    @JoinColumn(name="uniqueId")
    @JsonProperty
    private Set<PilotCertModel> certificates = new HashSet<>();
}
