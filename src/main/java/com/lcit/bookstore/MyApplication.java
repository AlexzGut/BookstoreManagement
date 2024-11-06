package com.lcit.bookstore;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.validation.ValidationFeature;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("com.lcit.bookstore", "com.lcit.exception");
        register(ValidationFeature.class);
        register(JacksonFeature.class);
    }
}