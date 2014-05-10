/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.submissioncontrols;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import littlemangame.notebookdeveloper.NotebookDeveloper;
import littlemangame.notebookdeveloper.gui.MemoryEditor;
import littlemangame.notebookdeveloper.gui.ProblemDescriptionWindow;
import littlemangame.notebookdeveloper.speedcontroller.SpeedController;

/**
 *
 * @author brian
 */
public class SubmissionControllerAdapter {

    private final NotebookDeveloper notebookDeveloper;
    private final SubmissionControlGui submissionControlGui;
    private final MemoryEditor memoryEditor;
    private final SpeedController speedController;
    private final ProblemDescriptionWindow problemDescriptionWindow;

    public SubmissionControllerAdapter(NotebookDeveloper notebookDeveloper, SubmissionControlGui submissionControlGui) {
        this.notebookDeveloper = notebookDeveloper;
        this.submissionControlGui = submissionControlGui;
        speedController = new SpeedController(submissionControlGui.getSpeedControllerGui());
        memoryEditor = new MemoryEditor();
        memoryEditor.setVisible(false);
        problemDescriptionWindow = new ProblemDescriptionWindow();
        hookIntoGui();
        hookIntoMemoryEditor();
        hookIntoProblemDescriptionWindow();
    }

    private void hookIntoGui() {
        submissionControlGui.setEditAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openMemoryEditor();
                respondToEditEvent(true);
            }

        });
        submissionControlGui.setSubmitAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notebookDeveloper.submitMemory();
            }

        });
        submissionControlGui.setTestAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                submissionControlGui.showSpeedPanel();
                speedController.enable();
            }

        });
        submissionControlGui.setObjectiveAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                problemDescriptionWindow.setProblemDescription(notebookDeveloper.getCurrentProblemDescription());
                problemDescriptionWindow.showProblemDescription();
                respondToObjectiveEvent(true);
            }

        });
    }

    private void hookIntoMemoryEditor() {
        memoryEditor.setSaveAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notebookDeveloper.setMemory(memoryEditor.getMemory());
                respondToEditEvent(false);
            }

        });
    }

    private void hookIntoProblemDescriptionWindow() {
        problemDescriptionWindow.addOkButtonListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                problemDescriptionWindow.setVisible(false);
                respondToObjectiveEvent(false);
            }

        });
    }

    private void respondToEditEvent(boolean isEditing) {
        submissionControlGui.setEditMemoryEnabled(!isEditing);
        submissionControlGui.setSubmitButtonEnabled(!isEditing);
        submissionControlGui.setTestButtonEnabled(!isEditing);
    }

    private void respondToObjectiveEvent(boolean isObjectiveShowing) {
        submissionControlGui.setObjectiveButtonEnabled(!isObjectiveShowing);
    }

    private void openMemoryEditor() {
        memoryEditor.setMemory(notebookDeveloper.getMemory());
        memoryEditor.setVisible(true);
    }

    public void printMessage(String message) {
        submissionControlGui.printMessage(message);
    }

    public int getCurrentSpeed() {
        return speedController.getCurrentSpeed();
    }

}
