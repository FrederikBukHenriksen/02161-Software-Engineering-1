package dtu.calculator;

public class User {

    String initials;
    String password;

    public User(String initials) {
        this.initials = initials;
        this.password = generatePassword();
    }

    public String generatePassword() {
        return "01234";
    }

}
