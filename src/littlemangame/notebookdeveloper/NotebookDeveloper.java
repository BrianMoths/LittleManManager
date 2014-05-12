/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import littlemangame.computer.Memory;
import littlemangame.littlemancommands.LittleManCommander;
import littlemangame.notebookdeveloper.gui.NotebookDeveloperGui;
import littlemangame.notebookdeveloper.submissioncontrols.SubmissionControllerAdapter;

/**
 *
 * @author brian
 */
public class NotebookDeveloper {

    private final SubmissionControllerAdapter submissionControllerAdapter;
    private final LittleManCommander littleManCommander;
    private final Memory memory;
    private final NotebookProblemSet notebookVerifier;

    public NotebookDeveloper(NotebookDeveloperGui notebookDeveloperGui) {
        notebookVerifier = NotebookProblemSet.makeDefaultNotebookProblemSet();
        notebookVerifier.beginNextProblem();
        submissionControllerAdapter = new SubmissionControllerAdapter(this, notebookDeveloperGui.getSubmissionControlGui());
        littleManCommander = new LittleManCommander(notebookDeveloperGui.getOfficeView());
        notebookDeveloperGui.registerLittleManCommander(littleManCommander);
        memory = new Memory();
        submissionControllerAdapter.setEndTestActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                littleManCommander.reset();
            }

        });
    }

    public void doFrame() {
        for (int i = 0; i < submissionControllerAdapter.getCurrentSpeed(); i++) {
            littleManCommander.doCycle();
        }
    }

    public void submitMemory() {
        final boolean isCorrect = notebookVerifier.verifyNotebook(memory);
        showMessage(notebookVerifier.getMessageFromLastTest());
        if (isCorrect) {
            if (notebookVerifier.hasNextProblem()) {
                notebookVerifier.beginNextProblem();
            } else {
                showMessage("You beat the game!");
            }
        }
    }

    private void showMessage(String message) {
        submissionControllerAdapter.printMessage(message);
    }

    public String getCurrentProblemDescription() {
        return notebookVerifier.getCurrentProblemDescription();
    }

    public Memory getMemory() {
        return new Memory(memory);
    }

    public void setMemory(Memory memory) {
        this.memory.loadCopyOfMemory(memory);
        littleManCommander.reset();
        littleManCommander.loadCopyOfMemory(memory);
    }

}
