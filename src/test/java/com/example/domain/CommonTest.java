package com.example.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.domain.Common.NUMBER_PATTERN;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class CommonTest {

    @Test
    public void test4NumbersWithRegexOk() {

        String number1 = "1";
        String number2 = "12.3";
        String number3 = "-1";
        String number4 = "-1.34534";

        assertTrue(NUMBER_PATTERN.matcher(number1).matches());
        assertTrue(NUMBER_PATTERN.matcher(number2).matches());
        assertTrue(NUMBER_PATTERN.matcher(number3).matches());
        assertTrue(NUMBER_PATTERN.matcher(number4).matches());
    }

    @Test
    public void test4NumbersWithRegexFails() {

        String number1 = "";
        String number2 = "12..3";
        String number3 = "- 1";
        String number4 = "A";

        assertFalse(NUMBER_PATTERN.matcher(number1).matches());
        assertFalse(NUMBER_PATTERN.matcher(number2).matches());
        assertFalse(NUMBER_PATTERN.matcher(number3).matches());
        assertFalse(NUMBER_PATTERN.matcher(number4).matches());
    }
}