package homeworkthree;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();
    static int takes = 3;

    public static void main(String[] args) {
        do {
            System.out.println("Guess the number (0 - 9)");
            playGame(rand.nextInt(10), takes);
        } while (askToReplay());
        scanner.close();
    }

    private static void playGame(int number, int takes){
        while (takes > 0) {
            if(isCorrectAnswer(number, getUserInput())){
                System.out.println("You win");
                return;
            }
            takes--;
        }
        System.out.println("You loose");
    }

    static boolean isCorrectAnswer(int number, int answer){
        if(number == answer){
            return true;
        }
        System.out.println(number > answer ? "The number is greater" : "The number is less");
        return false;
    }

    private static boolean askToReplay(){
        System.out.println("Play again (1 - yes, 0 - no)");
        int answer = getUserInput();
        while (!(answer == 1 || answer == 0)) {
            System.out.println("Enter 1 or 0");
            answer = getUserInput();
        }
        return answer == 1;
    }

    private static int getUserInput(){
        int input;
        while (!scanner.hasNextInt()){
            System.out.println("Enter a number.");
            scanner.nextLine();
        }
        input = scanner.nextInt();
        scanner.nextLine();                      // Consuming buffer to avoid scanner issues (nextInt / nextLine)
        return input;
    }
}
