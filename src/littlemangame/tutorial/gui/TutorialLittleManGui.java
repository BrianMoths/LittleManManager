/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial.gui;

import littlemangame.GenericLittleManGui;

/**
 *
 * @author brian
 */
public class TutorialLittleManGui extends GenericLittleManGui<TutorialNotebookDeveloperGui> {

    public TutorialLittleManGui() {
        super(new TutorialNotebookDeveloperGui(new SubmissionControllerTutorialGui()));
    }

}
