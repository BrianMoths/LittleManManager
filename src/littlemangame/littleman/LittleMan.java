/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littleman;

import littlemangame.computer.Computer;
import littlemangame.genericLittleMan.GenericLittleMan;
import littlemangame.littleman.littlemanutilities.littlemandata.LittleManData;

/**
 *
 * @author brian
 */
public class LittleMan extends GenericLittleMan<LittleManData> {

    public LittleMan(Computer computer) {
        this(computer, new PositionGetterAdapter());
//                LittleManData littleManData = new LittleManData(computer, positionGetterAdapter);

    }

    private LittleMan(Computer computer, PositionGetterAdapter positionGetterAdapter) {
        super(new LittleManData(computer, positionGetterAdapter), positionGetterAdapter);
    }

}
