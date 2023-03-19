package com.nickfistere.airmendatabase.airmencertification.NonPilotCert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nickfistere.airmendatabase.airmencertification.CertModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Non_Pilot_Cert")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NonPilotCertModel extends CertModel {

    public NonPilotCertModel() {
        super();
    }

    public NonPilotCertModel(List<String> row) {
        super(row);
    }
}
