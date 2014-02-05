/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littleman.LittleMan;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public interface Instruction {

    public boolean isOperandNeeded();

    public boolean doInstruction(LittleMan lIttleMan);

    public void acceptOperand(Word operands);

    public Word getOperand();

}
