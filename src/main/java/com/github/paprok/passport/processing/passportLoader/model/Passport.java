package com.github.paprok.passport.processing.passportLoader.model;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Passport {

    private Set<PassportField> fields;

    public Passport() {
        this.fields = new HashSet<>();
    }

    public void update(String line) {
        Arrays.stream(line.trim().split(" "))
                .map(PassportField::getFieldByPair)
                .forEach(field -> fields.add(field));
    }

    public boolean isValid() {
        return hasAllFields() || hasOnlyCidMissing();
    }

    private boolean hasOnlyCidMissing() {
        return hasOneMissing() && hasCidMissing();
    }

    private boolean hasCidMissing() {
        return !fields.contains(PassportField.CID);
    }

    private boolean hasOneMissing() {
        return fields.size() == PassportField.values().length - 1;
    }

    private boolean hasAllFields() {
        return fields.size() == PassportField.values().length;
    }
}
