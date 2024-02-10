package com.balionis.dainius.lesson1;

import com.balionis.dainius.lesson1.service.ApplicationService;

public class Application {

    public static void main(String[] args) {
        var service = new ApplicationService();
        service.run(args);
    }
}

