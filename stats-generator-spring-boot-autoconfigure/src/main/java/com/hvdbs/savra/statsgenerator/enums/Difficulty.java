package com.hvdbs.savra.statsgenerator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum Difficulty {
    SCHOOL("school", List.of(Platform.GEEKS_FOR_GEEKS)),
    BASIC("basic", List.of(Platform.GEEKS_FOR_GEEKS)),
    EASY("easy", List.of(Platform.GEEKS_FOR_GEEKS, Platform.HACKER_RANK, Platform.LEETCODE)),
    MEDIUM("medium", List.of(Platform.GEEKS_FOR_GEEKS, Platform.HACKER_RANK, Platform.LEETCODE)),
    HIGH("high", List.of(Platform.HACKER_RANK, Platform.LEETCODE)),
    HARD("hard", List.of(Platform.GEEKS_FOR_GEEKS)),
    ONE("1 kyu", List.of(Platform.CODEWARS)),
    TWO("2 kyu", List.of(Platform.CODEWARS)),
    THREE("3 kyu", List.of(Platform.CODEWARS)),
    FOUR("4 kyu", List.of(Platform.CODEWARS)),
    FIVE("5 kyu", List.of(Platform.CODEWARS)),
    SIX("6 kyu", List.of(Platform.CODEWARS)),
    SEVEN("7 kyu", List.of(Platform.CODEWARS)),
    EIGHT("8 kyi", List.of(Platform.CODEWARS));

    private final String description;
    private final List<Platform> platform;
}
