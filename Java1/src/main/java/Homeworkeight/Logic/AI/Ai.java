package Homeworkeight.Logic.AI;

import Homeworkeight.Logic.Cell;
import Homeworkeight.Logic.Field;

import static Homeworkeight.Common.Shared.*;

public class Ai {
    private static int lastRow;
    private static int lastCol;

    public static int getLastRow() {
        return lastRow;
    }

    public static int getLastCol() {
        return lastCol;
    }

    public static void placeDot(Field field){
        Cell[] line = Analyzer.getLine(Slicer.sliceTable(field.getCells()));
        if(line != null){
            Cell cell;
            do {
                cell = line[random.nextInt(line.length)];
            } while (!field.playerAction(cell.getRow(), cell.getCol(), DOT_O));
            lastRow = cell.getRow();
            lastCol = cell.getCol();
        } else {
            int row;
            int col;
            do {
                row = random.nextInt(FIELD_SIZE);
                col = random.nextInt(FIELD_SIZE);
            } while (!field.playerAction(row, col, DOT_O));
            lastRow = row;
            lastCol = col;
        }
    }
}
