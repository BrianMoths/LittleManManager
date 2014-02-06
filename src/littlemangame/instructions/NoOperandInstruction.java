/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littleman.LittleManAction;
import littlemangame.littleman.LittleManActionSequence;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class NoOperandInstruction implements Instruction {

    private final LittleManAction littleManAction;

    public NoOperandInstruction(LittleManAction littleManAction) {
        this.littleManAction = littleManAction;
    }

    public NoOperandInstruction(LittleManAction... littleManActions) {
        this.littleManAction = new LittleManActionSequence(littleManActions);
    }

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

    @Override
    public LittleManAction getAction() {
        return littleManAction;
    }

}
