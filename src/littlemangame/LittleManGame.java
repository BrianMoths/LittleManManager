/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame;

import littlemangame.notebookdeveloper.NotebookDeveloper;
import ListenerInputHandler.AbstractInputHandlerClient;
import RealTimeGame.AbstractRealTimeGame;

/**
 *
 * @author brian
 */
public class LittleManGame extends AbstractRealTimeGame<LittleManGui> {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LittleManGame littleMan = new LittleManGame();
        littleMan.startGameLoopThread();
    }

    private static LittleManGui makeGamePanel() {
        return new LittleManGui();
    }

    private final NotebookDeveloper notebookDeveloper;

    public LittleManGame() {
        super(new AbstractInputHandlerClient(), makeGamePanel());
        notebookDeveloper = new NotebookDeveloper(getGameGui().getNotebookDeveloperGui());
        init();
    }

    private void init() {
    }

    @Override
    protected void doLogic() {
        notebookDeveloper.doFrame();
    }

}
