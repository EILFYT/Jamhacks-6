package main.objects;

import java.io.File;

public enum BlockType {
    WOOD(new File("src/resources/blocks/wood_full.png")),
    KITCHEN(new File("src/resources/blocks/kitchen_full.png")),
    AIR(null),
    BRICK(new File("src/resources/blocks/brick_full.png")),
    STARTING(new File("src/resources/blocks/spawn_block.png")),
    FINSHING(new File("src/resources/blocks/finishing_flag.png"));
    File file;
    BlockType(File file) {
        this.file = file;
    }
    public File getFile() {
        return file;
    }
}
