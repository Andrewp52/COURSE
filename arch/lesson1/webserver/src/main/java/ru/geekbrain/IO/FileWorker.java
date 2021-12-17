package ru.geekbrain.IO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileWorker {
    private String home;

    public FileWorker(String home) {
        this.home = home;
    }

    public String readFile(String url) throws IOException {
        Path path = Path.of(home, url);
        StringBuilder body = new StringBuilder();
        if (!Files.exists(path)) {
            throw new FileNotFoundException("<H1>File not found</H1>");
        }
        Files.newBufferedReader(path).lines().forEach(s -> body.append(s));
        return body.toString();
    }

    public void writeFile(String url, String body) throws IOException {
        Path path = Path.of(home, url);
        if(!Files.exists(path)){
            throw new FileNotFoundException("<H1>File not found</H1>");
        }
        BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING);
        writer.write(body);
        writer.flush();
        writer.close();
    }
}
