package com.example.dummy.utility;

public class delay_emulator {
    public void delay(int nanos){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }

    }
}
