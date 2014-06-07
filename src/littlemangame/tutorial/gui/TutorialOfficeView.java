/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial.gui;

import littlemangame.notebookdeveloper.gui.GenericOfficeView;
import littlemangame.tutorial.TutorialInputPanel;
import littlemangame.tutorial.TutorialOutputPanel;

/**
 *
 * @author brian
 */
public class TutorialOfficeView extends GenericOfficeView<TutorialInputPanel, TutorialOutputPanel> {

    public TutorialOfficeView() {
        super(new TutorialInputPanel(), new TutorialOutputPanel());
    }

}
