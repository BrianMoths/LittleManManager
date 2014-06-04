/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.submissioncontrols;

import littlemangame.genericGui.GenericSubmissionControllerAdapter;
import littlemangame.genericGui.NotebookDeveloperGui;
import littlemangame.notebookdeveloper.NotebookDeveloper;

/**
 *
 * @author brian
 */
public class SubmissionControllerAdapter extends GenericSubmissionControllerAdapter<SubmissionControlGui, NotebookDeveloper> {

    public SubmissionControllerAdapter(NotebookDeveloper notebookDeveloper, SubmissionControlGui submissionControlGui) {
        super(notebookDeveloper, submissionControlGui);
    }

    public SubmissionControllerAdapter(NotebookDeveloperGui<? extends SubmissionControlGui> notebookDeveloperGui) {
        super(new NotebookDeveloper(notebookDeveloperGui.getOfficeView()), notebookDeveloperGui.getSubmissionControlGui());
    }

}
