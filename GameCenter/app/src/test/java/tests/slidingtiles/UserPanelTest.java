package tests.slidingtiles;

import org.junit.Test;

import project.controller.system.UserPanel;
import project.model.component.User;

import static org.junit.Assert.*;
/**
 * test the UserPanel
 */
public class UserPanelTest {
    @Test
    public void testPlayState() {
        UserPanel panel = UserPanel.getInstance();
        User user = new User("ad", "asd");
        panel.setUser(user);
        assertEquals("ad", panel.getName());
        assertFalse(panel.isPlayed());
        panel.play();
        assertTrue(panel.isPlayed());
        assertEquals(user, panel.getUser());
    }
}
