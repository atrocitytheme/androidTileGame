package project.helper;

/**
 * factory pattern for creating tiles, making it easier for us to decide the initialization of instances
 */

import java.io.Serializable;

import project.model.component.BasicTile;
import project.slidinggames.model.component.ImageTile;
import project.slidinggames.model.component.Tile;
import project.sudokugames.model.component.SudokuTile;
import project.tfgames.model.component.TfTile;

public class TileFactory implements Serializable {

    /**
     * to create a TF tile or an Sudoku tile.
     *
     * @param id   the background id of the tile
     * @param type the type of tile you want to create
     * @return a TF tile or a Sudoku tile
     */
    public BasicTile createTile(int id, String type) {
        if (type.equals("TfTile")) {
            return new TfTile(id);
        } else {
            return new SudokuTile(id);
        }
    }

    /**
     * to create a sliding tile or an image tile.
     *
     * @param background the background id of the tile
     * @param numRow     the position of row you want for this tile
     * @param numCol     the position of col you want for this tile
     * @param type       the type of tile you want to create
     * @return a sliding tile or an image tile
     */
    public BasicTile createTile(int background, int numRow, int numCol, String type) {
        if (type.equals("imageTile")) {
            return new ImageTile(background, numCol, numRow);
        } else {
            return new Tile(background, numCol, numRow);
        }
    }
}
