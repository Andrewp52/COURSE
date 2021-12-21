package ru.geekbrain.structural.proxy;

public class SecuredDoor {
    private Door door;

    public SecuredDoor(Door door) {
        this.door = door;
    }

    public void open(String password) {
        if (authenticate(password)){
            this.door.open();
        } else {
            System.out.println("Big no! It ain't possible.");
        }
    }

    public void close() {
        this.door.close();
    }

    private boolean authenticate(String password)
    {
        return password == "$ecr@t";
    }

}
