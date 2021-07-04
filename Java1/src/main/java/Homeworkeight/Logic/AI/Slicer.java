package Homeworkeight.Logic.AI;

import Homeworkeight.Logic.Cell;
import java.util.ArrayList;
import static Homeworkeight.Common.Shared.*;

                                                                // Slicing game field to set of separate lines
public class Slicer {
    private static ArrayList<Cell[]> lines;                     // ArrayList for lines with different length (important)

    public static ArrayList<Cell[]> sliceTable(final Cell[][] cells){
        lines = new ArrayList<>();
        Cell[][] turned = turnTable(cells);                     // Game field turned CCW with columns reading

        readLines(cells);                                       // Rows
        readDiagonals(cells);                                   // Left diagonals
        readDiagonals(turned);                                  // Right diagonals
        return lines;
    }

    private static void readLines(Cell[][] cells){
        for (int i = 0; i < cells.length; i++) {
            lines.add(cells[i]);
        }
    }

    private static void readDiagonals(Cell[][] cells){
        Cell[] center = new Cell[cells.length];
        for (int i = 0; i < cells.length; i++) {
            center[i] = cells[i][i];
        }
        lines.add(center);
        if(FIELD_SIZE > DOTS_TO_WIM){
            Cell[] shiftedUp;
            Cell[] shiftedDn;
            for (int i = 0; i < FIELD_SIZE - DOTS_TO_WIM ; i++) {
                shiftedUp = new Cell[FIELD_SIZE - (i + 1)];
                shiftedDn = new Cell[FIELD_SIZE - (i + 1)];
                for (int j = 1 + i; j < cells.length ; j++) {
                    shiftedUp[j - 1 - i] = cells[j - 1][j];
                    shiftedDn[j - 1 - i] = cells[j][j - 1];
                }
                lines.add(shiftedUp);
                lines.add(shiftedDn);
            }
        }
    }

    private static Cell[][] turnTable(final Cell[][] cells){
        Cell[][] res = new Cell[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                res[i][j] = cells[j][cells.length - 1 - i];
            }
            lines.add(res[i]);
        }
        return res;
    }
}
