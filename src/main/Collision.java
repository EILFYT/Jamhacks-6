package main;

import main.entities.Player;
import main.objects.Block;
import main.objects.BlockType;

import java.awt.*;

public class Collision {

    public static boolean playerBlock(Point p, Block b) {
        if (b.getType() == BlockType.FINSHING && b.contains(p)) Player.levelcomplete = true;
        return b.contains(p);
    }

}
