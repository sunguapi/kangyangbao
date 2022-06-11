package com.nbrt.kybao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@SpringBootTest
class LivingApplicationTests {

    @Test
    void contextLoads() {

        Calendar calendar = Calendar.getInstance(); // get current instance of the calendar

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(formatter.format(calendar.getTime()));
    }

}
