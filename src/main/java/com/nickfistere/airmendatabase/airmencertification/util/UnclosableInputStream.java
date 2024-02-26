package com.nickfistere.airmendatabase.airmencertification.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * UnclosableInputStream
 *
 * This class seems to be necessary to provide jackson csvMapper
 * a stream without allowing it to close it once it's finished with
 * the stream. JsonMapper seems to have a feature to disable autoclose,
 * but from what I see, there's no option for csvMapper.
 */
public class UnclosableInputStream extends InputStream {

    private final InputStream input;

    public UnclosableInputStream(InputStream input) {
        this.input = input;
    }

    @Override
    public void close() throws IOException {}

    @Override
    public int read() throws IOException {
        return input.read();
    }
}
