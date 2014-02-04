/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littleman.LittleMan;

/**
 *
 * @author brian
 */
public class NoOpInstruction implements Instruction {

    static public NoOpInstruction decodeInstruction(int word) {
        return new NoOpInstruction();
    }

    @Override
    public boolean isOperandNeeded() {
        return false;
    }

    @Override
    public boolean doInstruction(LittleMan lIttleMan) {
        return true;
    }

    @Override
    public void acceptOperands(int... operands) {
    }

}
