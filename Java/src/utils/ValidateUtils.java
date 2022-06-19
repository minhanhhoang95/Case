package utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String PASSWORD_REGEX = "^([a-zA-Z0-9]{8,}$)";// >8 gồm chữ vào số
    public static final String NAME_REGEX = "^([A-Z]+[a-z]*[ ]?)+$";//chữ Hoa đầu tiên
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";//số 0 đầu số thứ 2 lớn hơn 0
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";//có @
    public static final String USERNAME_PATTERN = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";//>8 ko kí tự đặc biệt

    public static boolean isPasswordValid(String password) {
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }

    public static boolean isUsernameValid(String password) {
        return Pattern.compile(USERNAME_PATTERN).matcher(password).matches();
    }

    public static boolean isNameValid(String name) {
        return Pattern.compile(NAME_REGEX).matcher(name).matches();
    }

    public static boolean isPhoneValid(String number) {
        return Pattern.compile(PHONE_REGEX).matcher(number).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
}
