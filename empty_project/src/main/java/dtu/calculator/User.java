package dtu.calculator;

public class User {

    protected String initials;
    protected String password;

    public User(String initials) {
        this.initials = initials;
        this.password = generatePassword();
    }

    public String generatePassword() {
        return "01234";
    }

    public String getInitials() {
        return initials;
    }

}
