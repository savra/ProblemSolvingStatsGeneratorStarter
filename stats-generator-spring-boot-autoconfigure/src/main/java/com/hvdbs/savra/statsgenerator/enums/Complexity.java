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
        public static final String CONSTANT = "O(1)";
        public static final String LOGARITHMIC = "LogN";
        public static final String LINEAR = "O(N)";
        public static final String LINEARITHMIC = "O(N * LogN)";
        public static final String QUADRATIC = "O(N^2)";
    }
}
