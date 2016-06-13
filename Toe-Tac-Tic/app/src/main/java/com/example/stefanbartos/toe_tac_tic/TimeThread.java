package com.example.stefanbartos.toe_tac_tic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Oliver on 13.06.2016.
 */
public class TimeThread extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.run();
        notify();
    }
}
