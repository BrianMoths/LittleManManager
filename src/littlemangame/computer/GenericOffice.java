/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.computer;

import Renderer.Drawable;
import java.awt.Graphics;
import littlemangame.computer.computercomponents.ComputerInputter;
import littlemangame.computer.computercomponents.ComputerOutputter;
import littlemangame.computer.computercomponents.Notebook;
import littlemangame.computer.computercomponents.NotebookPageSheet;
import littlemangame.computer.computercomponents.Worksheet;
import littlemangame.word.BinaryWordOperation;
import littlemangame.word.UnaryWordOperation;
import littlemangame.word.Word;

/**
 *
 * @author brian
 * @param <T> the type of worksheet to use
 * @param <U> the type of notebook to use
 * @param <V> the type of notebook page sheet to use
 * @param <W> the type of computer outputter to use
 * @param <X> the type of computer inputter to use
 */
public class GenericOffice<T extends Worksheet, U extends Notebook, V extends NotebookPageSheet, W extends ComputerOutputter, X extends ComputerInputter>
        implements Drawable {

    public final T worksheet;
    public final U notebook;
    public final V notebookPageSheet;
    public final W outputPanel;
    public final X inputPanel;

    /**
     * creates an office with the given components
     *
     * @param worksheet the worksheet for the office
     * @param notebook the notebook for the office
     * @param notebookPageSheet the notebook page sheet for the office
     * @param outputPanel the output panel for the office
     * @param inputPanel the input panel for the office
     */
    public GenericOffice(T worksheet, U notebook, V notebookPageSheet, W outputPanel, X inputPanel) {
        this.worksheet = worksheet;
        this.notebook = notebook;
        this.notebookPageSheet = notebookPageSheet;
        this.outputPanel = outputPanel;
        this.inputPanel = inputPanel;
    }

    @Override
    public void draw(Graphics graphics) {
        worksheet.draw(graphics);
        notebook.draw(graphics);
        notebookPageSheet.draw(graphics);
    }

    /**
     * Resets the notebookPageSheet to the zero word and clears the output
     * panel. The worksheet and notebook are left unchanged.
     */
    public void reset() {
        notebookPageSheet.setWord(Word.ZERO_WORD);
        outputPanel.clear();
    }

    /**
     * copies the contents of the given notebook in the notebook in this office
     *
     * @param memory the notebook to be copied
     */
    public void loadCopyOfMemory(Notebook memory) {
        this.notebook.loadCopyOfMemory(memory);
    }

    /**
     * gets the value of the word written in the given
     * {@link OfficeWordContainer}.
     *
     * @param computerWordContainer the officeWordContainer whose word is to
     * returned.
     *
     * @return the word stored at the given office word container
     */
    public Word getValueOfComputerWordContainer(OfficeWordContainer computerWordContainer) {
        return computerWordContainer.getWord(this);
    }

    /**
     * performs the given {@link UnaryWordOperation} on the given
     * {@link OfficeWordContainer}.
     *
     * @param officeWordContainer the office word container to be operated on
     * @param unaryWordOperation the operation to perform
     */
    public void doUnaryOperation(OfficeWordContainer officeWordContainer, UnaryWordOperation unaryWordOperation) {
        officeWordContainer.doUnaryOperation(this, unaryWordOperation);
    }

    /**
     * performs the given {@link BinaryWordOperation} on the given
     * {@link OfficeWordContainer} with the given operand.
     *
     * @param officeWordContainer the office word container to be operated on
     * @param binaryWordOperation the operation to perform
     * @param operand the second operand to be used with the given binary word
     * operation
     */
    public void doBinaryWordOperation(OfficeWordContainer officeWordContainer, BinaryWordOperation binaryWordOperation, Word operand) {
        officeWordContainer.doBinaryOperation(this, binaryWordOperation, operand);
    }

}
