package ru.geekbrain.structural.brige;

public class About implements WebPage{
    private Theme theme;

    public About(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String getContent() {
        return "About page in " + this.theme.getColor();
    }
}
