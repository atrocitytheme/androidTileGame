package project.model;

import java.io.Serializable;
import java.util.Map;

import project.controller.StorageIndexer;

/**
 * abstract class for defining the score model
 */
public abstract class GameScore implements Serializable {

    /**
     * return the raw data.
     */
    public abstract Map<String, int[]> data();

    /**
     * the indexer to help identify games
     */
    protected StorageIndexer indexer = new StorageIndexer();
}
