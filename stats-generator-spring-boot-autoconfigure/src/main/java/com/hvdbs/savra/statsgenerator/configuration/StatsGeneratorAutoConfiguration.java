package com.hvdbs.savra.statsgenerator.configuration;

import com.hvdbs.savra.statsgenerator.StatisticsGenerator;
import com.hvdbs.savra.statsgenerator.strategy.GenerateStrategy;
import com.hvdbs.savra.statsgenerator.strategy.JavaStatisticsGenerateStrategy;
import com.hvdbs.savra.statsgenerator.strategy.OracleStatisticsGenerateStrategy;
import com.hvdbs.savra.statsgenerator.strategy.PostgresStatisticsGenerateStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StatsGeneratorAutoConfiguration {
    @Bean
    @ConfigurationProperties("stats-generator")
    public StatsGeneratorProperties statsGeneratorProperties() {
        return new StatsGeneratorProperties();
    }

    @Bean
    @ConditionalOnExpression("${stats-generator.enable}")
    public StatisticsGenerator statisticsGenerator(List<? extends GenerateStrategy> generateStrategies) {
        return new StatisticsGenerator(generateStrategies);
    }

    @Bean
    @ConditionalOnProperty(prefix = "stats-generator.language", name = "java", havingValue = "true")
    public JavaStatisticsGenerateStrategy javaStatisticsGenerateStrategy() {
        return new JavaStatisticsGenerateStrategy(statsGeneratorProperties());
    }

    @Bean
    @ConditionalOnProperty(prefix = "stats-generator.language", name = "oracle", havingValue = "true")
    public OracleStatisticsGenerateStrategy oracleStatisticsGenerateStrategy() {
        return new OracleStatisticsGenerateStrategy(statsGeneratorProperties());
    }

    @Bean
    @ConditionalOnProperty(prefix = "stats-generator.language", name = "postgres", havingValue = "true")
    public PostgresStatisticsGenerateStrategy postgresStatisticsGenerateStrategy() {
        return new PostgresStatisticsGenerateStrategy(statsGeneratorProperties());
    }
}
