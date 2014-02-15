/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littlemancommands.LittleManAction;
import littlemangame.littlemancommands.LittleManActionSequence;
import static littlemangame.littlemancommands.LittleManCommander.*;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManWordContainer;

/**
 *
 * @author brian
 */
public enum SourceOperand {

    IMMEDIATE(nullAction),
    REGISTER(LittleManWordContainer.REGISTER),
    REGISTER_INDIRECT(new LittleManActionSequence(memorizeAddressAtContainerAction(LittleManWordContainer.REGISTER), memorizeDataAtContainerAction(LittleManWordContainer.REMEMBERED_MEMORY))),
    MEMORY(LittleManWordContainer.REMEMBERED_MEMORY);
    private final LittleManAction operandMemorizer;

    private SourceOperand(LittleManAction operandMemorizer) {
        this.operandMemorizer = operandMemorizer;
    }

    private SourceOperand(LittleManWordContainer littleManWordContainer) {
        this.operandMemorizer = memorizeDataAtContainerAction(littleManWordContainer);
    }

    LittleManAction getOperandMemorizer() {
        return operandMemorizer;
    }

}