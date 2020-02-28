package ru.azat;

import org.apache.commons.io.IOUtils;
import ru.azat.linesort.LineSorter;
import ru.azat.linesort.LineValue;
import ru.azat.logfinder.LogFinder;
import ru.azat.logfinder.LogRef;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, String> arguments = Arrays.stream(args)
                .filter(e -> e.startsWith("-") && e.contains("="))
                .map(e -> e.replaceAll("^-", ""))
                .collect(Collectors.toMap(key -> key.split("=")[0], value -> value.split("=")[1]));

        List<String> files = Arrays.stream(args)
                .filter(e -> !e.startsWith("-"))
                .collect(Collectors.toList());

        if (args.length == 0 || args[0].equals("-h")) {
            System.out.println("-sortOnlyTNull=Логи_где_Т_null");
            System.out.println("-analyseProjectDir=Корень_проекта_где_искать_log_ref");
            System.out.println("-fileType=Тип_файла_для_log_ref(java)");
            System.out.println("Через пробел пути до файлов с логами");
            return;
        }

        List<LogRef> logRefList = new ArrayList<>();
        String fileType = arguments.get("fileType");
        String analyseProjectDir = arguments.get("analyseProjectDir");

        if (analyseProjectDir != null) {
            LogFinder logFinder = new LogFinder(analyseProjectDir);
            logRefList.addAll(logFinder.parseLogReferences(fileType));
        }

        for (String fileName : files) {
            boolean sortOnlyTNull = Objects.equals(arguments.get("sortOnlyTNull"), "true");
            LineSorter lineSorter = new LineSorter(fileName);
            List<LineValue> lineValues = lineSorter.sortLine(sortOnlyTNull);

            lineValues.forEach(lineValue -> {
                logRefList.stream().filter(e -> e.getLogMessage().trim().equals(lineValue.getLogParamMessage().trim()))
                        .findFirst()
                        .ifPresent(lineValue::setLogRef);
            });

            OutputStream outputStream = new FileOutputStream(fileName + "_sort");
            sortAndWriteToFile(lineValues, outputStream);
        }
    }

    public static void sortAndWriteToFile(List<LineValue> lineValues, OutputStream outputStream) throws IOException {
        List<String> result = lineValues
                .stream()
                .map(LineValue::toString)
                .collect(Collectors.toList());

        IOUtils.writeLines(result, "\n", outputStream, "UTF-8");
    }
}
