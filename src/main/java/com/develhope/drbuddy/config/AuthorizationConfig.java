package com.develhope.drbuddy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import it.pasqualecavallo.studentsmaterial.authorization_framework.config.DefinitelyBasicAuthorizationFrameworkAutoconfiguration;

@Configuration
@Import(DefinitelyBasicAuthorizationFrameworkAutoconfiguration.class)
public class AuthorizationConfig {

    //importare classe lib di pasquale
}
