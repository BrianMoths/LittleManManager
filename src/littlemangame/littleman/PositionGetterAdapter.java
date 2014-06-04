/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littleman;

import java.awt.Point;

/**
 *
 * @author brian
 */
public class PositionGetterAdapter {

    public static interface PositionGetter {

        public Point getRegisterPosition();

        public Point getRememberedMemoryPosition();

        public Point getInstructionPointerPosition();

        public Point getOutputPanelPosition();

        public Point getInputPanelPosition();

    }

    private PositionGetter positionGetter;

    public PositionGetterAdapter() {
    }

    public void setPositionGetter(PositionGetter positionGetter) {
        this.positionGetter = positionGetter;
    }

    public Point getRegisterPosition() {
        return positionGetter.getRegisterPosition();
    }

    public Point getRememberedMemoryPosition() {
        return positionGetter.getRememberedMemoryPosition();
    }

    public Point getInstructionPointerPosition() {
        return positionGetter.getInstructionPointerPosition();
    }

    public Point getOutputPanelPosition() {
        return positionGetter.getOutputPanelPosition();
    }

    public Point getInputPanelPosition() {
        return positionGetter.getInputPanelPosition();
    }

}
