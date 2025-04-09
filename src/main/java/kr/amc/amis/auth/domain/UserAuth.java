package kr.amc.amis.auth.domain;

public class UserAuth {

    private final Email email;
    private final Password password;
    private final UserRole userRole;
    private Long userId;

    public UserAuth(String email, String password, String userRole) {
        this.email = Email.createEmail(email);
        this.password = Password.createEncryptedPassword(password);
        this.userRole = UserRole.valueOf(userRole);
    }

    public UserAuth(String email, String password, String userRole, Long userId) {
        this.email = Email.createEmail(email);
        this.password = Password.createPassword(password);
        this.userRole = UserRole.valueOf(userRole);
        this.userId = userId;
    }

    public String getEmail() {
        return this.email.getEmailText();
    }

    public String getPassword() {
        return this.password.getEncryptedPassword();
    }

    public boolean matchPassword(String password) {
        return this.password.matchPassword(password);
    }

    public String getUserRole() {
        return this.userRole.name();
    }

    public Long getUserId() {
        return this.userId;
    }
}
