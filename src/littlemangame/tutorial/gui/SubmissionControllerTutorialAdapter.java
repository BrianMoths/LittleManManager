/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial.gui;

import littlemangame.notebookdeveloper.NotebookDeveloper;
import littlemangame.genericGui.SubmissionControllerAdapter;

/**
 *
 * @author brian
 */
public class SubmissionControllerTutorialAdapter extends SubmissionControllerAdapter<SubmissionControllerTutorialGui> {

    public SubmissionControllerTutorialAdapter(NotebookDeveloper notebookDeveloper, SubmissionControllerTutorialGui submissionControlGui) {
        super(notebookDeveloper, submissionControlGui);
    }

    public void printDialogue(String dialogue) {
        submissionControlGui.printDialogue(dialogue);
    }

}
