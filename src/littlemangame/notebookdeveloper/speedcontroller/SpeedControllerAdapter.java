/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.speedcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author brian
 */
class SpeedControllerAdapter {

    private final SpeedController speedController;
    private final SpeedControllerGui speedControllerGui;

    SpeedControllerAdapter(SpeedController speedController, SpeedControllerGui speedControllerGui) {
        this.speedController = speedController;
        this.speedControllerGui = speedControllerGui;
        hookIntoGui();
        syncPauseResumeButtons();
    }

    private void hookIntoGui() {
        speedControllerGui.setSlowerAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                speedController.decreaseSpeed();
                syncSpeedButtons();
            }

        });
        speedControllerGui.setFasterAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                speedController.increaseSpeed();
                syncSpeedButtons();
            }

        });
        speedControllerGui.setPauseAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                speedController.pause();
                syncPauseResumeButtons();
            }

        });
        speedControllerGui.setResumeAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                speedController.resume();
                syncPauseResumeButtons();
            }

        });
        speedControllerGui.setEndTestAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                speedController.disable();
            }

        });
    }

    void enableGui() {
        syncPauseResumeButtons();
        syncSpeedButtons();
    }

    private void syncSpeedButtons() {
        speedControllerGui.setEnabledFasterButton(speedController.getBufferedSpeedIndex() < SpeedController.maxSpeed);
        speedControllerGui.setEnabledSlowerButton(speedController.getBufferedSpeedIndex() > SpeedController.minSpeed);
        speedControllerGui.setText(getSpeedString());
    }

    private void syncPauseResumeButtons() {
        final boolean bufferedIsRunning = speedController.getBufferedIsRunning();
        speedControllerGui.setEnabledPauseButton(bufferedIsRunning);
        speedControllerGui.setEnabledResumeButton(!bufferedIsRunning);
    }

    private String getSpeedString() {
        return Integer.toString(speedController.getBufferedSpeed()) + "x";
    }

    public void setEndTestActionListener(ActionListener actionListener) {
        speedControllerGui.setEndTestAction(actionListener);
    }

}
