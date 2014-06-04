/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper;

import littlemangame.genericLittleMan.GenericNotebookDeveloper;
import littlemangame.littlemancommands.LittleManCommander;
import littlemangame.notebookdeveloper.gui.OfficeView;

/**
 *
 * @author brian
 */
public class NotebookDeveloper extends GenericNotebookDeveloper<LittleManCommander> {

    public NotebookDeveloper(OfficeView officeView) {
        super(officeView, new LittleManCommander(officeView));
    }

}
