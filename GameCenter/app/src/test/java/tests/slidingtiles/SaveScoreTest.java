package tests.slidingtiles;
import android.content.Context;

import org.junit.Test;

import project.helper.SaveScore;

import static org.mockito.Mockito.*;

/**
 * test for saveScore
 */
public class SaveScoreTest {
    // test the function of score saving
    @Test
    public void testSaveScore() {

        SaveScore sc = new SaveScore();
        Context ctx = mock(Context.class);
        sc.saveScoreIntoMap(ctx, 1, 1);

    }
}
