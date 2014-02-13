/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman;

/**
 *
 * @author brian
 */
public enum SourceOperand {

    IMMEDIATE(LocationForInstruction.CURRENT_LOCATION, new WordMemorizer() {
        @Override
        public void memorizeWord(LittleMan littleMan) {
        }

    }),
    REGISTER(LocationForInstruction.REGISTER, new WordMemorizer() {
        @Override
        public void memorizeWord(LittleMan littleMan) {
            littleMan.memorizeDataAtRegister();
        }

    }),
    MEMORY(LocationForInstruction.REMEMBERED_MEMORY, new WordMemorizer() {
        @Override
        public void memorizeWord(LittleMan littleMan) {
            littleMan.memorizeDataAtRememberedAddress();
        }

    });
    private final LocationForInstruction sourceOperandLocation;
    private final WordMemorizer wordMemorizer;

    private SourceOperand(LocationForInstruction sourceOperandLocation, WordMemorizer wordMemorizer) {
        this.sourceOperandLocation = sourceOperandLocation;
        this.wordMemorizer = wordMemorizer;
    }

    void memorizeWord(LittleMan littleMan) {
        wordMemorizer.memorizeWord(littleMan);
    }

    LocationForInstruction getLocation() {
        return sourceOperandLocation;
    }

    private static interface WordMemorizer {

        void memorizeWord(LittleMan littleMan);

    }

}
