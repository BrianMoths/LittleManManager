/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import littlemangame.littlemancommands.GenericLittleManCommander;
import littlemangame.tutorial.gui.TutorialOfficeView;

/**
 *
 * @author brian
 */
public class TutorialLittleManCommander extends GenericLittleManCommander<TutorialLittleMan> {

    public TutorialLittleManCommander(TutorialLittleMan littleMan) {
        super(littleMan);
    }

    public TutorialLittleManCommander(TutorialOfficeView officeView) {
        this(new TutorialComputer(officeView.getOutputPanel(), officeView.getInputPanel()));
    }

    public TutorialLittleManCommander(TutorialComputer computer) {
        super(new TutorialLittleMan(computer));
    }

    public void setIsWorksheetArrowShown(boolean isArrowShown) {
        getLittleMan().setIsWorksheetArrowShown(isArrowShown);
    }

    public void setIsNotebookArrowShown(boolean isArrowShown) {
        getLittleMan().setIsNotebookArrowShown(isArrowShown);
    }

    public void setIsNotebookPageSheetArrowShown(boolean isArrowShown) {
        getLittleMan().setIsNotebookPageSheetArrowShown(isArrowShown);
    }

    public void setIsInputPanelArrowShown(boolean isArrowShown) {
        getLittleMan().setIsInputPanelArrowShown(isArrowShown);
    }

    public void setIsOutputPanelArrowShown(boolean isArrowShown) {
        getLittleMan().setIsOutputPanelArrowShown(isArrowShown);
    }

}
