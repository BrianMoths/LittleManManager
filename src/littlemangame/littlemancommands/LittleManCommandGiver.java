/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

/**
 *
 * @author brian
 */
public class LittleManCommandGiver {

    public static interface LittleManCommandDoer {

        public boolean doLittleManCommand(LittleManAction littleManAction);

    }

    private LittleManCommandDoer littleManCommandDoer;

    public void setLittleManCommandDoer(LittleManCommandDoer littleManCommandDoer) {
        this.littleManCommandDoer = littleManCommandDoer;
    }

    boolean doLittleManCommand(LittleManAction littleManAction) {
        return littleManCommandDoer.doLittleManCommand(littleManAction);
    }

}
