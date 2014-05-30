/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.LittleManCommands;

import littlemangame.genericLittleMan.GenericLittleMan;
import littlemangame.littleman.littlemanutilities.location.ComputerLocation;

/**
 *
 * @author brian
 */
final class LocalAction extends LittleManAction {

    LittleManAction littleManAction;

    LocalAction(ComputerLocation computerLocation, LittleManAction littleManAction) {
        this.littleManAction = new LittleManActionSequence(LittleManCommands.goToComputerLocation(computerLocation), littleManAction);
    }

    private LocalAction(LocalAction localAction) {
        littleManAction = localAction.littleManAction.getResetCopy();
    }

    @Override
    public boolean doAction(GenericLittleMan<?> littleMan) {
        return littleManAction.doAction(littleMan);
    }

    @Override
    public LittleManAction getResetCopy() {
        return new LocalAction(this);
    }

}
