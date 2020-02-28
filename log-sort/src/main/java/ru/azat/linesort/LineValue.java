package ru.azat.linesort;

import ru.azat.logfinder.LogRef;

import java.util.Objects;

public class LineValue {
    private String value;
    private String parsedValue;
    private Integer count;
    private String logParamMessage;
    private LogRef logRef;

    public LineValue(String value) {
        this.value = value;
        parseValue();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        parseValue();
    }

    public String getParsedValue() {
        return parsedValue;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LineValue setCountAndGet(Integer count) {
        this.count = count;
        return this;
    }

    public String getLogParamMessage() {
        return logParamMessage;
    }

    public void setLogParamMessage(String logParamMessage) {
        this.logParamMessage = logParamMessage;
    }

    public LogRef getLogRef() {
        return logRef;
    }

    public void setLogRef(LogRef logRef) {
        this.logRef = logRef;
    }

    public boolean isValid() {
        if (this.getValue().length() < 4) {
            return false;
        }
        return this.getValue().contains("2020") || this.getValue().contains("2019") || this.getValue().contains("2021");
    }

    private void parseValue() {
        parsedValue = value
                .replaceAll("(^[0-9\\-\\:\\+\\., ]*)", "")
                .replaceAll("(\\Q[\\E[\\w\\:\\+\\.\\,\\|\\~\\-\\@\\>\\<\\{\\}\\'\\\"\\=\\; ]*\\Q]\\E)", "[{}]")
                .replaceAll("\\#([\\w0-9]*)", "")
                .replaceAll("\\@([\\w0-9]*)", "");

        logParamMessage = parsedValue.replace("[{}][{}][{}][{}]. ", "");
    }

    @Override
    public String toString() {
        return "----------" + this.getCount() + "----------\n"
                + (this.getLogRef() != null ? (this.getLogRef().filePath + ":" + this.getLogRef().getLineNumber()) : "") + "\n"
                + this.getValue() + "\n"
                + this.getParsedValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LineValue lineValue = (LineValue) o;
        return Objects.equals(parsedValue, lineValue.parsedValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parsedValue);
    }
}
