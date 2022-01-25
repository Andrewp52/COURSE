package ru.geekbrains.taskadd.cycledlist;


public class CLMain {

    public static void main(String[] args) throws InterruptedException {
        CycledList<String> list = getCycledList();
        list.setIteratorProtected(true);                // Iterator infinity loop protection

        if(list.isCycled()){
            CycledList.Node<String> cycled = list.getCycledNode();
            System.out.println("List is cycled!");
            System.out.printf("Cycle from: %s to: %s\r\n", cycled.getValue(), cycled.getNext().getValue());
        }

        for (CycledList.Node<String> node : list){
            Thread.sleep(200);
            System.out.println(node.getValue());
        }

    }

    // Cycled list generation
    public static CycledList<String> getCycledList(){
        CycledList<String> list = new CycledList<>();
        list.add(new CycledList.Node<>("a"));
        list.add(new CycledList.Node<>("b"));
        CycledList.Node<String> n3 = new CycledList.Node<>("c");
        list.add(n3);
        list.add(new CycledList.Node<>("d"));
        list.add(new CycledList.Node<>("e"));
        CycledList.Node<String> n6 = new CycledList.Node<>("f");
        n6.setNext(n3);                                                 // CYCLE HERE
        list.add(n6);
        return list;
    }
}
