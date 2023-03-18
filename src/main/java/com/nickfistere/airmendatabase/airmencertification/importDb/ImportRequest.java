package com.nickfistere.airmendatabase.airmencertification.importDb;


import java.util.Objects;

public class ImportRequest {
    private String href;
    private String pilotBasicPath;
    private String pilotCertPath;
    private String nonPilotBasicPath;
    private String nonPilotCertPath;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPilotBasicPath() {
        return pilotBasicPath;
    }

    public void setPilotBasicPath(String pilotBasicPath) {
        this.pilotBasicPath = pilotBasicPath;
    }

    public String getPilotCertPath() {
        return pilotCertPath;
    }

    public void setPilotCertPath(String pilotCertPath) {
        this.pilotCertPath = pilotCertPath;
    }

    public String getNonPilotBasicPath() {
        return nonPilotBasicPath;
    }

    public void setNonPilotBasicPath(String nonPilotBasicPath) {
        this.nonPilotBasicPath = nonPilotBasicPath;
    }

    public String getNonPilotCertPath() {
        return nonPilotCertPath;
    }

    public void setNonPilotCertPath(String nonPilotCertPath) {
        this.nonPilotCertPath = nonPilotCertPath;
    }
}
