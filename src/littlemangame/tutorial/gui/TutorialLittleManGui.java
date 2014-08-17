/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial.gui;

import java.awt.event.ActionListener;
import littlemangame.GenericLittleManGui;
import littlemangame.notebookdeveloper.gui.NotebookEditorPanel;
import littlemangame.tutorial.tutorialnotebookdeveloper.TutorialNotebookDeveloper;

/**
 *
 * @author brian
 */
public class TutorialLittleManGui extends GenericLittleManGui<TutorialNotebookDeveloperGui, NotebookEditorPanel, TutorialNotebookDeveloper> {

    public TutorialLittleManGui() {
        super(new TutorialNotebookDeveloperGui(new SubmissionControllerTutorialGui()), new NotebookEditorPanel());
    }

    public void printDialogue(String dialogue) {
        genericNotebookDeveloperGui.printDialogue(dialogue);
    }

    public void showDialoguePanel() {
        genericNotebookDeveloperGui.showDialoguePanel();
    }

    public void showSubmissionPanel() {
        genericNotebookDeveloperGui.showSubmissionPanel();
    }

    public void addDialogueActionListener(ActionListener actionListener) {
        genericNotebookDeveloperGui.addDialogueActionListener(actionListener);
    }

    public void removeDialogueActionListener(ActionListener actionListener) {
        genericNotebookDeveloperGui.removeDialogueActionListener(actionListener);
    }

}
