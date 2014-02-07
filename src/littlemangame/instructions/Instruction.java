/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littleman.LittleManAction;

/**
 *
 * @author brian
 */
public interface Instruction {

    public LittleManAction getAction();

    public boolean isDataOperandNeeded();

//    public void acceptDataOperand(Word operand);
//    public Word getDataOperand();
    public boolean isAddressOperandNeeded();

//    public void acceptAddressOperand(Word operand);
//    public Word getAddressOperand();
}
