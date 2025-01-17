package ru.filter;

import ru.filter.files.read.ReadFromFile;
import ru.filter.files.write.WriteToFile;
import ru.filter.filter.Filter;
import ru.filter.filter.FilterService;
import ru.filter.statistic.StatisticRuntime;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main( String[] args ) {

        final ReadFromFile read = new ReadFromFile();
        final WriteToFile write = new WriteToFile();
        final Filter filter = new Filter();
        final StatisticRuntime statisticRuntime = new StatisticRuntime(filter);
        final FilterService filterService = new FilterService(read, write, filter, statisticRuntime);

        List<String> postActions = new ArrayList<>();
        List<String> documents = new ArrayList<>();
        boolean appendMode = false;

        int i = 0;
        while (i < args.length) {
            String arg = args[i];
            switch (arg) {
                case "-o" -> {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        filterService.setPath(args[++i]);
                    } else {
                        System.err.println("Error: Missing value for -o");
                        return;
                    }
                }
                case "-p" -> {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        filterService.setPrefix(args[++i]);
                    } else {
                        System.err.println("Error: Missing value for -p");
                        return;
                    }
                }
                case "-a" -> appendMode = true;
                case "-s", "-f" -> postActions.add(arg);
                default -> {
                    if (!arg.startsWith("-")) {
                        documents.add(arg);
                    } else {
                        System.err.println("Warning: Unknown flag " + arg);
                    }
                }
            }
            ++i;
        }

        try {
            if (!appendMode) {
                filterService.deleteOutputFiles();
            }

            filterService.filterFromFiles(documents);
        } catch (IOException | InvalidPathException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }

        for (String action : postActions) {
            switch (action) {
                case "-s" -> System.out.println(statisticRuntime.getShortStatistic());
                case "-f" -> System.out.println(statisticRuntime.getFullStatistic());
            }
        }
    }
}
