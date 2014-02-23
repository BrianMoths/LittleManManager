/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.notebooktester.littleManMock;

import java.awt.Point;
import littlemangame.computer.ComputerInputter;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class ComputerInputterMock implements ComputerInputter {

    private final InputProducerMock inputProducerMock;
    private Word lastWord;
    private boolean isEnabled = false;

    public ComputerInputterMock(InputProducerMock inputProducerMock) {
        this.inputProducerMock = inputProducerMock;
    }

    @Override
    public void disablePanel() {
        isEnabled = false;
    }

    @Override
    public void enablePanel() {
        isEnabled = true;
    }

    @Override
    public Point getAccessLocation() {
        return new Point();
    }

    @Override
    public Word getLastSelectedWord() {
        return inputProducerMock.getInputWord();
    }

    @Override
    public boolean isPanelEnabled() {
        return isEnabled; //may as well get rid of isEnabled and always return false
    }

    @Override
    public boolean isValueSelected() {
        return true;
    }

}
