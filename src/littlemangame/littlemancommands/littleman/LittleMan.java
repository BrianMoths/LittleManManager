/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman;

import littlemangame.littlemancommands.littleman.littlemanutilities.location.ComputerLocation;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManWordContainer;
import Renderer.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import littlemangame.instructions.Instruction;
import littlemangame.instructions.InstructionFromSet;
import littlemangame.littlemancommands.littleman.littlemanutilities.LittleManAction;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManData;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManMemory;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.Computer;
import littlemangame.littlemancommands.littleman.littlemanutilities.location.LittleManPosition;
import littlemangame.word.BinaryWordOperation;
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
//    private final Computer computer;
//    private final LittleManMemory littleManMemory;
    private final LittleManData littleManData;
    private Instruction instruction;
    private boolean isHalted = false;

    public LittleMan(Computer computer) {
        PositionGetterAdapter positionGetterAdapter = new PositionGetterAdapter();
        littleManData = new LittleManData(computer, positionGetterAdapter);
        littleManPosition = new LittleManPosition(pathY, stepSize, new Point(200, pathY), positionGetterAdapter);
    }

    public boolean goToInstructionLocation(ComputerLocation locationForInstruciton) {
        return littleManPosition.goTo(locationForInstruciton);
    }

//    //<editor-fold defaultstate="collapsed" desc="LittleManWordContainers">
//    public void memorizeDataAtWordContainer(LittleManWordContainer littleManWordContainer) {
//        littleManWordContainer.memorizeData(this);
//    }
//
//    public void memorizeAddressAtWordContainer(LittleManWordContainer littleManWordContainer) {
//        littleManWordContainer.memorizeAddress(this);
//    }
//
//    public void doOperationOnWordContainer(LittleManWordContainer littleManWordContainer, BinaryWordOperation wordOperation) {
//        littleManWordContainer.doBinaryOperation(this, wordOperation);
//    }
//
//    public boolean goToLittleManWordContainer(LittleManWordContainer littleManWordContainer) {
//        return littleManWordContainer.goToLocation(this);
//    }
//    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="interact with register">
    void setRegisterToRememberedWord() {
        computer.register.setWord(useRememberedData());
    }

    void setRegisterToResultOfOperation(BinaryWordOperation wordOperation) {
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
        computer.instructionPointer.setInstructionPointer(useRememberedData());
    }

    void setInstructionPointerToResultOfOperation(BinaryWordOperation wordOperation) {
        doWordOperationOnDesitination(wordOperation, computer.instructionPointer);
    }

    void memorizeAddressAtInstructionPointer() {
        memorizeAddress(computer.instructionPointer.getInstructionPointer());
    }

    void memorizeDataAtInstructionPointer() {
        memorizeData(computer.instructionPointer.getInstructionPointer());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with memory">
    void setMemoryAtRememberedAddressToRememberedData() {
        computer.memory.setMemory(useRememberedAddress(), useRememberedData());
    }

    void setMemoryAtRememberedAddressToResultOfOperation(BinaryWordOperation wordOperation) {
        doWordOperationOnDesitination(wordOperation, getMemoryAtRememberedAddress());
    }

    void memorizeDataAtRememberedAddress() {
        memorizeDataFromAddress(useRememberedAddress());
    }

    void memorizeAddressAtRememberedAddress() {
        memorizeAddress(getMemoryAtRememberedAddress().getWord());
    }

    private void memorizeDataFromAddress(Word address) {
        memorizeData(getMemory(address).getWord());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interact with output panel">
    void printUnsignedToOutputPanel() {
        computer.outputPanel.append(useRememberedData().toString());
    }
    //</editor-fold>

    public boolean doAction(LittleManAction littleManAction) {
        return littleManAction.doAction(this);
    }

    //<editor-fold defaultstate="collapsed" desc="deal with instructions">
    void decodeRememberedInstruction() {
        instruction = InstructionFromSet.decodeInstruction(useRememberedData());
    }

    boolean doInstruction() {
        return doAction(instruction.getAction());
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="short term memory">
    private void memorizeData(Word data) {
        littleManMemory.memorizeData(data);
    }

    private void memorizeAddress(Word address) {
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

    private Word useRememberedData() {
        return littleManMemory.useRememberedData();
    }

    private Word useRememberedAddress() {
        return littleManMemory.useRememberedAddress();
    }

    private Word getRememberedAddress() {
        return littleManMemory.getRememberedAddress();
    }
//</editor-fold>
//</editor-fold>

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillOval(getX(), getY(), 10, 10);
        littleManData.draw(graphics, getX(), getY());
    }

    public boolean halt() {
        isHalted = true;
        return false;
    }

    //<editor-fold defaultstate="collapsed" desc="getters">
    public boolean isHalted() {
        return isHalted;
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

    private void doWordOperationOnDesitination(BinaryWordOperation wordOperation, WordContainer destination) {
        wordOperation.operate(littleManMemory.useRememberedData(), destination);
    }

    private WordContainer getMemoryAtRememberedAddress() {
        return getMemory(useRememberedAddress());
    }

}
