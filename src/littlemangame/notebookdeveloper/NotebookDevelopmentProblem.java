/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper;

import littlemangame.computer.Memory;
import littlemangame.notebookdeveloper.notebooktester.RepeatingNotebookTester;

/**
 *
 * @author brian
 */
public class NotebookDevelopmentProblem {

    private final RepeatingNotebookTester notebookTester;
    private boolean wasLastTestCorrect;
    private String messageFromLastTest;

    public NotebookDevelopmentProblem(RepeatingNotebookTester notebookTester) {
        this.notebookTester = notebookTester;
    }

    public void testNotebook(Memory memory) {
        wasLastTestCorrect = notebookTester.isNotebookCorrect(memory);
        messageFromLastTest = notebookTester.getMessageFromTest(); //improve these later.
    }

    public boolean wasLastTestCorrect() {
        return wasLastTestCorrect;
    }

    public String getMessageFromLastTest() {
        return messageFromLastTest;
    }

}
