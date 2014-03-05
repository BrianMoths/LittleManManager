/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper;

import littlemangame.computer.Memory;
import littlemangame.notebookdeveloper.notebooktester.NotebookTester;

/**
 *
 * @author brian
 */
public class NotebookDevelopmentProblem {

    private final NotebookTester notebookTester;
    private boolean wasLastTestCorrect;
    private final String problemDescription;

    public NotebookDevelopmentProblem(NotebookTester notebookTester, String problemDescription) {
        this.notebookTester = notebookTester;
        this.problemDescription = problemDescription;
    }

    public void testNotebook(Memory memory) {
        wasLastTestCorrect = notebookTester.isNotebookCorrect(memory);
    }

    public boolean wasLastTestCorrect() {
        return wasLastTestCorrect;
    }

    public String getMessageFromLastTest() {
        return notebookTester.getMessageFromTest();
    }

    public String getProblemDescription() {
        return problemDescription;
    }

}
