package com.dev.tenet.hackaton.config;

import com.dev.tenet.hackaton.describer.PersonToPersonOperationDescriber;
import com.dev.tenet.hackaton.model.PersonToPersonOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class AppConfig {

    @Autowired
    PersonToPersonOperationDescriber personToPersonOperationDescriber;

    @Bean
    public PersonToPersonOperation createPersonToPersonOperation() {
        PersonToPersonOperation personToPersonOperation = new PersonToPersonOperation();
        personToPersonOperation.setFields(Collections.emptyList());
        personToPersonOperation.setId(1);
        personToPersonOperation.setName("Перевод на карту");
        personToPersonOperation.setOperationDescriber(personToPersonOperationDescriber);
        return personToPersonOperation;
    }
}
