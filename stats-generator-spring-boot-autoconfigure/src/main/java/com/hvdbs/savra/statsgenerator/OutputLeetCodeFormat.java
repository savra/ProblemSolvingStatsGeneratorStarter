package com.hvdbs.leetcode.statsgenerator;

import com.hvdbs.leetcode.statsgenerator.enums.Difficulty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OutputLeetCodeFormat {
    private String name;
    private String problemUrl;
    private String solutionUrl;
    private Difficulty difficulty;
    private String timeComplexity;
    private String spaceComplexity;
}
