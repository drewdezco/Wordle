import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 *
 * The purpose of this class is to:
 * - Display the game Wordle using a graphical interface
 * - Display the correct word at the end of the game
 * - If user guessed correctly, save score on scoreboard and display that to the user
 *
 * @author Julia Barnes
 * @author Sarah Harrington
 * @author Andrew Hernandez
 */
public class WordleGraphics{
    private int rowValue = 0; //counter to keep track of row value

    private int columnValue = 0; //counter to keep track of column value

    private  JFrame wordleFrame; //empty frame reference

    private JPanel wordlePanel = new JPanel(); //create new panel for letters

    private GridLayout wordleGrid = new GridLayout(6,5, 10, 10); //create new grid with 6 rows, five columns and 8x8 gap in between each grid

    private  JLabel wordleLabels[][] = new JLabel[6][5]; //create 2d array for storing labels

    private String userGuess[] = new String[5]; //1d array for user guess

    /**
     * 1 argument constructor for Wordle Graphics
     * @param wordleFrame Takes in JFrame parameter to populate JFrame variable
     */
    public WordleGraphics(JFrame wordleFrame) {
        this.wordleFrame = wordleFrame;
    }

    /**
     * Sets format to match an empty box at the start of the Wordle program for each label
     * @param label sets format for the label parameter given
     */
    public void setEmptyBoxFormat(JLabel label) { //empty
        wordleFrame.setContentPane(wordlePanel);
        Border labelBorder = BorderFactory.createLineBorder(Color.lightGray, 3);//gray border
        label.setOpaque(true); //to show background color
        label.setBorder(labelBorder); //set border to border style
        label.setBackground(new Color(255,255,255));//white background
        label.setForeground(Color.BLACK); //set text color to black
        label.setFont(new Font("Helvetica Neue", Font.BOLD, 60)); //set label font to match Wordle font and approximate size
        label.setText("");
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
     * Method used to set format option for each letter in specified column
     * @param option Sets format based on one of three options
     * @param column Sets format to specified column in the row
     */
    public void setColumnFormat(int option, int column) {
        switch (option) {
            case 1:setCorrectSpotAndCorrectLetterBoxFormat(wordleLabels[rowValue][column]); //set as the correct spot and letter box format
                break;
            case 2:setIncorrectSpotAndCorrectLetterBoxFormat(wordleLabels[rowValue][column]); //set as incorrect spot and correct letter box format
                break;
            case 3:setIncorrectSpotAndLetterBoxFormat(wordleLabels[rowValue][column]); //set incorrect spot and letter box format
                break;
        }
    }

    /**
     * Method used to set frame visibility to false for looping
     */
    public void setFrameToFalseVisibility() {
        wordleFrame.setVisible(false);
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
     * Method used to set user guess in each column
     */
    public void setUserGuess() {
        for (int i = 0; i < 5; i++) {
            userGuess[i] = wordleLabels[rowValue][i].getText();
        }
    }

    /**
     * Method used to get the users guess for comparison
     * @return the guess the user has input
     */
    public String[] getUserGuess() {
        return userGuess;
    }

    /**
     * Method used to add each label to the panel of 6x5
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
     * Method used to reset all labels at the start of a new game
     */
    public void resetLabels() {
        for (int i = 0; i < 6; i ++) { //rows
            for (int j = 0; j < 5; j++) { //columns
                setEmptyBoxFormat(wordleLabels[i][j]); //format each label to match empty box
            }
        }
    }

    /**
     * Method used to format the frame for Wordle
     */
    public void formatWordleFrame() {

        wordleFrame.add(wordlePanel); //add panel to frame

        wordleFrame.setPreferredSize(new Dimension(668, 815)); //set preferred frame size

        wordleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program ends when window closes

    }

    /**
     * Method to call functions to format frame, panel, and labels
     */
    public void setup() {
        formatWordleFrame(); //formate frame

        formatWordlePanel(); //format panels

        addLabelsToPanel(); //add labels to the panels
    }


    /**
     * Method used to get current column value
     * @return columnValue
     */
    public int getColumnValue() {
        return columnValue;
    }

    /**
     * Method used to get current row value
     * @return rowValue
     */
    public int getRowValue() {
        return rowValue;
    }

    /**
     * Method used to set current column value
     * @param columnValue new column value
     */
    public void setColumnValue(int columnValue) {
        this.columnValue = columnValue;
    }

    /**
     * Method used to set current row value
     * @param rowValue new row value
     */
    public void setRowValue(int rowValue) {
        this.rowValue = rowValue;
    }

    /**
     * Method used to get the JLabel array in order to
     * @return wordleLabels
     */
    public JLabel[][] getWordleLabels() {
        return wordleLabels;
    }

    /**
     * Method used to display simple popup message telling user to enter a valid guess
     */
    public void displayInvalidMessage() {
        String message = "Please enter a valid five-letter word";
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Method used to reset wordle game state
     */
    public void reset() {
        resetLabels();
        rowValue = 0;
        columnValue = 0;
    }
}