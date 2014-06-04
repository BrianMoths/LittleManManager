/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import Renderer.Drawable;
import java.awt.Graphics;

/**
 *
 * @author brian
 */
public class IndicatorArrow implements Drawable {

    static private final int arrowheadSmallnesFactor = 3;
    private final int tailX;
    private final int tailY;
    private final int headX;
    private final int headY;

    public IndicatorArrow(int tailX, int tailY, int headX, int headY) {
        this.tailX = tailX;
        this.tailY = tailY;
        this.headX = headX;
        this.headY = headY;
    }

    @Override
    public void draw(Graphics graphics) {
        drawShaft(graphics);
        drawFirstArrowhead(graphics);
        drawSecondArrowhead(graphics);
    }

    private void drawShaft(Graphics graphics) {
        graphics.drawLine(tailX, tailY, headX, headY);
    }

    private void drawFirstArrowhead(Graphics graphics) {
        final int displacementX = (tailX - headX) / arrowheadSmallnesFactor;
        final int displacementY = (tailY - headY) / arrowheadSmallnesFactor;

        final int arrowHeadX = headX + displacementX - displacementY;
        final int arrowHeadY = headY + displacementY + displacementX;

        graphics.drawLine(arrowHeadX, arrowHeadY, headX, headY);
    }

    ;

    private void drawSecondArrowhead(Graphics graphics) {
        final int displacementX = (tailX - headX) / arrowheadSmallnesFactor;
        final int displacementY = (tailY - headY) / arrowheadSmallnesFactor;

        final int arrowHeadX = headX + displacementX + displacementY;
        final int arrowHeadY = headY + displacementY - displacementX;

        graphics.drawLine(arrowHeadX, arrowHeadY, headX, headY);
    }

}
