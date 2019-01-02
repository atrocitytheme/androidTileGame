package project.controller;

import android.content.Context;

import project.helper.structure.ArrayStack;

public abstract class MovementController {

    /**
     * The collection of the previous states, which is responsible to record what happens after
     * previous step
     */

    /**
     * An ArrayStack of previous states
     */
    public ArrayStack stateStack;

    /**
     * An abstract method to be implemented that sets thw wanted boardManager
     */
    public abstract void setBoardManager(BasicBoardManager boardManager);

    /**
     * An abstract method to be implemented that process a given "tap"/move
     *
     * @param context  context of the current game
     * @param position a position on the board
     */
    public abstract void processTapMovement(Context context, int position);

}
