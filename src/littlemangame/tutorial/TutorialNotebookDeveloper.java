/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import littlemangame.genericLittleMan.GenericNotebookDeveloper;
import littlemangame.tutorial.gui.TutorialOfficeView;

/**
 *
 * @author brian
 */
public class TutorialNotebookDeveloper extends GenericNotebookDeveloper<TutorialLittleManCommander> {

    public TutorialNotebookDeveloper(TutorialOfficeView tutorialOfficeView) {
        super(tutorialOfficeView, new TutorialLittleManCommander(tutorialOfficeView));
    }

}
