package ru.azat.aspectexample;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    logger.info("main", null, "Thread " + Thread.currentThread().getId() + " " + j);
                }
                TraceIdPool.resetCurrentThreadTraceId();
                for (int j = 0; j < 100; j++) {
                    logger.info("main", null, "Thread " + Thread.currentThread().getId() + " " + j);
                }
            }).start();
        }
    }
}
