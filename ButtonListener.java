import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The purpose of this class is to start a new Wordle++ game when the button is pressed.
 *
 * @author Julia Barnes
 * @author Sarah Harrington
 * @author Andrew Hernandez
 */
public class ButtonListener implements ActionListener {

    private JFrame frame;

    public ButtonListener(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Restarts Wordle++ gameplay when button is clicked.
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Main.resetGame();
        Main.setScoreboardFrameVisbilityToFalse();
    }
}
