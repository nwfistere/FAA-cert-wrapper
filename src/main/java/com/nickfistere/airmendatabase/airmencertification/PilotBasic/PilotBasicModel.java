package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertModel;
import jakarta.persistence.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Pilot_Basic")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PilotBasicModel {

    @Id
    @JsonProperty("UNIQUE ID")
    private String uniqueId;

    @JsonProperty("FIRST NAME")
    private String firstName;
    @JsonProperty("LAST NAME")
    private String lastName;
    @JsonProperty("STREET 1")
    private String street1;
    @JsonProperty("STREET 2")
    private String street2;
    @JsonProperty("CITY")
    private String city;
    @JsonProperty("STATE")
    private String state;
    @JsonProperty("ZIP CODE")
    private String zipCode;
    @JsonProperty("COUNTRY")
    private String country;
    @JsonProperty("REGION")
    private String region;
    @JsonProperty("MED CLASS")
    private String medClass;
    @JsonProperty("MED DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMyyyy")
    private Date medDate;
    @JsonProperty("MED EXP DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMyyyy")
    private Date medExpDate;
    @JsonProperty("BASIC MED COURSE DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date basicMedCourseDate;
    @JsonProperty("BASIC MED CMEC DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date basicMedCMECDate;

//    @OneToMany
//    @JoinColumn(name="uniqueId")
//    @JsonProperty
//    private Set<PilotCertModel> certificates = new HashSet<>();

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

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMedClass() {
        return medClass;
    }

    public void setMedClass(String medClass) {
        this.medClass = medClass;
    }

    public Date getMedDate() {
        return medDate;
    }

    public void setMedDate(Date medDate) {
        this.medDate = medDate;
    }

    public Date getMedExpDate() {
        return medExpDate;
    }

    public void setMedExpDate(Date medExpDate) {
        this.medExpDate = medExpDate;
    }

    public Date getBasicMedCourseDate() {
        return basicMedCourseDate;
    }

    public void setBasicMedCourseDate(Date basicMedCourseDate) {
        this.basicMedCourseDate = basicMedCourseDate;
    }

    public Date getBasicMedCMECDate() {
        return basicMedCMECDate;
    }

    public void setBasicMedCMECDate(Date basicMedCMECDate) {
        this.basicMedCMECDate = basicMedCMECDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PilotBasicModel that = (PilotBasicModel) o;
        return Objects.equals(getUniqueId(), that.getUniqueId())
            && Objects.equals(getFirstName(), that.getFirstName())
            && Objects.equals(getLastName(), that.getLastName())
            && Objects.equals(getStreet1(), that.getStreet1())
            && Objects.equals(getStreet2(), that.getStreet2())
            && Objects.equals(getCity(), that.getCity())
            && Objects.equals(getState(), that.getState())
            && Objects.equals(getZipCode(), that.getZipCode())
            && Objects.equals(getCountry(), that.getCountry())
            && Objects.equals(getRegion(), that.getRegion())
            && Objects.equals(getMedClass(), that.getMedClass())
            && Objects.equals(getMedExpDate(), that.getMedExpDate())
            && Objects.equals(getBasicMedCourseDate(), that.getBasicMedCourseDate())
            && Objects.equals(getBasicMedCMECDate(), that.getBasicMedCMECDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUniqueId(), getFirstName(), getLastName(), getStreet1(), getStreet2(), getCity(),
            getState(), getZipCode(), getCountry(), getRegion(), getMedClass(), getMedExpDate(),
            getBasicMedCourseDate(), getBasicMedCMECDate());
    }
}
