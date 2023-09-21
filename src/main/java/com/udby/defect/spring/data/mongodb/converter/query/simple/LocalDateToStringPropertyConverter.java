package com.udby.defect.spring.data.mongodb.converter.query.simple;

import org.springframework.data.convert.PropertyValueConverter;
import org.springframework.data.convert.ValueConversionContext;
import org.springframework.data.mapping.PersistentProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateToStringPropertyConverter implements PropertyValueConverter<LocalDate, String, ValueConversionContext<? extends PersistentProperty<?>>> {
    @Override
    public LocalDate read(final String value, final ValueConversionContext context) {
        return value == null ? readNull(context) : LocalDate.parse(value);
    }

    @Override
    public String write(final LocalDate value, final ValueConversionContext context) {
        return value == null ? writeNull(context) : value.format(DateTimeFormatter.ISO_DATE);
    }
}
