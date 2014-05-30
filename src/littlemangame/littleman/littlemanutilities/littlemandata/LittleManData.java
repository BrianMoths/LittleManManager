/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littleman.littlemanutilities.littlemandata;

import littlemangame.computer.Computer;
import littlemangame.genericLittleMan.GenericLittleManData;
import littlemangame.littleman.PositionGetterAdapter;

/**
 *
 * @author brian
 */
public class LittleManData extends GenericLittleManData<Computer> {

    public LittleManData(final Computer computer, PositionGetterAdapter positionGetterAdapter) {
        super(computer, positionGetterAdapter);
    }

}
