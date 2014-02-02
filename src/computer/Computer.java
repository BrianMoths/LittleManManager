/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

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

    public Computer() {
        register = new Register();
        memory = new Memory();
        instructionPointer = new InstructionPointer();
    }

    @Override
    public void draw(Graphics graphics) {
        register.draw(graphics);
        memory.draw(graphics);
        instructionPointer.draw(graphics);
    }

}
