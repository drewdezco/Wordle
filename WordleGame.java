import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 *Julia Barnes
 *Sarah Harrington
 *Andrew Hernandez
 *
 * The purpose of this class is to:
 * - Give a player six guesses to guess a five-letter word that is chosen at random from a list
 * - Check to see if the word guessed is a real five-letter word
 * - After each guess, tell the player if they got any letters exactly right as well as right in the wrong position
 * - Provide the real word at the end of guesses or if they get it correctly
 */
public class WordleGame {

    private ArrayList<String> fiveLetterWords = new ArrayList<>();

    //take in target word as instance field
    private String targetWord;

    //default constructor
    public WordleGame() {
        targetWord = null;
    }

    //parameterized constructor
    public WordleGame(String t) {
        targetWord = t;
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
    public void fillArrayList(File filename) {
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


    //method to run through wordle game
    public void playWordle() {

        //call GUI so it is called when the game play is called

        //iterate through 6 times (6 guesses)

        //take in user input as guess word

        //compare guess word to target word

        //return correct letters

        //return letters in the target but in the incorrect spot

        //return incorrect letters

        //handle guesses of incorrect length, do not count as a guess

    }

}