/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.genericGui;

import littlemangame.computer.Memory;
import littlemangame.littlemancommands.LittleManCommander;
import littlemangame.notebookdeveloper.NotebookProblemSet;
import littlemangame.notebookdeveloper.gui.OfficeView;

/**
 *
 * @author brian
 */
public class NotebookDeveloper {

    private final LittleManCommander littleManCommander;
    private final Memory memory;
    private final NotebookProblemSet notebookVerifier;

    public NotebookDeveloper(OfficeView officeView) {
        littleManCommander = new LittleManCommander(officeView);
        officeView.registerLittleManCommander(littleManCommander);
        memory = new Memory();
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

    public Memory getMemory() {
        return new Memory(memory);
    }

    public void setMemory(Memory memory) {
        this.memory.loadCopyOfMemory(memory);
        littleManCommander.reset();
        littleManCommander.loadCopyOfMemory(memory);
    }

}
