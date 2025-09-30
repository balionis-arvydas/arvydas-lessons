package com.balionis.dainius.lesson3.service;

import java.util.List;

public class AvgMovingServiceFunction implements MovingServiceFunction {

    @Override
    public Double apply(List<Double> values) {
        double sum = 0;
        if (values.isEmpty()) {
            return sum;
        }

        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i);
        }
        return sum / values.size();
    }
}
