package main.states;

import main.GameStateManager;
import main.GameingPanel;
import main.objects.Block;
import main.objects.BlockType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;

public class BuilderState extends GameState {

    int row = 0, column = 0;
    static int maxRow = 2, maxColumn = 2;
    public static Block[][] block;

    public BuilderState(GameStateManager manager) {
        super(manager);
        block = new Block[maxRow][maxColumn];
        for (int x = 0; x < maxRow; x++) {
            for (int y = 0; y < maxColumn; y++) {
                block[x][y] = new Block(x, y, BlockType.AIR);
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        for (int c = 1; c <= maxColumn; c++) {
            g.drawLine(GameingPanel.WIDTH / maxColumn * c, GameingPanel.HEIGHT, GameingPanel.WIDTH / maxColumn * c, 0);
        }
        for (int r = 1; r<=maxRow; r++) {
            g.drawLine(GameingPanel.WIDTH, GameingPanel.HEIGHT / maxRow * r, 0, GameingPanel.HEIGHT / maxRow * r);
        }
        g.setColor(Color.RED);
        g.drawRect(GameingPanel.WIDTH / maxColumn * column, GameingPanel.HEIGHT / maxRow * row,
                GameingPanel.WIDTH / maxColumn, GameingPanel.HEIGHT / maxRow);

        for (Block[] block1 : block) {
            for (Block block2 : block1) {
                Image brick = null;
                Image start = null;
                Image end = null;
                BlockType type = block2.getType();
                if (block2.getType() != null && block2.getType() != BlockType.AIR) {
                    if (block2.getType() == BlockType.BRICK)
                        type = BlockType.BRICK;
                    else if (block2.getType() == BlockType.FINSHING)
                        type = BlockType.FINSHING;
                    else if (block2.getType() == BlockType.STARTING)
                        type = BlockType.FINSHING;


                    try {
                        assert type != null;
                        brick = ImageIO.read(BlockType.BRICK.getFile());
                        start = ImageIO.read(BlockType.STARTING.getFile());
                        end = ImageIO.read(BlockType.FINSHING.getFile());
                    } catch (IOException ignored) {
                    }
                    if (block2.getType() == BlockType.BRICK)
                    g.drawImage(brick, GameingPanel.WIDTH / maxColumn * (int) (block2.getX()), GameingPanel.HEIGHT / maxRow * (int) (block2.getY()),
                            GameingPanel.WIDTH / maxColumn, GameingPanel.HEIGHT / maxRow, null);
                    if (block2.getType() == BlockType.STARTING)
                        g.drawImage(start, GameingPanel.WIDTH / maxColumn * (int) (block2.getX()), GameingPanel.HEIGHT / maxRow * (int) (block2.getY()),
                                GameingPanel.WIDTH / maxColumn, GameingPanel.HEIGHT / maxRow, null);
                    if (block2.getType() == BlockType.FINSHING)
                        g.drawImage(end, GameingPanel.WIDTH / maxColumn * (int) (block2.getX()), GameingPanel.HEIGHT / maxRow * (int) (block2.getY()),
                                GameingPanel.WIDTH / maxColumn, GameingPanel.HEIGHT / maxRow, null);
                }
            }
        }
    }

    @Override
    public void keyPressed(int key) {//3
        if (key == KeyEvent.VK_UP) {
            row--;
            if (row < 0) {row = maxRow - 1;}
        } else if (key == KeyEvent.VK_DOWN) {
            row++;
            if (row >= maxRow) {row = 0;}
        } else if (key == KeyEvent.VK_RIGHT) {
            column++;
            if (column >= maxColumn) {column = 0;}
        } else  if (key == KeyEvent.VK_LEFT) {
            column--;
            if (column < 0) {column = maxColumn - 1;}
        } else if (key == KeyEvent.VK_SPACE) {
            if (block[row][column] == null || block[row][column].getType() == BlockType.AIR) {
                block[row][column] = new Block(column, row, BlockType.BRICK);}
            else if (block[row][column].getType() == BlockType.BRICK) {block[row][column] = new Block(column, row, BlockType.FINSHING);}
            else if (block[row][column].getType() == BlockType.FINSHING) {block[row][column] = new Block(column, row, BlockType.STARTING);}
            else {block[row][column] = new Block(column, row, BlockType.AIR);}
        } else if (key == KeyEvent.VK_ENTER) {
            saveFile();
        }
    }

    @Override
    public void keyReleased(int key) {

    }

    public void saveFile() {
        File file = new File("src/resources/savedfiles/newsave.map");
        try(FileWriter fw = new FileWriter(file)) {
            fw.write(maxColumn + "\n");
            fw.write(maxRow + "\n");
            file.delete();
            file.createNewFile();
            for (Block[] block1 : block) {
                StringBuilder sb = new StringBuilder();
                for (Block block2 : block1) {
                    if (block2.getType() == BlockType.AIR)
                        sb.append(0).append(" ");
                    else if (block2.getType() == BlockType.BRICK )sb.append(1).append(" ");
                    else if (block2.getType() == BlockType.FINSHING )sb.append(2).append(" ");
                    else if (block2.getType() == BlockType.STARTING )sb.append(3).append(" ");
                }
                sb.append("\n");
                fw.write(sb.toString());
            }
        } catch (IOException ignored) {
        }
    }
}


