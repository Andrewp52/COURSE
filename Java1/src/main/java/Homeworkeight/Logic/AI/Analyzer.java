package Homeworkeight.Logic.AI;
import Homeworkeight.Logic.Cell;
import java.util.ArrayList;
import static Homeworkeight.Common.Shared.*;

public class Analyzer {
                                                            // Analyzing threats line-by-line and choice line to block
    private static int[] threats;

    public static Cell[] getLine(ArrayList<Cell[]> lines){
        threats = new int[lines.size()];
        for (int i = 0; i < lines.size() ; i++) {
            threats[i] = countThreat(lines.get(i));
        }
        int index = choiceBest(threats);
        return threats[index] == 0 ? null : lines.get(index) ;                          // WARNING !!! May return NULL
    }

    private static int countThreat(Cell[] line) {
        int threat = 0;
        int aiDots = 0;
        for (int i = 0; i < line.length; i++) {
            if (line[i].isDot(DOT_X)) {
                threat += 10;
            } else if (line[i].isDot(DOT_O)) {
                aiDots++;
                if ((i >= line.length - DOTS_TO_WIM && i < line.length - 1 - (line.length - DOTS_TO_WIM))
                        || aiDots > line.length - DOTS_TO_WIM) {
                    return 0;
                }
            }
        }
        threat -= (aiDots - FIELD_SIZE - line.length);
        return threat;
    }

    private static int choiceBest(int[] threats){
        int index = 0;
        int maxval = threats[0];
        for (int i = 1; i < threats.length; i++) {
            if (threats[i] > maxval){
                maxval = threats[i];
                index = i;
            } else if (threats[i] == maxval){
                if(random.nextInt(2) > 0){
                    index = i;
                }
            }
        }
        return index;
    }
}
