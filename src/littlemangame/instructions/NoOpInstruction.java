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
public class NoOpInstruction extends NoOperandInstruction {

    @Override
    public boolean doInstruction(LittleMan lIttleMan) {
        return true;
    }

}
