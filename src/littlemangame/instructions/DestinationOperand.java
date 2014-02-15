/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littlemancommands.LittleManAction;
import littlemangame.littlemancommands.LittleManCommander;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManWordContainer;

/**
 *
 * @author brian
 */
public enum DestinationOperand {

    REGISTER(LittleManWordContainer.REGISTER),
    REGISER_INDIRECT(LittleManWordContainer.REMEMBERED_MEMORY, LittleManCommander.memorizeAddressAtContainerAction(LittleManWordContainer.REGISTER)),
    INSTRUCTION_POINTER(LittleManWordContainer.INSTRUCTION_POINTER),
    MEMORY(LittleManWordContainer.REMEMBERED_MEMORY);
    private final LittleManWordContainer destinationContainer;
    private final LittleManAction preparationAction;

    private DestinationOperand(LittleManWordContainer destinationContainer) {
        this.destinationContainer = destinationContainer;
        this.preparationAction = LittleManCommander.nullAction;
    }

    private DestinationOperand(LittleManWordContainer destinationContainer, LittleManAction preparationAction) {
        this.destinationContainer = destinationContainer;
        this.preparationAction = preparationAction;
    }

    public LittleManWordContainer getDestinationContainer() {
        return destinationContainer;
    }

    public LittleManAction getPreparationAction() {
        return preparationAction;
    }

}
