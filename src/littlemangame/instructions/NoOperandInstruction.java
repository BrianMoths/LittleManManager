/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

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
    public void acceptOperand(int operands) {
    }

    @Override
    public int getOperand() {
        throw new AssertionError("getOperand called on an instruction with no Operand");
    }

}
