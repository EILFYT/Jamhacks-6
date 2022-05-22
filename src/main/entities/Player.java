package main.entities;

import main.Collision;
import main.GameStateManager;
import main.GameingPanel;
import main.objects.Block;
import main.objects.BlockType;
import main.states.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Player {
    public static boolean levelcomplete;
    private boolean right = false, left = false, falling = false, jumping = false;
    private boolean topCollision = false;
    Image p;
    private double x, y;
    public static int z;
    private int width, height;
    private double jumpSpeed = 5;
    private double currentJumpSpeed = jumpSpeed;
    private double maxFallSpeed = 5;
    private double currentFallSpeed = 0.1;
    public Player(int width, int height) {
        this.width = width;
        this.height = height;
        x = GameingPanel.WIDTH / 2;
        y = GameingPanel.HEIGHT / 2;
        try {
            p = ImageIO.read(new File("src/resources/char.png"));
        } catch (IOException e) {e.printStackTrace();}
    }

    public void tick(Block[][] b) {
        int iX = (int) x;
        int iY = (int) y;
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (b[i][j] == null) break;
                if (b[i][j].getType() != BlockType.AIR) {
                    if (Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + 2), b[i][j]) ||
                            Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset, iY + (height / 2) + (int) GameState.yOffset), b[i][j])
                            || Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset, iY + height + (int) GameState.yOffset - 1), b[i][j])) {
                        right = false;
                    }
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX + (int) GameState.xOffset - 1, iY + (height / 2) + (int) GameState.yOffset), b[i][j])
                            || Collision.playerBlock(new Point(iX + (int) GameState.xOffset - 1, iY + height + (int) GameState.yOffset - 1), b[i][j])) {
                        left = false;
                    }
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset + 1, iY + (int) GameState.yOffset), b[i][j])
                            || Collision.playerBlock(new Point(iX + (int) GameState.xOffset + (width / 2), iY + (int) GameState.yOffset), b[i][j])
                            || Collision.playerBlock(new Point(iX + (int) GameState.xOffset + width - 1, iY + (int) GameState.yOffset), b[i][j])) {
                        jumping = false;
                        falling = true;
                    }
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset + width + 2, iY + (int) GameState.yOffset + height + 1), b[i][j]) ||
                            Collision.playerBlock(new Point(iX + (int) GameState.xOffset + (width / 2), iY + (int) GameState.yOffset + height + 1), b[i][j]) ||
                            Collision.playerBlock(new Point(iX + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset + height + 1), b[i][j])) {
                        GameState.yOffset = b[i][j].getY() - height - y;
                        //y = b[i][j].getY() - height - GameState.yOffset;
                        falling = false;
                        topCollision = true;
                        break;
                    } else {
                        if (!topCollision && !jumping)
                            falling = true;
                    }
                }
            }
        }
            topCollision = false;
            if (right) GameState.xOffset = GameState.xOffset + 8;
            if (left) GameState.xOffset = GameState.xOffset - 8;
            if (jumping) {
                GameState.yOffset -= currentJumpSpeed;
                currentJumpSpeed -= .1;

                if (currentJumpSpeed <= 0) {
                    currentJumpSpeed = jumpSpeed;
                    jumping = false;
                    falling = true;
                }
            }
            if (falling) {
                GameState.yOffset += currentFallSpeed;
                if (currentFallSpeed < maxFallSpeed) {
                    currentFallSpeed += .1;
                }
            } else {
                currentFallSpeed = .1;
            }

    }

    public void draw(Graphics g) {

       //     g.drawImage(p, GameingPanel.WIDTH / 2 - width / 2, GameingPanel.HEIGHT / 2 - height / 2, width, height, null);
            g.drawImage(p, (int)x, (int)y, width, height, null);
            levelcomplete(g);
    }

    public void keyPressed(int key) {
        if (key == KeyEvent.VK_D) right = true;
        if (key == KeyEvent.VK_A) left = true;
        if (key == KeyEvent.VK_SPACE && !jumping && !falling) jumping = true;
    }

    public void keyReleased(int key) {
        if (key == KeyEvent.VK_D) right = false;
        if (key == KeyEvent.VK_A) left = false;
    }
    public static void levelcomplete(Graphics g) {
        if (!levelcomplete) return;
        if (z == 20) {
            levelcomplete = false;
        }
        String string = "Level Complete!";
        g.setFont(new Font("font", Font.BOLD, 60));
        g.setColor(Color.BLUE);
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int width = fontMetrics.stringWidth(string);
        g.drawString(string, GameingPanel.WIDTH / 2 - width / 2, GameingPanel.HEIGHT / 2 - fontMetrics.getHeight() / 2);
        z++;
    }
}
