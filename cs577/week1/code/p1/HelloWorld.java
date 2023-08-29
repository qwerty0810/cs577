import java.util.Scanner;
import java.util.ArrayList;

public class HelloWorld {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> strings = new ArrayList<String>();
    
        int n;
        try {
            n = Integer.parseInt(input.nextLine()); // Get integer
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            input.close();
            return;
        }

        // Store string inputs
        for (int i = 0; i < n; i++) { 
            String s = input.nextLine();
            strings.add(s);
        }

        // Print strings
        for (String s : strings) { 
            System.out.println("Hello, " + s + "!");
        }

        input.close();
    }
}
