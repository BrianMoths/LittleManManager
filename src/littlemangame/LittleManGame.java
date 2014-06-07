/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame;

import ListenerInputHandler.AbstractInputHandlerClient;
import RealTimeGame.AbstractRealTimeGame;
import littlemangame.tutorial.gui.SubmissionControllerTutorialAdapter;
import littlemangame.tutorial.gui.TutorialLittleManGui;

/**
 *
 * @author brian
 */
public class LittleManGame extends AbstractRealTimeGame<TutorialLittleManGui> {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LittleManGame littleMan = new LittleManGame();
        littleMan.startGameLoopThread();
    }

    private static TutorialLittleManGui makeGamePanel() {
        return new TutorialLittleManGui();
    }

    private final SubmissionControllerTutorialAdapter submissionControllerAdapter;

    public LittleManGame() {
        super(new AbstractInputHandlerClient(), makeGamePanel());
        submissionControllerAdapter = new SubmissionControllerTutorialAdapter(getGameGui().getNotebookDeveloperGui());
        init();
    }

    private void init() {
    }

    @Override
    protected void doLogic() {
        submissionControllerAdapter.doFrames();
    }

}
