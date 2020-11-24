package com.minecraftcivproject.mcp.utils;

public class Delay {

    // Adds a delay with t milliseconds
    public static void addDelay(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
