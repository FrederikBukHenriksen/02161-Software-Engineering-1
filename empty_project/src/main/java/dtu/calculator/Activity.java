package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Activity {

    String title;
    int budgetedTime;
    GregorianCalendar startTime;
    GregorianCalendar endTime;
    ArrayList<Employee> employees = new ArrayList<>();

    public Activity(String title) {
        this.title = title;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public String getTitle() {
        return title;
    }

}