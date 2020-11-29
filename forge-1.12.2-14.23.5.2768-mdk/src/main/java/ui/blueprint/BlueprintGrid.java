package ui.blueprint;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BlueprintGrid extends JPanel {

    private int squareSize = 32;

    private static final int MAX_X_SIZE = 1000;
    private static final int MAX_Y_SIZE = 1000;
    private static final int MAX_Z_SIZE = 1000;

    private int xSize;
    private int zSize;

    private boolean mouseIsPressed = false;
    private int dragStartX = -1;
    private int dragStartZ = -1;

    private BlockGridLayer[] blockGridLayers;
    private BlockGridLayer[] selectionGridLayers;

    private int yLevel = 0;

    private final TabSelectionPanel tabSelectionPanel;


    public BlueprintGrid(int xSize, int zSize, TabSelectionPanel tabSelectionPanel) {
        setVisible(true);

        setGridSize(xSize, zSize);

        this.tabSelectionPanel = tabSelectionPanel;

        blockGridLayers = new BlockGridLayer[MAX_Y_SIZE];
        blockGridLayers[yLevel] = new BlockGridLayer(MAX_X_SIZE, MAX_Z_SIZE);

        selectionGridLayers = new BlockGridLayer[MAX_Y_SIZE];
        selectionGridLayers[yLevel] = new BlockGridLayer(MAX_X_SIZE, MAX_Z_SIZE);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                int x=mouseEvent.getX();
                int z=mouseEvent.getY();

                if(SwingUtilities.isRightMouseButton(mouseEvent)) {
                    dragRemove(dragStartX, dragStartZ, x, z);
                } else {
                    dragSelect(dragStartX, dragStartZ, x, z);
                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                int x=mouseEvent.getX();
                int z=mouseEvent.getY();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int x=mouseEvent.getX();
                int z=mouseEvent.getY();

                if(SwingUtilities.isRightMouseButton(mouseEvent)){
                    removeBlock(x, z);
                } else {
                    select(x, z);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int x=mouseEvent.getX();
                int z=mouseEvent.getY();
                mouseIsPressed = true;
                dragStartX = x;
                dragStartZ = z;
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                int x=mouseEvent.getX();
                int z=mouseEvent.getY();

                if(mouseIsPressed){
                    if(SwingUtilities.isRightMouseButton(mouseEvent)) {
                        finalizeDragRemove(dragStartX, dragStartZ, x, z);
                    } else {
                        finalizeDragSelect(dragStartX, dragStartZ, x, z);
                    }
                }

                mouseIsPressed = false;
                dragStartX = -1;
                dragStartZ = -1;
            }
        });
    }

    public void setGridSize(int xSize, int zSize){
        this.xSize = xSize;
        this.zSize = zSize;

        setPreferredSize(new Dimension(xSize * squareSize, zSize * squareSize));

        repaint();
        revalidate();
    }

    public void zoomIn(){
        squareSize = squareSize * 2;

        setPreferredSize(new Dimension(xSize * squareSize, zSize * squareSize));

        repaint();
        revalidate();
    }

    public void zoomOut(){
        if(squareSize / 2 == 0){
            return;
        }

        squareSize = squareSize / 2;

        setPreferredSize(new Dimension(xSize * squareSize, zSize * squareSize));

        repaint();
        revalidate();
    }

    public void setYLevel(int yLevel){
        if(yLevel > MAX_Y_SIZE || yLevel < 0){
            return;
        }

        this.yLevel = yLevel;

        if(blockGridLayers[yLevel] == null){
            blockGridLayers[yLevel] = new BlockGridLayer(MAX_X_SIZE, MAX_Z_SIZE);
            selectionGridLayers[yLevel] = new BlockGridLayer(MAX_X_SIZE, MAX_Z_SIZE);
        }

        setPreferredSize(new Dimension(xSize * squareSize, zSize * squareSize));

        repaint();
        revalidate();
    }


    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLACK);

        int xGridLength = xSize * squareSize;
        int zGridLength = zSize * squareSize;

        for(int x = 0; x < xGridLength; x+= squareSize){
            for(int z = 0; z < zGridLength; z+= squareSize){

                g.drawRect(x, z, squareSize, squareSize);

                Block block = blockGridLayers[yLevel].get(getXGridLocation(x), getZGridLocation(z));
                Block selectedBlock = selectionGridLayers[yLevel].get(getXGridLocation(x), getZGridLocation(z));

                if(yLevel > 0){
                    Block blockBelow = blockGridLayers[yLevel - 1].get(getXGridLocation(x), getZGridLocation(z));
                    if(blockBelow != null){
                        drawImage(x, z, g, blockBelow, 0.25f);
                    }
                }

                if(block != null) {
                    drawImage(x, z, g, block, 1.0f);
                }

                if(selectedBlock != null){
                    drawImage(x, z, g, selectedBlock, 0.5f);
                }
            }
        }
    }

    private void drawImage(int x, int z, Graphics g, Block block, float opacity){
        ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

        Image newImage = BlockToImage.getImageForBlock(block, squareSize);
        g.drawImage(newImage, x, z, null);

        ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    private int getXGridLocation(int pixelLocation){
        // integer division will do proper rounding down
        return handleMaxXGridSize(pixelLocation / squareSize);
    }

    private int getZGridLocation(int pixelLocation){
        // integer division will do proper rounding down
        return handleMaxZGridSize(pixelLocation / squareSize);
    }

    private void select(int x, int z){
        blockGridLayers[yLevel].fill(x, z, tabSelectionPanel.getSelectedBlock());

        repaint();
    }

    private void removeBlock(int x, int z){
        blockGridLayers[yLevel].fill(x, z, Blocks.AIR);
    }

    private void dragSelect(int xStart, int zStart, int xEnd, int zEnd){
        selectionGridLayers[yLevel].clear();


        int xStartGrid = getXGridLocation(xStart);
        int zStartGrid = getZGridLocation(zStart);

        int xEndGrid = getXGridLocation(xEnd);
        int zEndGrid = getZGridLocation(zEnd);

        selectionGridLayers[yLevel].fillRange(xStartGrid, zStartGrid, xEndGrid, zEndGrid, tabSelectionPanel.getSelectedBlock());

        repaint();
    }

    private void finalizeDragSelect(int xStart, int zStart, int xEnd, int zEnd){
        int xStartGrid = getXGridLocation(xStart);
        int zStartGrid = getZGridLocation(zStart);

        int xEndGrid = getXGridLocation(xEnd);
        int zEndGrid = getZGridLocation(zEnd);

        blockGridLayers[yLevel].fillRange(xStartGrid, zStartGrid, xEndGrid, zEndGrid, tabSelectionPanel.getSelectedBlock());

        selectionGridLayers[yLevel].clear();

        repaint();
    }

    private void dragRemove(int xStart, int zStart, int xEnd, int zEnd){
        selectionGridLayers[yLevel].clear();


        int xStartGrid = getXGridLocation(xStart);
        int zStartGrid = getZGridLocation(zStart);

        int xEndGrid = getXGridLocation(xEnd);
        int zEndGrid = getZGridLocation(zEnd);

        selectionGridLayers[yLevel].fillRange(xStartGrid, zStartGrid, xEndGrid, zEndGrid, Blocks.AIR);

        repaint();
    }

    private void finalizeDragRemove(int xStart, int zStart, int xEnd, int zEnd){
        int xStartGrid = getXGridLocation(xStart);
        int zStartGrid = getZGridLocation(zStart);

        int xEndGrid = getXGridLocation(xEnd);
        int zEndGrid = getZGridLocation(zEnd);

        blockGridLayers[yLevel].fillRange(xStartGrid, zStartGrid, xEndGrid, zEndGrid, Blocks.AIR);

        selectionGridLayers[yLevel].clear();

        repaint();
    }

    private int handleMaxXGridSize(int i){
        if(i >= xSize){
            return xSize - 1;
        }

        return i;
    }

    private int handleMaxZGridSize(int i){
        if(i >= zSize){
            return zSize - 1;
        }

        return i;
    }
}