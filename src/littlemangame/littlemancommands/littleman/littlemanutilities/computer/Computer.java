/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman.littlemanutilities.computer;

import Renderer.Drawable;
import java.awt.Graphics;

/**
 *
 * @author brian
 */
public class Computer implements Drawable {

    public final Register register;
    public final Memory memory;
    public final InstructionPointer instructionPointer;
    public final OutputPanel outputPanel;

    public Computer(OutputPanel outputPanel) {
        register = new Register();
        memory = new Memory();
        instructionPointer = new InstructionPointer();
        this.outputPanel = outputPanel;
    }

    @Override
    public void draw(Graphics graphics) {
        outputPanel.draw(graphics);
        register.draw(graphics);
        memory.draw(graphics);
        instructionPointer.draw(graphics);
    }

}
