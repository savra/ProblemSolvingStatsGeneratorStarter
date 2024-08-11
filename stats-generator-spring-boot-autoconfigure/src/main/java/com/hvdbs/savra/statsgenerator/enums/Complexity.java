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
}