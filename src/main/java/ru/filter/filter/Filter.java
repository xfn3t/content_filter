package ru.filter.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {

    public DataType checkType(String line) {

        String integerRegex = "^-?\\d+$";
        String floatRegex = "^-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?$";

        Pattern integerPattern = Pattern.compile(integerRegex);
        Pattern floatPattern = Pattern.compile(floatRegex);

        Matcher integerMatcher = integerPattern.matcher(line);
        Matcher floatMatcher = floatPattern.matcher(line);

        if (integerMatcher.matches()) return DataType.INTEGER;
        if (floatMatcher.matches()) return DataType.FLOAT;

        return DataType.STRING;

    }
}
