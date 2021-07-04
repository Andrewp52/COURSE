package Homeworkeight;
import static Homeworkeight.Common.Shared.*;
import static javax.swing.JOptionPane.showMessageDialog;

import Homeworkeight.GUI.Window;
import Homeworkeight.Logic.AI.Ai;
import Homeworkeight.Logic.Field;
import javax.swing.*;

//                                           ALL SETTINGS IN Common.Shared
public class Main {
    static Field field = new Field();
    static Window window = new Window(field);

    public static void main(String[] args) {
        window.setVisible(true);
    }

    public static void serveAction(){
        if(field.isWinner(DOT_X)){
            gameOver("You win");
            return;
        }
        if (field.ifFieldFull()){
            gameOver("Draw");
            return;
        } else {
            Ai.placeDot(field);
            window.getGameTable().updateCell(Ai.getLastRow(), Ai.getLastCol());
        }
        if(field.isWinner(DOT_O)){
            gameOver("Ai wins");
            return;
        } else if (field.ifFieldFull()){
            gameOver("Draw");
            return;
        }
    }

    private static void gameOver(String message){ ;
        showMessageDialog(window, message, "Game over", JOptionPane.INFORMATION_MESSAGE);
        field = new Field();
        window.getGameTable().resetTableModel(field);
    }

}
