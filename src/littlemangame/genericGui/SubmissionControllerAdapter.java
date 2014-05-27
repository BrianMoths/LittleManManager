/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.genericGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import littlemangame.notebookdeveloper.gui.MemoryEditor;
import littlemangame.notebookdeveloper.gui.ProblemDescriptionWindow;
import littlemangame.notebookdeveloper.speedcontroller.SpeedController;
import littlemangame.notebookdeveloper.submissioncontrols.SubmissionControlGui;

/**
 *
 * @author brian
 * @param <T>
 */
public class SubmissionControllerAdapter<T extends SubmissionControlGui> {

    private final NotebookDeveloper notebookDeveloper;
    protected final T submissionControlGui;
    private final MemoryEditor memoryEditor;
    private final SpeedController speedController;
    private final ProblemDescriptionWindow problemDescriptionWindow;

    public SubmissionControllerAdapter(NotebookDeveloper notebookDeveloper, T submissionControlGui) {
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

    public SubmissionControllerAdapter(NotebookDeveloperGui<? extends T> notebookDeveloperGui) {
        this(new NotebookDeveloper(notebookDeveloperGui.getOfficeView()), notebookDeveloperGui.getSubmissionControlGui());
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
        speedController.setEndTestActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                submissionControlGui.showSubmissionPanel();
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

    public void setEndTestActionListener(ActionListener actionListener) {
        speedController.setEndTestActionListener(actionListener);
    }

    public void doFrames() {
        notebookDeveloper.doFrames(speedController.getCurrentSpeed());
    }

}
