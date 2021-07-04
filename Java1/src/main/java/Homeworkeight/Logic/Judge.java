package Homeworkeight.Logic;
import static Homeworkeight.Common.Shared.*;

public class Judge {

    public static boolean isWinner(Cell[][] cells, char dot){
        return checkColsAndRows(cells, dot) || checkDiagonals(cells, dot);
    }

    private static  boolean checkColsAndRows(Cell[][] cells, char dot){
        int col = 0, row = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                col = cells[j][i].isDot(dot) ? col + 1 : 0;
                row = cells[i][j].isDot(dot) ? row + 1 : 0;
                if (col == DOTS_TO_WIM || row == DOTS_TO_WIM){
                    return true;
                }
            }
            col = row = 0;
        }
        return false;
    }

    private static boolean checkDiagonals(Cell[][] cells, char dot){
        int Left = 0, Right = 0;
        for (int i = 0; i < cells.length; i++) {                          // Center diagonals
            Left = cells[i][i].isDot(dot) ? Left + 1 : 0;
            Right = cells[cells.length - i - 1][i].isDot(dot) ? Right + 1 : 0;
            if(Left == DOTS_TO_WIM || Right == DOTS_TO_WIM){
                return true;
            }
        }
        if(FIELD_SIZE > DOTS_TO_WIM){                                     // Shifted diagonals
            int leftUp = 0, leftDn = 0, rightUp = 0, rightDn = 0;
            for (int x = 0; x < cells.length - DOTS_TO_WIM; x++) {
                for (int i = 1; i < cells.length - x; i++) {
                    leftDn = cells[i + x][i - 1].isDot(dot) ? leftDn + 1 : 0;
                    leftUp = cells[i - 1][i + x].isDot(dot) ? leftUp + 1 : 0;
                    rightDn = cells[i + x][cells.length - i].isDot(dot) ? rightDn + 1 : 0;
                    rightUp = cells[i - 1][cells.length - i - x - 1].isDot(dot) ? rightUp + 1 : 0;
                    if (leftUp == DOTS_TO_WIM || leftDn == DOTS_TO_WIM
                            || rightUp == DOTS_TO_WIM || rightDn == DOTS_TO_WIM){
                        return true;
                    }
                }
                leftUp = leftDn = rightUp = rightDn = 0;
            }
        }
        return false;
    }
}
