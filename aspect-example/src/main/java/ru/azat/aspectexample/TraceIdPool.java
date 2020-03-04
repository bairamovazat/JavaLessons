package ru.azat.aspectexample;

import java.util.UUID;

public class TraceIdPool {
    private ThreadLocal<String> treadTraceId = new ThreadLocal<>();

    private TraceIdPool() {
    }

    public String getOrCreateThreadTraceId() {
        if (this.treadTraceId.get() == null) {
            this.treadTraceId.set(generateThreadTraceId());
        }
        return this.treadTraceId.get();
    }

    private String generateThreadTraceId() {
        return UUID.randomUUID().toString();
    }

    private void resetThreadTraceId() {
        if (this.treadTraceId.get() != null) {
            this.treadTraceId.remove();
        }
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
        return traceIdPool.getOrCreateThreadTraceId();
    }

    public static void resetCurrentThreadTraceId() {
        TraceIdPool traceIdPool = getInstance();
        traceIdPool.resetThreadTraceId();
    }
}
