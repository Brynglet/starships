package com.example.domain;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Endpoint {

    APISystem value();
    enum APISystem {
        SWAPI
    }
}
