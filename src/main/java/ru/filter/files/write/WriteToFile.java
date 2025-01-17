package ru.filter.files.write;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WriteToFile implements FileWrite {

    public void writeToFile(Path path, String line) throws IOException {
        if (!path.toFile().exists()) Files.createFile(path);
        Files.writeString(path, line, StandardCharsets.UTF_8);
    }

    public void writeToFile(Path path, List<String> lines) throws IOException {
        if (!path.toFile().exists()) Files.createFile(path);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public void addToFile(Path path, String line) throws IOException {
        if (!path.toFile().exists()) Files.createFile(path);
        Files.writeString(path, line + "\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    public void addToFile(Path path, List<String> lines) throws IOException {
        if (!path.toFile().exists()) Files.createFile(path);
        Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }
}
