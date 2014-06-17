/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import Renderer.Drawable;
import java.awt.Graphics;
import java.awt.Point;
import littlemangame.computer.OutputPanel;

/**
 *
 * @author brian
 */
public class TutorialOutputPanel extends OutputPanel implements Drawable {

    static private final int arrowLength = 30;
    private boolean isArrowShown = true;
    private final IndicatorArrow indicatorArrow;

    public TutorialOutputPanel() {
        super();
        Point accessPoint = getAccessLocation();
        indicatorArrow = new IndicatorArrow(accessPoint.x + arrowLength, accessPoint.y, accessPoint.x, accessPoint.y);
    }

    @Override
    public void draw(Graphics graphics) {
        if (isArrowShown) {
            indicatorArrow.draw(getGraphics());
        }
    }

    public boolean isArrowShown() {
        return isArrowShown;
    }

    public void setIsArrowShown(boolean isArrowShown) {
        this.isArrowShown = isArrowShown;
    }

}
