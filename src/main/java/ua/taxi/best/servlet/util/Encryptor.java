package ua.taxi.best.servlet.util;

import org.apache.commons.codec.digest.DigestUtils;

public final class Encryptor {

    private static final String SALT = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*BestTaxi";

    private Encryptor() {
    }

    public static String encrypt(String unencryptedString) {
        return DigestUtils.md5Hex(unencryptedString + SALT);
    }
}
