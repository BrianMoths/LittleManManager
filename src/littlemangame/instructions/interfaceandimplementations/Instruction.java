/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions.interfaceandimplementations;

import littlemangame.littlemancommands.LittleManAction;

/**
 *
 * @author brian
 */
public interface Instruction {

    public LittleManAction getAction();

    public boolean isDataOperandNeeded();

    public boolean isAddressOperandNeeded();

}
