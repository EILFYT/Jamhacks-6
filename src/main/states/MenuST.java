package main.states;

import main.GameStateManager;
import main.GameingPanel;
import main.states.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class MenuST extends GameState {

    private String[] options = {"Start", "Map Builder", "Quit"};
    private int currentSelection = 0;
    public MenuST(GameStateManager manager) {
        super(manager);
    }

    public void init() {

    }

    public void tick() {

    }

    public void draw(Graphics g) {
        Image i;
        Image j;
        try {
            i = ImageIO.read(new File("src/resources/bakcgrounds/menu.png"));
            j = ImageIO.read(new File("src/resources/logo.png"));
            g.drawImage(i, 0, 0, GameingPanel.WIDTH, GameingPanel.HEIGHT, null);
            g.drawImage(j, GameingPanel.WIDTH / 2 - j.getWidth(null) * 2, GameingPanel.HEIGHT / 4 - j.getHeight(null) * 2,
                    j.getWidth(null) * 4, j.getHeight(null) * 4, null);
        } catch (IOException e) {
            e.printStackTrace();
            g.setColor(Color.CYAN);
            g.drawRect(0, 0, GameingPanel.WIDTH, GameingPanel.HEIGHT);
        }
        for (int ie = 0; ie < options.length; ie++) {
            if (ie == currentSelection) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLUE);
            }
            Graphics2D g2d = (Graphics2D) g;
            g.setFont(new Font("font", Font.BOLD, 24));
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int width = fontMetrics.stringWidth(options[ie]);
            g.drawString(options[ie], GameingPanel.WIDTH / 2 - width / 2 + (ie - 1) * 200, GameingPanel.HEIGHT / 4 * 3);



            int height = fontMetrics.getHeight();
            //int y = GameingPanel.HEIGHT / 4 + (ie - 1) * 30 - height + 2;
                g.drawRect(GameingPanel.WIDTH / 2 - width / 2 + (ie - 1) * 200, GameingPanel.HEIGHT / 4 * 3 - 25,
                        width, height);

        }
    }

    public void keyPressed(int key) {
        if (key == KeyEvent.VK_RIGHT) {
            currentSelection++;
            if (currentSelection >= options.length) currentSelection = 0;
        } else if (key == KeyEvent.VK_LEFT) {
            currentSelection--;
            if (currentSelection < 0) currentSelection = options.length - 1;
        } else if (key == KeyEvent.VK_ENTER) {
            if (currentSelection == 0) {
            manager.states.push(new LevelSelectState(manager));
            } else if (currentSelection == 1) {
                manager.states.push(new BuilderSizeSelectorState(manager));
            } else {
                System.exit(0);
            }
        }
    }

    public void keyReleased(int key) {

    }
}
