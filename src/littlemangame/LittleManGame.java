/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame;

import ListenerInputHandler.AbstractInputHandlerClient;
import RealTimeGame.AbstractRealTimeGame;
import littlemangame.littlemancommands.LittleManCommander;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.Computer;

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

    final LittleManCommander littleManCommander;
    final Computer computer;
    final SpeedController speedController;

    public LittleManGame() {
        super(new AbstractInputHandlerClient(), makeGamePanel());
        computer = new Computer(getGameGui().getOutputPanel(), getGameGui().getInputPanel());
        littleManCommander = new LittleManCommander(computer);
        speedController = new SpeedController(getGameGui().getSpeedControllerGui());
        init();
    }

    private void init() {
        getGameGui().getGameCanvas().getRenderer().addDrawable(littleManCommander);
    }

    @Override
    protected void doLogic() {
        speedController.flushBuffer();
        for (int i = 0; i < speedController.getCurrentSpeed(); i++) {
            if (!littleManCommander.isHalted()) {
                littleManCommander.doCycle();
            }
        }
    }

}
