/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import littlemangame.computer.Computer;
import littlemangame.computer.Memory;
import littlemangame.littlemancommands.LittleManCommander;
import littlemangame.notebookdeveloper.gui.MemoryEditor;
import littlemangame.notebookdeveloper.gui.NotebookDeveloperGui;
import littlemangame.notebookdevelopmentproblems.HaltProblem;

/**
 *
 * @author brian
 */
public class NotebookDeveloper {

    private final SpeedController speedController;
    private final LittleManCommander littleManCommander;
    private final NotebookDeveloperGui notebookDeveloperGui;
    private final Memory memory;
    private final MemoryEditor memoryEditor;
    private NotebookDevelopmentProblem notebookDevelopmentProblem;
    private boolean isProblemSolved;
    private boolean isExecuting;
    private boolean isEditing;

    public NotebookDeveloper(NotebookDeveloperGui notebookDeveloperGui) {
        this.notebookDeveloperGui = notebookDeveloperGui;
        speedController = new SpeedController(notebookDeveloperGui.getSpeedControllerGui());
        final Computer computer = new Computer(notebookDeveloperGui.getOutputPanel(), notebookDeveloperGui.getInputPanel());
        littleManCommander = new LittleManCommander(computer);
        memoryEditor = new MemoryEditor();
        memoryEditor.setSaveAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                memoryEditor.setVisible(false);
                isEditing = false;
                memory.setMemory(memoryEditor.getMemory());
                syncGui();
            }

        });
        notebookDeveloperGui.getGameCanvas().getRenderer().addDrawable(littleManCommander);
        hookIntoNotebookDeveloperGui();
        stopExecution();
        memory = new Memory();
        notebookDevelopmentProblem = new HaltProblem();
        isProblemSolved = false;
    }

    public void doFrame() {
        speedController.flushBuffer();
        for (int i = 0; i < speedController.getCurrentSpeed(); i++) {
            littleManCommander.doCycle();
        }
    }

    private void syncGui() {
        notebookDeveloperGui.setEnabledAbort(isExecuting);
        notebookDeveloperGui.setEnabledEditMemory(!isExecuting && !isEditing);
        notebookDeveloperGui.setEnabledExecute(!isExecuting && !isEditing);
        notebookDeveloperGui.setEnabledSubmit(!isExecuting && !isEditing);
    }

    private void stopExecution() {
        isExecuting = false;
        speedController.disable();
        syncGui();
    }

    private void execute() {
        isExecuting = true;
        littleManCommander.reset();
        littleManCommander.loadCopyOfMemory(memory);
        speedController.enable();
        syncGui();
    }

    private void openMemoryEditer() {
        memoryEditor.setMemory(memory);
        memoryEditor.setVisible(true);
        isEditing = true;
        syncGui();
    }

    private void hookIntoNotebookDeveloperGui() {
        notebookDeveloperGui.setAbortAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                stopExecution();
            }

        });
        notebookDeveloperGui.setExecuteAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                execute();
            }

        });
        notebookDeveloperGui.setEditMemoryAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                openMemoryEditer();
            }

        });
        notebookDeveloperGui.setSubmitAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                notebookDevelopmentProblem.testNotebook(memory);
                isProblemSolved = notebookDevelopmentProblem.wasLastTestCorrect();
                showMessage(notebookDevelopmentProblem.getMessageFromLastTest());
            }

        });
        notebookDeveloperGui.setUpdateObjectiveStringAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                notebookDeveloperGui.setObjectiveString(notebookDevelopmentProblem.getProblemDescription());
            }

        });
    }

    private void showMessage(String message) {
        notebookDeveloperGui.printMessage(message);
    }

    public void setNotebookDevelopmentProblem(NotebookDevelopmentProblem notebookDevelopmentProblem) {
        this.notebookDevelopmentProblem = notebookDevelopmentProblem;
        isProblemSolved = false;
    }

}
