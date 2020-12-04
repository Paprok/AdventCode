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

    public int getValidPassportsNumber() {
        return getValidPassportsNumber(0, passports);
    }

    private int getValidPassportsNumber(int number, Deque<Passport> passports) {
        if(isNotEmpty(passports)) {
            int newNumber = passports.pop().isValid() ? ++number : number;
            return getValidPassportsNumber(newNumber, passports);
        } else return number;
    }

    private boolean isNotEmpty(Deque<Passport> passports) {
        return passports.size() > 0;
    }
}
