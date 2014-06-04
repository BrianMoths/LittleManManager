/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import littlemangame.genericLittleMan.GenericLittleMan;
import littlemangame.littleman.PositionGetterAdapter;

/**
 *
 * @author brian
 */
public class TutorialLittleMan extends GenericLittleMan<TutorialLittleManData> {

    public TutorialLittleMan(TutorialComputer computer) {
        this(computer, new PositionGetterAdapter());
    }

    private TutorialLittleMan(TutorialComputer computer, PositionGetterAdapter positionGetterAdapter) {
        super(new TutorialLittleManData(computer, positionGetterAdapter), positionGetterAdapter);
    }

}
