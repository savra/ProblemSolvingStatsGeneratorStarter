package com.hvdbs.savra.statsgenerator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Complexity {
    CONSTANT,
    LOGARITHMIC,
    LINEAR,
    LINEARITHMIC,
    BILINEAR,
    QUADRATIC;

    public static class ConstantComplexity {
        public static final String CONSTANT = "O(1)";
        public static final String LOGARITHMIC = "LogN";
        public static final String LINEAR = "O(N)";
        public static final String LINEARITHMIC = "O(N * LogN)";
        public static final String BILINEAR = "O(N * M)";
        public static final String QUADRATIC = "O(N^2)";
    }
}
