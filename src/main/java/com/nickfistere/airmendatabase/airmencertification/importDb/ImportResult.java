package com.nickfistere.airmendatabase.airmencertification.importDb;

public class ImportResult {
    private String filepath;
    private Boolean pass;
    private Exception exception;

    private String message;

    public ImportResult (String filepath, Exception exception, String message) {
        this.filepath = filepath;
        this.pass = false;
        this.exception = exception;
        this.message = message;
    }

    public ImportResult (Exception exception, String message) {
        this.filepath = null;
        this.pass = false;
        this.exception = exception;
        this.message = message;
    }

    public ImportResult (String filepath, Boolean pass) {
        this.filepath = filepath;
        this.pass = pass;
        this.exception = null;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
