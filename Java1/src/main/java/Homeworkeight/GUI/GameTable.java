package Homeworkeight.GUI;

import Homeworkeight.Logic.Field;
import Homeworkeight.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Homeworkeight.Common.Shared.*;

public class GameTable extends JTable{

    private TableModel model;
    private Field field;

    public GameTable(Field field){
        this.field = field;
        model = new Model(field.getIconSet());
        setup();
    }

    public void updateCell(int row, int col){
        model.setValueAt(field.getIconAt(row, col), row, col);
    }

    public void resetTableModel(Field field){
        this.field = field;
        model = new Model(field.getIconSet());
        setModel(model);
    }

    private void setup(){
        setModel(model);
        setPreferredSize(new Dimension(ROW_HEIGHT * FIELD_SIZE,ROW_HEIGHT * FIELD_SIZE));
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getTableHeader().setVisible(false);
        setBackground(Color.black);
        setSelectionBackground(getBackground());
        setSelectionForeground(getForeground());
        setRowHeight(ROW_HEIGHT);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = getSelectedRow();
                int col = getSelectedColumn();
                if(field.playerAction(row, col, DOT_X)){
                    updateCell(row, col);
                } else {
                    return;
                }
                Main.serveAction();
            }
        });
    }

    private class Model extends DefaultTableModel {
        public Model(Object[][] data) {
            super(data, new String[data.length]);
        }

        public Class getColumnClass(int column)
        {
            return getValueAt(0, column).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
