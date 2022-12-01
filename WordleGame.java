import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;


/**
 *Julia Barnes
 *Sarah Harrington
 *Andrew Hernandez
 * The purpose of this class is to:
 * - Give a player six guesses to guess a five-letter word that is chosen at random from a list
 * - Check to see if the word guessed is a real five-letter word
 * - After each guess, tell the player if they got any letters exactly right as well as right in the wrong position
 * - Provide the real word at the end of guesses or if they get it correctly
 */
public class WordleGame implements KeyListener {

    private final ArrayList<String> fiveLetterWords = new ArrayList<>(); //arraylist for storing five-letter words

    private static int turnCounter = 1;

    private String targetWord; //target word chosen at random variable

    private WordleGraphics wordleGraphics; //Graphics class to pass into wordle

    private Scoreboard sb = new Scoreboard();

    /**
     * Constructor for WordleGame to take in wordle graphics, and set target word to null
     * @param wordleGraphics Passes in wordle graphics class to set variable in class
     * @param sb instance of Scoreboard class
     */
    public WordleGame(WordleGraphics wordleGraphics, Scoreboard sb) {
        targetWord = null;
        this.wordleGraphics = wordleGraphics;
        this.sb = sb;
    }

    /**
     * Constructor to take in target word for class testing
     * @param targetWord Takes in string for target word variable
     */
    public WordleGame(String targetWord) {
        this.targetWord = targetWord;
    }

    /**
     * Generates a random number given a range
     * @param range int for determining number range of Math.random
     * @return int
     */
    public static int generateNumber(int range) {

        return (int)(Math.random() * range);
    }

    /**
     * Initializes file words using word file given
     * Fills arraylist with words from the file
     * Catches file not found exception and returns error message as well as ends program
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

    public void resetTurnCounter() {
        turnCounter = 1;
    }

    /**
     * Gets target word from the word list (specific range given in assignment)
     * Usually I would take the size of the arraylist for the random parameter
     * @return String
     */
    public String generateRandomWord() {
        return fiveLetterWords.get(generateNumber(4500));
    }

    /**
     * Setter method for target word in class
     */
    public void setTargetWord() {
        targetWord = generateRandomWord();
    }

    /**
     * Getter method for target word in class
     * @return targetWord String
     */
    public String getTargetWord() {
        return targetWord;
    }

    /**
     * Checks if current user guess in row is in the list of possible words
     * @return true if it is in the list, false if it is not
     */
    public boolean checkValidGuess() { //functional AFAIK
        wordleGraphics.setUserGuess(); //set user input in current row

        String[] checkArray = wordleGraphics.getUserGuess();//set array equal to user guess

        StringBuilder checkInWords = new StringBuilder(); //instantiate empty string

        for (int i = 0; i < 5; i++) { //input each letter in array
            checkInWords.append(checkArray[i]);
        }

        //System.out.println(checkInWords);

        return fiveLetterWords.contains(checkInWords.toString().toLowerCase()); //if it contains return true, else false

    }

    /**
     * Checks if user guess is valid, if it is valid then it creates array to compare user guess with actual target word.
     * Depending on comparison, formats the column appropriately
     */
    public void checkColumn()  {

        if (checkValidGuess()) { //if user guess is a valid input
            String[] checkArray = wordleGraphics.getUserGuess();//set array equal to user guess

            StringBuilder checkInWords = new StringBuilder(); //instantiate empty string

            for (int i = 0; i < 5; i++) { //input each letter in array
                checkInWords.append(checkArray[i]);
            }

            if(targetWord.equalsIgnoreCase(String.valueOf(checkInWords))) { //if target word is equal to user guess
                System.out.println("You guessed the correct word in " + turnCounter + " guesses!");
                // load the scoreboard
                wordleGraphics.setFrameToFalseVisibility();
                sb.setGuesses(turnCounter);
                sb.masterScoreboard();
            }

            for (int i = 0; i < 5; i ++) {

                //System.out.print(checkArray[i] + " " + targetWord.charAt(i));
                if (Character.toString(targetWord.charAt(i)).equalsIgnoreCase(checkArray[i])) {
                    //System.out.print(": Equals!\n");
                    wordleGraphics.setColumnFormat(1, i);//format as correct letter correct spot
                    continue;
                }
                if (targetWord.contains(checkArray[i].toLowerCase())) {
                    //System.out.print(": Here but not here!\n");
                    wordleGraphics.setColumnFormat(2, i);//format as correct letter incorrect spot
                }
                else {
                    //System.out.print(": Not here period!!\n");
                    wordleGraphics.setColumnFormat(3, i);//format as incorrect letter incorrect spot
                }
            }
            turnCounter++; //adds to turn counter after checking column successfully
        }
        else {
            // message stating to input a five-letter word
            // this line is never reached, because this method is only called when the guess is valid
            wordleGraphics.displayInvalidMessage();
        }
    }

    /**
     * Empty override method that is unused
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Reads keycodes to determine which key is pressed.
     * Populates GUI with key pressed if A-Z
     * Removes characters if backspace is pressed
     * Moves to new row if row is filled
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 8) { // if backspace is pressed

            if (wordleGraphics.getColumnValue()==5) { //if at the end of the current row
                wordleGraphics.setColumnValue(wordleGraphics.getColumnValue()-1);//subtract one to end up in correct column
            }
            if (wordleGraphics.getWordleLabels()[wordleGraphics.getRowValue()][wordleGraphics.getColumnValue()].getText().equals("") && wordleGraphics.getColumnValue() != 0) { //if column is empty and not the first column in the row
                wordleGraphics.setColumnValue(wordleGraphics.getColumnValue()-1);//move back one column
            }
            if (wordleGraphics.getWordleLabels()[wordleGraphics.getRowValue()][wordleGraphics.getColumnValue()].getText().length() > 0) { //if column has something in it
                wordleGraphics.setEmptyBoxFormat(wordleGraphics.getWordleLabels()[wordleGraphics.getRowValue()][wordleGraphics.getColumnValue()]); //set to empty box format for that indices
                wordleGraphics.getWordleLabels()[wordleGraphics.getRowValue()][wordleGraphics.getColumnValue()].setText(""); //set text to empty
            }
        }
        //System.out.println(e);

        else { //otherwise key pressed is not backspace

            if (wordleGraphics.getColumnValue() < 5) { //if its columns 0-4
                if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) { //get which key is pressed (A-Z || 65-90)
                    wordleGraphics.getWordleLabels()[wordleGraphics.getRowValue()][wordleGraphics.getColumnValue()].setText(Character.toString(Character.toUpperCase(e.getKeyChar()))); //populate label with uppercase version of that key
                    wordleGraphics.setUncheckedBoxFormat(wordleGraphics.getWordleLabels()[wordleGraphics.getRowValue()][wordleGraphics.getColumnValue()]); //Change format to reflect a letter is in the box
                    wordleGraphics.setColumnValue(wordleGraphics.getColumnValue()+1);//move forward one column
                }
                //System.out.println(columnValue);
            }

            if (wordleGraphics.getColumnValue() == 5 && wordleGraphics.getRowValue() != 5) { //if column is at 5
                if (e.getKeyCode()==10) { //if enter is pressed
                    //System.out.println("You pressed enter");
                    if (checkValidGuess()) { //if user guess is a valid guess
                        checkColumn(); //check current column

                        wordleGraphics.setRowValue(wordleGraphics.getRowValue()+1); //move one row down
                        wordleGraphics.setColumnValue(0); //move column to first in the row
                    }
                    else {
                        // display message stating to put in correct input
                        wordleGraphics.displayInvalidMessage();
                        System.out.println("Input correct message");
                        //System.out.println("Input correct message");
                    }

                }
            }

            if (wordleGraphics.getColumnValue() == 5 && wordleGraphics.getRowValue() == 5) {
                if (e.getKeyCode()==10) {
                    if (checkValidGuess() == true) {
                        checkColumn();

                        wordleGraphics.setFrameToFalseVisibility();
                        //show scoreboard here
                        sb.setGuesses(turnCounter);
                        sb.masterScoreboard();
                    }
                }
            }
        }
    }

    /**
     * Empty override method that is unused
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
