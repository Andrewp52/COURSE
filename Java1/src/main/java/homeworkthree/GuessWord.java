package homeworkthree;
import java.util.Random;
import java.util.Scanner;

public class GuessWord {
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();
    static String[] words = {
            "apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot"
            , "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut"
            , "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"
    };

    public static void main(String[] args) {
        System.out.println("Guess the word");
        playGame(words[rand.nextInt(words.length + 1)]);
        scanner.close();
    }

    private static void playGame(String word){
        String answer = scanner.nextLine();
        while (!word.equals(answer)){
            showHint(word, answer);
            answer = scanner.nextLine();
        }
        System.out.println("You win!");
    }

    private static void showHint(String word, String answer){
        StringBuilder sb = new StringBuilder("###############");
        int limit = Math.min(answer.length(), word.length());
        for (int i = 0; i < limit; i++){
            if(answer.charAt(i) == word.charAt(i)){
                sb.setCharAt(i, answer.charAt(i));
            }
        }
        System.out.println(sb.toString());
    }
}
