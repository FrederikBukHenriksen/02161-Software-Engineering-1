package dtu.calculator;

import java.util.ArrayList;

public class View {

    public View() {
    }

    public void menu(String title, ArrayList<String> options) {
        clearScreen();

        title(title);

        for (int i = 0; i < options.size(); i++) {
            if (i < options.size() - 1) {
                subSectionln(options.get(i));
            } else {
                subSection(options.get(i));
            }
        }
    }

    public void menuEnumerate(String title, ArrayList<String> options) {
        clearScreen();

        title(title);

        for (int i = 0; i < options.size(); i++) {
            subSectionln((i + 1) + ". " + options.get(i));
        }
    }

    public void announcement(String title, ArrayList<String> text) {
        clearScreen();
        title(title);
        for (int i = 0; i < text.size(); i++) {
            subSectionln(text.get(i));
        }
    }

    private void title(String title) {
        System.out.println("######## " + title.toUpperCase() + " ########");
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
        title("ERROR");
        subSectionln("Something went wrong: ");
        subSectionln(e.getMessage());
        subSection("Press enter to continue");
    }
}
