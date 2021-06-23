package com.thedarkgamer.javaprgm;

public class Wait {
    public static void Sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            System.out.println("Process Interrupted");
            e.printStackTrace();
        }
    }
}
