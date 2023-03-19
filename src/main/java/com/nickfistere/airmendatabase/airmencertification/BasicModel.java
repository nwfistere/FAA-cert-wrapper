package com.nickfistere.airmendatabase.airmencertification;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class BasicModel {
    @Id
    @JsonProperty("UNIQUE ID")
    protected String uniqueId;
    @JsonProperty("FIRST NAME")
    protected String firstName;
    @JsonProperty("LAST NAME")
    protected String lastName;
    @JsonProperty("STREET 1")
    protected String street1;
    @JsonProperty("STREET 2")
    protected String street2;
    @JsonProperty("CITY")
    protected String city;
    @JsonProperty("STATE")
    protected String state;
    @JsonProperty("ZIP CODE")
    protected String zipCode;
    @JsonProperty("COUNTRY")
    protected String country;
    @JsonProperty("REGION")
    protected String region;
    @JsonProperty("MED CLASS")
    protected String medClass;
    @JsonProperty("MED DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMyyyy")
    protected Date medDate;
    @JsonProperty("MED EXP DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMyyyy")
    protected Date medExpDate;
    @JsonProperty("BASIC MED COURSE DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    protected Date basicMedCourseDate;
    @JsonProperty("BASIC MED CMEC DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    protected Date basicMedCMECDate;

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
}
