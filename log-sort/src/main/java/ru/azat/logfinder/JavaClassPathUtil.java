package ru.azat.logfinder;

public class JavaClassPathUtil {
    private JavaClassPathUtil() {
    }

    public static String getJavaClassNameFromPath(String javaClasspath) {
        return javaClasspath.replaceAll(".+src\\\\main\\\\java\\\\", "");
    }
}
