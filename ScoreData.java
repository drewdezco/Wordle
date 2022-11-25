import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ Sarah Harrington
 * @ Julia Barnes
 * @ Andrew Hernandez
 *
 * @author sarah
 * @params String
 * @return String, int
 *
 * The purpose of this class is to return a (String, int) tuple to store
 * in the ArrayList of scoreboard data in the Scoreboard class.
 */
public class ScoreData {

    //instance field for array list parameter
    private ArrayList<String> paramArray;

    //instance field to store split values
    private HashMap<String, Integer> splitValues = new HashMap<>();

    //return String after splitting
    private String name;

    //return int after splitting
    private int score;

    //default constructor
    public void ScoreData(){
        paramArray = new ArrayList<>();
    }

    // parameterized constructor
    public void ScoreData(ArrayList<String> a){
        paramArray = a;

        //debug the paramArray inputs
        //for (int i = 0; i < paramArray.size(); i++){
        //    System.out.println(paramArray.get(i));
        //}
    }

    //take in the paramArray and split the lines to get name and score
    public void splitLine(){
        String[] splitLine = {};

        //split the line to get the player name and score value
        for (int i = 0; i < paramArray.size(); i++) {
            //System.out.println(paramArray.get(i));

            splitLine = paramArray.get(i).split(" ");

        }

        for (int i = 0; i < paramArray.size(); i++ ){
            System.out.println(i + " : " + splitLine[i]);
        }


        //save name variable
        setName("");

        //save score variable
        setScore(0);

    }

    //setter for name
    public void setName(String n){
        name = n;
    }

    //setter for score
    public void setScore(int i){
        score = i;
    }

    //getter for name
    public String getName(){

        return name;
    }

    //getter for score
    public int getScore(){

        return score;
    }
}

class tester2 {

    private static ScoreData sd = new ScoreData();

    public static void main(String[] args){
        ArrayList<String> test = new ArrayList<>();

        test.add("one 10");
        test.add("two 20");

        sd.ScoreData(test);
        sd.splitLine();
    }
}
