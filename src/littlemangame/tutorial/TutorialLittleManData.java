/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import littlemangame.genericLittleMan.GenericLittleManData;
import littlemangame.littleman.PositionGetterAdapter;

/**
 *
 * @author brian
 */
public class TutorialLittleManData extends GenericLittleManData<TutorialComputer> {

    public TutorialLittleManData(TutorialComputer computer, PositionGetterAdapter positionGetterAdapter) {
        super(computer, positionGetterAdapter);
    }

}
