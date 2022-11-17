import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Julia Barnes
 *
 * This class plays a game of Wordle with the user. It chooses a random 5-letter word from the english
 * language, and allows the user 6 attempts to guess the secret word. After each user guess, feedback
 * is given regarding whether any of the letters are in the correct spot. If the user correctly guesses
 * the secret word, the game congratulates them and ends the program. If after 6 attempts the user has
 * not guessed correctly, the game ends and the secret word is revealed.
 *
 */

public class JuliaTextBasedWordle {
    /**
     * Chooses and returns a random lowercase word from an ArrayList of String words
     * @param words ArrayList of Strings
     * @return String random word
     */
    public static String pickTargetWord(ArrayList<String> words) {
        String word;
        Random rng = new Random();

        int number = rng.nextInt((words.size()+1));
        word = words.get(number);

        return word.toLowerCase();
    }


    /**
     * Gets a valid 5-letter word guess from the user, and returns the lowercase String.
     * @param words ArrayList of Strings
     * @return String user's 5-letter guess
     */
    public static String getUserGuess(ArrayList<String> words) {
        String guess;
        Scanner in = new Scanner(System.in);

        // get user guess
        System.out.print("What is your guess? ");
        guess = in.nextLine();

        // if invalid guess, ask user to guess again
        while (!words.contains(guess)) {
            System.out.println("Oops! Your guess must be a valid 5-letter word.");
            System.out.print("Please enter a new guess: ");
            guess = in.nextLine();
        }

        return guess.toLowerCase();
    }


    /**
     * Checks user's 5-letter guess against the 5-letter target word & outputs whether any letters
     * in the guess are in the target word, including whether they are in the correct position.
     * @param userGuess String word to be assessed for correctness
     * @param targetWord String word to be compared to
     * @return boolean true if user correctly guessed the word, otherwise false
     */
    public static boolean checkUserGuess(String userGuess, String targetWord) {
        boolean containsALetter = false;

        if (userGuess.equals(targetWord)) {
            System.out.println("Congratulations! You guessed the secret word!");
            return true;
        }

        // loop through userGuess to check each letter
        for (int i=0; i<userGuess.length(); i++) {
            String guessLetter = Character.toString(userGuess.charAt(i));
            if (guessLetter.equals(Character.toString(targetWord.charAt(i)))) {
                System.out.println("The " + userGuess.charAt(i) + " is right!");
                containsALetter = true;
            } else if (targetWord.contains(guessLetter)) {
                System.out.println("There is a " + userGuess.charAt(i) + ", but not here.");
                containsALetter = true;
            }
        }

        // tell the user if the word contains none of the letters
        if (!containsALetter) {
            System.out.println("None of these letters are in the word.");
        }

        return false;
    }

    /**
     * Functions as the Wordle game
     * @param args String[]
     */
    public static void main(String[] args) {
        FileReader reader;
        ArrayList<String> words = new ArrayList<>();

        // open the file
        try {
            reader = new FileReader("words.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file you tried to open could not be found.");
            return;
        }

        // make a scanner from the file
        Scanner fileIn = new Scanner(reader);

        // read in all words from the file into an ArrayList
        while (fileIn.hasNext()) {
            String word = fileIn.nextLine();
            words.add(word);
        }

        // gameplay
        System.out.println("Welcome to a knockoff Wordle game!");
        System.out.println("You have six tries to guess a five-letter word.");

        int guessCounter = 0;
        boolean userWon = false;
        String targetWord = pickTargetWord(words);
        while (guessCounter < 6 && !userWon) {
            System.out.println();
            String userGuess = getUserGuess(words);
            userWon = checkUserGuess(userGuess, targetWord);
            guessCounter++;
        }

        // tell user the secret word if they don't guess it correctly in 6 guesses
        if (!userWon) {
            System.out.println("Sorry, you didn't get it this time. The secret word is " + targetWord);
        }

    }
}
