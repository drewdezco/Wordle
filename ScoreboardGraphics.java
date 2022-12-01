import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;


/**
 * The purpose of this class is to:
 * - display a scoreboard for users with the highest scores from a game of Wordle
 * - display a button allowing the user to play the game again
 *
 * @author Julia Barnes
 * @author Sarah Harrington
 * @author Andrew Hernandez
 */
public class ScoreboardGraphics {
    private final JFrame scoreboardFrame;
    private final JPanel scoreGrid = new JPanel(); // inner panel for scores
    private final JPanel scoreBoardGrid = new JPanel(); // outer panel to allow for empty border around scoreGrid
    private final JPanel buttons = new JPanel(); // panel for holding play again button
    private final GridLayout scoreLayout; // layout for scoreGrid
    private final GridLayout scoreBoardLayout = new GridLayout(1, 1); // layout for scoreBoardGrid

    private final JLabel headingLabel = new JLabel("Scoreboard");

    private final ArrayList<String> highScores;


    /**
     * Constructor for Scoreboard Graphics
     * @param scoreboardFrame JFrame for scoreboard to be drawn in
     * @param highScores Top scores, each entry containing a user's name and their score
     */
    public ScoreboardGraphics(JFrame scoreboardFrame, ArrayList<String> highScores) {
        this.scoreboardFrame = scoreboardFrame;
        this.highScores = highScores;
        this.scoreLayout = new GridLayout(highScores.size(), 1);
    }


    /**
     * Formats each score label's alignment, text color, background color, font, and border
     * @param label JLabel to be formatted
     */
    public void formatScoreLabel(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(108, 171, 59, 255));
        label.setForeground(Color.white);
        label.setBorder(new LineBorder(new Color(54, 86, 29, 255)));
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 25));
    }


    /**
     * Converts each String into a label and adds it to the grid of high scores. If there are no saved scores, creates
     * a label with a message indicating that.
     */
    public void addLabels() {
        if (highScores.size() == 0) {
            // no scores saved, create label to indicate
            JLabel label = new JLabel("No scores yet!");
            formatScoreLabel(label);
            scoreGrid.add(label);
        } else {
            // create label for each saved score
            for (String str : highScores) {
                JLabel label = new JLabel(str);
                formatScoreLabel(label);
                scoreGrid.add(label);
            }
        }
    }


    /**
     * Formats each JPanel to have the proper layout, and nests the two score-related Panels together
     */
    public void formatPanels() {
        scoreLayout.setVgap(5);
        scoreGrid.setLayout(scoreLayout);

        scoreBoardLayout.setHgap(5);
        scoreBoardGrid.setLayout(scoreBoardLayout);
        scoreBoardGrid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // nest the score panels
        scoreBoardGrid.add(scoreGrid);
    }


    /**
     * Formats the heading label's alignment and text size
     */
    public void formatHeadingLabel() {
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setFont(new Font(headingLabel.getFont().getName(), headingLabel.getFont().getStyle(), 50));
    }


    /**
     * Adds the heading and score labels to the scoreboard JFrame, and formats the JFrame to function properly.
     */
    public void formatScoreboardFrame() {
        // clear previous items from the frame
        scoreboardFrame.getContentPane().removeAll();

        // add vertical box layout for frame
        BoxLayout mainLayout = new BoxLayout(scoreboardFrame.getContentPane(), BoxLayout.Y_AXIS);
        scoreboardFrame.getContentPane().setLayout(mainLayout);

        // add scoreboard content to frame
        scoreboardFrame.getContentPane().add(headingLabel);
        scoreboardFrame.getContentPane().add(scoreBoardGrid);
        scoreboardFrame.getContentPane().add(buttons);

        // format frame itself
        scoreboardFrame.setPreferredSize(new Dimension(500, 400));
        scoreboardFrame.setVisible(true);
        scoreboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scoreboardFrame.pack();
    }


    /**
     * Calls the functions necessary to display the scoreboard.
     */
    public void setup() {
        formatScoreboardFrame();
        formatPanels();
        addLabels();
        formatHeadingLabel();
        addPlayAgainButton();
    }


    /**
     * Displays a popup message to the user notifying them of the target word and asking for their name. Returns the
     * entered text.
     * @return User's name. Null if user exits the popup window or presses the cancel button. Returns an empty
     * string if user presses ok without typing anything.
     */
    public static String getUserName(String targetWord) {
        String message = "The target word was: " + targetWord + ". Please enter a name to be saved with your score:";
        String name = JOptionPane.showInputDialog(message);
        return name.toLowerCase();
    }


    /**
     * Adds button beneath scoreboard that restarts gameplay when pressed.
     */
    public void addPlayAgainButton() {
        // add and format button object
        JButton playButton = new JButton("Play again");
        playButton.setPreferredSize(new Dimension(250, 125));
        playButton.setFont(new Font(playButton.getFont().getName(),Font.BOLD,25));
        playButton.addActionListener(new ButtonListener(scoreboardFrame));
        buttons.add(playButton);
    }
}
