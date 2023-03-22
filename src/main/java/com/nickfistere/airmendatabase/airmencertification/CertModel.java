package com.nickfistere.airmendatabase.airmencertification;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@MappedSuperclass
public class CertModel {

    protected static final SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy", Locale.ENGLISH);

    public CertModel() {}

    public CertModel(List<String> row) {
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
    protected long id;
    @JsonProperty("UNIQUE ID")
    protected String uniqueId;
    @JsonProperty("FIRST NAME")
    protected String firstName;
    @JsonProperty("LAST NAME")
    protected String lastName;
    @JsonProperty("TYPE")
    protected String type;
    @JsonProperty("LEVEL")
    protected String level;
    @JsonProperty("EXPIRE DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMddyyyy")
    protected Date expireDate;
    protected List<String> ratings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
        CertModel certModel = (CertModel) o;
        return id == certModel.id
            && Objects.equals(uniqueId, certModel.uniqueId)
            && Objects.equals(firstName, certModel.firstName)
            && Objects.equals(lastName, certModel.lastName)
            && Objects.equals(type, certModel.type)
            && Objects.equals(level, certModel.level)
            && Objects.equals(expireDate, certModel.expireDate)
            && Objects.equals(ratings, certModel.ratings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uniqueId, firstName, lastName, type, level, expireDate, ratings);
    }
}
