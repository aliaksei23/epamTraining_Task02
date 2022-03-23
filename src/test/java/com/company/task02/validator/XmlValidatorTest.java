package com.company.task02.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XmlValidatorTest {

    @Test
    public void testXmlValidator() {

        boolean validator = XmlValidator.xmlValidator();
        assertTrue(validator);

    }
}