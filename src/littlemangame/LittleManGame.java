/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame;

import ListenerInputHandler.AbstractInputHandlerClient;
import RealTimeGame.AbstractRealTimeGame;
import littlemangame.littlemancommands.littleman.littlemanutilities.computer.Computer;
import littlemangame.littlemancommands.littleman.LittleManCommander;

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

    public LittleManGame() {
        super(new AbstractInputHandlerClient(), makeGamePanel());
        computer = new Computer(getGameGui().getOutputPanel());
        littleManCommander = new LittleManCommander(computer);
        init();
    }

    private void init() {
        renderer.addDrawable(littleManCommander);
        renderer.addDrawable(computer);
    }

    @Override
    protected void doLogic() {
        if (!littleManCommander.isHalted()) {
            littleManCommander.doAction(LittleManCommander.doCycle);
        }
    }

}
