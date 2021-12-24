package ru.geekbrain.creational.builder;

class Burger {

    private int size;
    private boolean cheese;
    private boolean pepperoni;
    private boolean lettuce;
    private boolean tomato;

    public Burger(int size, boolean cheese, boolean pepperoni, boolean lettuce, boolean tomato) {
        this.size = size;
        this.cheese = cheese;
        this.pepperoni = pepperoni;
        this.lettuce = lettuce;
        this.tomato = tomato;
    }

    @Override
    public String toString() {
        return "Burger{" +
                "size=" + size +
                ", cheese=" + cheese +
                ", pepperoni=" + pepperoni +
                ", lettuce=" + lettuce +
                ", tomato=" + tomato +
                '}';
    }

    public static class Builder{
        private int size;
        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean lettuce = false;
        private boolean tomato = false;

        public Builder withSize(int size){
            this.size = size;
            return this;
        }
        public Builder withCheese(boolean cheese){
            this.cheese = cheese;
            return this;
        }
        public Builder withPepperoni(boolean pepperoni){
            this.pepperoni = pepperoni;
            return this;
        }
        public Builder withLettuce(boolean lettuce){
            this.lettuce = lettuce;
            return this;
        }
        public Builder withTomato(boolean tomato){
            this.tomato = tomato;
            return this;
        }

        public Burger build(){
            return new Burger(size, cheese, pepperoni, lettuce, tomato);
        }
    }
}
