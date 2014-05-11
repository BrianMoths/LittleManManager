/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

import Renderer.Drawable;
import java.awt.Graphics;
import littlemangame.computer.Computer;
import littlemangame.computer.Memory;
import littlemangame.littleman.LittleMan;
import littlemangame.littlemancommands.LittleManCommands.LittleManAction;
import littlemangame.littlemancommands.LittleManCommands.LittleManCommands;
import littlemangame.notebookdeveloper.gui.OfficeView;

/**
 *
 * @author brian
 */
public class LittleManCommander implements Drawable {

    private final LittleMan littleMan;
    private LittleManAction doCycleCommand;

    public LittleManCommander(OfficeView officeView) {
        this(new Computer(officeView.getOutputPanel(), officeView.getInputPanel()));
    }

    public LittleManCommander(Computer computer) {
        this(new LittleMan(computer));
    }

    protected LittleManCommander(LittleMan littleMan) {
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

    public void loadCopyOfMemory(Memory memory) {
        littleMan.loadCopyOfMemory(memory);
    }

    public boolean isHalted() {
        return littleMan.isHalted();
    }

}
