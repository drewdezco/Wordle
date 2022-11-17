import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * - Andrew Hernandez
 * The purpose of this class is a game that:
 * A player has six guesses and must guess a five-letter word that is chosen at random from a list
 * Check to see if the word guessed is a real five-letter word
 * After each guess, tell the player if they got any letters exactly right as well as right in the wrong position
 * Provide the real word at the end of guesses or if they get it correctly
 */
class AndrewTextBasedWordle {

    static ArrayList<String> fiveLetterWords = new ArrayList<>();

    static String targetWord, userGuess;

    /**
     * Used to run our program through utilization of class methods.
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String args[]) {

        File words = new File("words.txt");

        fillArrayList(words);

        targetWord = getTargetWord(fiveLetterWords);

        readGuess();

    }

    /**
     * Generates a random number given a range
     * @param range
     * @return int
     */
    public static int generateNumber(int range) {

        return (int)(Math.random() * range);
    }

    /**
     * Initializes file words using word file given
     * Fills arraylist with words from the file
     * Catches file not found exception and returns error message as well as ends program
     * @throws FileNotFoundException
     */
    public static void fillArrayList(File filename) {
        try {
            Scanner scan = new Scanner(filename);
            while (scan.hasNext()) {
                fiveLetterWords.add(scan.next());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
            exit(0);
        }
    }

    /**
     * Gets target word from the word list (specific range given in assignment)
     * Usually I would take the size of the arraylist for the random parameter
     * @param yourList
     * @return String
     */
    public static String getTargetWord(ArrayList<String> yourList) {
        return yourList.get(generateNumber(4500));
    }

    /**
     * Takes in user guess, checks to see if the guess is valid
     * If the guess is valid, compares the two words using the method
     * Determines which letters are contained/what positions for letters are correct
     * @param
     */
    public static void readGuess() {
        Scanner scan = new Scanner(System.in);
        int numberOfGuesses = 0;
        System.out.println("You have six tries to guess a five-letter word.\n");

        while (numberOfGuesses < 6) {
            System.out.print("What is your guess? ");
            String guess = scan.next();
            if (guess.equals(targetWord)) {
                System.out.println("\nThe word was " + guess + "!\nYou won on guess number " + (numberOfGuesses + 1) + "!");
                exit(0);
            }
            if (!fiveLetterWords.contains(guess)) {
                System.out.println("Please enter a real five-letter word.\n");
                continue;
            }
            else {
                for (int i = 0; i <5; i++) {
                    if (targetWord.indexOf(guess.charAt(i)) == -1) {

                    }
                    else {
                        if (guess.charAt(i) == targetWord.charAt(i)) {
                            System.out.println("The " + guess.charAt(i) + " is right!");
                        }
                        else {
                            System.out.println("There is a " + guess.charAt(i) + " but not here.");
                        }

                    }
                }
                System.out.println();
                numberOfGuesses++;

            }
        }
        System.out.print("Sorry, the word is ");
        System.out.print(targetWord);

    }

}


