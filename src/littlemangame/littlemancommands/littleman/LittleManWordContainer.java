/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package littlemangame.littlemancommands.littleman;

import littlemangame.word.BinaryWordOperation;

/**
 *
 * @author brian
 */
public enum LittleManWordContainer {

    INSTRUCTION_POINTER(LocationForInstruction.INSTRUCTION_POINTER, new WordSetterGetter() {
        @Override
        public void memorizeData(LittleMan littleMan) {
            littleMan.memorizeDataAtInstructionPointer();
        }

        @Override
        public void memorizeAddress(LittleMan littleMan) {
            littleMan.memorizeAddressAtInstructionPointer();
        }

        @Override
        public void doBinaryOperation(LittleMan littleMan, BinaryWordOperation binaryWordOperation) {
            littleMan.setInstructionPointerToResultOfOperation(binaryWordOperation);
        }

    }),
    REGISTER(LocationForInstruction.REGISTER, new WordSetterGetter() {
        @Override
        public void memorizeData(LittleMan littleMan) {
            littleMan.memorizeDataAtRegister();
        }

        @Override
        public void memorizeAddress(LittleMan littleMan) {
            littleMan.memorizeAddressAtRegister();
        }

        @Override
        public void doBinaryOperation(LittleMan littleMan, BinaryWordOperation binaryWordOperation) {
            littleMan.setRegisterToResultOfOperation(binaryWordOperation);
        }

    }),
    REMEMBERED_MEMORY(LocationForInstruction.REMEMBERED_MEMORY, new WordSetterGetter() {
        @Override
        public void memorizeData(LittleMan littleMan) {
            littleMan.memorizeDataAtRememberedAddress();
        }

        @Override
        public void memorizeAddress(LittleMan littleMan) {
            littleMan.memorizeAddressAtRememberedAddress();
        }

        @Override
        public void doBinaryOperation(LittleMan littleMan, BinaryWordOperation binaryWordOperation) {
            littleMan.setMemoryAtRememberedAddressToResultOfOperation(binaryWordOperation);
        }

    });
    private final LocationForInstruction locationForInstruction;
    private final WordSetterGetter wordSetterGetter;

    private LittleManWordContainer(LocationForInstruction locationForInstruction, WordSetterGetter wordSetterGetter) {
        this.locationForInstruction = locationForInstruction;
        this.wordSetterGetter = wordSetterGetter;
    }

    void memorizeData(LittleMan littleMan) {
        wordSetterGetter.memorizeData(littleMan);
    }

    void memorizeAddress(LittleMan littleMan) {
        wordSetterGetter.memorizeAddress(littleMan);
    }

    void doBinaryOperation(LittleMan littleMan, BinaryWordOperation binaryWordOperation) {
        wordSetterGetter.doBinaryOperation(littleMan, binaryWordOperation);
    }

    boolean goToLocation(LittleMan littleMan) {
        return locationForInstruction.goToLocation(littleMan);
    }

    private interface WordSetterGetter {

        void memorizeData(LittleMan littleMan);

        void memorizeAddress(LittleMan littleMan);

        void doBinaryOperation(LittleMan littleMan, BinaryWordOperation binaryWordOperation);

    }

}
