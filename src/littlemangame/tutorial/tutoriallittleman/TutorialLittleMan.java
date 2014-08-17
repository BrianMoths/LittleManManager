/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial.tutoriallittleman;

import littlemangame.tutorial.tutorialoffice.TutorialOffice;
import littlemangame.littleman.GenericLittleMan;
import littlemangame.littleman.PositionGetterAdapter;

/**
 *
 * @author brian
 */
public class TutorialLittleMan extends GenericLittleMan<TutorialLittleManData> {

    public TutorialLittleMan(TutorialOffice computer) {
        this(computer, new PositionGetterAdapter());
    }

    private TutorialLittleMan(TutorialOffice computer, PositionGetterAdapter positionGetterAdapter) {
        super(new TutorialLittleManData(computer, positionGetterAdapter), positionGetterAdapter);
    }

    public void setIsWorksheetArrowShown(boolean isArrowShown) {
        getLittleManData().setIsWorksheetArrowShown(isArrowShown);
    }

    public void setIsNotebookArrowShown(boolean isArrowShown) {
        getLittleManData().setIsNotebookArrowShown(isArrowShown);
    }

    public void setIsNotebookPageSheetArrowShown(boolean isArrowShown) {
        getLittleManData().setIsNotebookPageSheetArrowShown(isArrowShown);
    }

    public void setIsInputPanelArrowShown(boolean isArrowShown) {
        getLittleManData().setIsInputPanelArrowShown(isArrowShown);
    }

    public void setIsOutputPanelArrowShown(boolean isArrowShown) {
        getLittleManData().setIsOutputPanelArrowShown(isArrowShown);
    }

}
