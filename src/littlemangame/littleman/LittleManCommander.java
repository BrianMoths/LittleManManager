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

    public static final LittleManAction setRegisterToRememberedWord;
    public static final LittleManAction memorizeRegister;
    public static final LittleManAction incrementInstructionPointer;
    public static final LittleManAction setInstructionPointerToRememberedWord;
    public static final LittleManAction memorizeInstructionPointer;
    public static final LittleManAction setMemoryAtOperandToRememberedWord;
    public static final LittleManAction memorizeMemoryAtRememberedAddress;
    public static final LittleManAction memorizeMemoryAtOperandAddress;
    public static final LittleManAction printUnsignedToOutputPanel;

    static {
        //<editor-fold defaultstate="collapsed" desc="movement">
        final LittleManAction goToRegister = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToRegister();
            }

        };
        final LittleManAction goToOutputPanel = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToOutputPanel();
            }

        };
        final LittleManAction goToInstructionPointer = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToInstructionPointer();
            }

        };

        final LittleManAction goToRememberedMemoryLocation = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToRememberedMemoryLocation();
            }

        };

        final LittleManAction goToOperandMemoryLocation = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToOperandMemoryLocation();
            }

        };
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="basic instructions without moving">
        //<editor-fold defaultstate="collapsed" desc="interact with register">
        final LittleManAction setRegisterToRememberedWordAfar = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.setRegisterToRememberedWord();
                return true;
            }

        };
        final LittleManAction rememberRegisterAfar = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.memorizeRegister();
                return true;
            }

        };
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="interact with instruction pointer">
        final LittleManAction incrementInstructionPointerAfar = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.incrementInstructionPointer();
                return true;
            }

        };

        final LittleManAction setInstructionPointerToRememberedAddressAfar = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.setInstructionPointerToRememberedWord();
                return true;
            }

        };

        final LittleManAction memorizeInstructionPointerAfar = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.rememberInstructionPointer();
                return true;
            }

        };
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="interact with memory">
        final LittleManAction setMemoryAtOperandAddressAfar = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.setMemoryAtOperandToRememberedWord();
                return true;
            }

        };
        final LittleManAction memorizeMemoryAtRememberedAddressAfar = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.memorizeMemoryAtRememberedAddress();
                return true;
            }

        };
        final LittleManAction memorizeMemoryAtOperandAddressAfar = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.memorizeMemoryAtOperandAddress();
                return true;
            }

        };
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="interact with outputPanel">
        final LittleManAction printUnsignedToOutputPanelAfar = new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.printUnsignedToOutputPanel();
                return true;
            }

        };
        //</editor-fold>
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="basic instructions with moving">
        setRegisterToRememberedWord = new LittleManActionSequence(goToRegister, setRegisterToRememberedWordAfar);
        memorizeRegister = new LittleManActionSequence(goToRegister, rememberRegisterAfar);
        incrementInstructionPointer = new LittleManActionSequence(goToInstructionPointer, incrementInstructionPointerAfar);
        setInstructionPointerToRememberedWord = new LittleManActionSequence(goToInstructionPointer, setInstructionPointerToRememberedAddressAfar);
        memorizeInstructionPointer = new LittleManActionSequence(goToInstructionPointer, memorizeInstructionPointerAfar);
        setMemoryAtOperandToRememberedWord = new LittleManActionSequence(goToOperandMemoryLocation, setMemoryAtOperandAddressAfar);
        memorizeMemoryAtRememberedAddress = new LittleManActionSequence(goToRememberedMemoryLocation, memorizeMemoryAtRememberedAddressAfar);
        memorizeMemoryAtOperandAddress = new LittleManActionSequence(goToOperandMemoryLocation, memorizeMemoryAtOperandAddressAfar);
        printUnsignedToOutputPanel = new LittleManActionSequence(goToOutputPanel, printUnsignedToOutputPanelAfar);

        //</editor-fold>
    }
//<editor-fold defaultstate="collapsed" desc="deal with instructions">

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
