package com.nickfistere.airmendatabase.airmencertification.importDb;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class ImportRequest {
    private String href;


    public URL getHref() throws MalformedURLException {
        return new URL(href);
    }

    public void setHref(String href) {
        this.href = href;
    }

}
