/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package littlemangame.littleman.littlemanutilities.littlemandata;

import littlemangame.littleman.littlemanutilities.location.ComputerLocation;
import littlemangame.word.Word;
import littlemangame.word.WordContainer;

/**
 *
 * @author brian
 */
public enum LittleManWordContainer {

    INSTRUCTION_POINTER(ComputerLocation.INSTRUCTION_POINTER, new WordContainerGetter() {
        @Override
        public WordContainer getWordContainer(LittleManData littleManData) {
            return littleManData.getInstructionPointer();
        }

    }),
    REGISTER(ComputerLocation.REGISTER, new WordContainerGetter() {
        @Override
        public WordContainer getWordContainer(LittleManData littleManData) {
            return littleManData.getRegister();
        }

    }),
    REMEMBERED_MEMORY(ComputerLocation.REMEMBERED_MEMORY, new WordContainerGetter() {
        @Override
        public WordContainer getWordContainer(LittleManData littleManData) {
            return littleManData.getRememberedMemory();
        }

    });
    private final ComputerLocation locationForInstruction;
    private final WordContainerGetter wordContainerGetter;

    private LittleManWordContainer(ComputerLocation locationForInstruction, WordContainerGetter wordContainerGetter) {
        this.locationForInstruction = locationForInstruction;
        this.wordContainerGetter = wordContainerGetter;
    }

    public ComputerLocation getLocation() {
        return locationForInstruction;
    }

    Word getWord(LittleManData littleManData) {
        return wordContainerGetter.getWordContainer(littleManData).getWord();
    }

    WordContainer getWordContainer(LittleManData littleManData) {
        return wordContainerGetter.getWordContainer(littleManData);
    }

    private interface WordContainerGetter {

        WordContainer getWordContainer(LittleManData littleManData);

    }

}
