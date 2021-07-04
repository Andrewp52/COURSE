package Homeworkeight.Logic;

import static Homeworkeight.Common.Shared.*;

import javax.swing.*;

public class Cell {
    private final int row;
    private final int col;

    private char dot = DOT_EMPTY;
    private ImageIcon icon = EMPTY_ICON;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void setCellDot(char dot){
        this.dot = dot;
        this.icon = dot == DOT_X ? X_ICON : O_ICON;
    }

    public boolean isEmpty(){
        return dot == DOT_EMPTY;
    }

    public boolean isDot(char dot){
        return this.dot == dot;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
