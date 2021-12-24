package ru.geekbrain.IO.readers;

import java.util.Deque;

public interface RequestReader {
    Deque<String> readRequest();
}
