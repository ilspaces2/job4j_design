package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return value;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        Arrays.stream(args).forEach(pair -> {
            if (pair.startsWith("-")) {
                String[] splitPair = pair.substring(1).split("=", 2);
                if (splitPair.length == 1 || splitPair[0].isBlank() || splitPair[1].isBlank()) {
                    throw new IllegalArgumentException();
                }
                values.put(splitPair[0], splitPair[1]);
            }
        });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}