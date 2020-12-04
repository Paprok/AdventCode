package com.github.paprok.passport.processing.passportLoader.model;

import lombok.NoArgsConstructor;

import java.util.*;

public class Passport {

    private Map<PassportField, String> fields;

    public Passport() {
        this.fields = new HashMap<>();
    }

    public void update(String line) {
        Arrays.stream(line.trim().split(" "))
                .map(PassportField::getKeyValuePair)
                .forEach(pair -> fields.put(pair.getKey(), pair.getValue()));
    }

    private boolean hasAllFields(long validFields) {
        return validFields == PassportField.values().length;
    }

    private boolean hasOnlyCidMissing(long validFields) {
        return hasOneMissing(validFields) && hasCidMissing();
    }

    private boolean hasCidMissing() {
        return !fields.containsKey(PassportField.CID);
    }

    private boolean hasOneMissing(long validFields) {
        return validFields == PassportField.values().length - 1;
    }

   public boolean isValid() {
        long validFieldsCount = fields.entrySet().stream()
                .filter(this::validateFieldPair)
                .count();
        return hasAllFields(validFieldsCount) || hasOnlyCidMissing(validFieldsCount);
    }

    private boolean validateFieldPair(Map.Entry<PassportField, String> keyValuePair) {
        PassportField field = keyValuePair.getKey();
        String value = keyValuePair.getValue();

        try {
            return field.validator.test(value);
        } catch (Exception e) {
            return false;
        }
    }
}
