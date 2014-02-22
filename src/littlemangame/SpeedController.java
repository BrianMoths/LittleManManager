/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brian
 */
public class SpeedController {

    static private final List<Integer> speeds = makeSpeeds();
    static private final int maxSpeed = speeds.size() - 1;
    static private final int minSpeed = 0;

    static private List<Integer> makeSpeeds() {
        List<Integer> mySpeeds = new ArrayList<>(10);
        mySpeeds.add(0);
        mySpeeds.add(1);
        mySpeeds.add(2);
        mySpeeds.add(5);
        mySpeeds.add(10);
        mySpeeds.add(100);
        mySpeeds.add(1000);
        mySpeeds.add(10000);
        mySpeeds.add(100000);
        mySpeeds.add(1000000);
        return mySpeeds;
    }

    private int speed;
    private int bufferedSpeed;
    private boolean isRunning;
    private boolean bufferedIsRunning;
    private final SpeedControllerGui speedControllerGui;

    public SpeedController(SpeedControllerGui speedControllerGui) {
        speed = 1;
        bufferedSpeed = 1;
        isRunning = true;
        bufferedIsRunning = true;
        this.speedControllerGui = speedControllerGui;
        hookIntoGui();
    }

    public int getCurrentSpeed() {
        if (isRunning) {
            return speeds.get(speed);
        } else {
            return 0;
        }
    }

    private void pause() {
        bufferedIsRunning = false;
    }

    private void resume() {
        bufferedIsRunning = true;
    }

    private void increaseSpeed() {
        if (bufferedSpeed < maxSpeed) {
            bufferedSpeed++;
        }
    }

    private void decreaseSpeed() {
        if (bufferedSpeed > minSpeed) {
            bufferedSpeed--;
        }
    }

    private void setBufferedSpeed(int bufferedSpeed) {
        this.bufferedSpeed = bufferedSpeed;
    }

    private void setBufferedIsRunning(boolean bufferedIsRunning) {
        this.bufferedIsRunning = bufferedIsRunning;
    }

    public void flushBuffer() {
        isRunning = bufferedIsRunning;
        speed = bufferedSpeed;
        speedControllerGui.setText(getSpeedString());
    }

    private String getSpeedString() {
        return Integer.toString(speeds.get(speed)) + "x";
    }

    private void hookIntoGui() {
        speedControllerGui.setFasterAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                increaseSpeed();
            }

        });
        speedControllerGui.setPauseAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                pause();
            }

        });
        speedControllerGui.setResumeAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                resume();
            }

        });
        speedControllerGui.setSlowerAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                decreaseSpeed();
            }

        });
    }

}
