package com.example.jpapersistence.idstrategy;

import com.example.jpapersistence.measure.Measured;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IdCompare {
    @Measured
    public void test() {
        try {
            Thread.sleep(new Random().nextInt(2000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
