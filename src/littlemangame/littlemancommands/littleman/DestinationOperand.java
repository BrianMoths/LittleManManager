/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman;

import littlemangame.littlemancommands.littleman.littlemanutilities.location.ComputerLocation;
import littlemangame.word.BinaryWordOperation;

/**
 *
 * @author brian
 */
public enum DestinationOperand {

    REGISTER(ComputerLocation.REGISTER, new OperationReceiver() {
        @Override
        public void receiveOperation(LittleMan littleMan, BinaryWordOperation wordOperation) {
            littleMan.setRegisterToResultOfOperation(wordOperation);
        }

    }),
    INSTRUCTION_POINTER(ComputerLocation.INSTRUCTION_POINTER, new OperationReceiver() {
        @Override
        public void receiveOperation(LittleMan littleMan, BinaryWordOperation wordOperation) {
            littleMan.setInstructionPointerToResultOfOperation(wordOperation);
        }

    }),
    MEMORY(ComputerLocation.REMEMBERED_MEMORY, new OperationReceiver() {
        @Override
        public void receiveOperation(LittleMan littleMan, BinaryWordOperation wordOperation) {
            littleMan.setMemoryAtRememberedAddressToResultOfOperation(wordOperation);
        }

    });
    private final ComputerLocation destinationOperandLocation;
    private final OperationReceiver operationReceiver;

    private DestinationOperand(ComputerLocation destinationOperandLocation, OperationReceiver operationReceiver) {
        this.destinationOperandLocation = destinationOperandLocation;
        this.operationReceiver = operationReceiver;
    }

    void receiveOperation(LittleMan littleMan, BinaryWordOperation wordOperation) {
        operationReceiver.receiveOperation(littleMan, wordOperation);
    }

    ComputerLocation getLocation() {
        return destinationOperandLocation;
    }

    private static interface OperationReceiver {

        void receiveOperation(LittleMan littleMan, BinaryWordOperation wordOperation);

    }

}
