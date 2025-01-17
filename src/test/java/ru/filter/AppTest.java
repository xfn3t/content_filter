package ru.filter;

import junit.framework.TestCase;
import ru.filter.files.read.ReadFromFile;
import ru.filter.files.write.WriteToFile;
import ru.filter.filter.Filter;
import ru.filter.filter.FilterService;
import ru.filter.statistic.StatisticRuntime;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AppTest extends TestCase {

    private FilterService filterService;
    private String testDirectoryPath;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        testDirectoryPath = System.getProperty("user.dir") + "\\testDirectory\\";

        File testDirectory = new File(testDirectoryPath);
        if (!testDirectory.exists()) {
            testDirectory.mkdirs();
        }

        ReadFromFile read = new ReadFromFile();
        WriteToFile write = new WriteToFile();
        Filter filter = new Filter();
        StatisticRuntime statisticRuntime = new StatisticRuntime(filter);
        filterService = new FilterService(read, write, filter, statisticRuntime);

        filterService.setPath(testDirectoryPath);
    }

    @Override
    protected void tearDown() throws Exception {

        super.tearDown();

        filterService.deleteOutputFiles();

        Files.deleteIfExists(Path.of(testDirectoryPath + "file1.txt"));
        Files.deleteIfExists(Path.of(testDirectoryPath + "file2.txt"));
        Files.delete(Path.of(testDirectoryPath));
    }

    public void testSetPrefix() {
        filterService.setPrefix("testPrefix");

        assertEquals("testPrefixstrings.txt", filterService.getFilenameString());
        assertEquals("testPrefixintegers.txt", filterService.getFilenameInteger());
        assertEquals("testPrefixfloats.txt", filterService.getFilenameFloat());
    }

    public void testWriteToFile_withString() throws IOException {
        String content = "Hello, World!";

        filterService.writeToFile(content);

        File file = new File(testDirectoryPath + "strings.txt");
        assertTrue(file.exists());

        List<String> lines = Files.readAllLines(file.toPath());
        assertTrue(lines.contains(content));
    }

    public void testWriteToFile_withInteger() throws IOException {
        String content = "123";

        filterService.writeToFile(content);

        File file = new File(testDirectoryPath + "integers.txt");
        assertTrue(file.exists());

        List<String> lines = Files.readAllLines(file.toPath());
        assertTrue(lines.contains(content));
    }

    public void testWriteToFile_withFloat() throws IOException {
        String content = "12.34";

        filterService.writeToFile(content);

        File file = new File(testDirectoryPath + "floats.txt");
        assertTrue(file.exists());

        List<String> lines = Files.readAllLines(file.toPath());
        assertTrue(lines.contains(content));
    }

    public void testFilterFromFiles() throws IOException {

        Path file1 = Path.of(testDirectoryPath + "file1.txt");
        Path file2 = Path.of(testDirectoryPath + "file2.txt");

        Files.write(file1, List.of("Hello", "123", "45.67"));
        Files.write(file2, List.of("World", "456", "78.90"));

        filterService.filterFromFiles(List.of("file1.txt", "file2.txt"));

        File stringFile = new File(testDirectoryPath + "strings.txt");
        File integerFile = new File(testDirectoryPath + "integers.txt");
        File floatFile = new File(testDirectoryPath + "floats.txt");

        assertTrue(stringFile.exists());
        assertTrue(integerFile.exists());
        assertTrue(floatFile.exists());

        List<String> stringLines = Files.readAllLines(stringFile.toPath());
        List<String> integerLines = Files.readAllLines(integerFile.toPath());
        List<String> floatLines = Files.readAllLines(floatFile.toPath());

        assertTrue(stringLines.contains("Hello"));
        assertTrue(integerLines.contains("123"));
        assertTrue(floatLines.contains("45.67"));
    }

    public void testDeleteOutputFiles() throws IOException {

        filterService.deleteOutputFiles();

        File stringFile = new File(testDirectoryPath + "strings.txt");
        File integerFile = new File(testDirectoryPath + "integers.txt");
        File floatFile = new File(testDirectoryPath + "floats.txt");

        assertFalse(stringFile.exists());
        assertFalse(integerFile.exists());
        assertFalse(floatFile.exists());
    }
}
