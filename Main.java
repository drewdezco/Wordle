import javax.swing.*;
import java.io.File;

/**
 *Julia Barnes
 *Sarah Harrington
 *Andrew Hernandez
 *
 * The purpose of this class is to:
 * - Utilize other classes in file to have a fully functioning Wordle game with a graphical user interface
 */
public class Main {

    private static File wordsFile = new File("words.txt"); //create a new file that points to words.txt in project folder

    private static JFrame wordleFrame = new JFrame("Wordle++"); //create new frame with the name Wordle++

    private static JFrame scoreboardFrame = new JFrame("Scoreboard"); //create new scoreboard frame with name Scoreboard

    private static WordleGraphics wordleGraphicUserInterface = new WordleGraphics(wordleFrame); //create new Wordle GUI using wordle frame

    //objects of the other classes
    private static Scoreboard sb = new Scoreboard(scoreboardFrame); //create scoreboard using scoreboard frame
    private static WordleGame wordle = new WordleGame(wordleGraphicUserInterface, sb); //create new game of wordle using both GUIs

    /**
     * This method is used to set up the frames for the GUI, as well as set up the game for Wordle
     */
    public static void playGame() {
        wordle.setTargetWord(); //get a random word as the target word

        sb.setTargetWord(wordle.getTargetWord()); //set the target word for scoreboard

        System.out.println(wordle.getTargetWord()); //print the target word in order to help demonstration, usually this would be removed

        wordleGraphicUserInterface.setup(); //setup Wordle GUI

        wordleFrame.addKeyListener(wordle); //add keylistener to Wordle GUI

        wordleFrame.pack(); //display window

        wordleFrame.setVisible(true); //Set the window as visible.
    }

    /**
     * This method is used to reset the Wordle game state so that you can continue playing after the game has finished
     */
    public static void resetGame() {

        wordleGraphicUserInterface.reset(); //resets Wordle GUI

        wordle.setTargetWord(); //Sets new target word for game

        sb.setTargetWord(wordle.getTargetWord());

        wordle.resetTurnCounter(); //resets user's turn counter to determine score at the end if they win

        wordleFrame.setVisible(true); //sets frame visibility to true

        System.out.println(wordle.getTargetWord()); //print the target word in order to help demonstration, usually this would be removed
    }

    /**
     * Method used to set scoreboard's frame visibility to false for looping
     */
    public static void setScoreboardFrameVisbilityToFalse() {
        scoreboardFrame.setVisible(false); //sets scoreboard frame visibility to false
    }


    /**
     * Method used to run the program
     * @param args
     */
    public static void main(String[] args) {

        wordle.fillArrayList(wordsFile); //fills array with random words

        playGame(); //plays the game wordle
    }
}