package main;

import main.GameStateManager;
import main.states.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameingPanel extends JPanel implements Runnable, KeyListener {
    private static final long serialVersionUID = 11;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    private Thread thread;
    private boolean isRunning = false;
    private int fps = 60;
    private long targetTime = 1000/fps;
    private GameStateManager manager = new GameStateManager(); //no karen you may not speak with him
    public GameingPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        start();
        addKeyListener(this);
        setFocusable(true);
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
        manager.init();
    }

    public void run() {
        long start, elapse, wait;
       while (isRunning) {
            start = System.nanoTime();
            tick();
            repaint();
            elapse = System.nanoTime() - start;
            wait = targetTime - elapse / 1000000;
            if (wait < 0) wait = 5;
            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
       }
    }

    public void tick() {
        manager.tick();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0,0, WIDTH, HEIGHT);
        manager.draw(g);
    }

    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {
        manager.keyPressed(e.getKeyCode());
    }


    public void keyReleased(KeyEvent e) {
        manager.keyReleased(e.getKeyCode());
    }
}
