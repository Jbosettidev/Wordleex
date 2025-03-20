package worldeex;

import java.util.*;

public class Wordle {
    public static void main(String[] agrs) {
        Scanner sc = new Scanner(System.in);
        String fullGame = Game.gameMidlle(sc);
        String tryAgain = PlayAgain.again(sc,fullGame);
    }
}

class Game{
    public static String gameMidlle(Scanner sc) {
        
        Random random = new Random();
        
        List<String> words = WordsEn.getWords();
        Set<Character> wordsTyped = new HashSet<>(); 

        final String BG_GREEN = "\u001b[42m";
        final String BG_YELLOW = "\u001b[43m";
        final String RESET = "\u001b[0m";
        
        System.out.println("Wordle\n");
        
        int wIndex = random.nextInt(words.size());
        String correct = words.get(wIndex);
        
        String guess = "";
        for (int round = 0; round < 5; round++) {
            System.out.print("Guess => ");
            guess = sc.nextLine().toUpperCase();
            
            if (guess.length() != correct.length()) {
                System.out.println("Please enter a " + correct.length() + "-letter word.");
                round--;
                continue;
            }

            for (int i = 0; i < guess.length(); i++) {
                wordsTyped.add(guess.charAt(i));
            }

            String correctCopy = correct;
            for (int i = 0; i < correct.length(); i++) {
                if (guess.charAt(i) == correct.charAt(i)) {
                    System.out.print(BG_GREEN + guess.charAt(i) + RESET);
                    correctCopy = correctCopy.substring(0, i) + "-" + correctCopy.substring(i + 1);
                } else if (correctCopy.contains(String.valueOf(guess.charAt(i)))) {
                    System.out.print(BG_YELLOW + guess.charAt(i) + RESET);
                    correctCopy = correctCopy.replaceFirst(String.valueOf(guess.charAt(i)), "-");
                } else {
                    System.out.print(guess.charAt(i));
                }
            }
            System.out.println("");

            System.out.println("\nLetters typed until now:");
            for (Character letra : wordsTyped) {
                System.out.print(letra + " ");
            }
            System.out.println("\n");

            if (guess.equals(correct)) {
                System.out.println("You win!!!");
                break;
            }
        }

        if (!guess.equals(correct)) {
            System.out.println("You lose! The correct answer is: " + correct);
        }
        return guess;
    }
}
class PlayAgain{
    public static String again(Scanner sc, String fullGame) {
        System.out.println("Do you wanna play again? ");
        String wannaPlay = sc.next();
        switch (wannaPlay) {
            case "yes" -> Game.gameMidlle(sc);
        }
        sc.close();
        return null;
    }
}