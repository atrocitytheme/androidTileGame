package tests.slidingtiles;

import org.junit.Test;

import project.tfgames.model.component.TfTile;

import static org.junit.Assert.*;

public class TFtileTest {
    /**
     * test the functionality of TfTiles
     */
    @Test
    public void testTftile() {
        TfTile tile = new TfTile(1);
        assertEquals(R.drawable.tftile_01, tile.getBackground());
        assertEquals(1, tile.getId());
        tile.setId(11);
        assertEquals(R.drawable.tftile_11, tile.getBackground());
    }
}
