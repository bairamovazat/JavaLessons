package ru.azat.linesort;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class LineSorter {
    public File file;

    public String fileData;

    private static Integer minLogCount = 500;

    public LineSorter(String fileLocation) {
        file = new File(fileLocation);
        if (!file.canRead() || !file.canWrite()) {
            throw new IllegalArgumentException("Cannot write or read from a file");
        }
    }

    public List<LineValue> sortLine(boolean sortOnlyTNull) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        List<String> fileLines = IOUtils.readLines(fileInputStream, "UTF-8");

        Map<String, Integer> stringCount = new HashMap<>();
        List<LineValue> sortedList = fileLines.stream()
                .filter(e -> !e.isEmpty())
                .filter(e -> !sortOnlyTNull || e.contains("[T:null]"))
                .map(LineValue::new)
                .collect(Collectors.toList());


        sortedList.forEach(line -> {
            Integer value = stringCount.get(line.getParsedValue());
            value = value == null ? 0 : value;
            stringCount.put(line.getParsedValue(), value + 1);
        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        List<LineValue> result = new HashSet<>(sortedList)
                .stream()
                .sorted(Comparator.comparing(LineValue::getParsedValue))
                .sorted(Comparator.comparingInt(a -> stringCount.get(a.getParsedValue())))
                .map(e -> e.setCountAndGet(stringCount.get(e.getParsedValue())))
                .filter(LineValue::isValid)
                .collect(Collectors.toList());
        return result;
    }

}
