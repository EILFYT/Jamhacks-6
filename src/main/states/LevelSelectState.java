package main.states;

import main.GameStateManager;
import main.GameingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LevelSelectState extends GameState {
    List<String> files = new ArrayList<>();
    int currSel = 0;

    public LevelSelectState(GameStateManager manager) {
        super(manager);
        files.add("/1-1.map");
        readcfg();
       /* try {
      /*      Files.walk(Paths.get("maps/playermademaps"))
                    .filter(Files::isRegularFile)
                    .forEach((e) -> {files.add(e.toString().replace("\\", "/"));
                        System.out.println(e.toString());});
        } catch (IOException e) {
            e.printStackTrace();
        } */
    }

    private void readcfg() {
        InputStream is = this.getClass().getResourceAsStream("src/resources/cfg.cfg");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        br.lines().forEach((e)-> files.add(e));
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, GameingPanel.WIDTH, GameingPanel.HEIGHT);
        int i = 0;
        for (String file : files) {
            i++;
            if (currSel == i-1)g.setColor(Color.BLUE);
            else g.setColor(Color.GREEN);
            Graphics2D g2d = (Graphics2D) g;
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int width = fontMetrics.stringWidth(file);
            g.drawString(file, GameingPanel.WIDTH / files.size() * i - width / 2, GameingPanel.HEIGHT - fontMetrics.getHeight() / 2);
            g.drawRect(GameingPanel.WIDTH / files.size() * i - width / 2, GameingPanel.HEIGHT - fontMetrics.getHeight() / 2 - 25,
                    width, fontMetrics.getHeight());
        }
    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_RIGHT) {
            currSel++;
            if (currSel >= files.size()) {
                currSel = 0;
            }
        } else if (key == KeyEvent.VK_LEFT) {
            currSel--;
            if (currSel < 0) currSel = files.size() - 1;
        } else if (key == KeyEvent.VK_ENTER) {
            manager.states.push(new Lv1ST(manager, files.get(currSel)));
        }
    }

    @Override
    public void keyReleased(int key) {

    }
}
