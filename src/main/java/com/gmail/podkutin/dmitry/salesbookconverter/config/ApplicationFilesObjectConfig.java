package com.gmail.podkutin.dmitry.salesbookconverter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.File;
import java.util.Objects;

@Configuration
public class ApplicationFilesObjectConfig {

    private final Environment environment;

    public ApplicationFilesObjectConfig(@Autowired Environment env) {
        this.environment = env;
    }

    @Bean
    public File incomingFile() {
        return new File(Objects.requireNonNull(environment.getProperty("incomingFilePath")));
    }

    @Bean
    public File resultFile() {
        return new File(Objects.requireNonNull(environment.getProperty("resultFilePath")));
    }

    @Bean
    public File expectedFile() {
        return new File(Objects.requireNonNull(environment.getProperty("expectedFilePath")));
    }
}