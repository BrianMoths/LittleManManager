/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial.gui;

import littlemangame.genericGui.GenericSubmissionControllerAdapter;
import littlemangame.genericLittleMan.GenericNotebookDeveloper;
import littlemangame.tutorial.TutorialLittleManCommander;
import littlemangame.tutorial.TutorialNotebookDeveloper;

/**
 *
 * @author brian
 */
public class SubmissionControllerTutorialAdapter extends GenericSubmissionControllerAdapter<SubmissionControllerTutorialGui, GenericNotebookDeveloper<TutorialLittleManCommander>> {

    public SubmissionControllerTutorialAdapter(TutorialNotebookDeveloper notebookDeveloper, SubmissionControllerTutorialGui submissionControlGui) {
        super(notebookDeveloper, submissionControlGui);
    }

    public SubmissionControllerTutorialAdapter(TutorialNotebookDeveloperGui notebookDeveloperGui) {
        super(new TutorialNotebookDeveloper(notebookDeveloperGui.getOfficeView()), notebookDeveloperGui.getSubmissionControlGui());
    }

    public void printDialogue(String dialogue) {
        submissionControlGui.printDialogue(dialogue);
    }

}
