package com.nickfistere.airmendatabase.airmencertification.importDb;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class ImportRequest {
    private Optional<String> href;

    public Optional<URL> getHref() throws MalformedURLException {
        if (href.isPresent()) {
            return Optional.of(new URL(href.get()));
        }
        return Optional.empty();
    }

    public void setHref(String href) {
        this.href = Optional.of(href);
    }

}
