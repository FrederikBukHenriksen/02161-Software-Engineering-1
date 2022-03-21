package dtu.calculator;

public class Employee {

    String initials;
    String password;

    public Employee(String initials) {
        this.initials = initials;
        this.password = generatePassword();
    }

    public String generatePassword() {
        return "pw1234";
    }

}
