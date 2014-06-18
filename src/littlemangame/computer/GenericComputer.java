/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.computer;

import littlemangame.computer.computercomponents.Notebook;
import littlemangame.computer.computercomponents.ComputerInputter;
import littlemangame.computer.computercomponents.ComputerOutputter;
import littlemangame.computer.computercomponents.NotebookPageSheet;
import littlemangame.computer.computercomponents.Worksheet;
import Renderer.Drawable;
import java.awt.Graphics;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class GenericComputer<T extends Worksheet, U extends Notebook, V extends NotebookPageSheet, W extends ComputerOutputter, X extends ComputerInputter>
        implements Drawable {

    public final T register;
    public final U memory;
    public final V instructionPointer;
    public final W outputPanel;
    public final X inputPanel;

    public GenericComputer(T register, U memory, V instructionPointer, W outputPanel, X inputPanel) {
        this.register = register;
        this.memory = memory;
        this.instructionPointer = instructionPointer;
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
        instructionPointer.setWord(Word.ZERO_WORD);
        outputPanel.clear();
    }

    public void loadCopyOfMemory(Notebook memory) {
        this.memory.loadCopyOfMemory(memory);
    }

}
