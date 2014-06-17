/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.computer;

import littlemangame.genericLittleMan.GenericComputer;

/**
 *
 * @author brian
 */
public class Computer extends GenericComputer<Worksheet, Notebook, NotebookPageSheet, ComputerOutputter, ComputerInputter> {

    public Computer(ComputerOutputter outputPanel, ComputerInputter inputPanel) {
        super(new Worksheet(), new Notebook(), new NotebookPageSheet(), outputPanel, inputPanel);
    }

}
