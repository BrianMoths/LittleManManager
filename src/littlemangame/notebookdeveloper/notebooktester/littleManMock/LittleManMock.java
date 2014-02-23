/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.notebooktester.littleManMock;

import littlemangame.computer.Computer;
import littlemangame.littleman.LittleMan;
import littlemangame.littleman.littlemanutilities.location.ComputerLocation;

/**
 *
 * @author brian
 */
public class LittleManMock extends LittleMan {

    public LittleManMock(Computer computer) {
        super(computer);
    }

    @Override
    public boolean goToComputerLocation(ComputerLocation computerLocation) {
        return true;
    }

}
