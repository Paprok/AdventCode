package com.github.paprok.passport.processing.passportLoader.model;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;

@Data
public class Passports {
    private final String endLine = "";
    private Deque<Passport> passports;

    public Passports() {
        this.passports = new ArrayDeque<>();
        passports.push(new Passport());
    }

    public Passports updateAndGet(String readLine) {
        update(readLine);
        return this;
    }

    private void update(String readLine) {
        if (endLine.equals(readLine)) {
            passports.push(new Passport());
        } else {
            passports.peek().update(readLine);
        }
    }

    public long getValidPassportsNumber() {
       return passports.stream().filter(Passport::isValid).count();
    }

    private boolean isNotEmpty(Deque<Passport> passports) {
        return passports.size() > 0;
    }
}
