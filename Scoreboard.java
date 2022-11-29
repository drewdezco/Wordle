import java.io.*;
import java.util.*;

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

    private Integer score;

    private HashMap<String, Integer> scoreboardMap = new HashMap<>();

    private String scoreboardPath;

    private File scoreboardFile;

    private Scanner scan;

    private HashMap<String, Integer> dataMap = new HashMap<>();

    /**
     * Constructor that instantiates the path for the scoreboard file
     */
    public void Scoreboard(){
      scoreboardPath = System.getProperty("user.dir") + "/highscores.txt";
    }

    /**
     * Constructor that instantiates the score value
     * @param i score Integer passed from WordleGame after a player finishes the game
     */
    public void Scoreboard(Integer i){
        score = i;
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

                String[] temp = line.split(" ");
                //System.out.println(temp[0] + " " + temp[1]);

                dataMap.put(temp[0], Integer.valueOf(temp[1]));
            }
        }catch (IOException e){
            System.out.println("Error in reading scoreboard file.");
        }
    }

    //sort the scoreboard
    public void sortScores(int n){
        // make an arraylist to store our winning players in
        ArrayList<String> topKeys = new ArrayList<>();
        // make temp of hash map ( so we can delete elements as we pull highest )
        HashMap<String, Integer> temp = dataMap;
        // for the top x scores
        for(int i = 0; i < n; i++){
            Integer highestValue = 0;
            String highestKey = "";

            // go through all, find top score and remove it from map
            for(String s: temp.keySet()){
                if(temp.get(s) > highestValue){
                    highestValue = temp.get(s);
                    highestKey = s;
                }
            }
            topKeys.add(highestKey);
            temp.remove(highestKey);
        }
        for(String s: topKeys){
            System.out.println("Key: " + s);
        }
    }

    //print the keys and values of the hash map
    public void printMap(){
        for (String s : dataMap.keySet());
    }

    //write new high scores in
    public void addHighScore(){
        //check current score against existing sorted scores

        //if current score > scores in the scoreboard, add it to the map and sort again
        //else return top scores and players score

    }

    //update scoreboard and save to hash map for writing to file
    public void updateScoreboard(){

    }
}

class tester{

    private static Scoreboard sc = new Scoreboard();

    public static void main(String[] args){
        sc.Scoreboard();
        sc.createScoreboard();
        sc.readScoreboard();
        sc.sortScores(3);
    }


}
