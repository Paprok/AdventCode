package com.github.paprok.passport.processing.passportLoader.model;

public enum PassportField {

    BYR,
    IYR,
    EYR,
    HGT,
    HCL,
    ECL,
    PID,
    CID;

    static PassportField getFieldByName(String name) {
        return PassportField.valueOf(name.toUpperCase());
    }

    static PassportField getFieldByPair(String pair) {
        return getFieldByName(pair.substring(0,3));
    }
}
