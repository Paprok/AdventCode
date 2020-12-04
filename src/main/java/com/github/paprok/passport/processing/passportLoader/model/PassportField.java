package com.github.paprok.passport.processing.passportLoader.model;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public enum PassportField {

    BYR(s -> isBetween(s, 1920, 2002)),
    IYR(s -> isBetween(s, 2010, 2020)),
    EYR(s -> isBetween(s, 2020, 2030)),
    HGT(s -> {
        if(s.endsWith("cm")) return isBetween(s.replaceAll("cm",""), 150, 193);
        if(s.endsWith("in")) return isBetween(s.replaceAll("in", ""), 59, 76);
        else return false;
    }),
    HCL(s -> s.matches("#[0-9a-f]{6}")),
    ECL(s -> List.of("mb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(s)),
    PID(s -> s.matches("[0-9]{9}")),
    CID(s -> true);

    private static boolean isBetween(String number, int left, int right) {
        return Integer.parseInt(number) >= left && Integer.parseInt(number) <= right;
    }

    final Predicate<String> validator;

    PassportField(Predicate<String> validator) {
        this.validator = validator;
    }

    private static PassportField getFieldByName(String name) {
        return PassportField.valueOf(name.toUpperCase());
    }

    static Map.Entry<PassportField, String> getKeyValuePair(String pair) {
        String[] pairArray = pair.trim().split(":");
        PassportField key = getFieldByName(pairArray[0]);
        String value = pairArray[1];
        return new AbstractMap.SimpleEntry<>(key, value);
    }
}
