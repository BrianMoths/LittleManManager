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
public class InstructionSet {

    static public final Instruction HALT;

    static {
        HALT = new Instruction() {
            @Override
            public boolean isOperandNeeded() {
                return false;
            }

            @Override
            public boolean doInstruction(LittleMan littleMan) {
                return littleMan.halt();
            }

            @Override
            public void acceptOperands(int... operands) {
            }

        };



    }

    static public Instruction decodeInstruction(int word) {
        if (word == 0) {
            return HALT;
        } else {
            return new NoOpInstruction();
        }

    }

}
