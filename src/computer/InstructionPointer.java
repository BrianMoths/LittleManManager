/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import Renderer.Drawable;
import java.awt.Graphics;
import java.awt.Point;
import littlemangame.littleman.AccessibleLocation;

/**
 *
 * @author brian
 */
public class InstructionPointer implements Drawable, AccessibleLocation {

    static private final int xPosition = 200;
    static private final int yPosition = 100;
    static private final int width = 22;
    static private final int height = 20;
    static private final int numDigits = 2;
    private int address;

    public InstructionPointer() {
        address = 00;
    }

    public void increment() {
        address++;
    }

    @Override
    public Point getAccessLocation() {
        return new Point(xPosition + width / 2 - 2, yPosition + height + 3);
    }

    public void setInstructionPointer(int address) {
        this.address = address;
    }

    public int getInstructionPointer() {
        return address;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(xPosition, yPosition, width, height);
        graphics.drawString(String.format("%02d", address), xPosition + 3, yPosition + height - 3);
    }

}
