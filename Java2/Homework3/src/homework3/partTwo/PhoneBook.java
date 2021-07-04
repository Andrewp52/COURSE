package homework3.partTwo;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<Integer>> book = new HashMap<>();

    public PhoneBook add(String lName, Integer phone){
        this.book.putIfAbsent(lName, new HashSet<>());
        this.book.get(lName).add(phone);
        return this;
    }

    public Set<Integer> get(String lName){
        return this.book.get(lName);
    }
}
