package com.github.paprok;

import com.github.paprok.passport.processing.PassportMain;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Runnable passport = new PassportMain();
        passport.run();
    }
}
