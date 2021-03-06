package tests.slidingtiles;

import org.junit.Test;

import project.controller.StorageIndexer;

import static org.junit.Assert.assertEquals;

public class StorageIndexerTest {

    @Test
    /**
     * test storage indexer
     */
    public void testIndexAndGetName(){
        StorageIndexer storageIndexer = new StorageIndexer();
        assertEquals("SlidingTiles", storageIndexer.getName(0));
        assertEquals(storageIndexer.index(1, 1), "score_storage_st1.ser");
        assertEquals(storageIndexer.getName(3), "2048");
    }
}
