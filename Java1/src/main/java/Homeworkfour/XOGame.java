package Homeworkfour;

import java.util.Random;
import java.util.Scanner;

public class XOGame {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static final char DOT_EMPTY = '*';
    static final char DOT_X = 'X';
    static final char DOT_O = '0';
    static final int MAP_SIZE = 6;
    static final int DOTS_TO_WIN = 5;
    static char[][] map;
    static int lastX;
    static int lastY;
    public static void main(String[] args) {
        initMap();
        showMap();
        playGame();
        scanner.close();
    }

    //                                          MAIN GAME SECTION
    static void playGame(){
        while (true){
            playerTurn();
            showMap();
            if (isWinner(DOT_X)){
                System.out.println("You win");
                break;
            }
            if (isMapFull()){
                System.out.println("Draw");
                break;
            }
            aiTurn();
            showMap();
            if (isWinner(DOT_O)){
                System.out.println("AI win");
                break;
            }
            if (isMapFull()){
                System.out.println("Draw");
                break;
            }
        }
    }
    static void playerTurn(){
        int x, y;
        System.out.println("Your turn");
        do {
            System.out.println("Enter X coordinate:");
            x = getUserInput() - 1;
            System.out.println("Enter Y coordinate:");
            y = getUserInput() - 1;
        } while (!isTurnPossible(x , y));
        map[y][x] = DOT_X;
        lastX = x;
        lastY = y;
    }
    static void aiTurn(){
        System.out.println("Ai turn");
        System.out.println();
        blockThreat();
    }

    static boolean isWinner(char c){
        return checkColsAndRows(c) || checkDiagonals(c);
    }
    static boolean checkColsAndRows(char c){
        int col = 0, row = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                col = map[j][i] == c ? col + 1 : 0;
                row = map[i][j] == c ? row + 1 : 0;
                if (col == DOTS_TO_WIN || row == DOTS_TO_WIN){
                    return true;
                }
            }
            col = row = 0;
        }
        return false;
    }
    static boolean checkDiagonals(char c){
        int Left = 0, Right = 0;
        for (int i = 0; i < map.length; i++) {                          // Center diagonals
            Left = map[i][i] == c ? Left + 1 : 0;
            Right = map[map.length - i - 1][i] == c ? Right + 1 : 0;
            if(Left == DOTS_TO_WIN || Right == DOTS_TO_WIN){
                return true;
            }
        }
        if(MAP_SIZE > DOTS_TO_WIN){                                     // Shifted diagonals
            int LeftUp = 0, LeftDn = 0, RightUp = 0, RightDn = 0;
            for (int x = 0; x < MAP_SIZE - DOTS_TO_WIN; x++) {
                for (int i = 1; i < map.length - x; i++) {
                    LeftDn = map[i + x][i - 1] == c ? LeftDn + 1 : 0;
                    LeftUp = map[i - 1][i + x] == c ? LeftUp + 1 : 0;
                    RightDn = map[i + x][map.length - i] == c ? RightDn + 1 : 0;
                    RightUp = map[i - 1][map.length - i - x - 1] == c ? RightUp + 1 : 0;
                    if (LeftUp == DOTS_TO_WIN || LeftDn == DOTS_TO_WIN
                            || RightUp == DOTS_TO_WIN || RightDn == DOTS_TO_WIN){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //                                       MAINTENANCE SECTION
    static boolean isTurnPossible(int x, int y){
        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE){
            System.out.println("Coordinates is out of the map");
            return false;
        }
        if (!isCellEmpty(x, y)){
            System.out.println("Cell " + (x + 1) + " " + (y + 1) + " is not empty");
            return false;
        }
        return true;
    }

    static boolean isCellEmpty(int x, int y){
        return map[y][x] == DOT_EMPTY;
    }
    static boolean isMapFull(){
        for (char[] arr : map) {
            for (char c : arr) {
                if(c == DOT_EMPTY){
                    return false;
                }
            }
        }
        return true;
    }
    static void initMap(){
        map = new char[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++){
            for (int j = 0; j < MAP_SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    static void showMap() {
        for (char[] row: map) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
    static int getUserInput(){
        while (!scanner.hasNextInt()){
            System.out.println("Enter a number.");
            scanner.nextLine();
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    //                                                    AI SECTION
    static void blockThreat(){
        int[][] threats = new int[4][1];
        threats[0] = checkRows();
        threats[1] = checkCols();
        threats[2] = checkDiagLeft();
        threats[3] = checkDiagRight();
        int best = choiceBest(threats);
        int index = threats[best][0] != 0 ? best : 4;
        switch (index)
        {
            case 0: blockRow(threats[0][1]);         break;
            case 1: blockCol(threats[1][1]);         break;
            case 2: blockDiagLeft(threats[2][1]);    break;
            case 3: blockDiagRight(threats[3][1]);   break;
            case 4: randomMove();                    break;
        }
    }
    static int choiceBest(int[][] thr){
        int maxVal = 0;
        int index = 0;
        for (int i = 0; i < thr.length; i++) {
            if (thr[i][0] > maxVal){
                maxVal = thr[i][0];
                index = i;
            } else if (thr[i][0] == maxVal) {
                if (random.nextInt(2) > 0){
                    index = i;
                }
            }
        }
        return index;
    }
    static void randomMove(){
        int x, y;
        do {
            x = random.nextInt(MAP_SIZE);
            y = random.nextInt(MAP_SIZE);
        } while (!isCellEmpty(x, y));
        map[y][x] = DOT_O;
    }
    //                                                                                            Threat collecting
    //                                                                                                 Rows threats
    static int[] checkRows(){
        int[][] res = new int[MAP_SIZE][2];                         // [weight][row]
        int aiDots = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            res[i][1] = i;
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == DOT_X){
                    res[i][0] += 10;
                } else if (map[i][j] == DOT_O) {
                    aiDots++;
                    if ((j >= MAP_SIZE - DOTS_TO_WIN && j < MAP_SIZE - (MAP_SIZE - DOTS_TO_WIN))
                            || aiDots > MAP_SIZE - DOTS_TO_WIN){
                        res[i][0] = aiDots = 0;
                        break;
                    }
                }
            }
            res[i][0] -= aiDots;
        }
        return res[choiceBest(res)];
    }
    //                                                                                              Columns threats
    static int[] checkCols(){
        int[][] res = new int[MAP_SIZE][2];                         // [weight][column]
        int aiDots = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            res[i][1] = i;
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[j][i] == DOT_X){
                    res[i][0] += 10;
                } else if (map[j][i] == DOT_O) {
                    aiDots++;
                    if ((j >= MAP_SIZE - DOTS_TO_WIN && j < MAP_SIZE - (MAP_SIZE - DOTS_TO_WIN))
                            || aiDots > MAP_SIZE - DOTS_TO_WIN){
                        res[i][0] = aiDots = 0;
                        break;
                    }
                }
            }
            res[i][0] -= aiDots;
        }
        return res[choiceBest(res)];
    }
    //                                                                                       left diagonals threats
    static int[] checkDiagLeft(){
        int[][] t = new int[3][1];                                  // [weight][shift]
        t[0] = new int[] {checkLeftCenter(), 0};
        if(MAP_SIZE > DOTS_TO_WIN){
            t[1] = checkLeftUp();
            t[2] = checkLeftDown();
        }
        return t[choiceBest(t)];
    }
    static int checkLeftCenter(){
        int center = 0, aiDots = 0;
        for (int i = 0; i < map.length; i++) {
            if(map[i][i] == DOT_X){
                center += 10;
            } else if (map[i][i] == DOT_O){
                aiDots++;
                if(aiDots > MAP_SIZE - DOTS_TO_WIN || (i > 0 && i < map.length - 1)){
                    return 0;
                }
            }
        }
        return center - aiDots;
    }
    static int[] checkLeftUp(){
        int[][] res = new int[MAP_SIZE - DOTS_TO_WIN][2];
        int up = 0, aiDots = 0;
        for (int x = 0; x < MAP_SIZE - DOTS_TO_WIN; x++) {
            for (int i = 1; i < map.length - x; i++) {
                if(map[i - 1][i + x] == DOT_X){
                    up += 10;
                } else if (map[i - 1][i + x] == DOT_O){
                    aiDots++;
                    if(aiDots > MAP_SIZE - DOTS_TO_WIN - (x + 1)){
                        up = aiDots = res[x][0] = 0;
                        break;
                    }
                }
                if (up - aiDots - x - 1> res[x][0]) {
                    res[x][0] = up - aiDots - x - 1;
                    res[x][1] = x + 1;
                }
            }
        }
        return res[choiceBest(res)];
    }
    static int[] checkLeftDown() {
        int[][] res = new int[MAP_SIZE - DOTS_TO_WIN][2];
        int down = 0, aiDots = 0;
        for (int x = 0; x < MAP_SIZE - DOTS_TO_WIN; x++) {
            for (int i = 1; i < map.length - x; i++) {
                if (map[i + x][i - 1] == DOT_X) {
                    down += 10;
                } else if (map[i + x][i - 1] == DOT_O) {
                    aiDots++;
                    if (aiDots > MAP_SIZE - DOTS_TO_WIN - (x + 1)){
                        down = aiDots = res[x][0] = 0;
                        break;
                    }
                }
            }
            if (down - aiDots - x - 1 > res[x][0]) {
                res[x][0] = down - aiDots - x - 1;
                res[x][1] = (x + 1) * -1;
            }
        }
        return res[choiceBest(res)];
    }
    //                                                                                      Right diagonals threats
    static int[] checkDiagRight() {
        int[][] threats = new int[3][1];                            // [weight][shift]
        threats[0] = new int[] {checkRightCenter(), 0};
        if(MAP_SIZE > DOTS_TO_WIN) {
            threats[1] = checkRightUp();
            threats[2] = checkRightDown();
        }
        return threats[choiceBest(threats)];
    }
    static int checkRightCenter(){
        int center = 0, aiDots = 0;
        for (int i = 0; i < map.length; i++) {
            if(map[map.length - i - 1][i] == DOT_X){
                center += 10;
            } else if (map[map.length - i - 1][i] == DOT_O){
                aiDots++;
                if(aiDots > MAP_SIZE - DOTS_TO_WIN || (i > 0 && i < map.length - 1)){
                    return 0;
                }
            }
        }
        return center - aiDots;
    }
    static int[] checkRightUp(){
        int[][] res = new int[MAP_SIZE - DOTS_TO_WIN][2];
        int up = 0, aiDots = 0;
        for (int x = 0; x < MAP_SIZE - DOTS_TO_WIN; x++) {
            for (int i = 1; i < map.length - x; i++) {
                if(map[i - 1][map.length - i - x - 1] == DOT_X){
                    up += 10;
                } else if (map[i - 1][map.length - i - x - 1] == DOT_O){
                    aiDots++;
                    if(aiDots > MAP_SIZE - DOTS_TO_WIN - (x + 1)){
                        up = aiDots = res[x][0] = 0;
                        break;
                    }
                }
                if (up - aiDots - x - 1 > res[x][0]) {
                    res[x][0] = up - aiDots - x - 1;
                    res[x][1] = x + 1;
                }
            }
        }
        return res[choiceBest(res)];
    }
    static int[] checkRightDown(){
        int[][] res = new int[MAP_SIZE - DOTS_TO_WIN][2];
        int down = 0, aiDots = 0;
        for (int x = 0; x < MAP_SIZE - DOTS_TO_WIN; x++) {
            for (int i = 1; i < map.length - x; i++) {
                if (map[i + x][map.length - i] == DOT_X) {
                    down += 10;
                } else if (map[i + x][map.length - i] == DOT_O) {
                    aiDots++;
                    if (aiDots > MAP_SIZE - DOTS_TO_WIN - (x + 1)){
                        down = aiDots = res[x][0] = 0;
                        break;
                    }
                }
            }
            if (down - aiDots - x - 1> res[x][0]) {
                res[x][0] = down - aiDots - x - 1;
                res[x][1] = (x + 1) * -1;
            }
        }
        return res[choiceBest(res)];
    }

    //                                                                                            Threat blocking
    static void blockRow(int y){
        int x;
        do {
            x = random.nextInt(MAP_SIZE);
        } while (!isCellEmpty(x, y));
        map[y][x] = DOT_O;
    }
    static void blockCol(int x){
        int y;
        do {
            y = random.nextInt(MAP_SIZE);
        } while (!isCellEmpty(x, y));
        map[y][x] = DOT_O;
    }
    static void blockDiagLeft(int delta){
        int newX, newY;
        int absDelta = Math.abs(delta);
        do {
            if(delta > 0){
                newX = random.nextInt(MAP_SIZE - absDelta) + absDelta;
                newY = newX - absDelta;
            } else if (delta < 0){
                newY = random.nextInt(MAP_SIZE - absDelta) + absDelta;
                newX = newY - absDelta;
            } else {
                newX = newY = random.nextInt(MAP_SIZE);
            }

        } while (!isCellEmpty(newX, newY));
        map[newY][newX] = DOT_O;
    }
    static void blockDiagRight(int delta){
        int newX, newY;
        int absDelta = Math.abs(delta);
        do {
            if (delta > 0){
                newX = random.nextInt(MAP_SIZE - 1 - Math.abs(absDelta)) + Math.abs(absDelta);
                newY = MAP_SIZE  - 1 - newX - Math.abs(absDelta);
            } else if (delta < 0){
                newX = random.nextInt(MAP_SIZE - 1) + Math.abs(absDelta);
                newY = MAP_SIZE - newX;
            } else {
                newX = random.nextInt(MAP_SIZE);
                newY = MAP_SIZE - newX - 1;
            }
        } while (!isCellEmpty(newX, newY));
        map[newY][newX] = DOT_O;
    }
}
