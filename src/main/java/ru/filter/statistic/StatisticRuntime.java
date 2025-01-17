package ru.filter.statistic;

import ru.filter.filter.DataType;
import ru.filter.filter.Filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StatisticRuntime implements Statistic {

    private final List<Integer> integers;
    private final List<Float> floats;
    private final List<String> strings;

    private final Filter filter;

    public StatisticRuntime(Filter filter) {
        this.integers = new ArrayList<>();
        this.floats = new ArrayList<>();
        this.strings = new ArrayList<>();
        this.filter = filter;
    }

    public void addData(String content) {

        DataType dataType = filter.checkType(content);

        switch (dataType) {
            case STRING -> this.strings.add(content);
            case INTEGER -> this.integers.add(Integer.parseInt(content));
            case FLOAT -> this.floats.add(Float.parseFloat(content));
        }
    }


    public void addToInteger(Integer integer) {
        this.integers.add(integer);
    }

    public void addToFloat(Float fl) {
        this.floats.add(fl);
    }

    public void addToString(String string) {
        this.strings.add(string);
    }


    public int getCountInteger() {
        return this.integers.size();
    }

    public int getMaxInteger() {
        return this.integers.stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    public int getMinInteger() {
        return this.integers.stream()
                .min(Integer::compareTo)
                .orElse(0);
    }

    public int getSumInteger() {
        return this.integers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public double getAvgInteger() {
        return this.integers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }



    public int getCountFloat() {
        return this.floats.size();
    }

    public float getMaxFloat() {
        return this.floats.stream()
                .max(Float::compareTo)
                .orElse(0f);
    }

    public float getMinFloat() {
        return this.floats.stream()
                .min(Float::compareTo)
                .orElse(0f);
    }

    public float getSumFloat() {
        return (float) this.floats.stream()
                .mapToDouble(Float::doubleValue)
                .sum();
    }

    public float getAvgFloat() {
        return (float) this.floats.stream()
                .mapToDouble(Float::doubleValue)
                .average()
                .orElse(0.0);
    }



    public int getCountString() {
        return this.strings.size();
    }

    public int getMaxStringLength() {
        if (this.strings.isEmpty()) return 0;
        return Collections.max(this.strings, Comparator.comparingInt(String::length)).length();
    }

    public int getMinStringLength() {
        if (this.strings.isEmpty()) return 0;
        return Collections.min(this.strings, Comparator.comparingInt(String::length)).length();
    }



    public String getShortStatistic() {
        return "Count integer: " + getCountInteger() + "\n" +
                "Count float: " + getCountFloat() + "\n" +
                "Count string: " + getCountString();
    }

    public String getFullStatistic() {
        return "=== INTEGER ===\n" +
                "Count integer: " + getCountInteger() + "\n" +
                "Max integer: " + getMaxInteger() + "\n" +
                "Min integer: " + getMinInteger() + "\n" +
                "Sum integer: " + getSumInteger() + "\n" +
                "Average integer: " + getAvgInteger() + "\n" +
                "\n" +
                "=== FLOAT ===" + "\n" +
                "Count float: " + getCountFloat() + "\n" +
                "Max float: " + getMaxFloat() + "\n" +
                "Min float: " + getMinFloat() + "\n" +
                "Sum float: " + getSumFloat() + "\n" +
                "Average float: " + getAvgFloat() + "\n" +
                "\n" +
                "=== STRING ===" + "\n" +
                "Count string: " + getCountString() + "\n" +
                "Largest length string: " + getMaxStringLength() + "\n" +
                "Smallest length string: " + getMinStringLength();
    }
}
