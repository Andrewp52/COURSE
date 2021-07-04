package homework3.partOne;

import java.util.*;

public class Words {
    public static void main(String[] args) {
        String[] wordsHeap = getWordsHeap();
        Map<String, Integer> counted = getDictAndCount(wordsHeap);
        
        System.out.println("Unique words : " + counted.size());
        System.out.printf("%-15s %10s\r\n\r\n", "Word", "Occurs");
        for (Map.Entry<String, Integer> entry : counted.entrySet()) {
            System.out.printf("%-15s %10d\r\n", entry.getKey(), entry.getValue());
        }
    }

    private static Map<String, Integer> getDictAndCount(String[] words){
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        return wordMap;
    }

    private static String[] getWordsHeap(){
        String[] words = {
                "garbage", "addition", "university", "selection", "argument", "manufacturer", "farmer",
                "concept", "farmer", "equipment", "description", "argument", "university", "university"
        };
        return words;
    }
}
