package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Activity {

    String title;
    int budgetedTime;
    String startTime;
    String endTime;
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

    public String getId() {
        return title;
    }

    public void setStartDate(Integer Year, Integer Week) {
        startTime = Year + "-" + Week;
    }

    public String getStartDate() {
        return startTime;

    }

}