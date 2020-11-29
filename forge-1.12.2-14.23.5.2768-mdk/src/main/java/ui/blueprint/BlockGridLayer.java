package ui.blueprint;

import net.minecraft.block.Block;

public class BlockGridLayer {

    private final int xSize;
    private final int ySize;

    private Block[][] blockGrid;

    public BlockGridLayer(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;

        this.blockGrid = new Block[xSize][ySize];
    }

    public void fill(int x, int y, Block block) {
        if (x < 0 || y < 0) {
            return;
        }

        if (xSize < x || ySize < y) {
            return;
        }

        blockGrid[x][y] = block;
    }

    public void fillRange(int startX, int startY, int endX, int endY, Block block) {
        if (startX < 0 || startY < 0) {
            return;
        }

        if (xSize < startX || ySize < startY) {
            return;
        }

        if (endX < 0 || endY < 0) {
            return;
        }

        if (xSize < endX || ySize < endY) {
            return;
        }

        int startFillX = startX;
        int startFillY = startY;
        int endFillX = endX;
        int endFillY = endY;

        // make sure we also fill in a consistent direction
        if (startX > endX) {
            startFillX = endX;
            endFillX = startX;
        }

        if (startY > endY) {
            startFillY = endY;
            endFillY = startY;
        }

        for (int x = startFillX; x <= endFillX; ++x) {
            for (int y = startFillY; y <= endFillY; ++y) {
                fill(x, y, block);
            }
        }
    }

    public Block get(int x, int y) {
        return blockGrid[x][y];
    }

    public void clear() {
        blockGrid = new Block[xSize][ySize];
    }
}
