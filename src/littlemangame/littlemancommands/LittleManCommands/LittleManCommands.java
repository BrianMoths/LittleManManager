/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.LittleManCommands;

import littlemangame.littlemancommands.littleman.LittleMan;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManWordContainer;
import littlemangame.littlemancommands.littleman.littlemanutilities.location.ComputerLocation;
import littlemangame.word.BinaryWordOperation;
import littlemangame.word.UnaryWordOperation;

/**
 *
 * @author brian
 */
public class LittleManCommands {

    private static final LittleManAction memorizeDataPointedByInstructionPointer = new LittleManActionSequence(memorizeAddressAtContainerAction(LittleManWordContainer.INSTRUCTION_POINTER), memorizeDataAtContainerAction(LittleManWordContainer.REMEMBERED_MEMORY), doUnaryOperationOnContainerAction(LittleManWordContainer.INSTRUCTION_POINTER, UnaryWordOperation.INCREMENT));
    public static final LittleManAction haltAction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return false;
        }

    };
    private static final LittleManAction fetchDataOperandIfNecessary = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            if (littleMan.isDataOperandNeeded()) {
                return littleMan.doAction(memorizeDataPointedByInstructionPointer);
            } else {
                return true;
            }
        }

    };
    private static final LittleManAction memorizeAddressPointedByInstructionPointer = new LittleManActionSequence(memorizeAddressAtContainerAction(LittleManWordContainer.INSTRUCTION_POINTER), memorizeAddressAtContainerAction(LittleManWordContainer.REMEMBERED_MEMORY), doUnaryOperationOnContainerAction(LittleManWordContainer.INSTRUCTION_POINTER, UnaryWordOperation.INCREMENT));
    private static final LittleManAction fetchAddressOperandIfNecessary = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            if (littleMan.isAddressOperandNeeded()) {
                return littleMan.doAction(memorizeAddressPointedByInstructionPointer);
            } else {
                return true;
            }
        }

    };
    public static final LittleManAction nullAction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return true;
        }

    };
    private static final LittleManAction doInstruction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.doInstruction();
        }

    };
    private static final LittleManAction clearMemory = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.clearMemory();
            return true;
        }

    };
    private static final LittleManAction decodeRememberedInstruction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.decodeRememberedInstruction();
            return true;
        }

    };
    private static final LittleManAction fetchInstruction = new LittleManActionSequence(memorizeDataPointedByInstructionPointer, decodeRememberedInstruction, clearMemory);
    private static final LittleManAction doCycle = new LittleManActionSequence(fetchInstruction, fetchDataOperandIfNecessary, fetchAddressOperandIfNecessary, doInstruction, clearMemory);
    private static final LittleManAction getDataFromInputPanelAction = new LocalAction(ComputerLocation.INPUT_PANEL, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.getDataFromInputPanel();
        }

    });
    private static final LittleManAction printUnsignedToOutputPanelAction = new LocalAction(ComputerLocation.OUTPUT_PANEL, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.printUnsignedToOutputPanel();
            return true;
        }

    });

    /**
     * @return the printUnsignedToOutputPanelAction
     */
    public static LittleManAction getPrintUnsignedToOutputPanelAction() {
        return printUnsignedToOutputPanelAction.getResetCopy();
    }

    public static LittleManAction doBinaryOperationOnContainerAction(final LittleManWordContainer littleManWordContainer, final BinaryWordOperation binaryWordOperation) {
        return new LocalAction(littleManWordContainer.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.doBinaryOperationOnContainer(littleManWordContainer, binaryWordOperation);
                return true;
            }

        });
    }

    /**
     * @return the doCycle
     */
    public static LittleManAction getDoCycle() {
        return doCycle.getResetCopy();
    }

    public static LittleManAction doUnaryOperationOnContainerAction(final LittleManWordContainer littleManWordContainer, final UnaryWordOperation unaryWordOperation) {
        return new LocalAction(littleManWordContainer.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.doUnaryOperationOnContainer(littleManWordContainer, unaryWordOperation);
                return true;
            }

        });
    }

    static LittleManAction goToComputerLocation(final ComputerLocation computerLocation) {
        return new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToComputerLocation(computerLocation);
            }

        };
    }

    /**
     * @return the getDataFromInputPanel
     */
    public static LittleManAction getGetDataFromInputPanelAction() {
        return getDataFromInputPanelAction.getResetCopy();
    }

    public static LittleManAction memorizeAddressAtContainerAction(final LittleManWordContainer littleManWordContainer) {
        return new LocalAction(littleManWordContainer.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.memorizeAddressAtContainer(littleManWordContainer);
                return true;
            }

        });
    }

    public static LittleManAction memorizeDataAtContainerAction(final LittleManWordContainer littleManWordContainer) {
        return new LocalAction(littleManWordContainer.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.memorizeDataAtContainer(littleManWordContainer);
                return true;
            }

        });
    }

    private LittleManCommands() {
    }

}
