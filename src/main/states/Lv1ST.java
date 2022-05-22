package main.states;

import main.GameStateManager;
import main.GameingPanel;
import main.entities.Player;
import main.maps.Map;
import main.objects.Block;
import main.objects.BlockType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Lv1ST extends GameState {

    private Player player = new Player(66, 90);
    private Map map;
  //  private Block b1, b2, b3;
    public Lv1ST(GameStateManager manager) {
        super(manager);
        map = new Map("/1-1.map");
        GameState.xOffset = xOffset2;
        GameState.yOffset = yOffset2;
//        b = new Block[6];
//        b[0] = new Block(692, 500, BlockType.WOOD);
//        b[1] = new Block(628, 500, BlockType.KITCHEN);
//        b[2] = new Block(372, 500, BlockType.BRICK);
//        b[3] = new Block(500, 500, BlockType.WOOD);
//        b[4] = new Block(436, 500, BlockType.KITCHEN);
//        b[5] = new Block(564, 500, BlockType.BRICK);
     //   b1 = new Block(200, 200, BlockType.BRICK);
   //     b2 = new Block(300, 200, BlockType.KITCHEN);
     //   b3 = new Block(100, 200, BlockType.BRICK);
    }

    public void init() {
        map = new Map("/1-1.map");
    player = new Player(66, 90);
    }

    public void tick() {

        if (GameState.yOffset >= 1000) {xOffset = 700; yOffset = 830;} //TODO: FIX
        player.tick(map.getBlocks());
        Block[][] e = map.getBlocks();
        for (Block[] ee : e) {
            for (Block eee : ee) {
                eee.tick();
            }
        }
    //    for (Block block : b) {
     //       block.tick();
    //    }
    }

    public void draw(Graphics g) {
        Image i;
        try {
            i = ImageIO.read(new File("src/resources/bakcgrounds/world1.png"));
            g.drawImage(i, 0, 0, GameingPanel.WIDTH, GameingPanel.HEIGHT, null);
        } catch (IOException e) {
            e.printStackTrace();
            g.setColor(Color.CYAN);
            g.drawRect(0, 0, GameingPanel.WIDTH, GameingPanel.HEIGHT);
        }
        player.draw(g);
     //   for (Block bl : b) {
     //       bl.draw(g);
      //  }
        map.draw(g);
    //    b1.draw(g);
     //   b2.draw(g);
      //  b3.draw(g);
        if (Player.z == 20) {manager.states.clear();
        manager.states.add(new MenuST(manager));
        Player.z = 0;
        }
    }

    public void keyPressed(int key) {
        player.keyPressed(key);
    }

    public void keyReleased(int key) {
        player.keyReleased(key);
    }
}
