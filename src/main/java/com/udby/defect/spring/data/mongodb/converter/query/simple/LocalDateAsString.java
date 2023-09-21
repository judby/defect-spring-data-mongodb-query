package com.udby.defect.spring.data.mongodb.converter.query.simple;

import org.springframework.data.convert.ValueConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@ValueConverter(LocalDateToStringPropertyConverter.class)
public @interface LocalDateAsString {
}
