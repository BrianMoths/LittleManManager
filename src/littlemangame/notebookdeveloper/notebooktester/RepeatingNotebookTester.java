/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.notebooktester;

import littlemangame.computer.Memory;

/**
 *
 * @author brian
 */
public class RepeatingNotebookTester implements NotebookTester {

    private final NotebookTesterFactory notebookTesterFactory;
    private final int numTests = 100;

    public RepeatingNotebookTester(NotebookTesterFactory notebookTesterFactory) {
        this.notebookTesterFactory = notebookTesterFactory;
    }

    @Override
    public boolean isNotebookCorrect(Memory memory) {
        boolean isCorrect = true;
        for (int i = 0; i < numTests; i++) {
            isCorrect &= notebookTesterFactory.produceNotebookTester().isNotebookCorrect(memory);
        }
        return isCorrect;

//        LittleManMock littleManMock = new LittleManMock(new Computer(new ComputerOutputterMock(null), new ComputerInputterMock(null)), new HaltListener() {
//
//            @Override
//            public void acceptHalt() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        });
    }

}
