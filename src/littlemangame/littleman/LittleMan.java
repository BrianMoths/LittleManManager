/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littleman;

import Renderer.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import littlemangame.computer.Computer;
import littlemangame.computer.Memory;
import littlemangame.instructions.interfaceandimplementations.Instruction;
import littlemangame.littleman.littlemanutilities.littlemandata.LittleManData;
import littlemangame.littleman.littlemanutilities.littlemandata.LittleManTest;
import littlemangame.littleman.littlemanutilities.littlemandata.LittleManWordContainer;
import littlemangame.littleman.littlemanutilities.location.ComputerLocation;
import littlemangame.littleman.littlemanutilities.location.LittleManPosition;
import littlemangame.littlemancommands.LittleManCommands.LittleManAction;
import littlemangame.word.BinaryWordOperation;
import littlemangame.word.UnaryWordOperation;

/**
 *
 * @author brian
 */
public class LittleMan implements Drawable {

    static private final int pathY = 200;
    static private final int stepSize = 4;
    private final LittleManPosition littleManPosition;
    private final LittleManData littleManData;
    private Instruction instruction;
    private boolean isHalted;

    public LittleMan(Computer computer) {
        final PositionGetterAdapter positionGetterAdapter = new PositionGetterAdapter();
        littleManData = new LittleManData(computer, positionGetterAdapter);
        littleManPosition = new LittleManPosition(pathY, stepSize, new Point(200, pathY), positionGetterAdapter);
        isHalted = false;
    }

    public boolean goToComputerLocation(ComputerLocation computerLocation) {
        return littleManPosition.goTo(computerLocation);
    }

    //<editor-fold defaultstate="collapsed" desc="container">
    public void memorizeDataAtContainer(LittleManWordContainer littleManWordContainer) {
        littleManData.memorizeDataAtContainer(littleManWordContainer);
    }

    public void memorizeAddressAtContainer(LittleManWordContainer littleManWordContainer) {
        littleManData.memorizeAddressAtContainer(littleManWordContainer);
    }

    public void doBinaryOperationOnContainer(LittleManWordContainer littleManWordContainer, BinaryWordOperation binaryWordOperation) {
        littleManData.doBinaryOperationOnContainer(littleManWordContainer, binaryWordOperation);
    }

    public void doUnaryOperationOnContainer(LittleManWordContainer littleManWordContainer, UnaryWordOperation unaryWordOperation) {
        littleManData.doUnaryOperationOnContainer(littleManWordContainer, unaryWordOperation);
    }
    //</editor-fold>

    public void printUnsignedToOutputPanel() {
        littleManData.printUnsigedToOutputPanel();
    }

    public boolean getDataFromInputPanel() {
        return littleManData.memorizeDataFromInputPanel();
    }

    public boolean test(LittleManTest littleManTest) {
        return littleManData.test(littleManTest);
    }

    public void clearMemory() {
        littleManData.clearMemory();
    }

    public void loadCopyOfMemory(Memory memory) {
        littleManData.loadCopyOfMemory(memory);
    }

    public void halt() {
        isHalted = true;
    }

    public void reset() {
        littleManData.reset();
        isHalted = false;
    }

    public boolean doAction(LittleManAction littleManAction) {
        return littleManAction.doAction(this);
    }

    //<editor-fold defaultstate="collapsed" desc="deal with instructions">
    public void decodeRememberedInstruction() {
        instruction = littleManData.decodeRememberedInstruction();
    }

    public boolean doInstruction() {
        return instruction.getAction().doAction(this);
    }
//</editor-fold>

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillOval(getX(), getY(), 10, 10);
        littleManData.draw(graphics, getX(), getY());
    }

    //<editor-fold defaultstate="collapsed" desc="getters">
    public boolean isDataOperandNeeded() {
        return instruction != null && instruction.isDataOperandNeeded();
    }

    public boolean isAddressOperandNeeded() {
        return instruction != null && instruction.isAddressOperandNeeded();
    }

    public boolean isHalted() {
        return isHalted;
    }

    private int getX() {
        return littleManPosition.getX();
    }

    private int getY() {
        return littleManPosition.getY();
    }
    //</editor-fold>

}