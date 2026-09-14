package com.snake;

public record GameEvent(
        GameEventType eventType,
        int score
) {
}
