package com.balionis.arvydas.lesson1;

import com.balionis.arvydas.lesson1.service.ApplicationService;

public class Application {

    public static void main(String[] args) {
        var service = new ApplicationService();
        service.run(args);
    }
}

