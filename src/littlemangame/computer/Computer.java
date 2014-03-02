/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.computer;

import Renderer.Drawable;
import java.awt.Graphics;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class Computer implements Drawable {

    public final Register register;
    public final Memory memory;
    public final InstructionPointer instructionPointer;
    public final ComputerOutputter outputPanel;
    public final ComputerInputter inputPanel;

    public Computer(ComputerOutputter outputPanel, ComputerInputter inputPanel) {
        register = new Register();
        memory = new Memory();
        instructionPointer = new InstructionPointer();
        this.outputPanel = outputPanel;
        this.inputPanel = inputPanel;
    }

    @Override
    public void draw(Graphics graphics) {
        register.draw(graphics);
        memory.draw(graphics);
        instructionPointer.draw(graphics);
    }

    public void reset() {
        instructionPointer.setInstructionPointer(Word.ZERO_WORD);
        outputPanel.clear();
    }

    public void loadCopyOfMemory(Memory memory) {
        this.memory.loadCopyOfMemory(memory);
    }

}