/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial.gui;

import java.awt.event.ActionListener;
import littlemangame.notebookdeveloper.gui.GenericNotebookDeveloperGui;
import littlemangame.tutorial.tutorialnotebookdeveloper.TutorialNotebookDeveloper;

/**
 *
 * @author brian
 */
public class TutorialNotebookDeveloperGui extends GenericNotebookDeveloperGui<SubmissionControllerTutorialGui, TutorialOfficeView, TutorialNotebookDeveloper> {

    public TutorialNotebookDeveloperGui(SubmissionControllerTutorialGui submissionControllerGui) {
        super(submissionControllerGui, new TutorialOfficeView());
    }

    public void printDialogue(String dialogue) {
        submissionControllerGui.printDialogue(dialogue);
    }

    public void showDialoguePanel() {
        submissionControllerGui.showDialoguePanel();
    }

    public void showSubmissionPanel() {
        submissionControllerGui.showSubmissionPanel();
    }

    public void addDialogueActionListener(ActionListener actionListener) {
        submissionControllerGui.addDialogueActionListener(actionListener);
    }

    public void removeDialogueActionListener(ActionListener actionListener) {
        submissionControllerGui.removeDialogueActionListener(actionListener);
    }

}
