package com.nickfistere.airmendatabase.airmencertification.PilotCert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nickfistere.airmendatabase.airmencertification.CertModel;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "Pilot_Cert")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PilotCertModel extends CertModel {

    public PilotCertModel() {
        super();
    }

    public PilotCertModel(List<String> row) {
        super(row);
        this.typeRatings = row.subList(18,
            row.size()).stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());

    }

    private List<String> typeRatings;

    public List<String> getTypeRatings() {
        return typeRatings;
    }

    public void setTypeRatings(List<String> typeRatings) {
        this.typeRatings = typeRatings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PilotCertModel that = (PilotCertModel) o;
        return Objects.equals(typeRatings, that.typeRatings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeRatings);
    }
}
