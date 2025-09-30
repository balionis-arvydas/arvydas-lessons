package com.balionis.arvydas.lesson01;

import com.balionis.arvydas.lesson01.service.ApplicationService;

public class Application {

    public static void main(String[] args) {
        var service = new ApplicationService();
        service.run(args);
    }
}

