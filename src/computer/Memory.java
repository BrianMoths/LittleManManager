/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import Renderer.Drawable;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brian
 */
public class Memory implements Drawable {

    static private final int xPosition = 600;
    static private final int yPosition = 20;
    static private final int width = 50;
    static private final int height = 400;
    private int numWords = 100;
    private List<Integer> memory = new ArrayList<>(numWords);

    {
        int i = 0;
        memory.add(20);
        i++;
        for (; i < numWords; i++) {
            memory.add(0);
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(xPosition, yPosition, width, height);
        final int step = height / numWords;
        final int maxYPos = yPosition + height;
        for (int yPos = 20; yPos <= maxYPos; yPos += step) {
            graphics.drawLine(xPosition, yPos, xPosition + width, yPos);
        }
    }

    public void setMemory(int address, int wordToBeStored) {
        memory.set(address, wordToBeStored);
    }

    public int getMemory(int address) {
        return memory.get(address);
    }

    public Point getMemoryLocation(int word) {
        final int x = xPosition - 10;
        final int y = yPosition + word * height / numWords;
        return new Point(x, y);
    }

}
