import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    private String filePath;
    private ArrayList<String> fileWords = new ArrayList<String>();//added String

    //default constructor
    public FileHandler() {
        filePath = null;
    } //removed void

    //parameterized constructor
    public void FileHandler(String f) {
        filePath = f;
    }

    //read the file and add each word to the array
    public void fileRead() throws FileNotFoundException {
        File wordFile = new File(filePath);
        FileReader readFile = new FileReader(wordFile);

        /*
        while (readFile.hasNext()) { //I believe in order to use readFile here like this it has to be a scanner
            for (int i = 0; i < readFile.length; i++) {

            }
        }

         */
    }
    //generate a random number for use in pulling a random word
    public void getRand() {
        int randInt = 0;
        getTarget(randInt);
    }

    //get and send the target word to Wordle
    public void getTarget(int rI) {

        //use the random number to pull that index from the words array
        String targetWord = fileWords.get(rI);
        //return this word as target word
        //Wordle.Wordle(targetWord);
    }
}
