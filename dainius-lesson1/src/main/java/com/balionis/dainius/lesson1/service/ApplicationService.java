package com.balionis.dainius.lesson1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    public String run(String... messages) {
        var result = Arrays.asList(messages).stream().map(msg -> "msg=" + msg).collect(Collectors.joining(","));
        logger.debug("myMessage={}", result);
        return result;
    }
}
