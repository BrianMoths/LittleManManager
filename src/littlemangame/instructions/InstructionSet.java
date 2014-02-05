/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littleman.LittleMan;
import littlemangame.littleman.LittleManCommander;
import littlemangame.word.Word;

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

    static public Instruction decodeInstruction(Word word) {
        if (Word.ZERO_WORD.equals(word)) {
            return HALT;
        } else if (Word.valueOfLastDigitsOfInteger(20).equals(word)) {
            return PRINT_UNSIGNED;
        } else {
            return new NoOpInstruction();
        }

    }

}
