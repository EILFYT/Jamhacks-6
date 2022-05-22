package main.objects;

import main.states.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Block extends Rectangle {
    int offsetX, offsetY;
    private static final long serialVersionUID = 11;
    public BlockType type;
    public static final int blockSize = 64;
    Image image;

    public Block(int x, int y, BlockType e) {
        setBounds(x, y, blockSize, blockSize);
        type = e;
        if (e.getFile() != null) {
            try {
                image = ImageIO.read(e.getFile());
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }

    public void tick() {
        offsetX= x -(int) GameState.xOffset;
        offsetY= y -(int) GameState.yOffset;
    }

    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
    //    g.fillRect(offsetX, offsetY, width, height);
        if (image != null)
        g.drawImage(image, offsetX, offsetY, width, height, null);

    }
    public BlockType getType() {
        return type;
    }
}
