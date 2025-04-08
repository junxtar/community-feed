package kr.amc.amis.auth.domain;

import java.util.regex.Pattern;
import lombok.Getter;

@Getter
public class Email {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private final String emailText;

    private Email(String emailText) {
        this.emailText = emailText;
    }

    public static Email createEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("email is null or empty");
        }
        if (isNotValidEmailString(email)) {
            throw new IllegalArgumentException("invalid email format");
        }
        return new Email(email);
    }

    private static boolean isNotValidEmailString(String email) {
        return !pattern.matcher(email).matches();
    }

}
