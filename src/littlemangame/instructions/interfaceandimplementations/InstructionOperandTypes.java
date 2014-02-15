/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions.interfaceandimplementations;

import littlemangame.littlemancommands.LittleManAction;

/**
 *
 * @author brian
 */
public enum InstructionOperandTypes {

    NEITHER(false, false),
    DATA_ONLY(true, false),
    ADDRESS_ONLY(false, true),
    BOTH(true, true);
    private final boolean isDataOperandNeeded, isAddressOperandNeeded;

    private InstructionOperandTypes(boolean isDataOperandNeeded, boolean isAddressOperandNeeded) {
        this.isDataOperandNeeded = isDataOperandNeeded;
        this.isAddressOperandNeeded = isAddressOperandNeeded;
    }

    boolean isDataOperandNeeded() {
        return isDataOperandNeeded;
    }

    boolean isAddressOperandNeeded() {
        return isAddressOperandNeeded;
    }

    Instruction makeInstruction(LittleManAction littleManAction) {
        return new ParselessInstruction(this, littleManAction);
    }

    Instruction makeInstruction(LittleManAction... littleManAction) {
        return new ParselessInstruction(this, littleManAction);
    }

}
