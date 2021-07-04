package Homeworkeight.Common;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Shared {
    public static final Random random = new Random();
    public static final int FIELD_SIZE  = 6;
    public static final int DOTS_TO_WIM = 5;
    public static final int ROW_HEIGHT = 70;
    public static final int ICON_SIZE = 60;

    public final static char DOT_X = 'X';
    public final static char DOT_O = 'O';
    public final static char DOT_EMPTY = '*';

    public static ImageIcon X_ICON;
    public static ImageIcon O_ICON;
    public static ImageIcon EMPTY_ICON;

    static {
        try {
            loadImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void loadImages() throws IOException {
        X_ICON = new ImageIcon(
                ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("x.png"))
                        .getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)
        );
        O_ICON = new ImageIcon(
                ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("o.png"))
                        .getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH)
        );
        EMPTY_ICON = new ImageIcon(
                ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("e.png"))
                        .getScaledInstance(5, 5, Image.SCALE_SMOOTH)
        );
    }
}
