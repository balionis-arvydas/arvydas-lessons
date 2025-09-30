package com.balionis.arvydas.lesson4.service;

import java.util.LinkedList;

public class MovingService {

    private final int limit;
    private final LinkedList<Double> values;

    private final MovingServiceFunction movingServiceFunction;

    public MovingService(MovingServiceFunction movingServiceFunction, int limit) {
        this.movingServiceFunction = movingServiceFunction;
        this.limit = limit;
        this.values = new LinkedList<>();
    }

    public void add(double value) {
        values.add(value);
        while (values.size() > limit) {
            values.remove();
        }
    }

    public double getMovingAverage() {
        return movingServiceFunction.apply(values);
    }
}
