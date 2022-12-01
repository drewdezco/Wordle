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

    private static File wordsFile = new File("words.txt");

    private static JFrame wordleFrame = new JFrame("Wordle++");

    private static JFrame scoreboardFrame = new JFrame("Scoreboard");

    private static WordleGraphics wordleGraphicUserInterface = new WordleGraphics(wordleFrame);

    //objects of the other classes
    private static Scoreboard sb = new Scoreboard(scoreboardFrame);
    private static WordleGame wordle = new WordleGame(wordleGraphicUserInterface, sb);


    public static void playGame() {
        wordle.setTargetWord();

        System.out.println(wordle.getTargetWord());

        // clear anything previously displayed in frame & reset wordleGUI
        //wordleFrame.getContentPane().removeAll();
        //wordleGraphicUserInterface.reset();

        wordleGraphicUserInterface.setup();

        wordleFrame.addKeyListener(wordle);

        wordleFrame.pack(); //display window

        wordleFrame.setVisible(true);

        //System.out.println(wordleGraphicUserInterface.askUserPlayAgain());
    }

    public static void resetGame() {
        wordleGraphicUserInterface.reset();
        wordle.setTargetWord();
        wordle.resetTurnCounter();
        wordleFrame.setVisible(true);
        System.out.println(wordle.getTargetWord());
    }

    public static void setScoreboardFrameVisbilityToFalse() {
        scoreboardFrame.setVisible(false);
    }


    public static void main(String[] args) {

        wordle.fillArrayList(wordsFile);

        playGame();
    }
}