package ru.geekbrain.structural.brige;

public class Usage {
    public static void main(String[] args) {
        Theme dark = new Dark();
        Theme light = new Light();

        WebPage about = new About(dark);
        WebPage careers = new Careers(light);

        System.out.println(about.getContent());
        System.out.println(careers.getContent());

    }
}
