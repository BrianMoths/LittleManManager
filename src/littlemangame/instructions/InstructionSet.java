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
public class InstructionSet {

    static public final Instruction HALT, PRINT_UNSIGNED;

    static {
        HALT = new NoOperandInstruction() {
            @Override
            public boolean doInstruction(LittleMan littleMan) {
                return littleMan.halt();
            }

        };
        PRINT_UNSIGNED = new NoOperandInstruction() {
            @Override
            public boolean doInstruction(LittleMan littleMan) {
                return littleMan.doAction(LittleManCommander.printUnsigned);
            }

        };



    }

    static public Instruction decodeInstruction(int word) {
        if (word == 0) {
            return HALT;
        } else if (word == 20) {
            return PRINT_UNSIGNED;
        } else {
            return new NoOpInstruction();
        }

    }

}
