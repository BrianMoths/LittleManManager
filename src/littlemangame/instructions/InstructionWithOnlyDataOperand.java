/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littlemancommands.littleman.littlemanutilities.LittleManAction;

/**
 *
 * @author brian
 */
public class InstructionWithOnlyDataOperand extends ParselessInstruction {

    static private final InstructionOperandTypes INSTRUCTION_OPERAND_TYPES = InstructionOperandTypes.DATA_ONLY;

    public InstructionWithOnlyDataOperand(LittleManAction littleManAction) {
        super(INSTRUCTION_OPERAND_TYPES, littleManAction);
    }

    public InstructionWithOnlyDataOperand(LittleManAction... littleManActions) {
        super(INSTRUCTION_OPERAND_TYPES, littleManActions);
    }

}
