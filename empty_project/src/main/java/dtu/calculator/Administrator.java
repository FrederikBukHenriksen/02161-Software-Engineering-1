package dtu.calculator;

public class Administrator extends User {

    public Administrator(String initials, String password, ProjectPlanner projectPlanner) {
        super(initials, projectPlanner);
        this.password = password;
    }

}
