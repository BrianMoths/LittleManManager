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
    public static final LittleManAction setRegisterToRememberedWord = new LocalAction(LocationForInstruction.REGISTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.setRegisterToRememberedWord();
            return true;
        }

    });
    public static final LittleManAction memorizeRegister = new LocalAction(LocationForInstruction.REGISTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeRegister();
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
    public static final LittleManAction setInstructionPointerToRememberedWord = new LocalAction(LocationForInstruction.INSTRUCTION_POINTER, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.setInstructionPointerToRememberedWord();
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
    public static final LittleManAction setMemoryAtOperandToRememberedWord = new LocalAction(LocationForInstruction.OPERAND_MEMORY, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.setMemoryAtOperandToRememberedWord();
            return true;
        }

    });
    public static final LittleManAction memorizeMemoryAtRememberedAddress = new LocalAction(LocationForInstruction.REMEMBERED_MEMORY, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeMemoryAtRememberedAddress();
            return true;
        }

    });
    public static final LittleManAction memorizeMemoryAtOperandAddress = new LocalAction(LocationForInstruction.OPERAND_MEMORY, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.memorizeMemoryAtOperandAddress();
            return true;
        }

    });
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="interact with outputPanel">
    public static final LittleManAction printUnsignedToOutputPanel = new LocalAction(LocationForInstruction.OUTPUT_PANEL, new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.printUnsignedToOutputPanel();
            return true;
        }

    });
    //</editor-fold>
    //</editor-fold>
    static private final LittleManAction decodeRememberedInstruction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.decodeRememberedInstruction();
            return true;
        }

    };
    static private final LittleManAction registerRememberedOperandToInstruction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.registerRemeberedOperandToInstruction();
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
    static private final LittleManAction memorizeOperandIfNecessary = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            if (littleMan.isOperandNeeded()) {
                return littleMan.doAction(memorizeDataPointedByInstructionPointer);
            } else {
                return true;
            }
        }

    };
    static private final LittleManAction memorizeDataPointedByInstructionPointer = new LittleManActionSequence(memorizeInstructionPointer, memorizeMemoryAtRememberedAddress, incrementInstructionPointer);
    static private final LittleManAction fetchInstruction = new LittleManActionSequence(memorizeDataPointedByInstructionPointer, decodeRememberedInstruction, clearMemory);
    static private final LittleManAction fetchOperandIfNecessary = new LittleManActionSequence(memorizeOperandIfNecessary, registerRememberedOperandToInstruction, clearMemory);
    static public final LittleManAction doCycle = new LittleManActionSequence(fetchInstruction, fetchOperandIfNecessary, doInstruction, clearMemory);
    static public final LittleManAction printUnsigned = new LittleManActionSequence(memorizeRegister, printUnsignedToOutputPanel);

    static private LittleManAction goToInstructionLocation(final LocationForInstruction locationForInstruction) {
        return new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToInstructionLocation(locationForInstruction);
            }

        };
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
