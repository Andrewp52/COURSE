package homework1;

public class Test {
    public static void main(String[] args) {
        Runner[] sportsmen = new Runner[] {
                new Cat(500, 3),
                new Human(300, 1),
                new Robot(500, 2),
        };

        Obstacle[] course = new Obstacle[]{
                new Track(150),
                new Track(250),
                new Wall(1),
                new Wall(2),
                new Track(500),
                new Wall(3),
                new Track(501)
        };

        for (Runner runner : sportsmen){
            System.out.println();
            for (Obstacle obstacle : course){
                if(!obstacle.interact(runner)){
                    break;
                }
            }
        }
    }
}
