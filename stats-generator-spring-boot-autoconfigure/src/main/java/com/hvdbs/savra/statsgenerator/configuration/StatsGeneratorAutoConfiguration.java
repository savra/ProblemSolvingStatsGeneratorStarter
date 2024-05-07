package com.hvdbs.savra.statsgenerator.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(StatsGeneratorProperties.class)
@Configuration
public class StatsGeneratorAutoConfiguration {
}
