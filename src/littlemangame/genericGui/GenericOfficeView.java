/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.genericGui;

import littlemangame.computer.InputPanel;
import littlemangame.computer.OutputPanel;
import littlemangame.genericLittleMan.GenericLittleManCommander;

/**
 *
 * @author brian
 * @param <T>
 * @param <U>
 */
public interface GenericOfficeView<T extends InputPanel, U extends OutputPanel> {

    public T getInputPanel();

    public U getOutputPanel();

    public void registerLittleManCommander(GenericLittleManCommander<?> littleManCommander);

}
