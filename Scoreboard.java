import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *Julia Barnes
 *Sarah Harrington
 *Andrew Hernandez
 *
 * The purpose of this class is to:
 * - Create a new scoreboard text file if one does not exist
 * - Read current high scores in file
 * - Write a new high score in with the correct position in the file
 * - Save changes to file
 */
public class Scoreboard {

    private int guesses;

    private Integer score;

    private String scoreboardPath = System.getProperty("user.dir") + "/highscores.txt";

    private File scoreboardFile;

    private Scanner scan;

    private HashMap<String, Integer> dataMap = new HashMap<>(); //map to contain read in file data

    private HashMap<String, Integer> updatedMap = new HashMap<>(); //map to sort and update the scoreboard

    private ArrayList<String> topKeys;

    private JFrame frame;

    private final int maxHighScores = 3;

    String targetWord;


    /**
     * Constructor that instantiates the frame.
     * @param frame JFrame reference to be passed to ScoreboardGraphics
     */
    public Scoreboard(JFrame frame){
        this.frame = frame;
    }

    public Scoreboard() {
    }

    /**
     * Sets the number of guesses the user took
     * @param numGuesses number of guesses
     */
    public void setGuesses(int numGuesses) {
        guesses = numGuesses;
    }


    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }


    /**
     * Create a file object with the universal path
     * Check if the file path already exists in the users directory
     * If not, create the scoreboard file
     */
    public void createScoreboard(){
        scoreboardFile = new File(scoreboardPath);

        boolean check = scoreboardFile.exists();

        try {
            if (!check) {
                scoreboardFile.createNewFile();
                //System.out.println("Scoreboard file created.");
            }
        }catch (IOException e){
            System.out.println("Error creating scoreboard file.");
        }
    }


    /**
     * Read in the lines of the scoreboard file and add them to a hash map for sorting and updating
     * Lines are split at the spaces between the username and score
     * Hash map key = String name
     * Hash map values = Integer score
     */
    public void readScoreboard(){
        String line;

        try {
            scan = new Scanner(scoreboardFile);

            while (scan.hasNext()) {
                line = scan.nextLine();
                //System.out.println(line);

                String[] dataArray = line.split(" ");

                dataMap.put(dataArray[0], Integer.valueOf(dataArray[1]));
            }
        }catch (IOException e){
            System.out.println("Error in reading scoreboard file.");
        }
    }


    /**
     * Calculate the users score based on their number of guesses
     */
    public Integer calculateScore(){

        //System.out.println("Guesses: " + guesses);

        if(guesses == 1){
            score = 10;
        }else if(guesses == 2){
            score = 8;
        }else if(guesses == 3){
            score = 6;
        }else if(guesses == 4){
            score = 4;
        }else if(guesses == 5){
            score = 2;
        }else if(guesses == 6){
            score = 0;
        }else{
            score = 0;
        }

        return score;
    }


    /**
     * Sort the scores in the scoreboard hash map
     * @param n how many high scores to record
     */
    public void sortScores(int n){

        // make an arraylist to store our winning players in
        topKeys = new ArrayList<>();

        // make updatedMap of hash map ( so we can delete elements as we pull highest )
        updatedMap = new HashMap<>(dataMap);

        // avoid attempting to add more scores to the arraylist than are actually stored
        if (n > dataMap.size()) {
            // stop once we get to the last score in dataMap
            n = dataMap.size();
        }

        // for the top x scores
        for(int i = 0; i < n; i++){
            Integer highestValue = -1;
            String highestKey = "";

            // go through all, find top score and remove it from map
            for(String s: updatedMap.keySet()){
                if(updatedMap.get(s) > highestValue){
                    highestValue = updatedMap.get(s);
                    highestKey = s;
                }
            }
            topKeys.add(highestKey);
            updatedMap.remove(highestKey);
        }
        //dataMap = updatedMap; //update the original map by making it equal to the sorted map
    }


    /**
     * Check if the users score is higher than the scores on the scoreboard
     * If it is, add it to the dataMap and re-sort
     */
    public void addHighScore(){

        //call the users score
        calculateScore();
        //System.out.println("Score: " + score);

        try {
            //boolean check to save if the users score belongs on the scoreboard
            boolean checkScore = false;

            // if no scores are stored, automatically add new score
            if (dataMap.isEmpty()) {
                checkScore = true;
            }

            //loop through the sorted hash map of high scores
            for (String s : dataMap.keySet()) {
                //if the users score is higher than the scores in the scoreboard, or we have less than the max number of scores stored
                if (score > dataMap.get(s) || dataMap.size() < maxHighScores) {
                    checkScore = true;
                }
            }

            //if the users score belongs on the scoreboard, take in their name to add
            if(checkScore){
                //take in user input for their name
                String name = ScoreboardGraphics.getUserName(targetWord);

                // if name exists already, update score
                if (dataMap.containsKey(name)) {
                    if (dataMap.get(name) < score) {
                        dataMap.put(name, score);
                    }
                } else if (name != null && !name.equals("")) {
                    // only add the score to the scoreboard hash map if the user actually entered a name
                    dataMap.put(name, score);
                }
            }

            //re-sort the score with the new score added
            sortScores(3);
            for(String s : dataMap.keySet()){
                System.out.println("Sorted map in addScore: " + s + " " + dataMap.get(s));
            }

        }catch (NullPointerException npe){
            System.out.println("Error: updated scoreboard map is empty.");
        }
    }


    /**
     * Write the updated scoreboard map back to the file
     */
    public void updateScoreboard(){
        try {
            FileWriter writeTo = new FileWriter(scoreboardFile);

            for(String s: topKeys) {
                writeTo.write(s + " " + dataMap.get(s) + "\n");
            }
            writeTo.close();


        }catch (IOException e){
            System.out.println("Error writing to scoreboard file.");
        }
    }


    /**
     * Prints the values of dataMap that contains the high scores
     */
    public void printMap(){

        for (String s : dataMap.keySet()){
            System.out.println(s + " : " + dataMap.get(s));
        }
    }


    /**
     * Converts the scoreboard hashmap to an array list for sending to the ScoreboardGraphics class
     */
    public void sendToGUI(){

        ArrayList<String> mapList = new ArrayList<>();

        // add sorted scores to arraylist
        for (String s : topKeys) {
            mapList.add(s + " : " + dataMap.get(s));
        }

        // create and load the scoreboard GUI
        ScoreboardGraphics sg = new ScoreboardGraphics(frame, mapList);
        sg.setup();
    }


    /**
     * Master scoreboard class, calls all necessary methods
     */
    public void masterScoreboard(){
        //once the game is over, call the scoreboard files
        createScoreboard(); //create a scoreboard file if one does not exist
        readScoreboard(); //read in the current high scores from the file
        calculateScore(); // calculate the users score based on guesses used
        addHighScore(); //check if users score is a high score, if so add to map and re-sort
        sendToGUI(); //convert scoreboard to array list, send to scoreboard GUI
        updateScoreboard(); //write the updated scoreboard back to the file
    }
}





class tester{

    private static Scoreboard sc;

    public static void main(String[] args){

        JFrame frame = new JFrame("Testing Scoreboard");
        sc = new Scoreboard(frame);
        sc.setGuesses(1);

        sc.createScoreboard();

        sc.readScoreboard();

        sc.calculateScore();

        sc.addHighScore();

        sc.updateScoreboard();





        //sc.masterScoreboard();
    }
}
