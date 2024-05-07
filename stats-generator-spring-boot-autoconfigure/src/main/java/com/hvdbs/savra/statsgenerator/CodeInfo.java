package com.hvdbs.leetcode.statsgenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hvdbs.leetcode.statsgenerator.enums.Difficulty;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface CodeInfo {
    Difficulty difficulty();

    String name();

    String url();
    String timeComplexity() default "not specified";
    String spaceComplexity() default "not specified";
}
