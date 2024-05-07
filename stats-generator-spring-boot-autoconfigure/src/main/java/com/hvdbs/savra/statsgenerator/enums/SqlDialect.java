package com.hvdbs.leetcode.statsgenerator.enums;

public enum SqlDialect {
    ORACLE,
    POSTGRES;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
