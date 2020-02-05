package com.api.commons.config;

import org.springframework.context.annotation.Configuration;import org.springframework.context.annotation.Profile;
@Configuration
@Profile("!prod")
public class SwaggerConfig {
}
