/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littleman;

/**
 *
 * @author brian
 */
enum LocationForInstruction {

    INSTRUCTION_POINTER(new LittleManMover() {
        @Override
        public boolean goToLocation(LittleMan littleMan) {
            return littleMan.goToInstructionPointer();
        }

    }),
    REGISTER(new LittleManMover() {
        @Override
        public boolean goToLocation(LittleMan littleMan) {
            return littleMan.goToRegister();
        }

    }),
    REMEMBERED_MEMORY(new LittleManMover() {
        @Override
        public boolean goToLocation(LittleMan littleMan) {
            return littleMan.goToRememberedMemoryLocation();
        }

    }),
    OUTPUT_PANEL(new LittleManMover() {
        @Override
        public boolean goToLocation(LittleMan littleMan) {
            return littleMan.goToOutputPanel();
        }

    });
    private final LittleManMover littleManMover;

    private LocationForInstruction(LittleManMover littleManMover) {
        this.littleManMover = littleManMover;
    }

    public boolean goToLocation(LittleMan littleMan) {
        return littleManMover.goToLocation(littleMan);
    }

    private static interface LittleManMover {

        public boolean goToLocation(LittleMan littleMan);

    }

}
