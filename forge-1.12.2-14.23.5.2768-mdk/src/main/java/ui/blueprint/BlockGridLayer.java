package ui.blueprint;

import net.minecraft.block.Block;

public class BlockGridLayer {

    private final int gridSize;
    private Block[][] blockGrid;

    public BlockGridLayer(int gridSize) {
        this.gridSize = gridSize;
        this.blockGrid = new Block[gridSize][gridSize];
    }

    public void fill(int x, int y, Block block) {
        if (x < 0 || y < 0) {
            return;
        }

        if (gridSize < x || gridSize < y) {
            return;
        }

        blockGrid[x][y] = block;
    }

    public void fillRange(int startX, int startY, int endX, int endY, Block block) {
        if (startX < 0 || startY < 0) {
            return;
        }

        if (gridSize < startX || gridSize < startY) {
            return;
        }

        if (endX < 0 || endY < 0) {
            return;
        }

        if (gridSize < endX || gridSize < endY) {
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
        blockGrid = new Block[gridSize][gridSize];
    }
}
