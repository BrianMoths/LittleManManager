/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdevelopmentproblems;

import littlemangame.notebookdeveloper.NotebookDevelopmentProblem;
import littlemangame.notebookdeveloper.notebooktester.InstanceNotebookTester;
import littlemangame.notebookdeveloper.notebooktester.NotebookTester;
import littlemangame.notebookdeveloper.notebooktester.NotebookTesterFactory;
import littlemangame.notebookdeveloper.notebooktester.RepeatingNotebookTester;

/**
 *
 * @author brian
 */
public class HaltProblem implements NotebookTesterFactory {

    public static NotebookDevelopmentProblem getNotebookDevelopmentProblem() {
        return new NotebookDevelopmentProblem(new RepeatingNotebookTester(new HaltProblem(), 1));
    }

    @Override
    public NotebookTester produceNotebookTester() {
        InstanceNotebookTester instanceNotebookTester = new InstanceNotebookTester();
        instanceNotebookTester.addHaltEvent();
        return instanceNotebookTester;
    }

}
