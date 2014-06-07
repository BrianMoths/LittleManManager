/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial.gui;

import littlemangame.genericGui.GenericNotebookDeveloperGui;

/**
 *
 * @author brian
 */
public class TutorialNotebookDeveloperGui extends GenericNotebookDeveloperGui<SubmissionControllerTutorialGui, TutorialOfficeView> {

    public TutorialNotebookDeveloperGui(SubmissionControllerTutorialGui submissionControllerGui) {
        super(submissionControllerGui, new TutorialOfficeView());
    }

}
