/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

import Renderer.Drawable;
import java.awt.Graphics;
import littlemangame.littlemancommands.littleman.LittleMan;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManWordContainer;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.Computer;
import littlemangame.littlemancommands.littleman.littlemanutilities.location.ComputerLocation;
import littlemangame.word.BinaryWordOperation;
import littlemangame.word.UnaryWordOperation;

/**
 *
 * @author brian
 */
public class LittleManCommander implements Drawable {

    static private final LittleManAction decodeRememberedInstruction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.decodeRememberedInstruction();
            return true;
        }

    };
    static private final LittleManAction doInstruction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.doInstruction();
        }

    };
    static private final LittleManAction clearMemory = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.clearMemory();
            return true;
        }

    };
    static private final LittleManAction fetchDataOperandIfNecessary = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            if (littleMan.isDataOperandNeeded()) {
                return littleMan.doAction(memorizeDataPointedByInstructionPointer);
            } else {
                return true;
            }
        }

    };
    static private final LittleManAction fetchAddressOperandIfNecessary = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            if (littleMan.isAddressOperandNeeded()) {
                return littleMan.doAction(memorizeAddressPointedByInstructionPointer);
            } else {
                return true;
            }
        }

    };
    static private final LittleManAction memorizeDataPointedByInstructionPointer = new LittleManActionSequence(memorizeAddressAtContainerAction(LittleManWordContainer.INSTRUCTION_POINTER), memorizeDataAtContainerAction(LittleManWordContainer.REMEMBERED_MEMORY), doUnaryOperationOnContainerAction(LittleManWordContainer.INSTRUCTION_POINTER, UnaryWordOperation.INCREMENT));
    static private final LittleManAction memorizeAddressPointedByInstructionPointer = new LittleManActionSequence(memorizeAddressAtContainerAction(LittleManWordContainer.INSTRUCTION_POINTER), memorizeAddressAtContainerAction(LittleManWordContainer.REMEMBERED_MEMORY), doUnaryOperationOnContainerAction(LittleManWordContainer.INSTRUCTION_POINTER, UnaryWordOperation.INCREMENT));
    static private final LittleManAction fetchInstruction = new LittleManActionSequence(memorizeDataPointedByInstructionPointer, decodeRememberedInstruction, clearMemory);
    private static final LittleManAction doCycle = new LittleManActionSequence(fetchInstruction, fetchDataOperandIfNecessary, fetchAddressOperandIfNecessary, doInstruction, clearMemory);
    public static final LittleManAction haltAction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return false;
        }

    };
    public static final LittleManAction nullAction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return true;
        }

    };
    private static final LittleManAction printUnsignedToOutputPanelAction = new LocalAction(ComputerLocation.OUTPUT_PANEL, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.printUnsignedToOutputPanel();
            return true;
        }

    });
    private static final LittleManAction getDataFromInputPanelAction = new LocalAction(ComputerLocation.INPUT_PANEL, new LittleManAction() {

        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.getDataFromInputPanel();
        }

    });

    public static LittleManAction memorizeDataAtContainerAction(final LittleManWordContainer littleManWordContainer) {
        return new LocalAction(littleManWordContainer.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.memorizeDataAtContainer(littleManWordContainer);
                return true;
            }

        });
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

    public static LittleManAction doBinaryOperationOnContainerAction(final LittleManWordContainer littleManWordContainer, final BinaryWordOperation binaryWordOperation) {
        return new LocalAction(littleManWordContainer.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.doBinaryOperationOnContainer(littleManWordContainer, binaryWordOperation);
                return true;
            }

        });
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
     * @return the doCycle
     */
    public static LittleManAction getDoCycle() {
        return doCycle.getResetCopy();
    }

    /**
     * @return the printUnsignedToOutputPanelAction
     */
    public static LittleManAction getPrintUnsignedToOutputPanelAction() {
        return printUnsignedToOutputPanelAction;
    }

    /**
     * @return the getDataFromInputPanel
     */
    public static LittleManAction getGetDataFromInputPanelAction() {
        return getDataFromInputPanelAction;
    }

    private final LittleMan littleMan;
    private final LittleManAction doCycleCommand;

    public LittleManCommander(Computer computer) {
        littleMan = new LittleMan(computer);
        doCycleCommand = getDoCycle();
    }

    public boolean doCycle() {
        return doAction(doCycleCommand);
    }

    public void reset() {
        littleMan.reset();
    }

    private boolean doAction(LittleManAction littleManAction) {
        return littleManAction.doAction(littleMan);
    }

    @Override
    public void draw(Graphics graphics) {
        littleMan.draw(graphics);
    }

    public boolean memorizeDataAtContainer(final LittleManWordContainer littleManWordContainer) {
        return memorizeDataAtContainerAction(littleManWordContainer).doAction(littleMan);
    }

    public boolean memorizeAddressAtContainer(final LittleManWordContainer littleManWordContainer) {
        return memorizeAddressAtContainerAction(littleManWordContainer).doAction(littleMan);
    }

    public boolean doBinaryOperationOnContainer(final LittleManWordContainer littleManWordContainer, BinaryWordOperation binaryWordOperation) {
        return doBinaryOperationOnContainerAction(littleManWordContainer, binaryWordOperation).doAction(littleMan);
    }

    public boolean doUnaryOperationOnContainer(final LittleManWordContainer littleManWordContainer, UnaryWordOperation unaryWordOperation) {
        return doUnaryOperationOnContainerAction(littleManWordContainer, unaryWordOperation).doAction(littleMan);
    }

    public boolean printUnsignedToOutputPanel() {
        return getPrintUnsignedToOutputPanelAction().doAction(littleMan);
    }

}
