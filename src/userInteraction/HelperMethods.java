package userInteraction;

import java.util.Scanner;

public class HelperMethods {
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean askYesNoQuestion(String question) {
        while (true) {
            System.out.print(question);
            char userInput = scanner.next().charAt(0);
            if (userInput == 'y') {
                return true;
            } else if (userInput == 'n') {
                return false;
            } else {
                System.out.println("Invalid option\n");
            }
        }
    }

    public static Integer askForInt(String question, int maxLimit) {
        while (true) {
            System.out.print(question);
            int userInput = scanner.nextInt();
            if (userInput > 0 && userInput <= maxLimit) {
                return userInput;
            } else {
                System.out.println("Invalid option\n");
            }
        }
    }

    public static LogicOption askForLogicOption() {
        while (true) {
            System.out.print("Please select an option: ");
            char userInput = scanner.next().charAt(0);

            if (userInput == 'f') {
                System.out.println("You have selected the functional logic.\n");
                return LogicOption.FUNCTIONAL;
            } else if (userInput == 'i') {
                System.out.println("You have selected the imperative logic.\n");
                return LogicOption.IMPERATIVE;
            } else {
                System.out.println("Invalid option\n");
            }
        }
    }
}
