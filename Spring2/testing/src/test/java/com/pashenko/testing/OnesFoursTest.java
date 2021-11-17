package com.pashenko.testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class OnesFoursTest {
    @ParameterizedTest
    @MethodSource("com.pashenko.testing.TestsArgsProvider#onesFoursTestArgs")
    void onesFoursArrayTest(int[] args, boolean expected){
        assertEquals(expected, OnesFours.checkOnesFoursArray(args));
    }
}
