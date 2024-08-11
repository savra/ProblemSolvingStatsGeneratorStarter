package com.hvdbs.savra.statsgenerator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Complexity {
    CONSTANT("O(1)"),
    LOGARITHMIC("LogN"),
    LINEAR("O(N)"),
    LINEARITHMIC("O(N * LogN)"),
    QUADRATIC("O(N^2)");

    private final String description;

    public static class ConstantComplexity {
        public static String CONSTANT = "O(1)";
        public static String LOGARITHMIC = "LogN";
        public static String LINEAR = "O(N)";
        public static String LINEARITHMIC = "O(N * LogN)";
        public static String QUADRATIC = "O(N^2)";
    }
}
