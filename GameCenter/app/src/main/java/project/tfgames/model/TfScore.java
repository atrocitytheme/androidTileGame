package project.tfgames.model;

import android.content.Context;

import java.io.IOException;
import java.util.Map;

import project.controller.StorageIndexer;
import project.helper.IOHelper;
import project.model.GameScore;
import project.model.component.User;

/**
 * the model for the data in sliding tile score
 */
public class TfScore extends GameScore {

    /**
     * The map stores some scores for tf game
     */
    private Map<String, int[]> data;

    @SuppressWarnings("unchecked")
    /**
     * To new a TfScore, stores data read from file
     */
    public TfScore(Context ctx) {
        String filePath = indexer.index(User.TF_GAME_INDEX, StorageIndexer.SCORE);
        try {
            data = IOHelper.readAndroidMap(filePath, ctx);
        } catch (IOException e) {
            System.out.println("model TfScore io error in reading filePath: " + filePath);
        }
    }

    @Override
    /**
     * The getter for data
     */
    public Map<String, int[]> data() {
        return data;
    }
}
