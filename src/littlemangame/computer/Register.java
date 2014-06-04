/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.computer;

import Renderer.Drawable;
import java.awt.Graphics;
import java.awt.Point;
import littlemangame.word.Word;
import littlemangame.word.WordContainer;

/**
 *
 * @author brian
 */
public class Register extends WordContainer implements Drawable {

    static protected final int xPosition = 200;
    static protected final int yPosition = 300;
    static protected final int width = 22;
    static protected final int height = 20;

    public Register() {
        super(Word.ZERO_WORD);
    }

    public Point getAccessLocation() {
        return new Point(xPosition + width / 2, yPosition - 10);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(xPosition, yPosition, width, height);
        graphics.drawString(getWord().toString(), xPosition + 3, yPosition + height - 3);
    }

}
