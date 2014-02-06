/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littleman.LittleManAction;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public interface Instruction {

    public boolean isOperandNeeded();

    public LittleManAction getAction();

    public void acceptOperand(Word operand);

    public Word getOperand();

}
