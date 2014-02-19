/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman.littlemanutilities.location;

/**
 *
 * @author brian
 */
public enum ComputerLocation {

    INSTRUCTION_POINTER(new PositionGetter() {
        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return littleManPosition.goToPoint(littleManPosition.getPositionGetterAdapter().getInstructionPointerPosition());
        }

    }),
    REGISTER(new PositionGetter() {
        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return littleManPosition.goToPoint(littleManPosition.getPositionGetterAdapter().getRegisterPosition());

        }

    }),
    REMEMBERED_MEMORY(new PositionGetter() {
        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return littleManPosition.goToPoint(littleManPosition.getPositionGetterAdapter().getRememberedMemoryPosition());
        }

    }),
    OUTPUT_PANEL(new PositionGetter() {
        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return littleManPosition.goToPoint(littleManPosition.getPositionGetterAdapter().getOutputPanelPosition());

        }

    }),
    INPUT_PANEL(new PositionGetter() {

        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return littleManPosition.goToPoint(littleManPosition.getPositionGetterAdapter().getInputPanelPosition());
        }

    }),
    CURRENT_LOCATION(new PositionGetter() {
        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return true;
        }

    });
    private final PositionGetter positionGetter;

    private ComputerLocation(PositionGetter positionGetter) {
        this.positionGetter = positionGetter;
    }

    boolean goTo(LittleManPosition littleManPosition) {
        return positionGetter.goTo(littleManPosition);

    }

    private static interface PositionGetter {

        public boolean goTo(LittleManPosition littleManPosition);

    }

}
