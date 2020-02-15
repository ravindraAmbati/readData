package com.bigbasket.readData;

import org.junit.Test;

import java.io.IOException;

public class FreshoTest {
    @Test
    public void readDataTest(){
        Fresho f = new Fresho();
        try {
            System.out.println(f.readData());;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
