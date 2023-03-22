package com.nickfistere.airmendatabase.airmencertification.NonPilotBasic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nickfistere.airmendatabase.airmencertification.BasicModel;
import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Non_Pilot_Basic")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NonPilotBasicQueryModel extends BasicModel {

    @OneToMany
    @JoinColumn(name="uniqueId")
    @JsonProperty
    private Set<NonPilotCertModel> certificates = new HashSet<>();
}
