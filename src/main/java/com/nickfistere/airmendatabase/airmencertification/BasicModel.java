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
}
