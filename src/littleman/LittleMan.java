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
    static private final LittleManAction goToOutputPanel = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.goToOutputPanel();
        }

    };
    static private final LittleManAction printUnsignedToOutputPanelAfar = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.printUnsignedToOutputPanel();
            return true;
        }

    };
    static private final LittleManAction setRegisterToRememberedWordAfar = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.setRegisterToRememberedWord();
            return true;
        }

    };
    static private final LittleManAction rememberRegisterAfar = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.rememberRegister();
            return true;
        }

    };
    static private final LittleManAction goToInstructionPointer = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.goToInstructionPointer();
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
    static private final LittleManAction rememberMemoryAtRememberedAddressAfar = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            littleMan.rememberMemory(littleMan.getRememberedWord());
            return true;
        }

    };
    static private final LittleManAction goToRememberedMemoryLocation = new LittleManAction() {
        @Override
        public boolean doAction(LittleMan littleMan) {
            return littleMan.goToMemoryLocation(littleMan.getRememberedWord());
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
            if (littleMan.isOperandNeeded()) {
                return littleMan.doAction(fetchDataFromInstructionPointer);
            } else {
                return true;
            }
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
    static public final LittleManAction fetchInstructionFromRememberedAddress = new LittleManActionSequence(goToRememberedMemoryLocation, rememberMemoryAtRememberedAddressAfar);
    static public final LittleManAction incrementInstructionPointer = new LittleManActionSequence(goToInstructionPointer, incrementInstructionPointerAfar);
    static public final LittleManAction fetchDataFromInstructionPointer = new LittleManActionSequence(fetchInstructionAddress, fetchInstructionFromRememberedAddress, incrementInstructionPointer);
    static public final LittleManAction fetchInstruction = new LittleManActionSequence(fetchDataFromInstructionPointer, decodeRememberedInstruction, clearMemory);
    static public final LittleManAction getOperandIfNecessary = new LittleManActionSequence(fetchOperand, registerRememberedOperandToInstruction, clearMemory);
    static public final LittleManAction doCycle = new LittleManActionSequence(fetchInstruction, fetchOperand, doInstruction, clearMemory);
    static public final LittleManAction printUnsigned = new LittleManActionSequence(goToRegister, rememberRegisterAfar, goToOutputPanel, printUnsignedToOutputPanelAfar);
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

    static private LittleManAction rememberMemoryAfar(final int n) {
        return new LittleManAction() {
            @Override
            public boolean doAction(LittleMan littleMan) {
                littleMan.rememberMemory(n);
                return true;
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

    private void incrementInstructionPointer() {
        computer.instructionPointer.increment();
    }

    private void setInstructionPointer(int n) {
        computer.instructionPointer.setInstructionPointer(n);
    }

    private void rememberInstructionPointer() {
        rememberWord(computer.instructionPointer.getInstructionPointer());
    }

    private void printUnsignedToOutputPanel() {
        computer.outputPanel.append(String.format("%02d", rememberedWord));
    }

    private void setRegisterToRememberedWord() {
        computer.register.setWord(rememberedWord);
    }

    private void rememberRegister() {
        rememberWord(computer.register.getWord());
    }

    private void setMemoryToRememberedWord(int address) {
        computer.memory.setMemory(address, rememberedWord);
    }

    private void rememberMemory(int address) {
        rememberWord(computer.memory.getMemory(address));
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
    public boolean goToOutputPanel() {
        return goToPoint(computer.outputPanel.getAccessLocation());
    }

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
            return stepInDirectionOfY(point.y);
        } else {
            goToX(point.x);
            return false;
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

    public boolean isOperandNeeded() {
        return instruction != null && instruction.isOperandNeeded();
    }

}
