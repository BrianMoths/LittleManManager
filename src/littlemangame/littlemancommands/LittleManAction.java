/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

import littlemangame.littlemancommands.littleman.LittleMan;

/**
 *
 * @author brian
 */
public abstract class LittleManAction {

    LittleManAction() {
    }

    public abstract boolean doAction(LittleMan littleMan);

}
