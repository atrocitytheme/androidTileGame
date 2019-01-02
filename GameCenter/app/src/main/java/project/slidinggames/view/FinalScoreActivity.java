package project.slidinggames.view;

import android.os.Bundle;

import project.BasicFinalScoreActivity;
import project.controller.system.GameCacheSystem;
import project.controller.system.UserPanel;

/**
 * Excluded from tests because it's a view class.
 * The FinalScore activity to show player their score after completed sliding Tile game.
 */
public class FinalScoreActivity extends BasicFinalScoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * set the boardManager
     */
    @Override
    public void setBM() {
        GameCacheSystem sys = GameCacheSystem.getInstance();
        currentBM = sys.get(UserPanel.getInstance().getName());
    }
}
