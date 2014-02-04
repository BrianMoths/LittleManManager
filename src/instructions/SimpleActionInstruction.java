/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import littleman.LittleMan;
import littleman.LittleManAction;

/**
 *
 * @author brian
 */
public class SimpleActionInstruction implements Instruction {

    private final LittleManAction littleManAction;

    public SimpleActionInstruction(LittleManAction littleManAction) {
        this.littleManAction = littleManAction;
    }

    @Override
    public boolean isOperandNeeded() {
        return false;
    }

    @Override
    public boolean doInstruction(LittleMan lIttleMan) {
        return lIttleMan.doAction(littleManAction);
    }

    @Override
    public void acceptOperands(int... operands) {
    }

}
