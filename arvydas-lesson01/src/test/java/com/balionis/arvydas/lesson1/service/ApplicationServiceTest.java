package com.balionis.arvydas.lesson1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 */
@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @Test
    public void testMe() {

        var service = new ApplicationService();

        var expected = "msg=myMsg";

        var actual = service.run("myMsg");

        assertEquals(expected, actual);
    }

}
