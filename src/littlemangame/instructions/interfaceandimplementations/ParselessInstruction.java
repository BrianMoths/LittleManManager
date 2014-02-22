/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions.interfaceandimplementations;

import littlemangame.littlemancommands.LittleManCommands.LittleManAction;
import littlemangame.littlemancommands.LittleManCommands.LittleManActionSequence;

/**
 *
 * @author brian
 */
public class ParselessInstruction implements Instruction {

    private final InstructionOperandTypes instructionOperandTypes;
    private final LittleManAction littleManAction;

    public ParselessInstruction(InstructionOperandTypes instructionOperandTypes, LittleManAction littleManAction) {
        this.instructionOperandTypes = instructionOperandTypes;
        this.littleManAction = littleManAction;
    }

    public ParselessInstruction(InstructionOperandTypes instructionOperandTypes, LittleManAction... littleManActions) {
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

    @Override
    public Instruction getResetCopy() {
        return new ParselessInstruction(instructionOperandTypes, littleManAction.getResetCopy());
    }

}
