/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import Renderer.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import littlemangame.littleman.location.MultiplyAccessibleLocation;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class Memory implements Drawable, MultiplyAccessibleLocation<Word> {

    static private final int xPosition = 600;
    static private final int yPosition = 20;
    static private final int width = 50;
    static private final int height = 400;
    private int numWords = 100;
    private List<Word> memory = new ArrayList<>(numWords);

    {
        int i = 0;
        memory.add(Word.valueOfLastDigitsOfInteger(30));
        i++;
        memory.add(Word.valueOfLastDigitsOfInteger(42));
        i++;
        memory.add(Word.valueOfLastDigitsOfInteger(20));
        i++;
        memory.add(Word.valueOfLastDigitsOfInteger(9));
        i++;
        for (; i < numWords; i++) {
            memory.add(Word.ZERO_WORD);
        }
    }

    @Override
    public void draw(Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.blue);
        graphics.drawRect(xPosition, yPosition, width, height);
        final int step = height / numWords;
        final int maxYPos = yPosition + height;
        for (int yPos = 20; yPos <= maxYPos; yPos += step) {
            graphics.drawLine(xPosition, yPos, xPosition + width, yPos);
        }
        graphics.setColor(color);
    }

    public void setMemory(Word address, Word wordToBeStored) {
        memory.set(address.getValue(), wordToBeStored);
    }

    public Word getMemory(Word address) {
        return memory.get(address.getValue());
    }

    /**
     *
     * @param word
     * @return
     */
    @Override
    public Point getAccessLocation(Word word) {
        final int x = xPosition - 10;
        final int y = yPosition + word.getValue() * height / numWords;
        return new Point(x, y);
    }

}
