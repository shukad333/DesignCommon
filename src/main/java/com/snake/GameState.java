package com.snake;

public interface GameState {

    void start(Game game);
    void pause(Game game);
    void resume(Game game);
    void tick(Game game);
}
