package Homeworkeight.GUI;

import Homeworkeight.Logic.Field;

import javax.swing.*;

public class Window extends JFrame {
    GameTable table;
    public Window(Field field) {
        super();
        this.table = new GameTable(field);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Tik - Tak Toe");
        setBounds(400,300, 200,200);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(table);
        add(panel);
        pack();
    }

    public GameTable getGameTable(){
        return this.table;
    }


}
