package com.nickfistere.airmendatabase.airmencertification.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.Constructor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvUtil {
    private static final CsvMapper mapper = new CsvMapper()
        .enable(CsvParser.Feature.TRIM_SPACES)
        .enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);
    private static final CsvMapper arrayMapper = (CsvMapper) new CsvMapper()
        .enable(CsvParser.Feature.WRAP_AS_ARRAY)
        .enable(CsvParser.Feature.TRIM_SPACES)
        .disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);

    public static <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
        CsvSchema schema = mapper.schemaFor(clazz).withHeader().withoutQuoteChar().withColumnReordering(true);
        ObjectReader reader = mapper.readerFor(clazz).with(schema);
        return reader.<T>readValues(stream).readAll();
    }

    public static <T> List<T> readAsArray(Class<T> clazz, InputStream stream) throws IOException {
        MappingIterator<List<String>> rows = arrayMapper.readerFor(List.class).readValues(stream);

        // Remove header from list.
        if (rows.hasNext()) {
            rows.next();
        } else {
            return new ArrayList<>();
        }

        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
        return rows.readAll().stream().filter(row -> !row.isEmpty()).map(x -> {
            try {
                // [0] is default construct, [1] is constructor with Array parameter
                return (T)constructors[1].newInstance(x);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
