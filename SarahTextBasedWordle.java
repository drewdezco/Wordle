/**
 * @author Sarah Harrington
 * This class takes in a file of target words and guesses from a user, then it compares these.
 * Correct/incorrect letters in the guess are returned to the user.
 * The program is terminated when the user guesses the target word or uses all 6 guesses.
 */

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class SarahTextBasedWordle {
    private static ArrayList<String> words = new ArrayList<String>();
    private static String addWord;
    private static String targetWord;

    public SarahTextBasedWordle(){
        targetWord = null;
        addWord = null;
    }

    public static void main(String[] args) {
        //read in the target word file
        try {
            FileInputStream wordFile = new FileInputStream("words.txt");
            Scanner scanFile = new Scanner(wordFile);

            while (scanFile.hasNextLine()) {
                addWord = scanFile.nextLine();
                words.add(addWord);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        //randomly choose the target word from the list
        Random rand = new Random();
        int randomInt = rand.nextInt(4499);

        targetWord = words.get(randomInt);
        //System.out.println(targetWord);

        //begin the Wordle game by taking in the first guess
        int i = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Wordle! You have 6 guesses to find the target word. Guess a 5 letter word: ");
        String guess = scan.nextLine().toLowerCase();

        //confirm guess is correct format; check guess for matches to target word; take in up to 6 guesses from user
        while(i<=5) {

            while (guess.length() != 5) {
                System.out.println("Please input a 5 letter word: ");
                guess = scan.nextLine().toLowerCase();
            }

            if (guess.equals(targetWord)) {
                System.out.println("You win!");
                return;
            } else {
                for (int j = 0; j < 5; j++) {
                    char letter = guess.charAt(j);

                    if (letter == targetWord.charAt(j)) {
                        System.out.println(letter + " is correct!");
                    } else if (targetWord.contains(Character.toString(letter))) {
                        System.out.println(letter + " is in the target word but is not in the correct spot.");
                    } else {
                        System.out.println(letter + " is not in the target word.");
                    }
                }

                if (i == 5) {
                    System.out.println("You're out of guesses, game over! The target word was " + targetWord + "!");
                    return;
                }else if (i == 4){
                    System.out.println(5 - i + " guess remaining. Guess again: ");
                    guess = scan.nextLine().toLowerCase();
                }else if (5-i != 0){
                    System.out.println(5 - i + " guesses remaining. Guess again: ");
                    guess = scan.nextLine().toLowerCase();
                }
            }

            i++;
        }
    }
}