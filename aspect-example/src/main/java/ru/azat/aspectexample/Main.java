package ru.azat.aspectexample;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.info("Main", null, "Test message");
    }
}
