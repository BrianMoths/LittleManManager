/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.computer;

import java.awt.Point;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public interface ComputerInputter {

    void disablePanel();

    void enablePanel();

    Point getAccessLocation();

    Word getLastSelectedWord();

    boolean isPanelEnabled();

    boolean isValueSelected();

}
