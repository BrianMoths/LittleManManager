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
import littlemangame.instructions.InstructionFromSet;
import littlemangame.littleman.location.LittleManPosition;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class LittleMan implements Drawable {

    static private final int pathY = 200;
    static private final int stepSize = 4;
    private final LittleManPosition littleManPosition;
    private final Computer computer;
    private Word rememberedAddress;
    private boolean isRememberingAddress;
    private Word rememberedData;
    private boolean isRememberingData = false;
    private Instruction instruction;
    private boolean isHalted = false;

    public LittleMan(Computer computer) {
        this.computer = computer;
        littleManPosition = new LittleManPosition(pathY, stepSize, new Point(200, pathY));
    }

    //<editor-fold defaultstate="collapsed" desc="movement">
    boolean goToInstructionLocation(LocationForInstruction locationForInstruciton) {
        return locationForInstruciton.goToLocation(this);
    }

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
        return goToMemoryLocation(getRememberedAddress());
    }

//    boolean goToOperandMemoryLocation() {
//        return goToMemoryLocation(instruction.getDataOperand());
//    }
    private boolean goToMemoryLocation(Word address) {
        return littleManPosition.goTo(computer.memory, address);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with register">
    void setRegisterToRememberedWord() {
        computer.register.setWord(getRememberedData());
        clearDataMemory();
    }

    void memorizeDataAtRegister() {
        memorizeData(computer.register.getWord());
    }

    void memorizeAddressAtRegister() {
        memorizeAddress(computer.register.getWord());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with instruction pointer">
    void incrementInstructionPointer() {
        computer.instructionPointer.increment();
    }

    void setInstructionPointerToRememberedData() {
        computer.instructionPointer.setInstructionPointer(getRememberedData());
        clearDataMemory();
    }

    void memorizeInstructionPointer() {
        memorizeAddress(computer.instructionPointer.getInstructionPointer());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with memory">
//    void setMemoryAtOperandToRememberedWord() {
//        computer.memory.setMemory(instruction.getDataOperand(), getRememberedData());
//    }
    void setMemoryAtRememberedAddressToRememberedData() {
        computer.memory.setMemory(getRememberedAddress(), getRememberedData());
    }

    void memorizeDataAtRememberedAddress() {
        memorizeDataFromAddress(getRememberedAddress());
    }

    void memorizeAddressAtRememberedAddress() {
        memorizeAddress(getMemory(getRememberedAddress()));
    }

//    void memorizeMemoryAtOperandAddress() {
//        memorizeData(instruction.getDataOperand());
//    }
    private void memorizeDataFromAddress(Word address) {
        memorizeData(getMemory(address));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with output panel">
    void printUnsignedToOutputPanel() {
        computer.outputPanel.append(getRememberedData().toString());
    }
    //</editor-fold>

    public boolean doAction(LittleManAction littleManAction) {
        return littleManAction.doAction(this);
    }
    //<editor-fold defaultstate="collapsed" desc="deal with instructions">

    void decodeRememberedInstruction() {
        instruction = InstructionFromSet.decodeInstruction(getRememberedData());
        clearDataMemory();
    }

//    void registerRemeberedOperandToInstruction() {
//        instruction.acceptDataOperand(getRememberedData());
//        clearDataMemory();
//    }
//    void memorizeOperand() {
//        memorizeData(instruction.getDataOperand());
//    }
    boolean doInstruction() {
        return doAction(instruction.getAction());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="short term memory (move into own class)">
    private void memorizeData(Word data) {
        this.rememberedData = data;
        isRememberingData = true;
    }

    private void memorizeAddress(Word address) {
        rememberedAddress = address;
        isRememberingAddress = true;
    }

    void clearDataMemory() {
        isRememberingData = false;
    }

    void clearAddressMemory() {
        isRememberingAddress = false;
    }

    void clearMemory() {
        clearAddressMemory();
        clearDataMemory();
    }

    boolean isThinkingOfData() {
        return isRememberingData;
    }

    boolean isThinkingOfAddress() {
        return isRememberingAddress;
    }
//</editor-fold>

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillOval(getX(), getY(), 10, 10);
        if (isRememberingData) {
            graphics.drawRect(getX() - 5, getY() - 22, 22, 20);
            graphics.drawString(getRememberedData().toString(), getX() - 2, getY() - 5);
        }
    }

    public boolean halt() {
        isHalted = true;
        return false;
    }

    //<editor-fold defaultstate="collapsed" desc="getters">
    public boolean isHalted() {
        return isHalted;
    }

    Word getRememberedData() {
        return rememberedData;
    }

    Word getRememberedAddress() {
        return rememberedAddress;
    }

    boolean isDataOperandNeeded() {
        return instruction != null && instruction.isDataOperandNeeded();
    }

    boolean isAddressOperandNeeded() {
        return instruction != null && instruction.isAddressOperandNeeded();
    }

    private int getX() {
        return littleManPosition.getX();
    }

    private int getY() {
        return littleManPosition.getY();
    }

    private Word getMemory(Word address) {
        return computer.memory.getMemory(address);
    }
    //</editor-fold>

}
