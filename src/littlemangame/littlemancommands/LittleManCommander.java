/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

import Renderer.Drawable;
import java.awt.Graphics;
import littlemangame.littlemancommands.littleman.LittleMan;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.Computer;
import littlemangame.littlemancommands.littleman.littlemanutilities.location.ComputerLocation;
import littlemangame.word.BinaryWordOperation;

/**
 *
 * @author brian
 */
public class LittleManCommander implements Drawable {

    private static class LocalAction implements LittleManAction {

        LittleManAction littleManAction;

        LocalAction(ComputerLocation locationForInstruction, LittleManAction littleManAction) {
            this.littleManAction = new LittleManActionSequence(goToInstructionLocation(locationForInstruction), littleManAction);
        }

        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleManAction.doAction(littleMan);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="basic instructions">
    //<editor-fold defaultstate="collapsed" desc="interact with register">
    public static final LittleManAction setRegisterToRememberedData = new LocalAction(ComputerLocation.REGISTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.setRegisterToRememberedWord();
            return true;
        }

    });
    public static final LittleManAction memorizeDataAtRegister = new LocalAction(ComputerLocation.REGISTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeDataAtRegister();
            return true;
        }

    });
    public static final LittleManAction memorizeAddressAtRegister = new LocalAction(ComputerLocation.REGISTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeDataAtRegister();
            return true;
        }

    });
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="interact with instruction pointer">
    public static final LittleManAction incrementInstructionPointer = new LocalAction(ComputerLocation.INSTRUCTION_POINTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.incrementInstructionPointer();
            return true;
        }

    });
    public static final LittleManAction setInstructionPointerToRememberedData = new LocalAction(ComputerLocation.INSTRUCTION_POINTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.setInstructionPointerToRememberedData();
            return true;
        }

    });
    public static final LittleManAction memorizeInstructionPointer = new LocalAction(ComputerLocation.INSTRUCTION_POINTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeAddressAtInstructionPointer();
            return true;
        }

    });
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="interact with memory">
    public static final LittleManAction setMemoryAtRememberedAddressToRememberedData = new LocalAction(ComputerLocation.REMEMBERED_MEMORY, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.setMemoryAtRememberedAddressToRememberedData();
            return true;
        }

    });
    public static final LittleManAction memorizeDataAtRememberedAddress = new LocalAction(ComputerLocation.REMEMBERED_MEMORY, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeDataAtRememberedAddress();
            return true;
        }

    });
    private static final LittleManAction memorizeAddressAtRememberedAddress = new LocalAction(ComputerLocation.REMEMBERED_MEMORY, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeAddressAtRememberedAddress();
            return true;
        }

    });
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="interact with outputPanel">
    public static final LittleManAction printRememberedWordToOutputPanel = new LocalAction(ComputerLocation.OUTPUT_PANEL, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.printUnsignedToOutputPanel();
            return true;
        }

    });
    //</editor-fold>
    public static final LittleManAction halt = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.halt();
        }

    };
    public static final LittleManAction NoOperation = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return true;
        }

    };
    //</editor-fold>
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
    static private final LittleManAction memorizeDataPointedByInstructionPointer = new LittleManActionSequence(memorizeInstructionPointer, memorizeDataAtRememberedAddress, incrementInstructionPointer);
    static private final LittleManAction memorizeAddressPointedByInstructionPointer = new LittleManActionSequence(memorizeInstructionPointer, memorizeAddressAtRememberedAddress, incrementInstructionPointer);
    static private final LittleManAction fetchInstruction = new LittleManActionSequence(memorizeDataPointedByInstructionPointer, decodeRememberedInstruction, clearMemory);
    static public final LittleManAction doCycle = new LittleManActionSequence(fetchInstruction, fetchDataOperandIfNecessary, fetchAddressOperandIfNecessary, doInstruction, clearMemory);

    static private LittleManAction goToInstructionLocation(final ComputerLocation locationForInstruction) {
        return new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToComputerLocation(locationForInstruction);
            }

        };
    }

    static public LittleManAction doOperationOnOperands(BinaryWordOperation wordOperation, SourceOperand sourceOperand, DestinationOperand destinationOperand) {
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
