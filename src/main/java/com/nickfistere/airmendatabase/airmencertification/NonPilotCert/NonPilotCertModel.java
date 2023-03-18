package com.nickfistere.airmendatabase.airmencertification.NonPilotCert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "Non_Pilot_Cert")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NonPilotCertModel {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy", Locale.ENGLISH);

    public NonPilotCertModel() {}

    public NonPilotCertModel(List<String> row) {
        this.uniqueId = row.get(0);
        this.firstName = row.get(1);
        this.lastName = row.get(2);
        this.type = row.get(3);
        this.level = row.get(4);
        try {
            this.expireDate = formatter.parse(row.get(5));
        } catch (ParseException e) {
            this.expireDate = null;
        }
        this.ratings = row.subList(6, 17).stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @JsonProperty("UNIQUE ID")
    private String uniqueId;
    @JsonProperty("FIRST NAME")
    private String firstName;
    @JsonProperty("LAST NAME")
    private String lastName;
    @JsonProperty("TYPE")
    private String type;
    @JsonProperty("LEVEL")
    private String level;
    @JsonProperty("EXPIRE DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMddyyyy")
    private Date expireDate;
    private List<String> ratings;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public List<String> getRatings() {
        return ratings;
    }

    public void setRatings(List<String> ratings) {
        this.ratings = ratings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonPilotCertModel that = (NonPilotCertModel) o;
        return Objects.equals(getUniqueId(), that.getUniqueId())
            && Objects.equals(getFirstName(), that.getFirstName())
            && Objects.equals(getLastName(), that.getLastName())
            && Objects.equals(getType(), that.getType())
            && Objects.equals(getLevel(), that.getLevel())
            && Objects.equals(getExpireDate(), that.getExpireDate())
            && Objects.equals(getRatings(), that.getRatings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUniqueId(), getFirstName(), getLastName(),
            getType(), getLevel(), getExpireDate(), getRatings());
    }
}
