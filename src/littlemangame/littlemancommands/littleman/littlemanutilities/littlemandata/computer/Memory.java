/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer;

import Renderer.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import littlemangame.instructions.InstructionFromSet;
import littlemangame.word.Word;
import littlemangame.word.WordContainer;

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
    private List<WordContainer> memory = new ArrayList<>(numWords);

    {
        final Word input = Word.valueOfLastDigitsOfInteger(90);
        final Word newValue = Word.valueOfLastDigitsOfInteger(91);
        final Word oldValue = Word.valueOfLastDigitsOfInteger(92);
        final Word temp = Word.valueOfLastDigitsOfInteger(93);
        final Word printOld = Word.valueOfLastDigitsOfInteger(70);
        final Word printNew = Word.valueOfLastDigitsOfInteger(80);
        int i = 0;
        memory.add(new WordContainer(InstructionFromSet.LOAD_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(input));
        i++;
        memory.add(new WordContainer(InstructionFromSet.JUMP_IF_ZERO.getOpcode()));
        i++;
        memory.add(new WordContainer(printOld));
        i++;
        final Word loop = Word.valueOfLastDigitsOfInteger(i);
        //move newValue to temp
        memory.add(new WordContainer(InstructionFromSet.LOAD_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(newValue));
        i++;
        memory.add(new WordContainer(InstructionFromSet.STORE_REGISTER_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(temp));
        i++;
        //add oldValue to newValue
        memory.add(new WordContainer(InstructionFromSet.LOAD_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(oldValue));
        i++;
        memory.add(new WordContainer(InstructionFromSet.ADD_REGISTER_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(newValue));
        //move temp to oldValue
        memory.add(new WordContainer(InstructionFromSet.LOAD_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(temp));
        i++;
        memory.add(new WordContainer(InstructionFromSet.STORE_REGISTER_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(oldValue));
        i++;
        //decrement input
        memory.add(new WordContainer(InstructionFromSet.DECREMENT_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(input));
        i++;
        //decrement input
        memory.add(new WordContainer(InstructionFromSet.LOAD_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(input));
        i++;
        //jump to return if zero
        memory.add(new WordContainer(InstructionFromSet.JUMP_IF_ZERO.getOpcode()));
        i++;
        memory.add(new WordContainer(printNew));
        i++;
        //jump to loop
        memory.add(new WordContainer(InstructionFromSet.UNCONDITIONAL_JUMP.getOpcode()));
        i++;
        memory.add(new WordContainer(loop));
        i++;
        for (; i < numWords; i++) {
            memory.add(new WordContainer(Word.ZERO_WORD));
        }
        memory.set(70, new WordContainer(InstructionFromSet.LOAD_MEMORY.getOpcode()));
        memory.set(71, new WordContainer(oldValue));
        memory.set(72, new WordContainer(InstructionFromSet.PRINT_UNSIGNED.getOpcode()));
        memory.set(73, new WordContainer(InstructionFromSet.HALT.getOpcode()));
        memory.set(80, new WordContainer(InstructionFromSet.LOAD_MEMORY.getOpcode()));
        memory.set(81, new WordContainer(newValue));
        memory.set(82, new WordContainer(InstructionFromSet.PRINT_UNSIGNED.getOpcode()));
        memory.set(83, new WordContainer(InstructionFromSet.HALT.getOpcode()));

        setMemory(input, Word.valueOfLastDigitsOfInteger(8));
        setMemory(newValue, Word.valueOfLastDigitsOfInteger(1));
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
        memory.get(address.getValue()).setWord(wordToBeStored);
    }

    public WordContainer getMemory(Word address) {
        return memory.get(address.getValue());
    }

    /**
     *
     * @param word
     *
     * @return
     */
    public Point getAccessLocation(Word word) {
        final int x = xPosition - 10;
        final int y = yPosition + word.getValue() * height / numWords;
        return new Point(x, y);
    }

}
