/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littleman;

import computer.Computer;
import GameGui.GameGui;
import ListenerInputHandler.AbstractInputHandlerClient;
import RealTimeGame.AbstractRealTimeGame;

/**
 *
 * @author brian
 */
public class LittleManGame extends AbstractRealTimeGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LittleManGame littleMan = new LittleManGame();
        littleMan.startGameLoopThread();
    }

    private static GameGui makeGamePanel() {
        return new LittleManGui();
    }

    final LittleMan littleMan;
    final Computer computer;

    public LittleManGame() {
        super(new AbstractInputHandlerClient(), makeGamePanel());
        computer = new Computer();
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
