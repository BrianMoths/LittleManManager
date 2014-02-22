/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

import Renderer.Drawable;
import java.awt.Graphics;
import littlemangame.littlemancommands.littleman.LittleMan;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.Computer;

/**
 *
 * @author brian
 */
public class LittleManCommander implements Drawable {

    private final LittleMan littleMan;
    private final LittleManAction doCycleCommand;

    public LittleManCommander(Computer computer) {
        littleMan = new LittleMan(computer);
        doCycleCommand = LittleManCommands.getDoCycle();
    }

    public boolean doCycle() {
        return doAction(doCycleCommand);
    }

    public void reset() {
        littleMan.reset();
    }

    private boolean doAction(LittleManAction littleManAction) {
        return littleManAction.doAction(littleMan);
    }

    @Override
    public void draw(Graphics graphics) {
        littleMan.draw(graphics);
    }

}
