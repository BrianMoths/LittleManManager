/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.computer;

import Renderer.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import littlemangame.instructions.InstructionFromSet;
import littlemangame.word.Word;
import littlemangame.word.WordContainer;

/**
 * The notebook class essentially holds list of {@link WordContainer}s. Instead
 * of being
 * indexed by ints, a WordContainer is indexed by {@link Word}s. Thus the
 * number of
 * words a notebook can hold is {@link Word#NUM_WORDS}.
 *
 * A notebook is the analog of main memory in a real computer
 *
 * @author brian
 */
public class Notebook implements Drawable {

    static protected final int xPosition = 400;
    static protected final int yPosition = 20;
    static protected final int width = 50;
    static protected final int height = 400;
    static private final int numWords = 100;
    private final List<WordContainer> memory = new ArrayList<>(numWords);

    {
        final Word input = Word.valueOfLastDigitsOfInteger(90);
        final Word newValue = Word.valueOfLastDigitsOfInteger(91);
        final Word oldValue = Word.valueOfLastDigitsOfInteger(92);
        final Word newCopy = Word.valueOfLastDigitsOfInteger(93);
        final Word printOld = Word.valueOfLastDigitsOfInteger(70);
        final Word printNew = Word.valueOfLastDigitsOfInteger(80);
        int i = 0;
        //get input
        memory.add(new WordContainer(InstructionFromSet.INPUT.getOpcode()));
        i++;
        //save input
        memory.add(new WordContainer(InstructionFromSet.STORE_REGISTER_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(input));
        i++;
        //jump if zero
        memory.add(new WordContainer(InstructionFromSet.JUMP_IF_ZERO.getOpcode()));
        i++;
        memory.add(new WordContainer(printOld));
        i++;
        final Word loop = Word.valueOfLastDigitsOfInteger(i);
        //add oldValue to newValue
        memory.add(new WordContainer(InstructionFromSet.LOAD_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(oldValue));
        i++;
        memory.add(new WordContainer(InstructionFromSet.ADD_REGISTER_TO_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(newValue));
        //move newCopy to oldValue
        memory.add(new WordContainer(InstructionFromSet.LOAD_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(newCopy));
        i++;
        memory.add(new WordContainer(InstructionFromSet.STORE_REGISTER_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(oldValue));
        i++;
        //move newValue to newCopy
        memory.add(new WordContainer(InstructionFromSet.LOAD_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(newValue));
        i++;
        memory.add(new WordContainer(InstructionFromSet.STORE_REGISTER_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(newCopy));
        i++;
        //decrement input
        memory.add(new WordContainer(InstructionFromSet.DECREMENT_MEMORY.getOpcode()));
        i++;
        memory.add(new WordContainer(input));
        i++;
        //load input
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

        setMemoryAtPage(input, Word.valueOfLastDigitsOfInteger(8));
        setMemoryAtPage(oldValue, Word.valueOfLastDigitsOfInteger(1));
    }

    /**
     * Constructs a notebook. No guarantees are made about the initial contents
     * of the notebook.
     */
    public Notebook() {

    }

    /**
     * Copy constructor. Creates a notebook whose contents are identical to the
     * given notebook. Subsequent changes in the given notebook will not affect
     * this notebook.
     *
     * @param memory the notebook to be copied
     */
    public Notebook(Notebook memory) {
        loadCopyOfMemory(memory);
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

    /**
     * sets the memory at the page specified by the given word to the given
     * word.
     *
     * @param page a word whose unsigned value gives the page to be written to
     * @param wordToBeStored the word to be stored on the given page
     */
    public void setMemoryAtPage(Word page, Word wordToBeStored) {
        memory.get(page.getUnsignedValue()).setWord(wordToBeStored);
    }

    /**
     * modifies the contents of this notebook to be identical to the given
     * notebook. This is a deep copy: subsequent changes to the given notebook
     * will not affect this notebook.
     *
     * @param notebook the notebook to be copied
     */
    public final void loadCopyOfMemory(Notebook notebook) {
        Iterator<Word> wordIterator = Word.getIterator();
        while (wordIterator.hasNext()) {
            final Word word = wordIterator.next();
            setMemoryAtPage(word, notebook.getMemory(word).getWord());
        }
    }

    /**
     * returns the word stored on the page corresponding to the given word
     *
     * @param page the page whose word is to be retrieved
     *
     * @return the word stored on the given page
     */
    public WordContainer getMemory(Word page) {
        return memory.get(page.getUnsignedValue());
    }

    /**
     * gets the point where the little man has to go to access the given page
     *
     * @param page the pages whose location is to be returned
     *
     * @return the point the little man has to go to in order to access this
     * page.
     */
    public Point getAccessLocation(Word page) {
        final int x = xPosition - 10;
        final int y = yPosition + page.getUnsignedValue() * height / numWords;
        return new Point(x, y);
    }

}
