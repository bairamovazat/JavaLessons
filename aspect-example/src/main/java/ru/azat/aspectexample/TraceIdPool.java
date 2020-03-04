package ru.azat.aspectexample;

import java.util.UUID;

public class TraceIdPool {
    private ThreadLocal<String> treadTraceId = new ThreadLocal<>();

    private TraceIdPool() {
    }

    public String getOrCreateCurrentThreadTraceId() {
        if (this.treadTraceId.get() == null) {
            this.treadTraceId.set(generateThreadTraceId());
        }
        return this.treadTraceId.get();
    }

    private String generateThreadTraceId() {
        return UUID.randomUUID().toString();
    }

    private static TraceIdPool traceIdPool = null;

    public static TraceIdPool getInstance() {
        if (traceIdPool == null) {
            traceIdPool = new TraceIdPool();
        }
        return traceIdPool;
    }

    public static String getCurrentThreadTraceId() {
        TraceIdPool traceIdPool = getInstance();
        return traceIdPool.getOrCreateCurrentThreadTraceId();
    }
}
