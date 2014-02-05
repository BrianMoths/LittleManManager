/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import Renderer.Drawable;
import java.awt.Graphics;
import java.awt.Point;
import littlemangame.littleman.location.AccessibleLocation;
import littlemangame.word.Word;

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
    private Word address;

    public InstructionPointer() {
        address = Word.ZERO_WORD;
    }

    public void increment() {
        address = address.incrementedWord();
    }

    @Override
    public Point getAccessLocation() {
        return new Point(xPosition + width / 2 - 2, yPosition + height + 3);
    }

    public void setInstructionPointer(Word address) {
        this.address = address;
    }

    public Word getInstructionPointer() {
        return address;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(xPosition, yPosition, width, height);
        graphics.drawString(address.toString(), xPosition + 3, yPosition + height - 3);
    }

}
