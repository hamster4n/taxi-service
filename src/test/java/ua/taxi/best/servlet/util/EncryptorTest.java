package ua.taxi.best.servlet.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncryptorTest {

    private static final String PASSWORD = "testPassword123%";

    @Test
    public void methodEncryptShouldReturnTheSameTwice(){
        String firstEncryptedPassword = Encryptor.encrypt(PASSWORD);
        String secondEncryptedPassword = Encryptor.encrypt(PASSWORD);

        assertEquals(firstEncryptedPassword, secondEncryptedPassword);
    }

}