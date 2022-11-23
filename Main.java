import javax.swing.*;

/**
 *Julia Barnes
 *Sarah Harrington
 *Andrew Hernandez
 *
 * The purpose of this class is to:
 * - Utilize other classes in file to have a fully functioning Wordle game with a graphical user interface
 */
public class Main {

    private static JFrame wordleFrame = new JFrame("Wordle++");

    //objects of the other classes
    private static WordleGame wordle = new WordleGame();

    public static void main(String[] args) {
        WordleGraphics wordleGraphicUserInterface = new WordleGraphics(wordleFrame);

        wordleGraphicUserInterface.setup();

        wordleFrame.addKeyListener(wordleGraphicUserInterface);

        wordleFrame.pack(); //display window

        wordleFrame.setVisible(true);

    }
}