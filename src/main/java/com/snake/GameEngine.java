package com.snake;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private List<GameEventListener> listeners = new ArrayList<>();

    private int score;
    private GameState gameState;


}
