package ru.azat.logfinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogRef {
    public String rawLog;
    public String logMessage;
    public String filePath;
    public int lineNumber;

    public LogRef(String rawLog, String logMessage, String filePath, int lineNumber) {
        this.rawLog = rawLog;
        this.logMessage = logMessage;
        this.filePath = filePath;
        this.lineNumber = lineNumber;
    }

    public String getRawLog() {
        return rawLog;
    }

    public void setRawLog(String rawLog) {
        this.rawLog = rawLog;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public static LogRef from(String javaClass, String line, int lineNumber) {
        Pattern pattern = Pattern.compile("(?i)(LOGGER|LOG|getLogger\\(\\))\\.(.*)\"(.*)\"");//. represents single character
        Matcher m = pattern.matcher(line);
        while (m.find()) {
            String value = m.group(3);
            return new LogRef(line, value, javaClass, lineNumber);
        }
        return null;
    }

    @Override
    public String toString() {
        return getLogMessage();
    }
}
