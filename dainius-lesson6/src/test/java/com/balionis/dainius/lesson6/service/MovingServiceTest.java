package com.balionis.dainius.lesson6.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 */
@ExtendWith(MockitoExtension.class)
public class MovingServiceTest {

    @Test
    public void should_have_9_of_9() {
        var service = new MovingService( new AvgMovingServiceFunction(), 1);
        service.add(9);

        assertEquals(9, service.getMovingAverage());
    }

    @Test
    public void should_have_1_5_of_12() {
        var service = new MovingService( new AvgMovingServiceFunction(),3);
        service.add(1);
        service.add(2);

        assertEquals(1.5, service.getMovingAverage());
    }

    @Test
    public void should_have_2_of_123() {
        var service = new MovingService( new AvgMovingServiceFunction(),3);
        service.add(1);
        service.add(2);
        service.add(3);

        assertEquals(2, service.getMovingAverage());
    }

    @Test
    public void should_have_3_of_234() {
        var service = new MovingService( new AvgMovingServiceFunction(),3);
        service.add(1);
        service.add(2);
        service.add(3);
        service.add(5);

        assertEquals(3.333, service.getMovingAverage(), 0.001);
    }

}
