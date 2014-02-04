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
public class Register implements Drawable, AccessibleLocation {

    static private final int xPosition = 200;
    static private final int yPosition = 300;
    static private final int width = 22;
    static private final int height = 20;
    private int word;

    @Override
    public Point getAccessLocation() {
        return new Point(xPosition + width / 2, yPosition - 10);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(xPosition, yPosition, width, height);
        graphics.drawString(String.format("%02d", word), xPosition + 3, yPosition + height - 3);
    }

    public int getWord() {
        return word;
    }

    public void setWord(int word) {
        this.word = word;
    }

}
