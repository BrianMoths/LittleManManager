/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.speedcontroller;

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
        isRunning = true;
        bufferedIsRunning = true;
        speedControllerAdapter = new SpeedControllerAdapter(this, speedControllerGui);
    }

    public int getCurrentSpeed() {
        if (isRunning) {
            return speeds.get(speed);
        } else {
            return 0;
        }
    }

    public void disable() {
        pause();
        speedControllerAdapter.disableGui();
    }

    public void enable() {
        resume();
        speedControllerAdapter.enableGui();
    }

    public void flushBuffer() {
        isRunning = bufferedIsRunning;
        speed = bufferedSpeed;
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
