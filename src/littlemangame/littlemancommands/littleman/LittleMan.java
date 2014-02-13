/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman;

import littlemangame.littlemancommands.LittleManMemory;
import littlemangame.littlemancommands.littleman.littlemanutilities.LittleManAction;
import littlemangame.word.WordOperation;
import Renderer.Drawable;
import computer.Computer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import littlemangame.instructions.Instruction;
import littlemangame.instructions.InstructionFromSet;
import littlemangame.littlemancommands.littleman.littlemanutilities.location.LittleManPosition;
import littlemangame.word.Word;
import littlemangame.word.WordContainer;

/**
 *
 * @author brian
 */
public class LittleMan implements Drawable {

    static private final int pathY = 200;
    static private final int stepSize = 4;
    private final LittleManPosition littleManPosition;
    private final Computer computer;
    private final LittleManMemory littleManMemory;
    private Instruction instruction;
    private boolean isHalted = false;

    public LittleMan(Computer computer) {
        this.computer = computer;
        littleManPosition = new LittleManPosition(pathY, stepSize, new Point(200, pathY));
        littleManMemory = new LittleManMemory();
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

    private boolean goToMemoryLocation(Word address) {
        return littleManPosition.goTo(computer.memory, address);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with register">
    void setRegisterToRememberedWord() {
        computer.register.setWord(getRememberedData());
        clearDataMemory();
    }

    void setRegisterToResultOfOperation(WordOperation wordOperation) {
        doWordOperationOnDesitination(wordOperation, computer.register);
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

    void setInstructionPointerToResultOfOperation(WordOperation wordOperation) {
        doWordOperationOnDesitination(wordOperation, computer.instructionPointer);
    }

    void memorizeInstructionPointer() {
        memorizeAddress(computer.instructionPointer.getInstructionPointer());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with memory">
    void setMemoryAtRememberedAddressToRememberedData() {
        computer.memory.setMemory(getRememberedAddress(), getRememberedData());
        clearAddressMemory();
    }

    void setMemoryAtRememberedAddressToResultOfOperation(WordOperation wordOperation) {
        doWordOperationOnDesitination(wordOperation, getMemoryAtRememberedAddress());
    }

    void memorizeDataAtRememberedAddress() {
        memorizeDataFromAddress(getRememberedAddress());
        clearAddressMemory();
    }

    void memorizeAddressAtRememberedAddress() {
        memorizeAddress(getMemoryAtRememberedAddress().getWord());
        clearAddressMemory();
    }

    private void memorizeDataFromAddress(Word address) {
        memorizeData(getMemory(address).getWord());
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

    boolean doInstruction() {
        return doAction(instruction.getAction());
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="short term memory">
    void memorizeData(Word data) {
        littleManMemory.memorizeData(data);
    }

    void memorizeAddress(Word address) {
        littleManMemory.memorizeAddress(address);
    }

    void clearDataMemory() {
        littleManMemory.clearDataMemory();
    }

    void clearAddressMemory() {
        littleManMemory.clearAddressMemory();
    }

    void clearMemory() {
        littleManMemory.clearMemory();
    }

    boolean isRememberingData() {
        return littleManMemory.isRememberingData();
    }

    boolean isRememberingAddress() {
        return littleManMemory.isRememberingAddress();
    }

//</editor-fold>
//</editor-fold>
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillOval(getX(), getY(), 10, 10);
        if (isRememberingData()) {
            final Color color = graphics.getColor();
            graphics.setColor(Color.red);
            graphics.drawRect(getX() - 15, getY() - 22, 22, 20);
            graphics.drawString(getRememberedData().toString(), getX() - 12, getY() - 5);
            graphics.setColor(color);
        }
        if (isRememberingAddress()) {
            final Color color = graphics.getColor();
            graphics.setColor(Color.blue);
            graphics.drawRect(getX() + 15, getY() - 22, 22, 20);
            graphics.drawString(getRememberedAddress().toString(), getX() + 18, getY() - 5);
            graphics.setColor(color);
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
        return littleManMemory.getRememberedData();
    }

    Word getRememberedAddress() {
        return littleManMemory.getRememberedAddress();
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

    private WordContainer getMemory(Word address) {
        return computer.memory.getMemory(address);
    }
    //</editor-fold>

    private void doWordOperationOnDesitination(WordOperation wordOperation, WordContainer destination) {
        wordOperation.operate(littleManMemory.getRememberedData(), destination);
    }

    private WordContainer getMemoryAtRememberedAddress() {
        return getMemory(getRememberedAddress());
    }

}
