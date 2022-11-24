import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

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

    private WordleGraphics wordleGraphics;

    //default constructor
    public WordleGame(WordleGraphics wordleGraphics) {
        targetWord = null;
        this.wordleGraphics = wordleGraphics;
    }

    //parameterized constructor
    public WordleGame(String targetWord) {
        this.targetWord = targetWord;
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
     * @return String
     */
    public String generateRandomWord() {
        return fiveLetterWords.get(generateNumber(4500));
    }

    public void setTargetWord() {
        targetWord = generateRandomWord();
    }

    public String getTargetWord() {
        return targetWord;
    }

    /**
     * Checks if current user guess in row is in the list of possible words
     * @return true if it is in the list, false if it is not
     */
    public boolean checkValidGuess() { //functional AFAIK
        wordleGraphics.setUserGuess(); //set user input in current row

        String checkArray[] = wordleGraphics.getUserGuess();//set array equal to user guess

        String checkInWords = ""; //instantiate empty string

        for (int i = 0; i < 5; i++) { //input each letter in array
            checkInWords += checkArray[i];
        }

        //System.out.println(checkInWords);

        if (!fiveLetterWords.contains(checkInWords.toLowerCase())) { //if list of words does not contain input
            return false;
        }
        else { //if it does contain input
            return true;
        }

    }

    public void checkColumn() throws InterruptedException {
        if (checkValidGuess() == true) { //if userguess is a valid input
            targetWord = "waers";
            String checkArray[] = wordleGraphics.getUserGuess();//set array equal to user guess

            String checkInWords = ""; //instantiate empty string

            for (int i = 0; i < 5; i++) { //input each letter in array
                checkInWords += checkArray[i];
            }

            for (int i = 0; i < 5; i ++) {
                sleep(625);
                //System.out.print(checkArray[i] + " " + targetWord.charAt(i));
                if (Character.toString(targetWord.charAt(i)).equalsIgnoreCase(checkArray[i])) {
                    //System.out.print(": Equals!\n");
                    wordleGraphics.setColumnFormat(1, i);//format as correct letter correct spot
                    continue;
                }
                if (targetWord.contains(checkArray[i].toLowerCase())) {
                    //System.out.print(": Here but not here!\n");
                    wordleGraphics.setColumnFormat(2, i);//format as correct letter incorrect spot
                    continue;
                }
                else {
                    //System.out.print(": Not here period!!\n");
                    wordleGraphics.setColumnFormat(3, i);//format as incorrect letter incorrect spot
                }
            }
        }
        else {
            //at some point put in message stating to input a five letter word
        }
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