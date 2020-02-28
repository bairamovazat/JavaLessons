package ru.azat.logfinder;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFinder {
    private String projectRootPath;

    public LogFinder(String logPath) {
        this.projectRootPath = logPath;
    }

    public List<LogRef> parseLogReferences(String fileType) {
        List<LogRef> logRefs = new ArrayList<>();
        recursiveFillLogRefsFromDirectory(this.projectRootPath, logRefs, fileType);
        return logRefs;
    }

    private void recursiveFillLogRefsFromDirectory(String directory, List<LogRef> logRefList, String fileType) {
        File directoryFile = new File(directory);
        if (!directoryFile.isDirectory() && !directory.contains("target")) {
            throw new IllegalArgumentException(directory + " is not directory");
        }

        File[] fileList = directoryFile.listFiles();
        if (fileList == null) {
            return;
        }
        for (File file : fileList) {
            if(file.isDirectory()) {
                recursiveFillLogRefsFromDirectory(file.getAbsolutePath(), logRefList, fileType);
            } else if (file.isFile()) {
                if(fileType == null || file.getAbsolutePath().endsWith(fileType)) {
                    parseFile(file, logRefList);
                }
            }
        }
    }

    private void parseFile(File file, List<LogRef> logRefList) {
        try {
            List<String> lines = IOUtils.readLines(new FileInputStream(file),  "UTF-8");
            for(int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if(line.toLowerCase().contains("log") ) {
                    String javaClassName = JavaClassPathUtil.getJavaClassNameFromPath(file.getAbsolutePath());
                    LogRef logRef = LogRef.from(javaClassName, line, i + 1);
                    if (logRef != null) {
                        logRefList.add(logRef);
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
