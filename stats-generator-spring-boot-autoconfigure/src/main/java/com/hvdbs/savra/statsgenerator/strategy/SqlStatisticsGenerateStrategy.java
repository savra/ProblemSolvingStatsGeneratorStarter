package com.hvdbs.savra.statsgenerator.strategy;

import com.hvdbs.savra.statsgenerator.OutputLeetCodeFormat;
import com.hvdbs.savra.statsgenerator.configuration.StatsGeneratorProperties;
import com.hvdbs.savra.statsgenerator.enums.Difficulty;
import com.hvdbs.savra.statsgenerator.enums.Language;
import com.hvdbs.savra.statsgenerator.enums.SqlDialect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public abstract class SqlStatisticsGenerateStrategy implements GenerateStrategy {
    protected Path basePackagePath;
    protected SqlDialect sqlDialect;
    protected String solutionUrl;

    public SqlStatisticsGenerateStrategy(StatsGeneratorProperties statsGeneratorProperties) {
        basePackagePath = Path.of("src", "main", "java")
                .resolve(Path.of(statsGeneratorProperties.getSolutionPackageName().replace(".", "/")))
                .resolve(Language.SQL.toString());
        solutionUrl = statsGeneratorProperties.getGithubRepositoryBaseUrl() + "/" + Language.SQL + "/";
    }

    @Override
    public void generate() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("README.md"), StandardOpenOption.APPEND)) {
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.append("## ").append(StringUtils.capitalize(sqlDialect.toString()));

            try (Stream<Path> files = Files.list(basePackagePath)) {
                Set<String> fileNames = files.filter(file -> !Files.isDirectory(file))
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toSet());

                Map<Difficulty, List<OutputLeetCodeFormat>> difficultyListMap = new HashMap<>();

                for (String fileName : fileNames) {
                    try (Stream<String> content = Files.lines(basePackagePath.resolve(fileName))) {
                        content.findFirst()
                                .filter(header -> header.startsWith("--"))
                                .ifPresent(header -> {
                                    String[] parts = header.split(";");
                                    Difficulty difficulty = Difficulty.valueOf(getValueFromHeader(parts[0]));
                                    String name = getValueFromHeader(parts[1]);
                                    String problemUrl = getValueFromHeader(parts[2]);

                                    OutputLeetCodeFormat leetCodeFormat = OutputLeetCodeFormat.builder()
                                            .difficulty(difficulty)
                                            .name(name)
                                            .problemUrl(problemUrl)
                                            .solutionUrl(solutionUrl + fileName)
                                            .build();

                                    difficultyListMap.computeIfAbsent(difficulty, difficultyList -> new ArrayList<>()).add(leetCodeFormat);
                                });
                    } catch (IOException ignored) {
                        log.warn("Error opening a file with name {}", fileName);
                    }
                }

                fillStatisticsTable(bufferedWriter, difficultyListMap);
            }
        } catch (NotDirectoryException e) {
            log.error("{} is not a directory!", basePackagePath);
        } catch (IOException ignored) {
            log.error("Error opening a directory with the name {}", basePackagePath);
        }
    }

    private String getValueFromHeader(String headerPart) {
        return headerPart.substring(headerPart.indexOf("=") + 1);
    }

    private void fillStatisticsTable(BufferedWriter bufferedWriter, Map<Difficulty, List<OutputLeetCodeFormat>> difficultyListMap) throws IOException {
        for (Difficulty difficulty : difficultyListMap.keySet()) {
            bufferedWriter.newLine();
            bufferedWriter.append("<details>");
            bufferedWriter.newLine();
            bufferedWriter.append("<summary>").append(String.valueOf(difficulty)).append("</summary>");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.append("|Name|Problem|Solution|");
            bufferedWriter.newLine();
            bufferedWriter.append("|---|---|---|");
            bufferedWriter.newLine();

            for (OutputLeetCodeFormat outputLeetCodeFormat : difficultyListMap.get(difficulty)) {
                bufferedWriter.append("|")
                        .append(outputLeetCodeFormat.getName())
                        .append("|")
                        .append(outputLeetCodeFormat.getProblemUrl())
                        .append("|")
                        .append("<a href='").append(outputLeetCodeFormat.getSolutionUrl()).append("'>").append(outputLeetCodeFormat.getName()).append("</a>")
                        .append("|");
                bufferedWriter.newLine();
            }
            bufferedWriter.append("</details>");
        }
    }
}
