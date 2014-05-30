/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.LittleManCommands;

import littlemangame.genericLittleMan.GenericLittleMan;

/**
 *
 * @author brian
 */
public abstract class LittleManAction {

    LittleManAction() {
    }

    public abstract boolean doAction(GenericLittleMan<?> littleMan);

    public LittleManAction getResetCopy() {
        return this;
    }

}
