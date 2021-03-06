package tests.slidingtiles;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.controller.system.ScoreBoardSystem;
import project.helper.SequenceBundlers;
import project.model.GameScore;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * test for ScoreBoardSystem
 */
public class ScoreBoardSystemTest {
    @Test
    public void systemTest() {
        GameScore score = mock(GameScore.class);
        Map<String, int[]> preparedMap = new HashMap<>();

        preparedMap.put("asd", new int[]{1,2,3,4});
        when(score.data()).thenReturn(preparedMap);
        ScoreBoardSystem sys = new ScoreBoardSystem(new GameScore[]{score});
        List<SequenceBundlers> bd = sys.displayScore(0);
        SequenceBundlers a = bd.get(0);
        assertEquals("asd", a.getKey());
        assertEquals(1, a.getValue());

    }
}
