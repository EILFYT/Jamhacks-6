package main;

import main.states.MenuST;
import main.states.GameState;

import java.awt.*;
import java.io.IOException;
import java.util.Stack;

public class GameStateManager {
    public Stack<GameState> states;
    public GameStateManager() {
        states = new Stack<>();
        states.push(new MenuST(this));
    }
    public void init() {
        states.peek().init();
    }
    public void tick() {
        states.peek().tick();
    }

    public void draw(Graphics g) {
        states.peek().draw(g);
    }

    public void keyPressed(int key) {
        states.peek().keyPressed(key);
    }

    public void keyReleased(int key) {
        states.peek().keyReleased(key);
    }
}
