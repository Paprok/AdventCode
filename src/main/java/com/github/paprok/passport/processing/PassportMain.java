package com.github.paprok.passport.processing;

import com.github.paprok.passport.processing.passportLoader.PassportLoader;
import lombok.Data;

import java.io.IOException;

@Data
public class PassportMain implements Runnable {


    @Override
    public void run() {
        PassportLoader pl = new PassportLoader();
        try {
            System.out.println( pl.getPassports().getValidPassportsNumber());
        } catch (IOException e) {
            System.out.println("couldn't read passport file");
        }
    }
}

