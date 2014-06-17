/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.genericLittleMan;

import Renderer.Drawable;
import java.awt.Graphics;
import littlemangame.computer.Notebook;
import littlemangame.littlemancommands.LittleManCommands.LittleManAction;
import littlemangame.littlemancommands.LittleManCommands.LittleManCommands;

/**
 *
 * @author brian
 * @param <T>
 */
public class GenericLittleManCommander<T extends GenericLittleMan<?>> implements Drawable {

    private final T littleMan;
    private LittleManAction doCycleCommand;

    protected GenericLittleManCommander(T littleMan) {
        this.littleMan = littleMan;
        doCycleCommand = LittleManCommands.getDoCycle();
    }

    public boolean doCycle() {
        return doCycleCommand.doAction(littleMan);
    }

    public void reset() {
        littleMan.reset();
        doCycleCommand = LittleManCommands.getDoCycle();
    }

    @Override
    public void draw(Graphics graphics) {
        littleMan.draw(graphics);
    }

    public void loadCopyOfMemory(Notebook memory) {
        littleMan.loadCopyOfMemory(memory);
    }

    public boolean isHalted() {
        return littleMan.isHalted();
    }

}
