package com.hvdbs.savra.statsgenerator.configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatsGeneratorProperties {
    private String githubRepositoryBaseUrl;
    private StatsGeneratorLanguageProperties language;
    private String solutionPackageName;

    public record StatsGeneratorLanguageProperties(boolean java, boolean oracle, boolean postgres) {
    }
}
