package ru.azat.aspectexample;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LoggerTraceIdChangerAspect {

    @Pointcut("execution(* *.*(..)) && @annotation(ru.azat.aspectexample.LoggerTraceIdChanger)")
    public void executeLogMethod() {
    }

    @Around("executeLogMethod()")
    public Object aroundCallAtMethod1(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = changeTraceId(joinPoint);
        return joinPoint.proceed(args);
    }

    private Object[] changeTraceId(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        LoggerTraceIdChanger loggerTraceIdChanger = getLoggerTraceIdChanger(joinPoint);
        if(loggerTraceIdChanger == null) {
            return args;
        }

        int traceIdArgumentNumber = getTraceIdArgumentNumber(loggerTraceIdChanger, joinPoint);
        if (traceIdArgumentNumber == -1) {
            return args;
        }

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        if(codeSignature.getParameterTypes()[traceIdArgumentNumber] != String.class) {
            return args;
        }

        if(args[traceIdArgumentNumber] != null) {
            return args;
        }

        args[traceIdArgumentNumber] = getTraceId(joinPoint);

        return args;
    }

    private LoggerTraceIdChanger getLoggerTraceIdChanger(JoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method m = ms.getMethod();
        return m.getAnnotation(LoggerTraceIdChanger.class);
    }

    private int getTraceIdArgumentNumber(LoggerTraceIdChanger loggerTraceIdChanger, JoinPoint joinPoint) {
        return 1;
    }

    private String getTraceId(JoinPoint joinPoint) {
        return TraceIdPool.getCurrentThreadTraceId();
    }
}
