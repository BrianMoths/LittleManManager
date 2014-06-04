/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import java.awt.Graphics;
import littlemangame.genericLittleMan.GenericComputer;

/**
 *
 * @author brian
 */
public class TutorialComputer extends GenericComputer<TutorialRegister, TutorialMemory, TutorialInstructionPointer, TutorialOutputPanel, TutorialInputPanel> {

    public TutorialComputer(TutorialOutputPanel tutorialOutputPanel, TutorialInputPanel tutorialInputPanel) {
        super(new TutorialRegister(), new TutorialMemory(), new TutorialInstructionPointer(), tutorialOutputPanel, tutorialInputPanel);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics); //To change body of generated methods, choose Tools | Templates.
        inputPanel.draw(graphics);
        outputPanel.draw(graphics);
    }

}
