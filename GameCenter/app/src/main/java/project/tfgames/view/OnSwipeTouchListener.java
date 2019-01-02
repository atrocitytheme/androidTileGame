package project.tfgames.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import project.tfgames.controller.MovementControllerTF;
import project.tfgames.controller.BoardManagerTF;

/**
 * This is an adapted class retrieved from
 * https://gist.github.com/beiliubei/6a89b5169fbb45b9fbf7 on
 * Nov, 2018. It's purpose is to detect a "fling" direction of each swiping gesture made by the user.
 *
 * @author Timothy Lee
 */
public class OnSwipeTouchListener implements View.OnTouchListener {

    /**
     * Direction signals expressed by 0,1,2,3.
     */
    public static final int UP_SIGNAL = 0;
    public static final int RIGHT_SIGNAL = 1;
    public static final int DOWN_SIGNAL = 2;
    public static final int LEFT_SIGNAL = 3;

    /**
     * A gesture Detector
     */
    private GestureDetector gestureDetector;

    /**
     * Current Context
     */
    private Context context;

    /**
     * Initializes a new Movement Controller for 2048.
     */
    private MovementControllerTF movementControllerTF = new MovementControllerTF();

    /**
     * Constructor for OnSwipeTouchListener
     *
     * @param c       Context
     * @param manager Board Manager of 2048 game
     */
    public OnSwipeTouchListener(Context c, BoardManagerTF manager) {
        gestureDetector = new GestureDetector(c, new GestureListener());
        context = c;
        setBoardManager(manager);
    }

    @Override
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    /**
     * Class that extends from android.view.GestureDetector.SimpleOnGestureListener
     */
    private final class GestureListener extends SimpleOnGestureListener {

        /**
         * The threshold of swipe and its velocity.
         */
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        // Determines the fling velocity and then fires the appropriate swipe event accordingly
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            swipe(OnSwipeTouchListener.RIGHT_SIGNAL);
                        } else {
                            swipe(OnSwipeTouchListener.LEFT_SIGNAL);
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            swipe(OnSwipeTouchListener.DOWN_SIGNAL);
                        } else {
                            swipe(OnSwipeTouchListener.UP_SIGNAL);
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    /**
     * Process the swipe (gesture)
     *
     * @param signal signal generated by swipe to tell direction
     */
    public void swipe(int signal) {
        movementControllerTF.processTapMovement(context, signal);
    }

    /**
     * Set the 2048 BoardManager
     *
     * @param boardManager 2048 BoardManager
     */
    public void setBoardManager(BoardManagerTF boardManager) {
        movementControllerTF.setBoardManager(boardManager);
    }
}