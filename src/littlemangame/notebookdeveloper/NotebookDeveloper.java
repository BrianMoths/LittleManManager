/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper;

import littlemangame.notebookdeveloper.gui.NotebookDeveloperGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import littlemangame.littlemancommands.LittleManCommander;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.Computer;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.Memory;

/**
 *
 * @author brian
 */
public class NotebookDeveloper {

    private final SpeedController speedController;
    private final LittleManCommander littleManCommander;
    private final NotebookDeveloperGui notebookDeveloperGui;
    private final Memory memory;
    private boolean isExecuting;

    public NotebookDeveloper(NotebookDeveloperGui notebookDeveloperGui) {
        this.notebookDeveloperGui = notebookDeveloperGui;
        speedController = new SpeedController(notebookDeveloperGui.getSpeedControllerGui());
        final Computer computer = new Computer(notebookDeveloperGui.getOutputPanel(), notebookDeveloperGui.getInputPanel());
        littleManCommander = new LittleManCommander(computer);
        notebookDeveloperGui.getGameCanvas().getRenderer().addDrawable(littleManCommander);
        hookIntoNotebookDeveloperGui();
        stopExecution();
        memory = new Memory();
    }

    public void doFrame() {
        speedController.flushBuffer();
        for (int i = 0; i < speedController.getCurrentSpeed(); i++) {
            littleManCommander.doCycle();
        }
    }

    private void syncGui() {
        notebookDeveloperGui.setEnabledAbort(isExecuting);
        notebookDeveloperGui.setEnabledEditMemory(!isExecuting);
        notebookDeveloperGui.setEnabledExecute(!isExecuting);
        notebookDeveloperGui.setEnabledSubmit(!isExecuting);
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
    }

}
