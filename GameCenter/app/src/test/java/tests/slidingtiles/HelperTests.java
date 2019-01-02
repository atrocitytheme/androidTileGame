package tests.slidingtiles;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannedString;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;

import project.helper.SequenceBundlers;
import project.helper.ActivityHelper;
import project.helper.structure.ArrayStack;
import project.helper.structure.InputFilterMinMax;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class HelperTests {
    @Test
    /**
     * Test the arraystack functionalities
     */
    public void testArrayStack() {
        ArrayStack<Integer> stack = new ArrayStack<>(2000);
        stack.push(3);
        stack.push(4);
        assertEquals(4, (int) stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(3, (int)stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(null, stack.pop());
    }

    @Test
    /**
     *
     */
    public void testSequenceBundler() {
        SequenceBundlers sequenceBundlers1 = new SequenceBundlers("xm", 1);
        SequenceBundlers sequenceBundlers2 = new SequenceBundlers("ly", 3);
        assertEquals(1, sequenceBundlers1.getValue());
        assertEquals("xm", sequenceBundlers1.getKey());
        assertEquals(2, sequenceBundlers2.compareTo(sequenceBundlers1));
    }

    @Test
    /**
     * test input filterMinMax using mock
     */
    public void testInputFilter() {
        InputFilterMinMax mx = new InputFilterMinMax("1", "10");
        String testStr = "1";
        SpannedString word = mock(SpannedString.class);
        when(word.toString()).thenReturn("100");
        String result = (String) mx.filter(testStr, 0, 1, word, 2, 4);
        assertEquals("", result);
        when(word.toString()).thenReturn("0");
        String s = (String) mx.filter(testStr, 0, 1, word, 2, 4);
        assertEquals("", s);
    }

    @Test
    /**
     * test input Activity Helper using mock
     */
    public void testActivityHelper(){
        Context ctx = mock(Context.class);
        Button bt = new Button(ctx);
        TextView tx = new TextView(ctx);
        ActivityHelper.disableButton(bt, tx, "asd");
        Bitmap bmap = mock(Bitmap.class);
        when(bmap.getWidth()).thenReturn(10);
        when(bmap.getHeight()).thenReturn(10);
        Bitmap[][] result = ActivityHelper.splitBitmap(bmap, 5, 5);
        assertEquals(5, result.length);
    }
}
