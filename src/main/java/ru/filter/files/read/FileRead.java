package ru.filter.files.read;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileRead {
    List<String> readFileLines(Path path) throws IOException;
}
