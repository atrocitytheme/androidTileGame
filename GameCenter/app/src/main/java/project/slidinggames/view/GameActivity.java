package project.slidinggames.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import project.controller.system.GameCacheSystem;
import project.slidinggames.controller.MovementControllerST;
import project.slidinggames.controller.BoardManager;
import project.GestureDetectGridView;
import tests.slidingtiles.R;
import project.slidinggames.model.component.BitmapCollection;
import project.slidinggames.model.component.ImageTile;
import project.slidinggames.model.component.Board;
import project.CustomAdapter;
import project.controller.system.UserPanel;

/**
 * Excluded from tests because it's a view class.
 * The game activity for slidingTile game.
 */
public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The buttons to display.
     */
    private List<Button> tileButtons;

    /**
     * The Grid View and calculated column height and width based on device size
     */
    private GestureDetectGridView gridView;

    /**
     * The width and height of column for current view
     */
    private static int columnWidth, columnHeight;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameCacheSystem sys = GameCacheSystem.getInstance();
        boardManager = (BoardManager) sys.get(UserPanel.getInstance().getName());
        UserPanel.getInstance().play();

        createTileButtons(this);
        setContentView(R.layout.activity_main);

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setController(new MovementControllerST()); // set the controller
        gridView.setNumColumns(boardManager.getBoardNumOfCols());
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);

        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / boardManager.getBoardNumOfCols();
                        columnHeight = displayHeight / boardManager.getBoardNumOfRows();
                        display();
                    }
                });
        addUndoButtonListener();
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != boardManager.getBoardNumOfRows(); row++) {
            for (int col = 0; col != boardManager.getBoardNumOfCols(); col++) {
                Button tmp = new Button(context);
                if (!BitmapCollection.getInstance().isLocked()) {
                    Drawable drawable = new BitmapDrawable(tmp.getResources(),
                            ((ImageTile) board.getTile(row, col)).getBack());
                    tmp.setBackground(drawable);
                } else {
                    tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                }
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        Board board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoardNumOfRows();
            int col = nextPos % boardManager.getBoardNumOfCols();
            if (!BitmapCollection.getInstance().isLocked()) {
                Drawable drawable = new BitmapDrawable(b.getResources(),
                        ((ImageTile) board.getTile(row, col)).getBack());
                b.setBackground(drawable);
            } else {
                b.setBackgroundResource(board.getTile(row, col).getBackground());
            }
            nextPos++;
        }
        GameCacheSystem sys = GameCacheSystem.getInstance();
        sys.update(UserPanel.getInstance().getName(), boardManager);
        sys.save(this);
    }

    /**
     * The maximum undo steps that the user sets.
     */
    static int maxUndoSteps;

    /**
     * Activate the undo button.
     */
    private void addUndoButtonListener() {
        final ImageView undo = findViewById(R.id.imageView2);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gridView.stackIsEmpty()) {
                    cannotUndoText();
                    return;
                }
                if (maxUndoSteps > 0) {
                    int position = gridView.getUndoPop();
                    boardManager.touchMove(position);
                    GameCacheSystem sys = GameCacheSystem.getInstance();
                    sys.update(UserPanel.getInstance().getName(), boardManager);
                    boardManager.minusScore();
                    maxUndoSteps--;
                }
                remainedUndoText();
                display();
            }
        });
    }

    /**
     * Display error message as toast when no steps are made, but undo is clicked.
     */
    private void cannotUndoText() {
        Toast.makeText(this, "You should make a move first!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display remaining undo steps as toast.
     */
    private void remainedUndoText() {
        Toast.makeText(this, "Remained undo steps: " + maxUndoSteps, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BitmapCollection.getInstance().latch(true);
    }
}
