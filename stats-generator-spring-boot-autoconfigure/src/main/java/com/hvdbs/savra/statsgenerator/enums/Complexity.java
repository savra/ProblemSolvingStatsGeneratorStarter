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
    BILINEAR("O(N * M)"),
    QUADRATIC("O(N^2)");

    private final String description;

    public static class ConstantComplexity {
        public static final String CONSTANT = Complexity.CONSTANT.getDescription();
        public static final String LOGARITHMIC = Complexity.LOGARITHMIC.getDescription();
        public static final String LINEAR = Complexity.LINEAR.getDescription();
        public static final String LINEARITHMIC = Complexity.LINEARITHMIC.getDescription();
        public static final String BILINEAR = Complexity.BILINEAR.getDescription();
        public static final String QUADRATIC = Complexity.QUADRATIC.getDescription();
    }
}
