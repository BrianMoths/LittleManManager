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
public class InstructionWithBothOperands extends ParselessInstruction {

    static private final InstructionOperandTypes INSTRUCTION_OPERAND_TYPES = InstructionOperandTypes.BOTH;

    public InstructionWithBothOperands(LittleManAction littleManAction) {
        super(INSTRUCTION_OPERAND_TYPES, littleManAction);
    }

    public InstructionWithBothOperands(LittleManAction... littleManActions) {
        super(INSTRUCTION_OPERAND_TYPES, littleManActions);
    }

}
