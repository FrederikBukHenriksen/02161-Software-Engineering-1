package dtu.calculator;

public class User {

    String initials;
    String password;

    public User(String initials) {
        this.initials = initials;
        this.password = generatePassword();
    }

    public String generatePassword() {
        return "pw1234";
    }

    public boolean Login(String initals, String password) {
        if (this.initials.equals(initals) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

}
