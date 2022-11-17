public class Main {

    //objects of the other classes
    private static FileHandler fh = new FileHandler();
    private static Wordle wrdl = new Wordle();

    public static void main(String[] args) {

        //send FileHandler a file name
        String fileName = "test";
        fh.FileHandler(fileName);

        //call wordle to play
        wrdl.playWordle();

    }
}