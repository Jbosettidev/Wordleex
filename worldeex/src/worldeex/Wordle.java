package worldeex;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Wordle {

    public static void main(String[] args) {
        
        final String BG_GREEN = "\u001b[42m";
        final String BG_YELLOW = "\u001b[43m";
        final String RESET = "\u001b[0m";
        Scanner sc = new Scanner(System.in);

        System.out.println("Wordle\n");

        // archive words go to list
        List<String> words = null;
        try {
            words = Files.readAllLines(Paths.get("words.txt"));
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
            return;
        }

        if (words == null || words.isEmpty()) {
            System.out.println("words.txt is empty");
            return;
        }

        // choose the random word
        Random random = new Random();
        String correct = words.get(random.nextInt(words.size())).toUpperCase();

        String guess = null;

        // Loop to try guess
        for (int round = 0; round < 6; round++) {
            System.out.print("Guess: ");
            guess = sc.nextLine().toUpperCase();

            if (guess.length() != 5) {
                System.out.println("The word must be 5 letters!");
                round--; // this doesnt count
                continue;
            }
            
            // Loop to verify every letter
            for (int i = 0; i < 5; i++) {
                if (guess.charAt(i) == correct.charAt(i)) {
                    System.out.print(BG_GREEN + guess.charAt(i) + RESET);
                } else if (correct.contains(String.valueOf(guess.charAt(i)))) {
                    System.out.print(BG_YELLOW + guess.charAt(i) + RESET);
                } else {
                    System.out.print(guess.charAt(i));
                }
            }

            System.out.println("");

            // if guess is correct
            if (guess.equals(correct)) {
                System.out.println("Correct !!! You win!");
                return;
            }
        }

        // if dont guess the word
        System.out.println("You lose ! The correct answer is: " + correct);
    }
}
