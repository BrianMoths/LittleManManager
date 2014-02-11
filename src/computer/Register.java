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
import littlemangame.word.WordContainer;

/**
 *
 * @author brian
 */
public class Register extends WordContainer implements Drawable, AccessibleLocation {

    static private final int xPosition = 200;
    static private final int yPosition = 300;
    static private final int width = 22;
    static private final int height = 20;

    public Register() {
        super(Word.ZERO_WORD);
    }

    @Override
    public Point getAccessLocation() {
        return new Point(xPosition + width / 2, yPosition - 10);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(xPosition, yPosition, width, height);
        graphics.drawString(getWord().toString(), xPosition + 3, yPosition + height - 3);
    }

}
