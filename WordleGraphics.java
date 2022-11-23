import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *Julia Barnes
 *Sarah Harrington
 *Andrew Hernandez
 *
 * The purpose of this class is to:
 * - Display the game Wordle using a graphical interface
 * - Display the correct word at the end of the game
 * - If user guessed correctly, save score on scoreboard and display that to the user
 */
public class WordleGraphics implements KeyListener {

    private int rowValue = 0;
    private int columnValue = 0;
    private  JFrame wordleFrame;

    public WordleGraphics(JFrame wordleFrame) {
        this.wordleFrame = wordleFrame;
    }

    private JPanel wordlePanel = new JPanel(); //create new panel for letters

    private GridLayout wordleGrid = new GridLayout(6,5, 10, 10); //create new grid with 6 rows, five columns and 8x8 gap in between each grid

    private  JLabel wordleLabels[][] = new JLabel[6][5]; //create 2d array for storing labels


    public void setEmptyBoxFormat(JLabel label) { //empty
        Border labelBorder = BorderFactory.createLineBorder(Color.lightGray, 3);//gray border
        label.setOpaque(true);
        label.setBorder(labelBorder);
        label.setBackground(new Color(255,255,255));//white background
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Helvetica Neue", Font.BOLD, 60));
    }

    public void setUncheckedBoxFormat(JLabel label) { //letter unchecked
        Border labelBorder = BorderFactory.createLineBorder(new Color(135,138,140), 3);//gray border
        label.setBorder(labelBorder);
        label.setBackground(new Color(255,255,255));//white background
        label.setForeground(Color.BLACK);
    }

    public void setCorrectSpotAndCorrectLetterBoxFormat(JLabel label) { //green
        Border labelBorder = BorderFactory.createLineBorder(Color.WHITE, 0); //For checked letters
        label.setBorder(labelBorder);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(113,170,97)); //green
    }

    public void setIncorrectSpotAndCorrectLetterBoxFormat(JLabel label) { //orange
        Border labelBorder = BorderFactory.createLineBorder(Color.WHITE, 0); //For checked letters
        label.setBorder(labelBorder);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(198,180,81)); //orange
    }

    public void setIncorrectSpotAndLetterBoxFormat(JLabel label) { //dark gray
        Border labelBorder = BorderFactory.createLineBorder(Color.WHITE, 0); //For checked letters
        label.setBorder(labelBorder);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(120, 124, 126));
    }

    public void formatWordlePanel() {

        wordlePanel.setBackground(Color.white); //set panel to white background

        wordlePanel.setLayout(wordleGrid); //give panel grid layout

        Border wordlePanelBorder = BorderFactory.createLineBorder(Color.WHITE, 10); //set border on panel to 10 for visual clarity

        wordlePanel.setBorder(wordlePanelBorder); //set panel border to wordlePanelBorder

    }

    public void addLabelsToPanel() {
        for (int i = 0; i < 6; i ++) {
            for (int j = 0; j < 5; j++) {
                JLabel emptyLabel = new JLabel("",SwingConstants.CENTER);
                setEmptyBoxFormat(emptyLabel);
                wordleLabels[i][j] = emptyLabel;
                wordlePanel.add(wordleLabels[i][j]);
            }
        }
    }

    public void formatWordleFrame() {

        wordleFrame.add(wordlePanel);

        wordleFrame.setPreferredSize(new Dimension(668, 815));

        wordleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program ends when window closes

    }

    public void setup() {
        formatWordleFrame();

        formatWordlePanel();

        addLabelsToPanel();
    }

    public static void main(String[] args) {

        /*

        formatWordleFrame();

        formatWordlePanel();

        addLabelsToPanel();

        WordleGraphics myTest = new WordleGraphics();

        wordleFrame.addKeyListener(myTest);


        wordleFrame.pack(); //display window

        wordleFrame.setVisible(true);

         */

    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 8) {
            if (columnValue==5) {
                columnValue--;
            }
            if (wordleLabels[rowValue][columnValue].getText().equals("") && columnValue != 0) {
                columnValue--;
            }
            if (wordleLabels[rowValue][columnValue].getText().length() > 0) {
                setEmptyBoxFormat(wordleLabels[rowValue][columnValue]);
                wordleLabels[rowValue][columnValue].setText("");
            }
        }
        //System.out.println(e);

        else {

            if (columnValue < 5) {
                if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) {
                    wordleLabels[rowValue][columnValue].setText(Character.toString(Character.toUpperCase(e.getKeyChar())));
                    setUncheckedBoxFormat(wordleLabels[rowValue][columnValue]);
                    columnValue++;
                }
                //System.out.println(columnValue);
            }
            if (columnValue == 5) {
                if (e.getKeyCode()==10) {
                    //System.out.println("You pressed enter");
                    rowValue++;
                    columnValue = 0;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}