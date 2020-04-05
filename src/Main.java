import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Set (Single Player)");
        System.out.println("Rules can be found here: https://www.setgame.com/sites/default/files/instructions/SET%20INSTRUCTIONS%20-%20ENGLISH.pdf");
        Random r = new Random();
        Scanner in = new Scanner(System.in);

        Board b = new Board();
        System.out.println("Select three cards by giving three column-row pairs separated by commas.");
        System.out.println("Ex: a1, B2, 3c");
        System.out.println("Press any button and enter when you are ready to begin.");
        in.nextLine();
        long start = System.currentTimeMillis();
        int score = 0;
        while (!b.gameOver) {
            System.out.println(b);
            System.out.println("Select three cards.");
            String input = in.nextLine();
            while (!validInput(input) || !b.selectCards(parseInput(input))) {
                System.out.println("Your input was invalid or those cards do not form a set. Select three cards.");
                input = in.nextLine();
            }

            System.out.println("Valid Set!");
            score++;
        }

        long end = System.currentTimeMillis();
        System.out.println("Game over!");
        System.out.println("Your time was: " + (end - start) / 1000);
    }

    static int[] parseInput(String input) {
        input = input.toUpperCase().replaceAll(" ", "");
        String[] cardCoords = input.split(",");
        int[] output = new int[3];
        for (int i = 0; i < output.length; i++) {
            String standardizedInput = standardizeInput(cardCoords[i]);
            char col = standardizedInput.charAt(0);
            char row = standardizedInput.charAt(1);
            output[i] = (row - 1) * 4 + (col - 'A');
        }

        return output;
    }

    static String standardizeInput(String coord) {
        if (coord.charAt(1) >= 'A' && coord.charAt(1) <= 'D') {
            return "" + coord.charAt(1) + coord.charAt(0);
        }

        return coord;
    }

    static boolean validInput(String input) {
        input = input.toUpperCase().replaceAll(" ", "");
        String[] cardCoords = input.split(",");
        if (cardCoords.length != 3) {
            return false;
        }

        for (String s : cardCoords) {
            if (!validCard(s)) {
                return false;
            }
        }

        return true;
    }

    static boolean validCard(String s) {
        if (s.length() != 2) {
            return false;
        }

        String cols = "ABCD";
        String rows = "123";
        if (s.charAt(0) >= 'A' && s.charAt(0) <= 'D' && s.charAt(1) >= '1' && s.charAt(1) <= '3') {
            return true;
        }

        s = "" + s.charAt(1) + s.charAt(0);
        if (s.charAt(0) >= 'A' && s.charAt(0) <= 'D' && s.charAt(1) >= '1' && s.charAt(1) <= '3') {
            return true;
        }

        return false;
    }
}
