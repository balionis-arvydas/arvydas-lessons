package com.balionis.arvydas.lesson05.service;

import java.util.List;

public class AvgMovingServiceFunction implements MovingServiceFunction {

    @Override
    public Double apply(List<Double> values) {
        double sum = 0;
        if (values.isEmpty()) {
            return sum;
        }

        for (Double value : values) {
            sum += value;
        }
        return sum / values.size();
    }
}
