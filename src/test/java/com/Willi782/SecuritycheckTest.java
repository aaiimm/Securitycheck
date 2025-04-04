package com.Willi782;

import com.Willi782.Securitycheck.*;
import com.cthiebaud.passwordvalidator.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecuritycheckTest {

    private static Securitycheck validator;

    @BeforeAll
    public static void setup() {
        validator = new Securitycheck();
    }

    @Test
    public void testNullPassword() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.isValid());
        assertEquals("Password cannot be null or empty.", result.message());
    }

    @Test
    public void testEmptyPassword() {
        ValidationResult result = validator.validate("");
        assertFalse(result.isValid());
        assertEquals("Password cannot be null or empty.", result.message());
    }

    @Test
    public void testMissingRGBColor() {
        ValidationResult result = validator.validate("MyPasswort1!");
        assertEquals(false, result.isValid());
        assertEquals("Password must contain one of the RGB colors: red, green or blue.", result.message());
    }

    @Test
    public void testShortPassword() {
        ValidationResult result = validator.validate("Red1!");
        assertFalse(result.isValid());
        assertEquals("Password must be at least 8 characters long.", result.message());
    }

    @Test
    public void testMissingUppercase() {
        ValidationResult result = validator.validate("1redmessage!");
        assertFalse(result.isValid());
        assertEquals("Password must contain at least one uppercase letter.", result.message());
    }

    @Test
    public void testMissingLowercase() {
        ValidationResult result = validator.validate("1REDMESSAGE!");
        assertFalse(result.isValid());
        assertEquals("Password must contain at least one lowercase letter.", result.message());
    }

    @Test
    public void testMissingDigit() {
        ValidationResult result = validator.validate("!RedMessage");
        assertFalse(result.isValid());
        assertEquals("Password must contain at least one digit.", result.message());
    }

    @Test
    public void testMissingSpecialCharacter() {
        ValidationResult result = validator.validate("Red1Message");
        assertFalse(result.isValid());
        assertEquals("Password must contain at least one special character.", result.message());
    }

    public void testValidPassword() {
        String validPassword = "Red1Message!";
    
        ValidationResult result = validator.validate(validPassword);
        assertTrue(result.isValid(), "Password should be valid.");
        assertEquals("Password is valid.", result.message());
    }
}