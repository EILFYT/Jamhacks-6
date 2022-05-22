package main.states;

import main.GameStateManager;
import main.GameingPanel;
import main.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

public class BuilderSizeSelectorState extends GameState {

    public BuilderSizeSelectorState(GameStateManager manager) {
        super(manager);
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {
        String string = "current amount of rows is: " + BuilderState.maxRow + ", and the current number of columns is: " + BuilderState.maxColumn;
        String string2 = "to change the numbers, press the up, down, left, and right arrow keys!";
        String string3 = "press enter when you are complete!";
        String string4 = "on the next page, use the arrow keys to move the highlighted block,";
        String string5 = "and press the space bar to swap that block between a block and air,";
        String string6 = "and the enter button saves the map!";
        g.setFont(new Font("font", Font.BOLD, 24));
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int width = fontMetrics.stringWidth(string);
        int width2 = fontMetrics.stringWidth(string2);
        int width3 = fontMetrics.stringWidth(string3);
        int width4 = fontMetrics.stringWidth(string4);
        int width5 = fontMetrics.stringWidth(string5);
        int width6 = fontMetrics.stringWidth(string6);


        g.drawString(string, GameingPanel.WIDTH/2 - width / 2, GameingPanel.HEIGHT / 2);
        g.drawString(string2, GameingPanel.WIDTH/2 - width2 / 2, GameingPanel.HEIGHT/2 - (int)(fontMetrics.getHeight() * 2));
        g.drawString(string3, GameingPanel.WIDTH/2 - width3 / 2, GameingPanel.HEIGHT/2 - (int)(fontMetrics.getHeight()));
        g.drawString(string4, GameingPanel.WIDTH/2 - width4 / 2, GameingPanel.HEIGHT/2 - (int)(fontMetrics.getHeight() * -2));
        g.drawString(string5, GameingPanel.WIDTH/2 - width5 / 2, GameingPanel.HEIGHT/2 - (int)(fontMetrics.getHeight() * -3));
        g.drawString(string6, GameingPanel.WIDTH/2 - width6 / 2, GameingPanel.HEIGHT/2 - (int)(fontMetrics.getHeight() * -4));
    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_UP) {
            BuilderState.maxRow++;
        } else if (key == KeyEvent.VK_DOWN) {
            BuilderState.maxRow--;
        } else if (key == KeyEvent.VK_RIGHT) {
            BuilderState.maxColumn++;
        } else if (key == KeyEvent.VK_LEFT) {
            BuilderState.maxColumn--;
        } else if (key == KeyEvent.VK_ENTER) {
            manager.states.add(new BuilderState(manager));
        }
    }

    @Override
    public void keyReleased(int key) {

    }
}
