package com.hvdbs.savra.statsgenerator.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Setter
@Getter
@ConfigurationProperties(prefix = "stats-generator")
public class StatsGeneratorProperties {
    private String githubRepositoryBaseUrl;
    private List<String> language;
    private List<String> sqlDialect;
    private String solutionPackageName;
}
