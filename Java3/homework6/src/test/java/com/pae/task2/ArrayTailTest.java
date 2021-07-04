package com.pae.task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayTailTest {

    @Test
    void shouldThrowRuntimeExceptionIfInputIsNull(){
        assertThrows(RuntimeException.class, () -> ArrayTail.getTailAfterFour(null));
    }

    @MethodSource("invalidInputProvider")
    @ParameterizedTest
    void shouldThrowRuntimeExceptionIfInputIsEmptyOrNotContainsFour(int[] input){
        assertThrows(RuntimeException.class, () -> ArrayTail.getTailAfterFour(input));
    }

    @MethodSource("validInputProvider")
    @ParameterizedTest
    void shouldReturnTailOfInputArrayAfterFour(int[] expect, int[] input){
        assertArrayEquals(expect, ArrayTail.getTailAfterFour(input));
    }

    static Stream<Arguments> invalidInputProvider(){
        List<Arguments> inputs = new LinkedList<>();
        inputs.add(Arguments.arguments(new int[0]));
        inputs.add(Arguments.arguments(new int[] {1,2,5}));
        return inputs.stream();
    }

    static Stream<Arguments> validInputProvider(){
        List<Arguments> args = new LinkedList<>();
        args.add(Arguments.arguments(new int[] {1,2}, new int[] {3,4,1,2}));
        args.add(Arguments.arguments(new int[] {}, new int[] {3,4}));
        return args.stream();
    }
}
