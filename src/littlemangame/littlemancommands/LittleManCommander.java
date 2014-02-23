/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

import Renderer.Drawable;
import java.awt.Graphics;
import littlemangame.littlemancommands.LittleManCommands.LittleManAction;
import littlemangame.littlemancommands.LittleManCommands.LittleManCommands;
import littlemangame.littleman.LittleMan;
import littlemangame.computer.Computer;
import littlemangame.computer.Memory;

/**
 *
 * @author brian
 */
public class LittleManCommander implements Drawable {

    private final LittleMan littleMan;
    private LittleManAction doCycleCommand;

    public LittleManCommander(Computer computer) {
        littleMan = new LittleMan(computer);
        doCycleCommand = LittleManCommands.getDoCycle();
    }

    public boolean doCycle() {
        return doAction(doCycleCommand);
    }

    public void reset() {
        littleMan.reset();
        doCycleCommand = LittleManCommands.getDoCycle();
    }

    private boolean doAction(LittleManAction littleManAction) {
        return littleManAction.doAction(littleMan);
    }

    @Override
    public void draw(Graphics graphics) {
        littleMan.draw(graphics);
    }

    public void loadCopyOfMemory(Memory memory) {
        littleMan.loadCopyOfMemory(memory);
    }

}
