import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import javax.swing.*;

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

    private HashMap<String, Integer> updatedMap; //map to sort and update the scoreboard

    /**
     * Default constructor that instantiates the number of guesses to null (0)
     */
    public void Scoreboard(){
        guesses = 0;
    }

    /**
     * Parameterized constructor that instantiates the number of guesses
     * @param i score Integer passed from WordleGame after a player finishes the game
     */
    public void Scoreboard(int i){
        guesses = i;
    }

    //create a scoreboard file if one does not exist
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

    //read in the file data
    public void readScoreboard(){
        String line;

        try {
            scan = new Scanner(scoreboardFile);

            while (scan.hasNext()) {
                line = scan.nextLine();
                //System.out.println(line);

                String[] updatedMap = line.split(" ");
                //System.out.println(updatedMap[0] + " " + updatedMap[1]);

                dataMap.put(updatedMap[0], Integer.valueOf(updatedMap[1]));
            }
        }catch (IOException e){
            System.out.println("Error in reading scoreboard file.");
        }
    }

    /**
     * Calculate the users score based on their number of guesses
     */
    public void calculateScore(){

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
    }

    //sort the scoreboard
    public void sortScores(int n){

        // make an arraylist to store our winning players in
        ArrayList<String> topKeys = new ArrayList<>();

        // make updatedMap of hash map ( so we can delete elements as we pull highest )
        updatedMap = dataMap;
        // for the top x scores
        for(int i = 0; i < n; i++){
            Integer highestValue = 0;
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
        dataMap = updatedMap;
    }

    //print the keys and values of the hash map
    public void printMap(){
        for (String s : dataMap.keySet());
    }

    //write new high scores in
    public void addHighScore(){
        Scanner scanName = new Scanner(System.in);

        //call the users score
        calculateScore();

        try {
            //loop through the sorted hash map of high scores
            for (String s : updatedMap.keySet()) {
                //if the users score is higher than the scores in the scoreboard
                if (score > updatedMap.get(s)) {
                    //take in user input for their name
                    System.out.println("Input a name for the scoreboard: ");
                    String name = scanName.nextLine();
                    updatedMap.put(name, score);
                }
            }

            //re-sort the score with the new score added
            sortScores(3);
            dataMap = updatedMap;

        }catch (NullPointerException npe){
            System.out.println("Error: updated scoreboard map is empty.");
        }
    }

    //write the updated scoreboard hashmap back to the file
    public void updateScoreboard(){
        try {
            FileWriter writeTo = new FileWriter(scoreboardFile);

            for(String s: updatedMap.keySet()){
                writeTo.write(s + " " + updatedMap.get(s));
                writeTo.close();
            }
        }catch (IOException e){
            System.out.println("Error writing to scoreboard file.");
        }
    }

    /**
     * Converts the scoreboard hashmap to an array list for sending to the 
     * ScoreboardGraphics class
     */
    public void sendToGUI(){

        ArrayList<String> mapList = new ArrayList<>();
        
        for (String s: dataMap.keySet()){
            mapList.add(s + " " + dataMap.get(s));
        }

        JFrame scoreboardFrame = new JFrame("Scoreboard");

        ScoreboardGraphics sg = new ScoreboardGraphics(scoreboardFrame, mapList);
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
    }
}

class tester{

    private static Scoreboard sc = new Scoreboard();

    public static void main(String[] args){
        sc.masterScoreboard();
    }


}
