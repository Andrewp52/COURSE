package com.pashenko.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ArrayAfterFourTest {
    @ParameterizedTest
    @MethodSource("com.pashenko.testing.TestsArgsProvider#arrayAfterFourTestCorrectArgs")
    void arrayAfterFourCorrectArgsTest(int[] args, int[] expected){
        assertArrayEquals(expected, ArrayAfterFour.getArrayAfterFour(args));
    }

    @ParameterizedTest
    @MethodSource("com.pashenko.testing.TestsArgsProvider#arrayAfterFourTestIncorrectArgs")
    void arrayAfterFourIncorrectArgsExceptionTest(int[] args){
        assertThrows(RuntimeException.class, () -> ArrayAfterFour.getArrayAfterFour(args));
    }
}
