/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import littleman.LittleMan;

/**
 *
 * @author brian
 */
public interface Instruction {

    public boolean isOperandNeeded();

    public boolean doInstruction(LittleMan lIttleMan);

    public void acceptOperands(int... operands);

}
