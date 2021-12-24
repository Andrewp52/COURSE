package ru.geekbrain.structural.brige;

public class Careers implements WebPage{
    private Theme theme;

    public Careers(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String getContent() {
        return "Careers page in " + this.theme.getColor();
    }
}
