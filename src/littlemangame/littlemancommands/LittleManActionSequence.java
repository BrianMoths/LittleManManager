/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import littlemangame.littlemancommands.littleman.LittleMan;

/**
 *
 * @author brian
 */
public final class LittleManActionSequence extends LittleManAction {

    private final List<LittleManAction> littleManActions;
    private final int numActions;
    private int currentAction = 0;

    public LittleManActionSequence(List<LittleManAction> littleManActions) {
        this.littleManActions = new ArrayList<>(littleManActions);
        numActions = littleManActions.size();
    }

    public LittleManActionSequence(LittleManAction... littleManActions) {
        this.littleManActions = new ArrayList(Arrays.asList(littleManActions));
        numActions = this.littleManActions.size();
    }

    @Override
    public boolean doAction(LittleMan littleMan) {
        boolean isComplete = littleManActions.get(currentAction).doAction(littleMan);
        if (isComplete) {
            incrementAction();
            return currentAction == 0;
        } else {
            return false;
        }
    }

    private void incrementAction() {
        currentAction++;
        currentAction %= numActions;
    }

    @Override
    public LittleManAction getResetCopy() {
        List<LittleManAction> littleManActionsCopy = new ArrayList<>(numActions);
        for (LittleManAction littleManAction : littleManActions) {
            littleManActionsCopy.add(littleManAction.getResetCopy());
        }
        return new LittleManActionSequence(littleManActionsCopy);
    }

}
