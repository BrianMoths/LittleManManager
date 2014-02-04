/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littleman;

import Renderer.Drawable;
import computer.Computer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import littlemangame.instructions.Instruction;
import littlemangame.instructions.InstructionSet;
import littlemangame.littleman.location.LittleManPosition;

/**
 *
 * @author brian
 */
public class LittleMan implements Drawable {

    static private final int pathY = 200;
    static private final int stepSize = 4;
    private final LittleManPosition littleManPosition;
    private final Computer computer;
    private int rememberedWord;
    private boolean isRememberingWord = false;
    private Instruction instruction;
    private boolean isHalted = false;

    public LittleMan(Computer computer) {
        this.computer = computer;
        littleManPosition = new LittleManPosition(pathY, stepSize, new Point(200, pathY));
    }

    //<editor-fold defaultstate="collapsed" desc="movement">
    boolean goToOutputPanel() {
        return littleManPosition.goTo(computer.outputPanel);
    }

    boolean goToRegister() {
        return littleManPosition.goTo(computer.register);
    }

    boolean goToInstructionPointer() {
        return littleManPosition.goTo(computer.instructionPointer);
    }

    boolean goToRememberedMemoryLocation() {
        return goToMemoryLocation(rememberedWord);
    }

    boolean goToOperandMemoryLocation() {
        return goToMemoryLocation(instruction.getOperand());
    }

    private boolean goToMemoryLocation(int address) {
        return littleManPosition.goTo(computer.memory, address);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with register">
    void setRegisterToRememberedWord() {
        computer.register.setWord(getRememberedWord());
        clearMemory();
    }

    void memorizeRegister() {
        memorizeWord(computer.register.getWord());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with instruction pointer">
    void incrementInstructionPointer() {
        computer.instructionPointer.increment();
    }

    void setInstructionPointerToRememberedWord() {
        computer.instructionPointer.setInstructionPointer(getRememberedWord());
        clearMemory();
    }

    void rememberInstructionPointer() {
        memorizeWord(computer.instructionPointer.getInstructionPointer());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with memory">
    void setMemoryAtOperandToRememberedWord() {
        computer.memory.setMemory(instruction.getOperand(), getRememberedWord());
    }

    void memorizeMemoryAtRememberedAddress() {
        memorizeMemory(getRememberedWord());
    }

    void memorizeMemoryAtOperandAddress() {
        memorizeMemory(instruction.getOperand());
    }

    private void memorizeMemory(int address) {
        memorizeWord(getMemory(address));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with output panel">
    void printUnsignedToOutputPanel() {
        computer.outputPanel.append(String.format("%02d", getRememberedWord()));
    }
    //</editor-fold>

    public boolean doAction(LittleManAction littleManAction) {
        return littleManAction.doAction(this);
    }
    //<editor-fold defaultstate="collapsed" desc="deal with instructions">

    void decodeRememberedInstruction() {
        instruction = InstructionSet.decodeInstruction(getRememberedWord());
        clearMemory();
    }

    void registerRemeberedOperandToInstruction() {
        instruction.acceptOperand(getRememberedWord());
        clearMemory();
    }

    boolean doInstruction() {
        return instruction.doInstruction(this);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="short term memory">
    private void memorizeWord(int word) {
        this.rememberedWord = word;
        isRememberingWord = true;
    }

    void clearMemory() {
        isRememberingWord = false;
    }

    public boolean isThinkingOfWord() {
        return isRememberingWord;
    }
//</editor-fold>

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillOval(getX(), getY(), 10, 10);
        if (isRememberingWord) {
            graphics.drawRect(getX() - 5, getY() - 22, 22, 20);
            graphics.drawString(String.format("%02d", getRememberedWord()), getX() - 2, getY() - 5);
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

    boolean isOperandNeeded() {
        return instruction != null && instruction.isOperandNeeded();
    }

    private int getX() {
        return littleManPosition.getX();
    }

    private int getY() {
        return littleManPosition.getY();
    }

    private int getMemory(int address) {
        return computer.memory.getMemory(address);
    }

}
