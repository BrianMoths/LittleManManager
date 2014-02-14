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

    private static class LocalAction implements LittleManAction {

        LittleManAction littleManAction;

        LocalAction(ComputerLocation computerLocation, LittleManAction littleManAction) {
            this.littleManAction = new LittleManActionSequence(goToComputerLocation(computerLocation), littleManAction);
        }

        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleManAction.doAction(littleMan);
        }

    }

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
    //</editor-fold>
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
    static private final LittleManAction memorizeDataPointedByInstructionPointer = new LittleManActionSequence(memorizeAddressAtContainer(LittleManWordContainer.INSTRUCTION_POINTER), memorizeDataAtContainer(LittleManWordContainer.REMEMBERED_MEMORY), doUnaryOperationOnContainer(LittleManWordContainer.INSTRUCTION_POINTER, UnaryWordOperation.INCREMENT));
    static private final LittleManAction memorizeAddressPointedByInstructionPointer = new LittleManActionSequence(memorizeAddressAtContainer(LittleManWordContainer.INSTRUCTION_POINTER), memorizeAddressAtContainer(LittleManWordContainer.REMEMBERED_MEMORY), doUnaryOperationOnContainer(LittleManWordContainer.INSTRUCTION_POINTER, UnaryWordOperation.INCREMENT));
    static private final LittleManAction fetchInstruction = new LittleManActionSequence(memorizeDataPointedByInstructionPointer, decodeRememberedInstruction, clearMemory);
    static public final LittleManAction doCycle = new LittleManActionSequence(fetchInstruction, fetchDataOperandIfNecessary, fetchAddressOperandIfNecessary, doInstruction, clearMemory);

    public static LittleManAction memorizeDataAtContainer(final LittleManWordContainer littleManWordContainer) {
        return new LocalAction(littleManWordContainer.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.memorizeDataAtContainer(littleManWordContainer);
                return true;
            }

        });
    }

    public static LittleManAction memorizeAddressAtContainer(final LittleManWordContainer littleManWordContainer) {
        return new LocalAction(littleManWordContainer.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.memorizeAddressAtContainer(littleManWordContainer);
                return true;
            }

        });
    }

    public static LittleManAction doBinaryOperationOnContainer(final LittleManWordContainer littleManWordContainer, final BinaryWordOperation binaryWordOperation) {
        return new LocalAction(littleManWordContainer.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.doBinaryOperationOnContainer(littleManWordContainer, binaryWordOperation);
                return true;
            }

        });
    }

    public static LittleManAction doUnaryOperationOnContainer(final LittleManWordContainer littleManWordContainer, final UnaryWordOperation unaryWordOperation) {
        return new LocalAction(littleManWordContainer.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.doUnaryOperationOnContainer(littleManWordContainer, unaryWordOperation);
                return true;
            }

        });
    }

    private static LittleManAction goToComputerLocation(final ComputerLocation computerLocation) {
        return new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToComputerLocation(computerLocation);
            }

        };
    }

    public static LittleManAction doOperationOnOperands(BinaryWordOperation wordOperation, SourceOperand sourceOperand, DestinationOperand destinationOperand) {
        return new LittleManActionSequence(memorizeSourceOperand(sourceOperand), doOperationOnDestination(wordOperation, destinationOperand));
    }

    static private LittleManAction memorizeSourceOperand(final SourceOperand sourceOperand) {
        return new LocalAction(sourceOperand.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                sourceOperand.memorizeWord(littleMan);
                return true;
            }

        });
    }

    static private LittleManAction doOperationOnDestination(final BinaryWordOperation wordOperation, final DestinationOperand destinationOperand) {
        return new LocalAction(destinationOperand.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                destinationOperand.receiveOperation(littleMan, wordOperation);
                return true;
            }

        });
    }

    private final LittleMan littleMan;
    private final LittleManCommandGiver littleManCommandGiver;

    public LittleManCommander(Computer computer) {
        littleManCommandGiver = new LittleManCommandGiver();
        littleMan = new LittleMan(computer, littleManCommandGiver);
    }

    public void doCycle() {
        doAction(doCycle);
    }

    private boolean doAction(LittleManAction littleManAction) {
        return littleManCommandGiver.doLittleManCommand(littleManAction);
    }

    @Override
    public void draw(Graphics graphics) {
        littleMan.draw(graphics);
    }

    public boolean isHalted() {
        return littleMan.isHalted();
    }

}
