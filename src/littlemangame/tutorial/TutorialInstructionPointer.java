/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import java.awt.Graphics;
import littlemangame.computer.InstructionPointer;

/**
 *
 * @author brian
 */
public class TutorialInstructionPointer extends InstructionPointer {

    static private final int arrowLength = 30;
    private boolean isArrowShown = true;

    @Override

    public void draw(Graphics graphics) {
        super.draw(graphics); //To change body of generated methods, choose Tools | Templates.
        if (isArrowShown) {
            drawArrow(graphics);
        }
    }

    private void drawArrow(Graphics graphics) {
        graphics.drawLine(xPosition + width / 2 - 2, yPosition - arrowLength - 3, xPosition + width / 2 - 2, yPosition - 3);
        graphics.drawLine(xPosition + width / 2 - 2 - arrowLength / 3, yPosition - arrowLength / 3 - 3, xPosition + width / 2 - 2, yPosition - 3);
        graphics.drawLine(xPosition + width / 2 - 2 + arrowLength / 3, yPosition - arrowLength / 3 - 3, xPosition + width / 2 - 2, yPosition - 3);
    }

    public boolean isArrowShown() {
        return isArrowShown;
    }

    public void setIsArrowShown(boolean isArrowShown) {
        this.isArrowShown = isArrowShown;
    }

}
