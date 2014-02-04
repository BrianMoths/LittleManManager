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
public class InstructionSet {

    static public final Instruction HALT, PRINT_UNSIGNED;

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
        PRINT_UNSIGNED = new Instruction() {
            @Override
            public boolean isOperandNeeded() {
                return false;
            }

            @Override
            public boolean doInstruction(LittleMan littleMan) {
                return littleMan.doAction(LittleMan.printUnsigned);
            }

            @Override
            public void acceptOperands(int... operands) {
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
