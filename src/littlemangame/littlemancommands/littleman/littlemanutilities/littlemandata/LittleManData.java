/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata;

import java.awt.Graphics;
import java.awt.Point;
import littlemangame.instructions.InstructionFromSet;
import littlemangame.instructions.interfaceandimplementations.Instruction;
import littlemangame.littlemancommands.littleman.PositionGetterAdapter;
import littlemangame.littlemancommands.littleman.PositionGetterAdapter.PositionGetter;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.Computer;
import littlemangame.word.BinaryWordOperation;
import littlemangame.word.UnaryWordOperation;
import littlemangame.word.Word;
import littlemangame.word.WordContainer;

/**
 *
 * @author brian
 */
public class LittleManData {

    private final Computer computer;
    private final LittleManMemory littleManMemory;

    public LittleManData(final Computer computer, PositionGetterAdapter positionGetterAdapter) {
        this.computer = computer;
        littleManMemory = new LittleManMemory();
        positionGetterAdapter.setPositionGetter(makePositionGetter(computer));
    }

    public void memorizeDataAtContainer(LittleManWordContainer littleManWordContainer) {
        memorizeData(littleManWordContainer.getWord(this));
    }

    public void memorizeAddressAtContainer(LittleManWordContainer littleManWordContainer) {
        memorizeAddress(littleManWordContainer.getWord(this));
    }

    public void doBinaryOperationOnContainer(LittleManWordContainer littleManWordContainer, BinaryWordOperation binaryWordOperation) {
        binaryWordOperation.operate(useRememberedData(), littleManWordContainer.getWordContainer(this));
    }

    public void doUnaryOperationOnContainer(LittleManWordContainer littleManWordContainer, UnaryWordOperation unaryWordOperation) {
        unaryWordOperation.operate(littleManWordContainer.getWordContainer(this));
    }

    public void printUnsigedToOutputPanel() {
        computer.outputPanel.append(useRememberedData().toStringUnsigned());
    }

    public boolean memorizeDataFromInputPanel() {
        if (!computer.inputPanel.isPanelEnabled()) {
            computer.inputPanel.enablePanel();
        }
        if (computer.inputPanel.isValueSelected()) {
            memorizeData(computer.inputPanel.getLastSelectedWord());
            return true;
        } else {
            return false;
        }
    }

    public Instruction decodeRememberedInstruction() {
        return InstructionFromSet.decodeInstruction(useRememberedData());
    }

    public boolean test(LittleManTest littleManTest) {
        return littleManTest.test(this);
    }

    public void reset() {
        clearMemory();
        computer.reset();
    }

    //<editor-fold defaultstate="collapsed" desc="containers">
    WordContainer getInstructionPointer() {
        return computer.instructionPointer;
    }

    WordContainer getRegister() {
        return computer.register;
    }

    WordContainer getRememberedMemory() {
        return computer.memory.getMemory(useRememberedAddress());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="short term memory">
    void memorizeData(Word data) {
        littleManMemory.memorizeData(data);
    }

    void memorizeAddress(Word address) {
        littleManMemory.memorizeAddress(address);
    }

//    void clearDataMemory() {
//        littleManMemory.clearDataMemory();
//    }
//
//    void clearAddressMemory() {
//        littleManMemory.clearAddressMemory();
//    }
//
    public void clearMemory() {
        littleManMemory.clearMemory();
    }
//
//    boolean isRememberingData() {
//        return littleManMemory.isRememberingData();
//    }
//
//    boolean isRememberingAddress() {
//        return littleManMemory.isRememberingAddress();
//    }

    Word useRememberedData() {
        return littleManMemory.useRememberedData();
    }

    Word useRememberedAddress() {
        return littleManMemory.useRememberedAddress();
    }

    Word getRememberedAddress() {
        return littleManMemory.getRememberedAddress();
    }
    //</editor-fold>

    public void draw(Graphics graphics, int x, int y) {
        computer.draw(graphics);
        littleManMemory.draw(graphics, x, y);
    }

    private PositionGetter makePositionGetter(final Computer computer) {
        return new PositionGetter() {
            @Override
            public Point getRegisterPosition() {
                return computer.register.getAccessLocation();
            }

            @Override
            public Point getRememberedMemoryPosition() {
                return computer.memory.getAccessLocation(getRememberedAddress());
            }

            @Override
            public Point getInstructionPointerPosition() {
                return computer.instructionPointer.getAccessLocation();
            }

            @Override
            public Point getOutputPanelPosition() {
                return computer.outputPanel.getAccessLocation();
            }

            @Override
            public Point getInputPanelPosition() {
                return computer.inputPanel.getAccessLocation();
            }

        };
    }

}
