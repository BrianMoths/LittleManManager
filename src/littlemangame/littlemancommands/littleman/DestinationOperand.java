/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman;

import littlemangame.word.WordOperation;

/**
 *
 * @author brian
 */
public enum DestinationOperand {

    REGISTER(LocationForInstruction.REGISTER, new OperationReceiver() {
        @Override
        public void receiveOperation(LittleMan littleMan, WordOperation wordOperation) {
            littleMan.setRegisterToResultOfOperation(wordOperation);
        }

    }),
    INSTRUCTION_POINTER(LocationForInstruction.INSTRUCTION_POINTER, new OperationReceiver() {
        @Override
        public void receiveOperation(LittleMan littleMan, WordOperation wordOperation) {
            littleMan.setInstructionPointerToResultOfOperation(wordOperation);
        }

    }),
    MEMORY(LocationForInstruction.REMEMBERED_MEMORY, new OperationReceiver() {
        @Override
        public void receiveOperation(LittleMan littleMan, WordOperation wordOperation) {
            littleMan.setMemoryAtRememberedAddressToResultOfOperation(wordOperation);
        }

    });
    private final LocationForInstruction destinationOperandLocation;
    private final OperationReceiver operationReceiver;

    private DestinationOperand(LocationForInstruction destinationOperandLocation, OperationReceiver operationReceiver) {
        this.destinationOperandLocation = destinationOperandLocation;
        this.operationReceiver = operationReceiver;
    }

    void receiveOperation(LittleMan littleMan, WordOperation wordOperation) {
        operationReceiver.receiveOperation(littleMan, wordOperation);
    }

    LocationForInstruction getLocation() {
        return destinationOperandLocation;
    }

    private static interface OperationReceiver {

        void receiveOperation(LittleMan littleMan, WordOperation wordOperation);

    }

}
