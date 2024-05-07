package com.hvdbs.savra.statsgenerator;

import com.hvdbs.savra.statsgenerator.enums.Difficulty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface CodeInfo {
    Difficulty difficulty();
    String name();
    String url();
    String timeComplexity() default "not specified";
    String spaceComplexity() default "not specified";
}
