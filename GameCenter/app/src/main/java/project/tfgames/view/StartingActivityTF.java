package project.tfgames.view;

import android.content.Intent;
import android.os.Bundle;

import project.BasicStartingActivity;
import tests.slidingtiles.R;

/**
 * Excluded from tests because it's a view class.
 * The initial activity for the 2048 game, from which user can start a game or view the scoreBoard.
 */
public class StartingActivityTF extends BasicStartingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setView() {
        setContentView(R.layout.activity_starting_tf);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    @Override
    public void switchToGame() {
        Intent tmp = new Intent(this, GameActivityTF.class);
        startActivity(tmp);
    }

    /**
     * Switch to the ScoreBoardActivity Activity view to see global scoreboard.
     */
    @Override
    public void switchToScoreboard() {
        Intent tmp = new Intent(this, ScoreboardtfActivity.class);
        startActivity(tmp);
    }
}
