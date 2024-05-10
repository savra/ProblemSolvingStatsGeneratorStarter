package com.hvdbs.savra.statsgenerator.strategy;

import com.hvdbs.savra.statsgenerator.CodeInfo;
import com.hvdbs.savra.statsgenerator.OutputLeetCodeFormat;
import com.hvdbs.savra.statsgenerator.configuration.StatsGeneratorProperties;
import com.hvdbs.savra.statsgenerator.enums.Difficulty;
import com.hvdbs.savra.statsgenerator.enums.Language;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JavaStatisticsGenerateStrategy implements GenerateStrategy {
    private final StatsGeneratorProperties statsGeneratorProperties;
    private final String packageName;

    public JavaStatisticsGenerateStrategy(StatsGeneratorProperties statsGeneratorProperties) {
        this.statsGeneratorProperties = statsGeneratorProperties;
        packageName = statsGeneratorProperties.getSolutionPackageName() + "." + Language.JAVA.toString().toLowerCase();
    }

    @Override
    public void generate() {
        try (InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replace('.', '/'))) {
            if (inputStream == null) {
                return;
            }

            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("README.md"), StandardOpenOption.APPEND);
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                bufferedWriter.newLine();
                bufferedWriter.append("## ").append(StringUtils.capitalize(Language.JAVA.toString()));

                Map<Difficulty, List<OutputLeetCodeFormat>> difficultyListMap = bufferedReader.lines()
                        .map(solution -> {
                            String className = packageName + "." + solution.substring(0, solution.lastIndexOf('.'));

                            try {
                                CodeInfo codeInfo = Class.forName(className).getAnnotation(CodeInfo.class);

                                if (codeInfo != null) {
                                    return OutputLeetCodeFormat.builder()
                                            .difficulty(codeInfo.difficulty())
                                            .name(codeInfo.name())
                                            .problemUrl(codeInfo.url())
                                            .solutionUrl(statsGeneratorProperties.getGithubRepositoryBaseUrl() +
                                                    "/" + Language.JAVA.toString().toLowerCase() + "/" +
                                                    solution.replace("class", Language.JAVA.toString().toLowerCase()))
                                            .timeComplexity(codeInfo.timeComplexity())
                                            .spaceComplexity(codeInfo.spaceComplexity())
                                            .build();
                                }

                                return null;
                            } catch (ClassNotFoundException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.groupingBy(OutputLeetCodeFormat::getDifficulty));

                fillStatisticsTable(bufferedWriter, difficultyListMap);
            }

        } catch (IOException ignored) {
        }
    }

    private void fillStatisticsTable(BufferedWriter bufferedWriter, Map<Difficulty, List<OutputLeetCodeFormat>> difficultyListMap) throws IOException {
        for (Difficulty difficulty : difficultyListMap.keySet()) {
            bufferedWriter.newLine();
            bufferedWriter.append("<details>");
            bufferedWriter.newLine();
            bufferedWriter.append("<summary>").append(String.valueOf(difficulty)).append("</summary>");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.append("|Name|Problem|Solution|Time Complexity|Space complexity");
            bufferedWriter.newLine();
            bufferedWriter.append("|---|---|---|---|---|");
            bufferedWriter.newLine();

            for (OutputLeetCodeFormat outputLeetCodeFormat : difficultyListMap.get(difficulty)) {
                bufferedWriter.append("|")
                        .append(outputLeetCodeFormat.getName())
                        .append("|")
                        .append(outputLeetCodeFormat.getProblemUrl())
                        .append("|")
                        .append("<a href='").append(outputLeetCodeFormat.getSolutionUrl()).append("'>").append(outputLeetCodeFormat.getName()).append("</a>")
                        .append("|")
                        .append("$").append(outputLeetCodeFormat.getTimeComplexity()).append("$")
                        .append("|")
                        .append("$").append(outputLeetCodeFormat.getSpaceComplexity()).append("$");
                bufferedWriter.newLine();
            }

            bufferedWriter.append("</details>");
        }
    }
}
