/*
-------------------------------------------
- Title:  Wordle Java Clone
- Author: Myles Grant
- Date:   20 Jan 2022
-------------------------------------------
*/
package Wordle;
import java.io.*;
import java.util.*;

public class Wordle {

    protected static final int MIN = 0;
    protected static final int MAX = 2313;

    protected static final String ANSI_RESET = "\u001B[0m";
    protected static final String WHITE = "\u001B[37m";
    protected static final String GREEN_BACKGROUND = "\u001B[42m";
    protected static final String YELLOW_BACKGROUND = "\u001B[43m";
    protected static final String BLACK_BACKGROUND = "\u001B[40m";

    public static boolean contains(char c, char[] array) {
    for (char x : array) {
        if (x == c) {
            return true;
        }
    }
    return false;
}

    public static String randomWord() throws Exception {
        List<String> words = constructWordList();
        int random_int = (int)Math.floor(Math.random()*(MAX-MIN+1)+MIN);
        String targetWord = words.get(random_int);
        return targetWord;
    }

    public static String guessWord() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }

    public static List<String> constructWordList() throws Exception {
        List<String> list = new ArrayList<String>();
        List<String> wordlist = new ArrayList<String>();
        File file = new File("/Users/mylesgrant/Documents/atomcode/Java/Side_Projects/Wordle Answers.txt");
        Scanner scanner = new Scanner(file);

        if(file.exists()){
            try {
                for (int i = 0; i < 12971; i++) {
                    list.add(scanner.nextLine());
                }
            } catch (Exception ex) {}

        for(String line : list) {
            String [] res = line.split("	");
            wordlist.add(res[2]);
            }
        }
        scanner.close();
        return wordlist;
    }

    public static void main(String[] args) throws Exception {
        System.out.print("\033[H\033[2J");
        System.out.println("WORDLE");
        List<String> wordlist = constructWordList();
        Random rand = new Random();
        String targetWord = wordlist.get(rand.nextInt(MAX));
        char[] targetWordCharArray = targetWord.toCharArray();
        boolean playing = true;
        List<String> guessList = new ArrayList<String>();

        while (playing) {
            String guess = guessWord();
            char[] guessWordCharArray = guess.toCharArray();
            int count = 0;
            String guesses = "";
            System.out.print("\033[H\033[2J");
            int l;

            if (guess.length() > 5) {
                l = 5;
            } else {
                l = guess.length();
            }

            for (int i = 0; i < l; i++) {
                if(targetWordCharArray[i] == guessWordCharArray[i]) {
                    guesses = guesses + WHITE + GREEN_BACKGROUND + guessWordCharArray[i] + ANSI_RESET;
                    count++;
                }
                else {
                    if(contains(guessWordCharArray[i],targetWordCharArray) == true) {
                        guesses = guesses + WHITE + YELLOW_BACKGROUND + guessWordCharArray[i] + ANSI_RESET;
                    }
                    else {
                        guesses = guesses + WHITE + BLACK_BACKGROUND + guessWordCharArray[i] + ANSI_RESET;
                    }
                }
            }
            if(wordlist.contains(guess)) {
                guessList.add(guesses);
            }
            System.out.println("WORDLE");
            for (int k = 0; k < guessList.size(); k++) {
                System.out.println(guessList.get(k));
            }
            if(count == 5) {
                playing = false;
                System.out.println("Congrats!");
            }
            if (guessList.size() == 6 && playing == true) {
                System.out.println("You Lost!");
                System.out.println("the Wordle was " + targetWord);
                playing = false;
            }
        }

    }
}
