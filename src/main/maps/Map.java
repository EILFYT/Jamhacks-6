package main.maps;

import main.objects.Block;
import main.objects.BlockType;
import main.states.GameState;

import java.awt.*;
import java.io.*;

public class Map {
    protected String path;
    private int width, height;
    private Block[][] blocks;
    public Map(String str) {
        path = str;
        blocks = new Block[height][width];
        loadMap();
    }

    public void draw(Graphics g) {
        for (Block[] block : blocks) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[j] != null)
                    block[j].draw(g);
            }
        }
    }
    public void loadMap() {
        InputStream is = this.getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                width = Integer.parseInt(br.readLine());
                height = Integer.parseInt(br.readLine());
                blocks = new Block[height][width];
                for (int i = 0;i < height;i++) {
                    String line = br.readLine();
                    int ii = 0;
                    for (String s : line.split("\\s+")) {
                        if (s.equals("0"))
                            blocks[i][ii] = new Block(ii * Block.blockSize, i * Block.blockSize, BlockType.AIR);
                        if (s.equals("1"))
                        blocks[i][ii] = new Block(ii * Block.blockSize, i * Block.blockSize, BlockType.BRICK);
                        if (s.equals("2"))
                            blocks[i][ii] = new Block(ii * Block.blockSize, i * Block.blockSize, BlockType.FINSHING);
                        if (s.equals("3")) {
                            blocks[i][ii] = new Block(ii * Block.blockSize, i * Block.blockSize, BlockType.STARTING);
                            GameState.xOffset2 = blocks[i][ii].getX();
                            GameState.yOffset2 = blocks[i][ii].getCenterY();
                        }
                        ii++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    public Block[][] getBlocks() {
        return blocks;
    }
}
