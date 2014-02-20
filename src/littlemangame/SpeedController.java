/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame;

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

    public SpeedController() {
        speed = 1;
        bufferedSpeed = 1;
        isRunning = true;
        bufferedIsRunning = true;
    }

    public int getCurrentSpeed() {
        if (isRunning) {
            return speeds.get(speed);
        } else {
            return 0;
        }
    }

    public void pause() {
        bufferedIsRunning = false;
    }

    public void resume() {
        bufferedIsRunning = true;
    }

    public void increaseSpeed() {
        if (bufferedSpeed < maxSpeed) {
            bufferedSpeed++;
        }
    }

    public void decreaseSpeed() {
        if (bufferedSpeed > minSpeed) {
            bufferedSpeed--;
        }
    }

    public void setBufferedSpeed(int bufferedSpeed) {
        this.bufferedSpeed = bufferedSpeed;
    }

    public void setBufferedIsRunning(boolean bufferedIsRunning) {
        this.bufferedIsRunning = bufferedIsRunning;
    }

    public void flushBuffer() {
        isRunning = bufferedIsRunning;
        speed = bufferedSpeed;
    }

    String getSpeedString() {
        return Integer.toString(speeds.get(speed)) + "x";
    }

}
