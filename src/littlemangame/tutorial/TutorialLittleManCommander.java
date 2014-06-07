/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import littlemangame.genericLittleMan.GenericLittleManCommander;
import littlemangame.tutorial.gui.TutorialOfficeView;

/**
 *
 * @author brian
 */
public class TutorialLittleManCommander extends GenericLittleManCommander<TutorialLittleMan> {

    public TutorialLittleManCommander(TutorialLittleMan littleMan) {
        super(littleMan);
    }

    public TutorialLittleManCommander(TutorialOfficeView officeView) {
        this(new TutorialComputer(officeView.getOutputPanel(), officeView.getInputPanel()));
    }

    public TutorialLittleManCommander(TutorialComputer computer) {
        super(new TutorialLittleMan(computer));
    }

}
