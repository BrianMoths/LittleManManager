/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.genericGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import littlemangame.notebookdeveloper.GenericNotebookDeveloper;
import littlemangame.notebookdeveloper.gui.MemoryEditor;
import littlemangame.notebookdeveloper.speedcontroller.SpeedController;
import littlemangame.notebookdeveloper.submissioncontrols.SubmissionControlGui;

/**
 *
 * @author brian
 * @param <T>
 * @param <U>
 */
public class GenericSubmissionControllerAdapter<T extends SubmissionControlGui, U extends GenericNotebookDeveloper<?>> {

    private final U notebookDeveloper;
    protected final T submissionControlGui;
    private final MemoryEditor memoryEditor;
    private final SpeedController speedController;

    public GenericSubmissionControllerAdapter(U notebookDeveloper, T submissionControlGui) {
        this.notebookDeveloper = notebookDeveloper;
        this.submissionControlGui = submissionControlGui;
        speedController = new SpeedController(submissionControlGui.getSpeedControllerGui());
        memoryEditor = new MemoryEditor();
        memoryEditor.setVisible(false);
        hookIntoGui();
        hookIntoMemoryEditor();
    }

    private void hookIntoGui() {
        submissionControlGui.setEditMemoryAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                memoryEditor.setProblemDescription(notebookDeveloper.getCurrentProblemDescription());
                openMemoryEditor();
                respondToEditEvent(true);
            }

        });
        submissionControlGui.setSubmitAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final String resultString = notebookDeveloper.submitMemory();
                submissionControlGui.printMessage(resultString);
            }

        });
        submissionControlGui.setTestAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                submissionControlGui.showSpeedPanel();
                speedController.enable();
            }

        });
        speedController.setEndTestActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                submissionControlGui.showSubmissionPanel();
                notebookDeveloper.endTest();
            }

        });
    }

    private void hookIntoMemoryEditor() {
        memoryEditor.setSaveAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notebookDeveloper.setMemory(memoryEditor.getNotebook());
                respondToEditEvent(false);
            }

        });
    }

    private void respondToEditEvent(boolean isEditing) {
        submissionControlGui.setEditMemoryEnabled(!isEditing);
        submissionControlGui.setSubmitButtonEnabled(!isEditing);
        submissionControlGui.setTestButtonEnabled(!isEditing);
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

    protected final U getNotebookDeveloper() {
        return notebookDeveloper;
    }

}
