/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import java.awt.Graphics;
import littlemangame.computer.GenericOffice;

/**
 *
 * @author brian
 */
public class TutorialComputer extends GenericOffice<TutorialRegister, TutorialMemory, TutorialInstructionPointer, TutorialOutputPanel, TutorialInputPanel> {

    public TutorialComputer(TutorialOutputPanel tutorialOutputPanel, TutorialInputPanel tutorialInputPanel) {
        super(new TutorialRegister(), new TutorialMemory(), new TutorialInstructionPointer(), tutorialOutputPanel, tutorialInputPanel);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics); //To change body of generated methods, choose Tools | Templates.
        inputPanel.draw(graphics);
        outputPanel.draw(graphics);
    }

    public void setIsWorksheetArrowShown(boolean isArrowShown) {
        worksheet.setIsArrowShown(isArrowShown);
    }

    public void setIsNotebookArrowShown(boolean isArrowShown) {
        notebook.setIsArrowShown(isArrowShown);
    }

    public void setIsNotebookPageSheetArrowShown(boolean isArrowShown) {
        notebookPageSheet.setIsArrowShown(isArrowShown);
    }

    public void setIsInputPanelArrowShown(boolean isArrowShown) {
        inputPanel.setIsArrowShown(isArrowShown);
    }

    public void setIsOutputPanelArrowShown(boolean isArrowShown) {
        outputPanel.setIsArrowShown(isArrowShown);
    }

}
