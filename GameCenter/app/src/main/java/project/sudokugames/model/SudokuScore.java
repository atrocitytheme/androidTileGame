package project.sudokugames.model;

import android.content.Context;

import java.io.IOException;
import java.util.Map;

import project.controller.StorageIndexer;
import project.helper.IOHelper;
import project.model.GameScore;
import project.model.component.User;

/*
 * the model for the data in sliding tile score
 * */
public class SudokuScore extends GameScore {
    private Map<String, int[]> data;

    @SuppressWarnings("unchecked")
    public SudokuScore(Context ctx) {
        String filePath = indexer.index(User.SD_GAME_INDEX, StorageIndexer.SCORE);
        try {
            data = IOHelper.readAndroidMap(filePath, ctx);
        } catch (IOException e) {
            System.out.println("model SudokuScore io error in reading filePath: " + filePath);
        }
    }

    @Override
    public Map<String, int[]> data() {
        return data;
    }
}
