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
public class InstructionWithOnlyAddressOperand extends ParselessInstruction {

    static private final InstructionOperandTypes INSTRUCTION_OPERAND_TYPES = InstructionOperandTypes.ADDRESS_ONLY;

    public InstructionWithOnlyAddressOperand(LittleManAction littleManAction) {
        super(INSTRUCTION_OPERAND_TYPES, littleManAction);
    }

    public InstructionWithOnlyAddressOperand(LittleManAction... littleManActions) {
        super(INSTRUCTION_OPERAND_TYPES, littleManActions);
    }

}
