/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littlemancommands.LittleManAction;

/**
 *
 * @author brian
 */
public class NoOperandInstruction extends ParselessInstruction {

    static private final InstructionOperandTypes INSTRUCTION_OPERAND_TYPES = InstructionOperandTypes.NEITHER;

    public NoOperandInstruction(LittleManAction littleManAction) {
        super(INSTRUCTION_OPERAND_TYPES, littleManAction);
    }

    public NoOperandInstruction(LittleManAction... littleManActions) {
        super(INSTRUCTION_OPERAND_TYPES, littleManActions);
    }

}
