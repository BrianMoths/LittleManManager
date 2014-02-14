/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman.littlemanutilities.location;

import java.awt.Point;
import littlemangame.littlemancommands.littleman.PositionGetterAdapter;

/**
 *
 * @author brian
 */
public class LittleManPosition {

    private final int pathY;
    private final int stepSize;
    private final PositionGetterAdapter positionGetterAdapter;
    private int x;
    private int y;

    public LittleManPosition(int pathY, int stepSize, Point initialPoint, PositionGetterAdapter positionGetterAdapter) {
        this.pathY = pathY;
        this.stepSize = stepSize;
        x = initialPoint.x;
        y = initialPoint.y;
        this.positionGetterAdapter = positionGetterAdapter;
    }

//    public boolean goTo(AccessibleLocation accessibleLocation) {
//        return goToPoint(accessibleLocation.getAccessLocation());
//    }
//
//    public <T> boolean goTo(MultiplyAccessibleLocation<T> multiplyAccessibleLocation, T t) {
//        return goToPoint(multiplyAccessibleLocation.getAccessLocation(t));
//    }
    public boolean goTo(ComputerLocation computerLocation) {
        return computerLocation.goTo(this);
    }

    boolean goToPoint(Point point) {
        if (isAtX(point.x)) {
            return stepInDirectionOfY(point.y);
        } else {
            goToX(point.x);
            return false;
        }
    }

    private boolean goToX(int xDestination) {
        if (x != xDestination) {
            if (!isOnPath()) {
                goToPath();
                return false;
            } else {
                return stepInDirectionOfX(xDestination);
            }
        } else {
            return true;
        }
    }

    private boolean goToPath() {
        return stepInDirectionOfY(pathY);
    }

    private boolean stepInDirectionOfX(int xDestination) {
        if (x < xDestination) {
            x += Math.min(stepSize, xDestination - x);
        } else if (x > xDestination) {
            x -= Math.min(x - xDestination, stepSize);
        }
        return x == xDestination;
    }

    private boolean stepInDirectionOfY(int yDestination) {
        if (y < yDestination) {
            y += Math.min(stepSize, yDestination - y);
        } else if (y > yDestination) {
            y -= Math.min(y - yDestination, stepSize);
        }
        return y == yDestination;
    }

    private boolean isOnPath() {
        return isAtY(pathY);
    }

    private boolean isAtX(int x) {
        return this.x == x;
    }

    private boolean isAtY(int y) {
        return this.y == y;
    }

    private boolean isAtPoint(Point point) {
        return isAtX(point.x) && isAtY(point.y);
    }

    private int getPathY() {
        return pathY;
    }

    private int getStepSize() {
        return stepSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    PositionGetterAdapter getPositionGetterAdapter() {
        return positionGetterAdapter;
    }

}
