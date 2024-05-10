package com.hvdbs.savra.statsgenerator.strategy;

import com.hvdbs.savra.statsgenerator.configuration.StatsGeneratorProperties;
import com.hvdbs.savra.statsgenerator.enums.SqlDialect;

public class PostgresStatisticsGenerateStrategy extends SqlStatisticsGenerateStrategy {
    public PostgresStatisticsGenerateStrategy(StatsGeneratorProperties statsGeneratorProperties) {
        super(statsGeneratorProperties);
        this.sqlDialect = SqlDialect.POSTGRES;
        this.solutionUrl += sqlDialect + "/";
        this.basePackagePath = basePackagePath.resolve(sqlDialect.toString());
    }
}
