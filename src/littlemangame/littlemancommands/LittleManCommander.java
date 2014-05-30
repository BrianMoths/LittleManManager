/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

import littlemangame.computer.Computer;
import littlemangame.genericLittleMan.GenericLittleManCommander;
import littlemangame.littleman.LittleMan;
import littlemangame.notebookdeveloper.gui.OfficeView;

/**
 *
 * @author brian
 */
public class LittleManCommander extends GenericLittleManCommander<LittleMan> {

    public LittleManCommander(LittleMan littleMan) {
        super(littleMan);
    }

    public LittleManCommander(OfficeView officeView) {
        this(new Computer(officeView.getOutputPanel(), officeView.getInputPanel()));
    }

    public LittleManCommander(Computer computer) {
        super(new LittleMan(computer));
    }

}
