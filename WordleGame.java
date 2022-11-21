/**
 *Julia Barnes
 *Sarah Harrington
 *Andrew Hernandez
 *
 * The purpose of this class is to:
 * - Give a player six guesses to guess a five-letter word that is chosen at random from a list
 * - Check to see if the word guessed is a real five-letter word
 * - After each guess, tell the player if they got any letters exactly right as well as right in the wrong position
 * - Provide the real word at the end of guesses or if they get it correctly
 */
public class WordleGame {

    //take in target word as instance field
    private String targetWord;

    //default constructor
    public WordleGame() {
        targetWord = null;
    }

    //parameterized constructor
    public WordleGame(String t) {
        targetWord = t;
    }


    //method to run through wordle game
    public void playWordle() {

        //call GUI so it is called when the game play is called

        //iterate through 6 times (6 guesses)

        //take in user input as guess word

        //compare guess word to target word

        //return correct letters

        //return letters in the target but in the incorrect spot

        //return incorrect letters

        //handle guesses of incorrect length, do not count as a guess

    }

}