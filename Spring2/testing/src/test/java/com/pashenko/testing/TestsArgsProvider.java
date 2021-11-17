package com.pashenko.testing;


import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestsArgsProvider {
    public static Stream<Arguments> onesFoursTestArgs(){
        return Stream.of(
                Arguments.of(new int[]{1, 4}, true),
                Arguments.of(new int[]{4, 1, 4}, true),
                Arguments.of(new int[]{}, false),
                Arguments.of(new int[]{1}, false),
                Arguments.of(new int[]{4}, false)
        );
    }

    public static Stream<Arguments> arrayAfterFourTestCorrectArgs(){
        return Stream.of(
                Arguments.of(new int[]{1,2,3,4}, new int[]{}),
                Arguments.of(new int[]{1,2,4,2}, new int[]{2}),
                Arguments.of(new int[]{4,1,2}, new int []{1,2})
        );
    }

    public static Stream<Arguments> arrayAfterFourTestIncorrectArgs(){
        return Stream.of(
                Arguments.of(new int[]{}),
                Arguments.of(new int[]{1}),
                Arguments.of(new int[]{1,2,3})
        );
    }

}
