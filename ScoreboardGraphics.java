import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

<<<<<<< HEAD
public class ScoreboardGraphics {
    //instances of the data from wordle to be used by the GUI
    private String targetWord;
    private ArrayList<String> guesses = new ArrayList<>();

    //method to control the window

    //method to control the text box

    //method to control the button(s)

    /**
     * Creates and displays a scoreboard in the window.
     * @param frame window to draw scoreboard in
     * @param highScores arraylist of strings
     */
    public void renderScoreboard(JFrame frame, ArrayList<String> highScores) {
        // panel and grid layout for scores
        JPanel scoreGrid = new JPanel();
        GridLayout scoreLayout = new GridLayout(highScores.size(),1);
        scoreLayout.setVgap(5);
        scoreGrid.setLayout(scoreLayout);

        // add each high score to the grid
        for (String str : highScores) {
            JLabel label = new JLabel(str);
            // format label
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBackground(new Color(108, 171, 59, 255));
            label.setForeground(Color.white);
            label.setBorder(new LineBorder(new Color(54, 86, 29, 255)));
            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 25));
            scoreGrid.add(label);
        }

        // create and format panel to allow for outside empty border
        JPanel scoreBoardGrid = new JPanel();
        GridLayout scoreBoardLayout = new GridLayout(1,1);
        scoreBoardLayout.setHgap(5);
        scoreBoardGrid.setLayout(scoreBoardLayout);
        scoreBoardGrid.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        scoreBoardGrid.add(scoreGrid);

        // vertical box layout for heading and scoreboard
        BoxLayout mainLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.getContentPane().setLayout(mainLayout);

        // add and format heading label
        JLabel headingLabel = new JLabel("Scoreboard");
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setFont(new Font(headingLabel.getFont().getName(), headingLabel.getFont().getStyle(), 50));

        // clear game components from window, then add heading and scoreboard
        frame.getContentPane().removeAll();
        frame.getContentPane().add(headingLabel);
        frame.getContentPane().add(scoreBoardGrid);

        // display window
        frame.setMinimumSize(new Dimension(500,400));
        frame.setVisible(true);

=======

/**
 * Julia Barnes
 * Sarah Harrington
 * Andrew Hernandez
 *
 * The purpose of this class is to display a scoreboard for users with the highest scores from a game.
 */
public class ScoreboardGraphics {
    private JFrame scoreboardFrame;
    private JPanel scoreGrid = new JPanel(); // inner panel for scores
    private JPanel scoreBoardGrid = new JPanel(); // outer panel to allow for empty border around scoreGrid
    private GridLayout scoreLayout; // layout for scoreGrid
    private GridLayout scoreBoardLayout = new GridLayout(1, 1); // layout for scoreBoardGrid

    JLabel headingLabel = new JLabel("Scoreboard");

    private ArrayList<String> highScores;


    /**
     * Constructor for Scoreboard Graphics
     * @param scoreboardFrame JFrame for scoreboard to be drawn in
     * @param highScores Arraylist of Strings, each entry consisting of a name and a score
     */
    public ScoreboardGraphics(JFrame scoreboardFrame, ArrayList<String> highScores) {
        this.scoreboardFrame = scoreboardFrame;
        this.highScores = highScores;
        this.scoreLayout = new GridLayout(highScores.size(), 1);
    }


    /**
     * Formats each score label
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
     * Converts each String into a label and adds it to the grid of high scores
     */
    public void addLabels() {
        for (String str : highScores) {
            JLabel label = new JLabel(str);
            formatScoreLabel(label);
            scoreGrid.add(label);
        }
    }


    /**
     * Formats each JPanel to have the proper layout, and nests the two Panels together
     */
    public void formatPanels() {
        scoreLayout.setVgap(5);
        scoreGrid.setLayout(scoreLayout);

        scoreBoardLayout.setHgap(5);
        scoreBoardGrid.setLayout(scoreBoardLayout);
        scoreBoardGrid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // nest the two panels
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

        // add scoreboard content
        scoreboardFrame.getContentPane().add(headingLabel);
        scoreboardFrame.getContentPane().add(scoreBoardGrid);

        // format frame
        scoreboardFrame.setMinimumSize(new Dimension(500, 400));
        scoreboardFrame.setVisible(true);
        scoreboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Calls the functions necessary to format the frame, labels, and panels needed to display the scoreboard.
     */
    public void setup() {
        formatScoreboardFrame();
        formatPanels();
        addLabels();
        formatHeadingLabel();
>>>>>>> origin/main
    }
}
