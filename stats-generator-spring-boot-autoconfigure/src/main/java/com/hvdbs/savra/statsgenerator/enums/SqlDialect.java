package com.hvdbs.savra.statsgenerator.enums;

public enum SqlDialect {
    ORACLE,
    POSTGRES;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
