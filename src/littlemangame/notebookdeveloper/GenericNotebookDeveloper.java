/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper;

import littlemangame.computer.computercomponents.Notebook;
import littlemangame.littlemancommands.GenericLittleManCommander;
import littlemangame.notebookdeveloper.gui.GenericOfficeView;

/**
 * This class is responsible for keeping track of the notebook problems to be
 * solved, accepting attempts at solving the notebook problem, and managing the
 * little man commander as he does the instructions in the notebook given as an
 * attempt to solve the problem.
 *
 * @author brian
 * @param <T>
 */
public class GenericNotebookDeveloper<T extends GenericLittleManCommander<?>> {

    private final T littleManCommander;
    private final Notebook memory;
    private final NotebookProblemSet notebookVerifier;

    public GenericNotebookDeveloper(GenericOfficeView<?, ?> officeView, T littleManCommander) {
        this.littleManCommander = littleManCommander;
        officeView.registerLittleManCommander(littleManCommander);
        memory = new Notebook();
        notebookVerifier = NotebookProblemSet.makeDefaultNotebookProblemSet();
        notebookVerifier.beginNextProblem();
    }

    public void doFrames(int numFrames) {
        for (int i = 0; i < numFrames; i++) {
            littleManCommander.doCycle();
        }
    }

    public void endTest() {
        littleManCommander.reset();
    }

    public String submitMemory() {
        final boolean isCorrect = notebookVerifier.verifyNotebook(memory);
        final StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append(notebookVerifier.getMessageFromLastTest());
        if (isCorrect) {
            if (notebookVerifier.hasNextProblem()) {
                notebookVerifier.beginNextProblem();
            } else {
                resultStringBuilder.append("You beat the game!");
            }
        }
        return resultStringBuilder.toString();
    }

    public String getCurrentProblemDescription() {
        return notebookVerifier.getCurrentProblemDescription();
    }

    public Notebook getNotebook() {
        return new Notebook(memory);
    }

    public void setNotebook(Notebook memory) {
        this.memory.loadCopyOfNotebook(memory);
        littleManCommander.reset();
        littleManCommander.loadCopyOfMemory(memory);
    }

    protected final T getLittleManComander() {
        return littleManCommander;
    }

}
