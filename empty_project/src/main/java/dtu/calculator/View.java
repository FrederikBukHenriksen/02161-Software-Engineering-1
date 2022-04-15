package dtu.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class View {

    Scanner scanner = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private void back() {
        subSectionln("0. Return");
    }

    public ArrayList<String> logIn() {
        title("Login", ANSI_GREEN);
        subSection("You initials: ");
        String initials = scanner.nextLine();
        subSection("You password: ");
        String password = scanner.nextLine();

        return new ArrayList<>(Arrays.asList(initials, password));
    }

    public void announcement(String title, String color, ArrayList<String> text) {
        title(title, color);
        for (int i = 0; i < text.size(); i++) {
            subSectionln(text.get(i));
        }

    }

    public int choiceMenu(String title, ArrayList<String> options, boolean back) {
        // Print menu header
        clearScreen();

        title(title);
        for (int i = 0; i < options.size(); i++) {
            subSectionln((i + 1) + ". " + options.get(i));
        }

        if (back) {
            back();
        }

        subSectionln("");
        subSection("Your choice: ");

        return scanner.nextInt();
    }

    public ArrayList<String> insertMenu(String title, ArrayList<String> options, boolean back) {
        clearScreen();

        title(title);

        ArrayList<String> inputs = new ArrayList<>();

        if (back) {
            back();
        }

        for (int i = 0; i < options.size(); i++) {
            subSection(options.get(i));
            String input = scanner.next();
            inputs.add(input);
        }

        return inputs;

    }

    private void title(String title) {
        System.out.println("######## " + title.toUpperCase() + " ########");
    }

    private void title(String title, String color) {
        System.out.println("######## " + color + title.toUpperCase() + ANSI_RESET + " ########");
    }

    public void subSection(String input) {
        System.out.print("   " + input);

    }

    public void subSectionln(String input) {
        System.out.println("   " + input);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J"); // Magic sequence
        System.out.flush();
    }

    public void error(Exception e) {
        title("ERROR", ANSI_RED);
        subSection("Something went wrong: ");
        subSectionln(e.getMessage());
    }

    public void enterToContinue() {
        subSection("-> Press enter to proceed");

        boolean continueFlag = false;
        while (!continueFlag) {
            String enter = scanner.nextLine();
            if (enter.equals("")) {
                continueFlag = true;
            }
        }
    }

    public boolean choiceCheckRange(int choice, int min, int max) {
        if (min > choice || choice > max) {
            return true;
        }
        return false;
    }

    public void choiceOutOfRange(String choice, int min, int max) {
        if (min > Integer.valueOf(choice) || Integer.valueOf(choice) > max) {
            error(new Exception("Invalid choice"));
        }
    }

}
