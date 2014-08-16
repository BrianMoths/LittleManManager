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
    private final Notebook notebook;
    private final NotebookProblemSet notebookVerifier;

    /**
     * constructs a notebook developer to be shown in the given office view, and
     * to command the given little man commnader.
     *
     * @param officeView the office view in which to show this notebook
     * developer
     * @param littleManCommander the little man commander used for testing
     * pruposes by this little man commander
     */
    public GenericNotebookDeveloper(GenericOfficeView<?, ?> officeView, T littleManCommander) {
        this.littleManCommander = littleManCommander;
        officeView.registerLittleManCommander(littleManCommander);
        notebook = new Notebook();
        notebookVerifier = NotebookProblemSet.makeDefaultNotebookProblemSet();
        notebookVerifier.beginNextProblem();
    }

    /**
     * this notebook developer makes the little man commander to the given
     * number of cycles.
     *
     * @param numFrames the number of cycles for the little man commander to do.
     */
    public void doFrames(int numFrames) {
        for (int i = 0; i < numFrames; i++) {
            littleManCommander.doCycle();
        }
    }

    /**
     * ends the little man commnander's test of the notebook.
     */
    public void endTest() {
        littleManCommander.reset();
    }

    /**
     * submits this notebook developer's notebook for verification. Returns a
     * string explaining why the notebook is wrong or a message saying the
     * notebook is correct.
     *
     * @return a string explaining if the notebook submitted was correct or not
     */
    public String submitNotebookSolutionAttempt() {
        final boolean isCorrect = notebookVerifier.verifyNotebook(notebook);
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

    /**
     * returns a description of the current notebook development problem
     *
     * @return a description of the current notebook development problem
     */
    public String getCurrentProblemDescription() {
        return notebookVerifier.getCurrentProblemDescription();
    }

    /**
     * returns a copy of the notebook solution attempt currently held by this
     * notebook developer
     *
     * @return a copy of the notebook solution attempt currently held by this
     * notebook developer
     */
    public Notebook getNotebookSolutionAttempt() {
        return new Notebook(notebook);
    }

    /**
     * set the notebook solution attempt for this notebook developer.
     *
     * @param notebook the notebook solution attempt for this notebook
     * developer.
     */
    public void setNotebookSolutionAttempt(Notebook notebook) {
        this.notebook.loadCopyOfNotebook(notebook);
        littleManCommander.reset();
        littleManCommander.loadCopyOfNotebook(notebook);
    }

    protected final T getLittleManComander() {
        return littleManCommander;
    }

}
