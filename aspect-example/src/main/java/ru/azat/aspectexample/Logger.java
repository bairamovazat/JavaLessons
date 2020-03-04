package ru.azat.aspectexample;

import java.util.Date;

public class Logger {
    @LoggerTraceIdChanger
    public void info(String methodName, String traceId, String message, Object... args) {
        System.out.println(new Date().toString() + ": [M:" + methodName + "][T:" + traceId + "] " + String.format(message, args));
    }
}