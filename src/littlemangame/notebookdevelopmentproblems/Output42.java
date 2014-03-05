/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdevelopmentproblems;

import littlemangame.notebookdeveloper.NotebookDevelopmentProblem;
import littlemangame.notebookdeveloper.notebooktester.InstanceNotebookTester;
import littlemangame.notebookdeveloper.notebooktester.NotebookTester;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class Output42 extends NotebookDevelopmentProblem {

    private static final String problemDescription = "The little man must output the word \"42\". Then the little man must halt.";

    public static NotebookTester produceNotebookTester() {
        InstanceNotebookTester instanceNotebookTester = new InstanceNotebookTester();
        instanceNotebookTester.addOutputEvent(Word.valueOfLastDigitsOfInteger(42));
        instanceNotebookTester.addHaltEvent();
        return instanceNotebookTester;
    }

    public Output42() {
        super(produceNotebookTester(), problemDescription);
    }

}
