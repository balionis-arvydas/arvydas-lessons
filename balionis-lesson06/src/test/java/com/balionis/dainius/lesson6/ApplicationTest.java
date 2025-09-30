package com.balionis.dainius.lesson6;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
class ApplicationTest {

    @Test
    void contextLoads() {
    }
}
