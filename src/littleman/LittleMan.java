/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littleman;

import Renderer.Drawable;
import computer.Computer;
import instructions.Instruction;
import instructions.InstructionSet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author brian
 */
public class LittleMan implements Drawable {

    static private final LittleManAction goToRegister = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.goToRegister();
        }

    };
    static public final LittleManAction goToInstructionPointer = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.goToInstructionPointer();
        }

    };
    static public final LittleManAction fetchInstructionFromRememberedAddress = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.goToMemoryLocation(littleMan.getRememberedWord());
        }

    };
    static private final LittleManAction incrementInstructionPointerAfar = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.incrementInstructionPointer();
            return true;
        }

    };
    static private final LittleManAction rememberInstructionPointerAfar = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.rememberInstructionPointer();
            return true;
        }

    };
    static public final LittleManAction decodeRememberedInstruction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.decodeRememberedInstruction();
            return true;
        }

    };
    static public final LittleManAction fetchOperand = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.fetchOperand();
        }

    };
    static public final LittleManAction doInstruction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.doInstruction();
        }

    };
    static public final LittleManAction clearMemory = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.clearMemory();
            return true;
        }

    };
    static public final LittleManAction registerRememberedOperandToInstruction = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.registerRemeberedOperandToInstruction();
            return true;
        }

    };
    static public final LittleManAction fetchInstructionAddress = new LittleManActionSequence(goToInstructionPointer, rememberInstructionPointerAfar);
    static public final LittleManAction incrementInstructionPointer = new LittleManActionSequence(goToInstructionPointer, incrementInstructionPointerAfar);
    static public final LittleManAction fetchDataFromInstructionPointer = new LittleManActionSequence(fetchInstructionAddress, fetchInstructionFromRememberedAddress, incrementInstructionPointer);
    static public final LittleManAction fetchInstruction = new LittleManActionSequence(fetchDataFromInstructionPointer, decodeRememberedInstruction, clearMemory);
    static public final LittleManAction getOperandIfNecessary = new LittleManActionSequence(fetchOperand, registerRememberedOperandToInstruction);
    static public final LittleManAction doCycle = new LittleManActionSequence(fetchInstruction, fetchOperand, doInstruction);
    static private final int pathY = 200;
    static private final int stepSize = 4;

    static private LittleManAction setInstructionPointerAfar(final int address) {
        return new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.setInstructionPointer(address);
                return true;
            }

        };
    }

    static private LittleManAction goToMemoryLocationAction(final int n) {
        return new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                return littleMan.goToMemoryLocation(n);
            }

        };
    }

    private int x = 20, y = 0;
    private final Computer computer;
    private int rememberedWord;
    private boolean isRememberingWord = false;
    private Instruction instruction;
    private boolean isHalted = false;

    public LittleMan(Computer computer) {
        this.computer = computer;
    }

    public boolean fetchInstructionAddress() {
        if (!isAtPoint(computer.instructionPointer.getAccessLocation())) {
            goToInstructionPointer();
            return false;
        } else {
            rememberWord(computer.instructionPointer.getInstructionPointer());
            return true;
        }
    }

    private void incrementInstructionPointer() {
        computer.instructionPointer.increment();
    }

    private void setInstructionPointer(int n) {
        computer.instructionPointer.setInstructionPointer(n);
    }

    private void rememberInstructionPointer() {
        rememberedWord = computer.instructionPointer.getInstructionPointer();
    }

    public boolean fetchOperand() {
        if (instruction.isOperandNeeded()) {
            return doAction(fetchDataFromInstructionPointer);
        } else {
            return true;
        }
    }

    public boolean doAction(LittleManAction littleManAction) {
        return littleManAction.doAction(this);
    }

    public boolean doInstruction() {
        return instruction.doInstruction(this);
    }

    private void decodeRememberedInstruction() {
        instruction = InstructionSet.decodeInstruction(rememberedWord);
    }

    private void registerRemeberedOperandToInstruction() {
        instruction.acceptOperands(rememberedWord);
    }

    //<editor-fold defaultstate="collapsed" desc="movement">
    public boolean goToRegister() {
        return goToPoint(computer.register.getAccessLocation());
    }

    public boolean goToInstructionPointer() {
        return goToPoint(computer.instructionPointer.getAccessLocation());
    }

    public boolean goToMemoryLocation(int address) {
        return goToPoint(computer.memory.getMemoryLocation(address));
    }

    public boolean goToPoint(Point point) {
        if (isAtX(point.x)) {
            stepInDirectionOfY(point.y);
            return false;
        } else {
            return goToX(point.x);
        }
    }

    public boolean goToX(int xDestination) {
        if (x != xDestination) {
            if (!isOnPath()) {
                goToPath();
                return false;
            } else {
                return stepInDirectionOfX(xDestination);
            }
        } else {
            return true;
        }
    }

    public boolean goToPath() {
        return stepInDirectionOfY(pathY);
    }

    private boolean stepInDirectionOfX(int xDestination) {
        if (x < xDestination) {
            x += Math.min(stepSize, xDestination - x);
        } else if (x > xDestination) {
            x -= Math.min(x - xDestination, stepSize);
        }
        return x == xDestination;
    }

    private boolean stepInDirectionOfY(int yDestination) {
        if (y < yDestination) {
            y += Math.min(stepSize, yDestination - y);
        } else if (y > yDestination) {
            y -= Math.min(y - yDestination, stepSize);
        }
        return y == yDestination;
    }

    private boolean isOnPath() {
        return isAtY(pathY);
    }

    private boolean isAtX(int x) {
        return this.x == x;
    }

    private boolean isAtY(int y) {
        return this.y == y;
    }

    private boolean isAtPoint(Point point) {
        return isAtX(point.x) && isAtY(point.y);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="short term memory">
    private void rememberWord(int word) {
        this.rememberedWord = word;
        isRememberingWord = true;
    }

    private void clearMemory() {
        isRememberingWord = false;
    }

    public boolean isThinkingOfWord() {
        return isRememberingWord;
    }
//</editor-fold>

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillOval(x, y, 10, 10);
        if (isRememberingWord) {
            graphics.drawRect(x - 5, y - 22, 22, 20);
            graphics.drawString(String.format("%02d", rememberedWord), x - 2, y - 5);
        }
    }

    public boolean halt() {
        isHalted = true;
        return false;
    }

    public boolean isHalted() {
        return isHalted;
    }

    public int getRememberedWord() {
        return rememberedWord;
    }

}
