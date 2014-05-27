/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame;

import ListenerInputHandler.AbstractInputHandlerClient;
import RealTimeGame.AbstractRealTimeGame;
import littlemangame.genericGui.SubmissionControllerAdapter;
import littlemangame.notebookdeveloper.submissioncontrols.SubmissionControlGui;

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

    private final SubmissionControllerAdapter<SubmissionControlGui> submissionControllerAdapter;

    public LittleManGame() {
        super(new AbstractInputHandlerClient(), makeGamePanel());
        submissionControllerAdapter = new SubmissionControllerAdapter<>(getGameGui().getNotebookDeveloperGui());
        init();
    }

    private void init() {
    }

    @Override
    protected void doLogic() {
        submissionControllerAdapter.doFrames();
    }

}
