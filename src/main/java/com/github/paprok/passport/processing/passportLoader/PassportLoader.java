package com.github.paprok.passport.processing.passportLoader;

import com.github.paprok.passport.processing.passportLoader.model.Passports;

import java.io.*;

public class PassportLoader {

    public Passports getPassports() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("passports.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
               return fillPassportsFromFile(reader);
        }
    }

    private Passports fillPassportsFromFile(BufferedReader reader) throws IOException{
        return fillPassportsFromFile(reader, new Passports());
    }

    private Passports fillPassportsFromFile(BufferedReader reader, Passports passports) throws IOException {
        return reader.ready() ? fillPassportsFromFile(reader, passports.updateAndGet(reader.readLine())) : passports;
    }
}
