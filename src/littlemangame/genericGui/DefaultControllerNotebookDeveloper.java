/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.genericGui;

import littlemangame.notebookdeveloper.submissioncontrols.SubmissionControlGui;

/**
 *
 * @author brian
 */
public class DefaultControllerNotebookDeveloper extends NotebookDeveloper<SubmissionControlGui, SubmissionControllerAdapter<SubmissionControlGui>> {

    public DefaultControllerNotebookDeveloper(NotebookDeveloperGui<SubmissionControlGui> notebookDeveloperGui) {
        super(notebookDeveloperGui, new SubmissionControllerAdapter<>(super, notebookDeveloperGui));
    }

}
