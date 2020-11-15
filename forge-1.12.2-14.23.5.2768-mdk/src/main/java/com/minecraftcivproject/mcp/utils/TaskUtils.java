package com.minecraftcivproject.mcp.utils;

import java.util.Random;

public class TaskUtils {

    private static final Random RANDOM = new Random();

    public static void runAboutOnceOutOfXTimes(Runnable runnable, int times){
        if(RANDOM.nextInt() % times == 0){
            runnable.run();
        }
    }

}
