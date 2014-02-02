/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littleman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author brian
 */
public class LittleManActionSequence implements LittleManAction {

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
        boolean isActionSequenceComplete = false;
        if (isComplete) {
            incrementAction();
            if (currentAction == 0) {
                isActionSequenceComplete = true;
            }
        }
        return isActionSequenceComplete;
    }

    private void incrementAction() {
        currentAction++;
        currentAction %= numActions;
    }

}
