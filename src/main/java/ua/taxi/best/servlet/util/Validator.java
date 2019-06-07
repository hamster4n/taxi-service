package ua.taxi.best.servlet.util;

public final class Validator {

    private static final String VALID_EMAIL = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private static final String VALID_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    private Validator() {
    }

    public static boolean isEmailValid(String email){
        return email.matches(VALID_EMAIL);
    }

    /**
     * The password policy is:
     * At least 8 chars
     * Contains at least one digit
     * Contains at least one lower alpha char and one upper alpha char
     * Contains at least one char within a set of special chars (@#%$^ etc.)
     * Does not contain space, tab, etc.
     */

    public  static boolean isPasswordValid(String password){
        return password.matches(VALID_PASSWORD);
    }


}
