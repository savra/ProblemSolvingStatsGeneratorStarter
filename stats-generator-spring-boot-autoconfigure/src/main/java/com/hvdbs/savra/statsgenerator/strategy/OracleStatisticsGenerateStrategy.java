package com.hvdbs.savra.statsgenerator.strategy;

import com.hvdbs.savra.statsgenerator.configuration.StatsGeneratorProperties;
import com.hvdbs.savra.statsgenerator.enums.SqlDialect;

public class OracleStatisticsGenerateStrategy extends SqlStatisticsGenerateStrategy {
    public OracleStatisticsGenerateStrategy(StatsGeneratorProperties statsGeneratorProperties) {
        super(statsGeneratorProperties);
        this.sqlDialect = SqlDialect.ORACLE;
        this.solutionUrl += sqlDialect + "/";
        this.basePackagePath = basePackagePath.resolve(sqlDialect.toString());
    }
}
