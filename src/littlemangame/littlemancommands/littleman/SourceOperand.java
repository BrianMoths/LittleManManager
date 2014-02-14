/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman;

import littlemangame.littlemancommands.littleman.littlemanutilities.location.ComputerLocation;

/**
 *
 * @author brian
 */
public enum SourceOperand {

    IMMEDIATE(ComputerLocation.CURRENT_LOCATION, new WordMemorizer() {
        @Override
        public void memorizeWord(LittleMan littleMan) {
        }

    }),
    REGISTER(ComputerLocation.REGISTER, new WordMemorizer() {
        @Override
        public void memorizeWord(LittleMan littleMan) {
            littleMan.memorizeDataAtRegister();
        }

    }),
    MEMORY(ComputerLocation.REMEMBERED_MEMORY, new WordMemorizer() {
        @Override
        public void memorizeWord(LittleMan littleMan) {
            littleMan.memorizeDataAtRememberedAddress();
        }

    });
    private final ComputerLocation sourceOperandLocation;
    private final WordMemorizer wordMemorizer;

    private SourceOperand(ComputerLocation sourceOperandLocation, WordMemorizer wordMemorizer) {
        this.sourceOperandLocation = sourceOperandLocation;
        this.wordMemorizer = wordMemorizer;
    }

    void memorizeWord(LittleMan littleMan) {
        wordMemorizer.memorizeWord(littleMan);
    }

    ComputerLocation getLocation() {
        return sourceOperandLocation;
    }

    private static interface WordMemorizer {

        void memorizeWord(LittleMan littleMan);

    }

}
