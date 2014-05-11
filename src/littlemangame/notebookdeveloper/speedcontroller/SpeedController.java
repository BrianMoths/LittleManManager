/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.speedcontroller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brian
 */
public class SpeedController {

    static private final List<Integer> speeds = makeSpeeds();
    static final int maxSpeed = speeds.size() - 1;
    static final int minSpeed = 0;

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
    final private SpeedControllerAdapter speedControllerAdapter;

    public SpeedController(SpeedControllerGui speedControllerGui) {
        speed = 1;
        bufferedSpeed = 1;
        isRunning = false;
        bufferedIsRunning = false;
        speedControllerAdapter = new SpeedControllerAdapter(this, speedControllerGui);
        disable();
    }

    public int getCurrentSpeed() {
        flushBuffer();
        if (isRunning) {
            return speeds.get(speed);
        } else {
            return 0;
        }
    }

    public final void disable() {
        pause();
    }

    public void enable() {
        resume();
        speedControllerAdapter.enableGui();
    }

    public void flushBuffer() {
        isRunning = bufferedIsRunning;
        speed = bufferedSpeed;
    }

    public void setEndTestActionListener(ActionListener actionListener) {
        speedControllerAdapter.setEndTestActionListener(actionListener);
    }

    void pause() {
        bufferedIsRunning = false;
    }

    void resume() {
        bufferedIsRunning = true;
    }

    void increaseSpeed() {
        if (bufferedSpeed < maxSpeed) {
            bufferedSpeed++;
        }
    }

    void decreaseSpeed() {
        if (bufferedSpeed > minSpeed) {
            bufferedSpeed--;
        }
    }

    int getBufferedSpeed() {
        return speeds.get(bufferedSpeed);
    }

    boolean getBufferedIsRunning() {
        return bufferedIsRunning;
    }

    int getBufferedSpeedIndex() {
        return bufferedSpeed;
    }

}
