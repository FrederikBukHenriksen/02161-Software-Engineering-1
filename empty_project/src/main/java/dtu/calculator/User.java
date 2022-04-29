package dtu.calculator;

import java.util.ArrayList;

public class User {

    protected String initials;
    protected String password;

    protected ArrayList<Registration> registration = new ArrayList<>();

    public User(String initials) {
        this.initials = initials;
        this.password = generatePassword();
    }

    public void setPassword(String password) {
        password = password;
    }

    public String generatePassword() {
        return "01234";
    }

    public String getInitials() {
        return initials;
    }


}
