package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Activity {

    private String title;
    int budgetedTime;
    GregorianCalendar startTime;
    GregorianCalendar endTime;
    ArrayList<User> employees = new ArrayList<>();

    public Activity(String title) {
        this.title = title;
    }

    public void addEmployee(User employee) {
        employees.add(employee);
    }

    public void removeEmployee(User employee) {
        employees.remove(employee);
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<User> getEmployees() {
        return employees;
    }

}