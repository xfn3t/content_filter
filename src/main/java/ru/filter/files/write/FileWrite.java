package ru.filter.files.write;

import java.io.IOException;
import java.nio.file.Path;

public interface FileWrite {
    void writeToFile(Path path, String line) throws IOException;
    void addToFile(Path path, String line) throws IOException;
}
