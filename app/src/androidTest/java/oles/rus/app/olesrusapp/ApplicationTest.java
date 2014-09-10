package oles.rus.app.olesrusapp;

import android.test.InstrumentationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends InstrumentationTestCase
{

    public ApplicationTest() {
    }

    public void testTest()
    {
        System.out.println("TESTING");
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}