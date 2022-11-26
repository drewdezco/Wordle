import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class ScoreboardGraphics {
    private JFrame scoreboardFrame;
    private JPanel scoreGrid = new JPanel(); // inner panel for scores
    private JPanel scoreBoardGrid = new JPanel(); // outer panel to allow for empty border around scoreGrid
    private GridLayout scoreLayout; // layout for scoreGrid
    private GridLayout scoreBoardLayout = new GridLayout(1, 1); // layout for scoreBoardGrid

    JLabel headingLabel = new JLabel("Scoreboard");

    private ArrayList<String> highScores;


    // constructor
    public ScoreboardGraphics(JFrame scoreboardFrame, ArrayList<String> highScores) {
        this.scoreboardFrame = scoreboardFrame;
        this.highScores = highScores;
        this.scoreLayout = new GridLayout(highScores.size(), 1);
    }


    // format score labels
    public void formatScoreLabel(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(108, 171, 59, 255));
        label.setForeground(Color.white);
        label.setBorder(new LineBorder(new Color(54, 86, 29, 255)));
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 25));
    }


    // add labels (scores) to the grid
    public void addLabels() {
        for (String str : highScores) {
            JLabel label = new JLabel(str);
            formatScoreLabel(label);
            scoreGrid.add(label);
        }
    }


    // format Panels
    public void formatPanels() {
        scoreLayout.setVgap(5);
        scoreGrid.setLayout(scoreLayout);

        scoreBoardLayout.setHgap(5);
        scoreBoardGrid.setLayout(scoreBoardLayout);
        scoreBoardGrid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // nest the two panels
        scoreBoardGrid.add(scoreGrid);
    }


    // format heading label
    public void formatHeadingLabel() {
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setFont(new Font(headingLabel.getFont().getName(), headingLabel.getFont().getStyle(), 50));
    }


    // format frame for the scoreboard
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


    // call functions needed to format frame, labels, and panels
    public void setup() {
        formatScoreboardFrame();
        formatPanels();
        addLabels();
        formatHeadingLabel();
    }
}
