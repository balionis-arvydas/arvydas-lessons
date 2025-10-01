package com.balionis.arvydas.lesson04.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 */
@ExtendWith(MockitoExtension.class)
public class SumMovingServiceFunctionTest {

    @Test
    public void should_have_0_of_empty() {
        var function = new SumMovingServiceFunction();
        List<Double> values = List.of();

        assertEquals(0.0, function.apply(values), 0.001);
    }

    @Test
    public void should_have_9_of_9() {
        var function = new SumMovingServiceFunction();
        var values = List.of(9.0);

        assertEquals(9.0, function.apply(values), 0.001);
    }

    @Test
    public void should_have_1_5_of_124() {
        var function = new SumMovingServiceFunction();
        var values = List.of(1.0, 2.0, 4.0);

        assertEquals(7.0, function.apply(values), 0.001);
    }
}
