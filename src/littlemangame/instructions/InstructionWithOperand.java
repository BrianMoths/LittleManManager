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
public class InstructionWithOperand implements Instruction {

    private Word operand;
    private final LittleManAction littleManAction;

    public InstructionWithOperand(LittleManAction littleManAction) {
        this.littleManAction = littleManAction;
    }

    public InstructionWithOperand(LittleManAction... littleManActions) {
        this.littleManAction = new LittleManActionSequence(littleManActions);
    }

    @Override
    public boolean isOperandNeeded() {
        return true;
    }

    @Override
    public LittleManAction getAction() {
        return littleManAction;
    }

    @Override
    public void acceptOperand(Word operand) {
        this.operand = operand;
    }

    @Override
    public Word getOperand() {
        return operand;
    }

}
