package ru.geekbrain.util.parsers;

import ru.geekbrain.domain.Request;

import java.util.Deque;

public interface Parser {
    Request parse(Deque<String> request);
}
