/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littleman;

import Renderer.Drawable;
import computer.Computer;
import java.awt.Graphics;

/**
 *
 * @author brian
 */
public class LittleManCommander implements Drawable {

    private static class LocalAction implements LittleManAction {

        LittleManAction littleManAction;

        LocalAction(LocationForInstruction locationForInstruction, LittleManAction littleManAction) {
            this.littleManAction = new LittleManActionSequence(goToInstructionLocation(locationForInstruction), littleManAction);
        }

        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleManAction.doAction(littleMan);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="basic instructions">
    //<editor-fold defaultstate="collapsed" desc="interact with register">
    public static final LittleManAction setRegisterToRememberedData = new LocalAction(LocationForInstruction.REGISTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.setRegisterToRememberedWord();
            return true;
        }

    });
    public static final LittleManAction memorizeDataAtRegister = new LocalAction(LocationForInstruction.REGISTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeDataAtRegister();
            return true;
        }

    });
    public static final LittleManAction memorizeAddressAtRegister = new LocalAction(LocationForInstruction.REGISTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeDataAtRegister();
            return true;
        }

    });
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="interact with instruction pointer">
    public static final LittleManAction incrementInstructionPointer = new LocalAction(LocationForInstruction.INSTRUCTION_POINTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.incrementInstructionPointer();
            return true;
        }

    });
    public static final LittleManAction setInstructionPointerToRememberedData = new LocalAction(LocationForInstruction.INSTRUCTION_POINTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.setInstructionPointerToRememberedData();
            return true;
        }

    });
    public static final LittleManAction memorizeInstructionPointer = new LocalAction(LocationForInstruction.INSTRUCTION_POINTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeInstructionPointer();
            return true;
        }

    });
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="interact with memory">
    public static final LittleManAction setMemoryAtRememberedAddressToRememberedData = new LocalAction(LocationForInstruction.REMEMBERED_MEMORY, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.setMemoryAtRememberedAddressToRememberedData();
            return true;
        }

    });
    public static final LittleManAction memorizeDataAtRememberedAddress = new LocalAction(LocationForInstruction.REMEMBERED_MEMORY, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeDataAtRememberedAddress();
            return true;
        }

    });
    private static final LittleManAction memorizeAddressAtRememberedAddress = new LocalAction(LocationForInstruction.REMEMBERED_MEMORY, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeAddressAtRememberedAddress();
            return true;
        }

    });
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="interact with outputPanel">
    public static final LittleManAction printRememberedWordToOutputPanel = new LocalAction(LocationForInstruction.OUTPUT_PANEL, new LittleManAction() {
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

    static private LittleManAction goToInstructionLocation(final LocationForInstruction locationForInstruction) {
        return new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToInstructionLocation(locationForInstruction);
            }

        };
    }

    static public LittleManAction doOperationOnOperands(WordOperation wordOperation, SourceOperand sourceOperand, DestinationOperand destinationOperand) {
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

    static private LittleManAction doOperationOnDestination(final WordOperation wordOperation, final DestinationOperand destinationOperand) {
        return new LocalAction(destinationOperand.getLocation(), new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                destinationOperand.receiveOperation(littleMan, wordOperation);
                return true;
            }

        });
    }

    private final LittleMan littleMan;

    public LittleManCommander(Computer computer) {
        littleMan = new LittleMan(computer);
    }

    public void doCycle() {
        doAction(doCycle);
    }

    public void doAction(LittleManAction littleManAction) {
        littleMan.doAction(littleManAction);
    }

    @Override
    public void draw(Graphics graphics) {
        littleMan.draw(graphics);
    }

    public boolean isHalted() {
        return littleMan.isHalted();
    }

}
