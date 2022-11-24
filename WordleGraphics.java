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

    private int rowValue = 0; //counter to keep track of row value
    private int columnValue = 0; //counter to keep track of column value
    private  JFrame wordleFrame; //empty frame reference

    /**
     * 1 argument constructor for Wordle Graphics
     * @param wordleFrame Takes in JFrame parameter to populate JFrame variable
     */
    public WordleGraphics(JFrame wordleFrame) {
        this.wordleFrame = wordleFrame;
    }

    private JPanel wordlePanel = new JPanel(); //create new panel for letters

    private GridLayout wordleGrid = new GridLayout(6,5, 10, 10); //create new grid with 6 rows, five columns and 8x8 gap in between each grid

    private  JLabel wordleLabels[][] = new JLabel[6][5]; //create 2d array for storing labels


    /**
     * Sets format to match an empty box at the start of the Wordle program for each label
     * @param label sets format for the label parameter given
     */
    public void setEmptyBoxFormat(JLabel label) { //empty
        Border labelBorder = BorderFactory.createLineBorder(Color.lightGray, 3);//gray border
        label.setOpaque(true); //to show background color
        label.setBorder(labelBorder); //set border to border style
        label.setBackground(new Color(255,255,255));//white background
        label.setForeground(Color.BLACK); //set text color to black
        label.setFont(new Font("Helvetica Neue", Font.BOLD, 60)); //set label font to match Wordle font and approximate size
    }

    /**
     * Sets format to match a box with a character input that has not been checked yet
     * @param label sets format for the label parameter given
     */
    public void setUncheckedBoxFormat(JLabel label) { //letter unchecked
        Border labelBorder = BorderFactory.createLineBorder(new Color(135,138,140), 3);//gray border
        label.setBorder(labelBorder);
        label.setBackground(new Color(255,255,255));//white background
        label.setForeground(Color.BLACK);
    }

    /**
     * Sets format to match a box that has been checked and is both correct in letter and placement
     * @param label sets format for the label parameter given
     */
    public void setCorrectSpotAndCorrectLetterBoxFormat(JLabel label) { //green
        Border labelBorder = BorderFactory.createLineBorder(Color.WHITE, 0); //For checked letters
        label.setBorder(labelBorder);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(113,170,97)); //green
    }

    /**
     * Sets format to match a box that has been checked and is correct in letter / incorrect in placement
     * @param label sets format for the label parameter given
     */
    public void setIncorrectSpotAndCorrectLetterBoxFormat(JLabel label) { //orange
        Border labelBorder = BorderFactory.createLineBorder(Color.WHITE, 0); //For checked letters
        label.setBorder(labelBorder);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(198,180,81)); //orange
    }

    /**
     * Sets format to match a box that has been checked and is both incorrect in letter and placement
     * @param label sets format for the label parameter given
     */
    public void setIncorrectSpotAndLetterBoxFormat(JLabel label) { //dark gray
        Border labelBorder = BorderFactory.createLineBorder(Color.WHITE, 0); //For checked letters
        label.setBorder(labelBorder);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(120, 124, 126));
    }

    /**
     * Sets format for panel to be added into the frame
     */
    public void formatWordlePanel() {

        wordlePanel.setBackground(Color.white); //set panel to white background

        wordlePanel.setLayout(wordleGrid); //give panel grid layout

        Border wordlePanelBorder = BorderFactory.createLineBorder(Color.WHITE, 10); //set border on panel to 10 for visual clarity

        wordlePanel.setBorder(wordlePanelBorder); //set panel border to wordlePanelBorder

    }

    /**
     * Adds each label to the panel of 6x5
     */
    public void addLabelsToPanel() {
        for (int i = 0; i < 6; i ++) { //rows
            for (int j = 0; j < 5; j++) { //columns
                JLabel emptyLabel = new JLabel("",SwingConstants.CENTER); //make each label center and empty
                setEmptyBoxFormat(emptyLabel); //format each label to match empty box
                wordleLabels[i][j] = emptyLabel; //set the new empty label to the correct indices
                wordlePanel.add(wordleLabels[i][j]); //add label to panel
            }
        }
    }

    /**
     * Formats frame for Wordle
     */
    public void formatWordleFrame() {

        wordleFrame.add(wordlePanel);

        wordleFrame.setPreferredSize(new Dimension(668, 815));

        wordleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program ends when window closes

    }

    /**
     * Calls functions to format frame, panel, and labels
     */
    public void setup() {
        formatWordleFrame();

        formatWordlePanel();

        addLabelsToPanel();
    }

    /**
     * Used to test the class, more than likely this will be deleted.
     * @param args Nothing here
     */

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


    /**
     * Empty override method that is unused
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Reads keycodes to determine which key is pressed.
     * Populates GUI with keypressed if A-Z
     * Removes characters if backspace is pressed
     * Moves to new row if row is filled
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(rowValue + " : " + columnValue);
        //System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 8) { // if backspace is pressed

            if (columnValue==5) { //if at the end of the current row
                columnValue--; //subtract one to end up in correct column
            }
            if (wordleLabels[rowValue][columnValue].getText().equals("") && columnValue != 0) { //if column is empty and not the first column in the row
                columnValue--; //move back one column
            }
            if (wordleLabels[rowValue][columnValue].getText().length() > 0) { //if column has something in it
                setEmptyBoxFormat(wordleLabels[rowValue][columnValue]); //set to empty box format for that indices
                wordleLabels[rowValue][columnValue].setText(""); //set text to empty
            }
        }
        //System.out.println(e);

        else { //otherwise keypressed is not backspace

            if (columnValue < 5) { //if its columns 0-4
                if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) { //get which key is pressed (A-Z || 65-90)
                    wordleLabels[rowValue][columnValue].setText(Character.toString(Character.toUpperCase(e.getKeyChar()))); //populate label with uppercase version of that key
                    setUncheckedBoxFormat(wordleLabels[rowValue][columnValue]); //Change format to reflect a letter is in the box
                    columnValue++;//move forward one column
                }
                //System.out.println(columnValue);
            }
            if (columnValue == 5 && rowValue != 5) { //if column is at 5
                if (e.getKeyCode()==10) { //if enter is pressed
                    //System.out.println("You pressed enter");
                    rowValue++; //move one row down
                    columnValue = 0; //move column to first in the row
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