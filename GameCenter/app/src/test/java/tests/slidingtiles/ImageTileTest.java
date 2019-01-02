package tests.slidingtiles;

import android.graphics.Bitmap;

import org.junit.Test;

import project.slidinggames.model.component.BitmapCollection;
import project.slidinggames.model.component.ImageTile;

import static org.junit.Assert.*;

/**
 * test for image tile
 */
public class ImageTileTest {
    @Test
    public void testImageTile() {
        BitmapCollection.getInstance().loadImage(new Bitmap[10][10]);
        ImageTile tile = new ImageTile(0, 2, 2);
        assertEquals(1, tile.getId());
        assertEquals(null, tile.getBack());
    }
}
