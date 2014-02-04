/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littleman;

import java.awt.Point;

/**
 *
 * @author brian
 */
class LittleManPosition {

    final int pathY;
    final int stepSize;
    int x;
    int y;

    LittleManPosition(int pathY, int stepSize, Point initialPoint) {
        this.pathY = pathY;
        this.stepSize = stepSize;
        x = initialPoint.x;
        y = initialPoint.y;
    }

    public boolean goTo(AccessibleLocation accessibleLocation) {
        return goToPoint(accessibleLocation.getAccessLocation());
    }

    public <T> boolean goTo(MultiplyAccessibleLocation<T> multiplyAccessibleLocation, T t) {
        return goToPoint(multiplyAccessibleLocation.getAccessLocation(t));
    }

    private boolean goToPoint(Point point) {
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

    int getPathY() {
        return pathY;
    }

    int getStepSize() {
        return stepSize;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

}
