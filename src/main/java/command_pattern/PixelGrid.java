package command_pattern;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;




class PixelGrid {
    private static final int GRID_SIZE = 8;
    private final Rectangle[][] pixels = new Rectangle[GRID_SIZE][GRID_SIZE];
    private int selectedRow = 0;
    private int selectedCol = 0;

    public PixelGrid(GridPane grid, int pixelSize) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle pixel = new Rectangle(pixelSize, pixelSize);
                pixel.setFill(Color.WHITE);
                pixel.setStroke(Color.LIGHTGRAY);
                pixels[row][col] = pixel;
                grid.add(pixel, col, row);
            }
        }
        highlight();
    }
    public void move(int dRow, int dCol) {
        clearHighlight();
        selectedRow = Math.max(0, Math.min(pixels.length - 1, selectedRow + dRow));
        selectedCol = Math.max(0, Math.min(pixels[0].length - 1, selectedCol + dCol));
        highlight();
    }

    public void toggle() {
        Rectangle pixel = pixels[selectedRow][selectedCol];
        pixel.setFill(pixel.getFill() == Color.BLACK ? Color.WHITE : Color.BLACK);
    }

    private void highlight() {
        pixels[selectedRow][selectedCol].setStroke(Color.GREEN);
        pixels[selectedRow][selectedCol].setStrokeWidth(2);
    }

    private void clearHighlight() {
        pixels[selectedRow][selectedCol].setStroke(Color.LIGHTGRAY);
        pixels[selectedRow][selectedCol].setStrokeWidth(1);
    }

    public void print(){
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                System.out.print(pixels[i][j].getFill() == Color.BLACK ? "X " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
