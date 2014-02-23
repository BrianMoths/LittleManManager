/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.LittleManCommands;

import littlemangame.littleman.LittleMan;

/**
 *
 * @author brian
 */
public abstract class LittleManAction {

    LittleManAction() {
    }

    public abstract boolean doAction(LittleMan littleMan);

    public LittleManAction getResetCopy() {
        return this;
    }

}
