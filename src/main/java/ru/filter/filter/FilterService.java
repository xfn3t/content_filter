package ru.filter.filter;

import ru.filter.files.read.ReadFromFile;
import ru.filter.files.write.WriteToFile;
import ru.filter.statistic.StatisticRuntime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class FilterService {

    private String path = System.getProperty("user.dir") + "\\";
    private String prefix = "";

    private String filenameString = "strings.txt";
    private String filenameInteger = "integers.txt";
    private String filenameFloat = "floats.txt";


    private final ReadFromFile read;
    private final WriteToFile write;
    private final Filter filter;
    private final StatisticRuntime statisticRuntime;

    public FilterService(ReadFromFile read, WriteToFile write, Filter filter, StatisticRuntime statisticRuntime) {
        this.read = read;
        this.write = write;
        this.filter = filter;
        this.statisticRuntime = statisticRuntime;
    }

    public String getFilenameString() {
        return filenameString;
    }

    public String getFilenameInteger() {
        return filenameInteger;
    }

    public String getFilenameFloat() {
        return filenameFloat;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        this.filenameString = prefix + this.filenameString;
        this.filenameInteger = prefix + this.filenameInteger;
        this.filenameFloat = prefix + this.filenameFloat;
    }

    public void setPath(String path) {
        this.path = path + "/";
    }

    public void writeToFile(String content) throws IOException {

        String fullPath = path;
        DataType dataType = filter.checkType(content);

        switch (dataType) {
            case STRING -> fullPath += filenameString;
            case INTEGER -> fullPath += filenameInteger;
            case FLOAT -> fullPath += filenameFloat;
        }

        statisticRuntime.addData(content);
        write.addToFile(Path.of(fullPath), content);
    }

    public void filterFromFiles(List<String> files) throws IOException {
        for (String file : files) {
            List<String> lines = read.readFileLines(Path.of(path + file));
            for (String line : lines) {
                writeToFile(line);
            }
        }
    }

    public void deleteOutputFiles() throws IOException, InvalidPathException {
        Files.deleteIfExists(Path.of(path,prefix + "strings.txt"));
        Files.deleteIfExists(Path.of(path,prefix + "integers.txt"));
        Files.deleteIfExists(Path.of(path,prefix + "floats.txt"));
    }
}
