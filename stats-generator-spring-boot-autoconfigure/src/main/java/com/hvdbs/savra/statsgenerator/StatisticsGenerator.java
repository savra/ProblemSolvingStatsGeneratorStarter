package com.hvdbs.savra.statsgenerator;

import com.hvdbs.savra.statsgenerator.strategy.GenerateStrategy;
import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RequiredArgsConstructor
public class StatisticsGenerator {
    private final List<? extends GenerateStrategy> generateStrategies;

    public void generate() {
        final Path pathToReadme = Paths.get("README.md");

        try (FileChannel channel = FileChannel.open(pathToReadme, StandardOpenOption.WRITE)) {
            channel.truncate(0);
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(pathToReadme, StandardOpenOption.APPEND)) {
                bufferedWriter.append("# Statistics of problem solving ⭐");
            }

            generateStrategies.forEach(GenerateStrategy::generate);
        } catch (IOException ignored) {
        }
    }
}
