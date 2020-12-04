package com.github.paprok.passport.processing.passportLoader;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PassportLoaderTest {

    private PassportLoader loader;

    @Before
    public void setUp() {
        loader = new PassportLoader();
    }

    @Test
    @SneakyThrows
    public void testThatGetsRightNumberOfPassportsForPreparedFile() throws IOException {
        //given
        long expected = 4L;

        //when
        long actual = loader.getPassports().getValidPassportsNumber();

        //then
        assertEquals(expected, actual);
    }

}