package ui.blueprint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BlueprintGrid extends JPanel {

    private static final int SQUARE_SIZE = 30;

    private final int gridSize;

    private boolean mouseIsPressed = false;
    private int dragStartX = -1;
    private int dragStartY = -1;

    private final BlockGridLayer blockGridLayer;
    private final BlockGridLayer selectionGridLayer;


    public BlueprintGrid(int gridSize) {
        setSize(1000, 1000);
        setVisible(true);

        this.gridSize = gridSize;

        blockGridLayer = new BlockGridLayer(gridSize);
        selectionGridLayer = new BlockGridLayer(gridSize);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                int x=mouseEvent.getX();
                int y=mouseEvent.getY();

                dragSelect(dragStartX, dragStartY, x, y);
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                int x=mouseEvent.getX();
                int y=mouseEvent.getY();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int x=mouseEvent.getX();
                int y=mouseEvent.getY();

                select(x, y);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int x=mouseEvent.getX();
                int y=mouseEvent.getY();
                mouseIsPressed = true;
                dragStartX = x;
                dragStartY = y;
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                int x=mouseEvent.getX();
                int y=mouseEvent.getY();

                if(mouseIsPressed){
                    finalizeDragSelect(dragStartX, dragStartY, x, y);
                }

                mouseIsPressed = false;
                dragStartX = -1;
                dragStartY = -1;
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);

        int gridLength = gridSize * SQUARE_SIZE;

        for(int x = 0; x < gridLength; x+=SQUARE_SIZE){
            for(int y = 0; y < gridLength; y+=SQUARE_SIZE){

                g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);

                if(blockGridLayer.get(getGridLocation(x), getGridLocation(y))) {
                    g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                }

                if(selectionGridLayer.get(getGridLocation(x), getGridLocation(y))){
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.BLACK);
                }
            }
        }
    }

    private int getGridLocation(int pixelLocation){
        // integer division will do proper rounding down
        return handleMaxGridSize(pixelLocation / SQUARE_SIZE);
    }

    private void select(int x, int y){
        blockGridLayer.fill(x, y);

        repaint();
    }

    private void dragSelect(int xStart, int yStart, int xEnd, int yEnd){
        selectionGridLayer.clear();

        System.out.println("drag select " + xStart + "," + yStart + " -> " + xEnd + "," + yEnd);

        int xStartGrid = getGridLocation(xStart);
        int yStartGrid = getGridLocation(yStart);

        int xEndGrid = getGridLocation(xEnd);
        int yEndGrid = getGridLocation(yEnd);

        selectionGridLayer.fillRange(xStartGrid, yStartGrid, xEndGrid, yEndGrid);

        repaint();
    }

    private void finalizeDragSelect(int xStart, int yStart, int xEnd, int yEnd){
        System.out.println("finalize drag select " + xStart + "," + yStart + " -> " + xEnd + "," + yEnd);

        int xStartGrid = getGridLocation(xStart);
        int yStartGrid = getGridLocation(yStart);

        int xEndGrid = getGridLocation(xEnd);
        int yEndGrid = getGridLocation(yEnd);

        blockGridLayer.fillRange(xStartGrid, yStartGrid, xEndGrid, yEndGrid);

        selectionGridLayer.clear();

        repaint();
    }

    private int handleMaxGridSize(int i){
        if(i >= gridSize){
            return gridSize - 1;
        }

        return i;
    }
}