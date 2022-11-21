public class Main {

    //objects of the other classes
    private static FileHandler fh = new FileHandler();
    private static WordleGame wrdl = new WordleGame();

    public static void main(String[] args) {

        //send FileHandler a file name
        String fileName = "test";
        fh.FileHandler(fileName);

        //call wordle to play
        wrdl.playWordle();

    }
}