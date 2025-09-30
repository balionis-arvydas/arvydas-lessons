package com.balionis.arvydas.lesson4.service;

import java.util.List;

public class SumMovingServiceFunction implements MovingServiceFunction {

    @Override
    public Double apply(List<Double> values) {
        double sum = 0;
        for (Double value : values) {
            sum += value;
        }
        return sum;
    }
}
