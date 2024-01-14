package org.example.snake_ladder_dsa;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


// here tile class - extended with rectangle
public class Tile extends Rectangle {
    public Tile(int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);

        setFill(Color.AZURE);
        setStroke(Color.BLACK);  /// give border
    }
}
