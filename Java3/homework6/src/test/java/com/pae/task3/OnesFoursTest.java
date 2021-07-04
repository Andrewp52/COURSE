package com.pae.task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class OnesFoursTest {
    @Test
    void shouldReturnFalseIfInputIsNull(){
        assertFalse(OnesFours.checkOnesFours(null));
    }

    @MethodSource("invalidInputProvider")
    @ParameterizedTest
    void shouldReturnFalseIfInputIsTooShortContainsWrongNumsOrLackOfOnesOrFours(int[] input){
        assertFalse(OnesFours.checkOnesFours(input));
    }

    @Test
    void shouldReturnTrueIfInputIsValid(){
        assertTrue(OnesFours.checkOnesFours(new int[] {1,4,1,1,4,4,1}));
    }

    static Stream<Arguments> invalidInputProvider(){
        List<Arguments> inputs = new LinkedList<>();
        inputs.add(Arguments.arguments(new int[]{1}));
        inputs.add(Arguments.arguments(new int[]{1,3,4}));
        inputs.add(Arguments.arguments(new int[] {1,1,1,1}));
        inputs.add(Arguments.arguments(new int[] {4,4,4,4}));
        return inputs.stream();
    }
}
