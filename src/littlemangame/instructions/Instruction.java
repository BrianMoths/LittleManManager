/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littleman.LittleMan;
import littlemangame.littleman.LittleManCommander;

/**
 *
 * @author brian
 */
public interface Instruction {

    public boolean isOperandNeeded();

    public boolean doInstruction(LittleMan lIttleMan);

    public void acceptOperand(int operands);

    public int getOperand();

}
