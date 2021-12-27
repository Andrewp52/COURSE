package ru.geekbrain.behavioral.iterator;
import ru.geekbrain.behavioral.iterator.StationList.RadioStation;

public class Usage {
    public static void main(String[] args) {
        StationList stations = new StationList();
        stations.add(new RadioStation(90.2f));
        stations.add(new RadioStation(102.2f));
        stations.add(new RadioStation(103.5f));

        stations.forEach(station -> System.out.println(station.getFrequency()));
    }
}
