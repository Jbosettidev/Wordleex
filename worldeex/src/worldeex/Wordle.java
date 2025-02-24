package worldeex;

import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Wordle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        
        // Obtendo a lista de palavras do WordsEn
        List<String> words = WordsEn.getWords();

        final String BG_GREEN = "\u001b[42m";
        final String BG_YELLOW = "\u001b[43m";
        final String RESET = "\u001b[0m";
        
        System.out.println("Wordle\n");
        
        // Escolher uma palavra aleatória da lista WordsEn
        int wIndex = random.nextInt(words.size()); // Corrigido p ara evitar IndexOutOfBoundsException
        String correct = words.get(wIndex);
        
        String guess = "";
        for (int round = 0; round < 6; round++) {
	        System.out.print("Guess > ");
	        guess = sc.nextLine().toUpperCase();
        		
            if (guess.length() != correct.length()) {
                System.out.println("Please enter a " + correct.length() + "-letter word.");
                round--;
                continue;
            }

            for (int i = 0; i < correct.length(); i++) {
                if (guess.charAt(i) == correct.charAt(i)) {
                    System.out.print(BG_GREEN + guess.charAt(i) + RESET);
                } else if (correct.contains(String.valueOf(guess.charAt(i)))) {
                    System.out.print(BG_YELLOW + guess.charAt(i) + RESET);
                } else {
                    System.out.print(guess.charAt(i));
                }
            }
            System.out.println("");

            if (guess.equals(correct)) {
                System.out.println("You win!!!");
                break;
            }
        }

        if (!guess.equals(correct)) {
            System.out.println("You lose! The correct answer is: " + correct);
        }

        sc.close();
    }
}
