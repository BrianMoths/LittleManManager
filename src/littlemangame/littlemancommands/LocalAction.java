/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

import littlemangame.littlemancommands.littleman.LittleMan;
import littlemangame.littlemancommands.littleman.littlemanutilities.location.ComputerLocation;

/**
 *
 * @author brian
 */
final class LocalAction extends LittleManAction {

    LittleManAction littleManAction;

    LocalAction(ComputerLocation computerLocation, LittleManAction littleManAction) {
        this.littleManAction = new LittleManActionSequence(LittleManCommander.goToComputerLocation(computerLocation), littleManAction);
    }

    private LocalAction(LocalAction localAction) {
        littleManAction = localAction.littleManAction.getResetCopy();
    }

    @Override
    public boolean doAction(LittleMan littleMan) {
        return littleManAction.doAction(littleMan);
    }

    @Override
    public LittleManAction getResetCopy() {
        return new LocalAction(this);
    }

}
