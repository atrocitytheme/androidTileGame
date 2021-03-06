package project.sudokugames.controller;

import java.io.Serializable;

import project.controller.BasicBoardManager;
import project.model.component.User;
import project.sudokugames.model.component.BoardSudoku;
import project.sudokugames.model.component.SudokuTile;

public class BoardManagerSudoku extends BasicBoardManager implements Serializable {

    /**
     * The board of this board manager
     */
    private BoardSudoku boardSudoku;

    /**
     * The current number of steps.
     */
    private int score = 0;

    /**
     * the number of Rows/columns of current board.
     */
    private int boardNumOfRows;
    private int boardNumOfCols;

    /**
     * A 2D array of tiles containing values the user inputs
     */
    private SudokuTile[][] sudokuTiles;

    /**
     * A 2D array of tiles containing values of a completed sudoku board
     */
    private SudokuTile[][] completeTiles;

    /**
     * Constructor of BoardManagerTF
     *
     * @param lengthOfSide The length of the side of the board
     */
    public BoardManagerSudoku(int lengthOfSide) {
        this.boardNumOfCols = this.boardNumOfRows = lengthOfSide;
        this.boardSudoku = new BoardSudoku(lengthOfSide);
        sudokuTiles = boardSudoku.getSudokuTiles();
        completeTiles = boardSudoku.getCompleteTiles();
    }

    @Override
    public BoardSudoku getBoard() {
        return boardSudoku;
    }

    @Override
    public boolean hasWon() {
        boolean win = true;
        for (int i = 0; i < boardNumOfRows; i++)
            for (int j = 0; j < boardNumOfCols; j++)
                if (sudokuTiles[i][j].getId() != completeTiles[i][j].getId()) {
                    win = false;
                    break;
                }
        return win;
    }

    /**
     * Check if the board after inputting from the user is valid
     *
     * @return whether the board if valid after the user inputs numbers
     */
    public boolean checkBoardValidation() {
        boolean valid = true;
        for (int i = 0; i < boardNumOfRows; i++)
            for (int j = 0; j < boardNumOfCols; j++)
                if (sudokuTiles[i][j].getId() != 0 &&
                        sudokuTiles[i][j].getId() != completeTiles[i][j].getId()) {
                    valid = false;
                    break;
                }
        return valid;
    }

    /**
     * Add or change the values of a tile
     *
     * @param newId    New id the user wants to give the tile
     * @param position The postion of the tile
     */
    public void updateSudokuTiles(int newId, int position) {

        int row = position / boardNumOfRows;
        int col = position % boardNumOfCols;
        boardSudoku.getSudokuTiles()[row][col].setId(newId);
        boardSudoku.change();
        boardSudoku.notifyObservers(); // notify the activity to update the view
        sudokuTiles = boardSudoku.getSudokuTiles();
    }

    public boolean isValidTap(int position) {
        int row = position / boardNumOfRows;
        int col = position % boardNumOfCols;
        SudokuTile selectedTile = boardSudoku.getSudokuTiles()[row][col];
        return !selectedTile.generated();
    }

    @Override
    public void addScore() {
        this.score++;
    }

    @Override
    public void minusScore() {
        this.score--;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int getBoardNumOfRows() {
        return this.boardNumOfRows;
    }

    @Override
    public int getBoardNumOfCols() {
        return this.boardNumOfCols;
    }

    @Override
    public int getComplexity() {
        return 9;
    }

    @Override
    public int getGameIndex() {
        return User.SD_GAME_INDEX;
    }

    /**
     * Make sudokuTiles equal to completeTiles (Used for testing)
     */
    public void setSudokuTiles() {
        sudokuTiles = completeTiles;
    }
}
