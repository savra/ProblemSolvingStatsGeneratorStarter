package com.hvdbs.leetcode.statsgenerator;

import com.hvdbs.leetcode.statsgenerator.enums.SqlDialect;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RequiredArgsConstructor
public class StatisticsGenerator {
    private static final List<GenerateStrategy> generateStrategies = List.of(
            new JavaStatisticsGenerateStrategy(),
            new SqlStatisticsGenerateStrategy(SqlDialect.ORACLE),
            new SqlStatisticsGenerateStrategy(SqlDialect.POSTGRES)
    );

    public static void generate() {
        try {
            Path pathToReadme = Paths.get("README.md");
            FileChannel.open(pathToReadme, StandardOpenOption.WRITE).truncate(0);

            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(pathToReadme, StandardOpenOption.APPEND)) {
                bufferedWriter.append("# Statistics of problem solving ⭐");
            }

            generateStrategies.forEach(GenerateStrategy::generate);

        } catch (IOException ignored) {
        }
    }
}
