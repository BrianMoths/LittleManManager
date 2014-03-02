/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.notebooktester;

import littlemangame.computer.Memory;

/**
 *
 * @author brian
 */
public class RepeatingNotebookTester implements NotebookTester {

    private final NotebookTesterFactory notebookTesterFactory;
    private final int numTests;
    private String messageFromTest;

    public RepeatingNotebookTester(NotebookTesterFactory notebookTesterFactory, int numTests) {
        this.notebookTesterFactory = notebookTesterFactory;
        this.numTests = numTests;
    }

    @Override
    public boolean isNotebookCorrect(Memory memory) {
        boolean isCorrect = true;
        for (int i = 0; i < numTests; i++) {
            final NotebookTester notebookTester = notebookTesterFactory.produceNotebookTester();
            isCorrect &= notebookTester.isNotebookCorrect(memory);
            messageFromTest = notebookTester.getMessageFromTest();
        }
        return isCorrect;

    }

    @Override
    public String getMessageFromTest() {
        return messageFromTest;
    }

}
