import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

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

    }
}
