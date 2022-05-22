package main.states;

import main.GameStateManager;

import java.awt.*;
import java.io.IOException;

public abstract class GameState {
    protected GameStateManager manager;
    public static double xOffset, yOffset, xOffset2, yOffset2;
    public static int builderWidth, builderHeight;
    public GameState(GameStateManager manager) {
        this.manager = manager;
        xOffset = 400;
        yOffset = -300;
    }

    public abstract void init();
    public abstract void tick();
    public abstract void draw(Graphics g);
    public abstract void keyPressed(int key);
    public abstract void keyReleased(int key);
}
