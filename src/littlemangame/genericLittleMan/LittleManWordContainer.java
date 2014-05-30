/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package littlemangame.genericLittleMan;

import littlemangame.genericLittleMan.GenericLittleManData;
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
        public WordContainer getWordContainer(GenericLittleManData<?> littleManData) {
            return littleManData.getInstructionPointer();
        }

    }),
    REGISTER(ComputerLocation.REGISTER, new WordContainerGetter() {
        @Override
        public WordContainer getWordContainer(GenericLittleManData<?> littleManData) {
            return littleManData.getRegister();
        }

    }),
    REMEMBERED_MEMORY(ComputerLocation.REMEMBERED_MEMORY, new WordContainerGetter() {
        @Override
        public WordContainer getWordContainer(GenericLittleManData<?> littleManData) {
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

    Word getWord(GenericLittleManData<?> littleManData) {
        return wordContainerGetter.getWordContainer(littleManData).getWord();
    }

    WordContainer getWordContainer(GenericLittleManData<?> littleManData) {
        return wordContainerGetter.getWordContainer(littleManData);
    }

    private interface WordContainerGetter {

        WordContainer getWordContainer(GenericLittleManData<?> littleManData);

    }

}
