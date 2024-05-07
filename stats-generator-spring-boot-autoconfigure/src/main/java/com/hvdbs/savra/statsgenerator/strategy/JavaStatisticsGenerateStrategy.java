package com.hvdbs.leetcode.statsgenerator;

import com.hvdbs.leetcode.statsgenerator.enums.Difficulty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.hvdbs.leetcode.statsgenerator.StatisticsConstants.GITHUB_REPOSITORY_BASE_URL;

public class JavaStatisticsGenerateStrategy implements GenerateStrategy {
    private static final String PACKAGE_NAME = "com.hvdbs.leetcode.solution.java";
    private static final String LANGUAGE = "Java";

    @Override
    public void generate() {
        try (InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(PACKAGE_NAME.replace('.', '/'))) {
            if (inputStream == null) {
                return;
            }

            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("README.md"), StandardOpenOption.APPEND);
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                bufferedWriter.newLine();
                bufferedWriter.append("## " + LANGUAGE);

                Map<Difficulty, List<OutputLeetCodeFormat>> difficultyListMap = bufferedReader.lines()
                        .map(solution -> {
                            String className = PACKAGE_NAME + "." + solution.substring(0, solution.lastIndexOf('.'));

                            try {
                                CodeInfo codeInfo = Class.forName(className).getAnnotation(CodeInfo.class);

                                if (codeInfo != null) {
                                    return OutputLeetCodeFormat.builder()
                                            .difficulty(codeInfo.difficulty())
                                            .name(codeInfo.name())
                                            .problemUrl(codeInfo.url())
                                            .solutionUrl(GITHUB_REPOSITORY_BASE_URL + "/java/" + solution.replace("class", "java"))
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
