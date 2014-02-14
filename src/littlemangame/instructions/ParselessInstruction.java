/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littlemancommands.LittleManAction;
import littlemangame.littlemancommands.LittleManActionSequence;

/**
 *
 * @author brian
 */
class ParselessInstruction implements Instruction {

    private final InstructionOperandTypes instructionOperandTypes;
    private final LittleManAction littleManAction;

    ParselessInstruction(InstructionOperandTypes instructionOperandTypes, LittleManAction littleManAction) {
        this.instructionOperandTypes = instructionOperandTypes;
        this.littleManAction = littleManAction;
    }

    ParselessInstruction(InstructionOperandTypes instructionOperandTypes, LittleManAction... littleManActions) {
        this(instructionOperandTypes, new LittleManActionSequence(littleManActions));
    }

    @Override
    public boolean isDataOperandNeeded() {
        return instructionOperandTypes.isDataOperandNeeded();
    }

    @Override
    public LittleManAction getAction() {
        return littleManAction;
    }

    @Override
    public boolean isAddressOperandNeeded() {
        return instructionOperandTypes.isAddressOperandNeeded();
    }

}
