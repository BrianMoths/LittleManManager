/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public abstract class NoOperandInstruction implements Instruction {

    @Override
    public boolean isOperandNeeded() {
        return false;
    }

    @Override
    public void acceptOperand(Word operands) {
    }

    @Override
    public Word getOperand() {
        throw new AssertionError("getOperand called on an instruction with no Operand");
    }

}
