package tests.slidingtiles;
import android.content.Context;

import org.junit.Test;

import project.slidinggames.model.SlidingScore;
import project.sudokugames.model.SudokuScore;
import project.tfgames.model.TfScore;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * test for Score
 */
public class ScoreTest {
    @Test
    public void scoreModelTest() {
        Context ctx = mock(Context.class);
        SlidingScore sl = new SlidingScore(0, ctx);
        assertEquals(null, sl.data());
        TfScore tf = new TfScore(ctx);
        assertEquals(null, tf.data());
        SudokuScore sk = new SudokuScore(ctx);
        assertEquals(null, sk.data());
    }
}
