package ru.filter.files.read;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFromFile implements FileRead {
    public List<String> readFileLines(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
}
