/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame;

import ListenerInputHandler.AbstractInputHandlerClient;
import RealTimeGame.AbstractRealTimeGame;
import littlemangame.notebookdeveloper.speedcontroller.SpeedController;
import littlemangame.tutorial.tutorialnotebookdeveloper.TutorialNotebookDeveloper;
import littlemangame.tutorial.TutorialDriver;
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

    private final TutorialDriver tutorialDriver;
    private final TutorialNotebookDeveloper tutorialNotebookDeveloper;
    private final SpeedController speedController;

    public LittleManGame() {
        super(new AbstractInputHandlerClient(), makeGamePanel());
        speedController = new SpeedController();
        tutorialNotebookDeveloper = new TutorialNotebookDeveloper(getGameGui().getNotebookDeveloperGui().getOfficeView());
        tutorialDriver = new TutorialDriver(tutorialNotebookDeveloper, getGameGui());
//        submissionControllerAdapter = new SubmissionControllerTutorialAdapter(getGameGui().getNotebookDeveloperGui());
        init();
    }

    private void init() {
        getGameGui().setNotebookDeveloper(tutorialNotebookDeveloper);
        getGameGui().registerSpeedController(speedController);
//        submissionControllerAdapter.setEditMemoryActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                getGameGui().setProblemDescription(submissionControllerAdapter.getProblemDescription());
//            }
//
//        });
//        getGameGui().setMemorySaveAction(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                submissionControllerAdapter.setNotebook(getGameGui().getNotebookEditorNotebook());
//            }
//
//        });
    }

    @Override
    protected void doLogic() {
        tutorialDriver.doFrames(speedController.getCurrentSpeed());
    }

}
