package Homeworkeight.Logic;
import static Homeworkeight.Common.Shared.*;

import javax.swing.*;

public class Field {
    private Cell[][] cells;

    public Field() {
        this.cells = new Cell[FIELD_SIZE][FIELD_SIZE];
        init();
    }

    public boolean playerAction(int row, int col, char dot){
        if(cells[row][col].isEmpty()){
            cells[row][col].setCellDot(dot);
            return true;
        }
        return false;
    }

    public boolean isWinner(char dot){
        return Judge.isWinner(cells, dot);
    }

    public boolean ifFieldFull(){
        for ( Cell[] row: cells) {
            for ( Cell c : row) {
                if(c.isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    public ImageIcon[][] getIconSet(){
        ImageIcon[][] res = new ImageIcon[cells.length][cells.length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                res[i][j] = cells[i][j].getIcon();
            }
        }
        return res;
    }

    public ImageIcon getIconAt(int row, int col){
        return cells[row][col].getIcon();
    }

    public Cell[][] getCells(){
        return this.cells;
    }

    private void init(){
        for (int i = 0; i < this.cells.length ; i++) {
            for (int j = 0; j < this.cells.length ; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }
    }
}
