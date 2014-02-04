/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame;

import ListenerInputHandler.AbstractInputHandlerClient;
import RealTimeGame.AbstractRealTimeGame;
import computer.Computer;
import littlemangame.littleman.LittleMan;

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

    final LittleMan littleMan;
    final Computer computer;

    public LittleManGame() {
        super(new AbstractInputHandlerClient(), makeGamePanel());
        computer = new Computer(getGameGui().getOutputPanel());
        littleMan = new LittleMan(computer);
        init();
    }

    private void init() {
        renderer.addDrawable(littleMan);
        renderer.addDrawable(computer);
    }

    @Override
    protected void doLogic() {
        if (!littleMan.isHalted()) {
            littleMan.doAction(LittleMan.doCycle);
        }
    }

}
