/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.LittleManCommands;

import java.util.EnumMap;
import littlemangame.genericLittleMan.GenericLittleMan;
import littlemangame.genericLittleMan.LittleManTest;

/**
 *
 * @author brian
 */
public final class LittleManConditionalAction extends LittleManAction {

    private static enum ConditionalActionStep {

        GO_TO_TEST(),
        DO_TEST(),
        DO_ACTION();

        static private final EnumMap<ConditionalActionStep, ConditionalActionStep> nextStep;

        static {
            nextStep = new EnumMap<>(ConditionalActionStep.class);
            nextStep.put(GO_TO_TEST, DO_TEST);
            nextStep.put(DO_TEST, DO_ACTION);
            nextStep.put(DO_ACTION, GO_TO_TEST);
        }

        ConditionalActionStep getNextStep() {
            return nextStep.get(this);
        }

    }

    private final LittleManTest littleManTest;
    private final LittleManAction littleManConditionalAction;
    private ConditionalActionStep conditionalActionStep;

    public LittleManConditionalAction(LittleManTest littleManTest, LittleManAction littleManConditionalAction) {
        this.littleManTest = littleManTest;
        this.littleManConditionalAction = littleManConditionalAction;
        this.conditionalActionStep = ConditionalActionStep.GO_TO_TEST;
    }

    public LittleManConditionalAction(LittleManTest littleManTest, LittleManAction... littleManConditionalAction) {
        this(littleManTest, new LittleManActionSequence(littleManConditionalAction));
    }

    @Override
    public boolean doAction(GenericLittleMan<?> littleMan) {
        switch (conditionalActionStep) {
            case GO_TO_TEST:
                goToTest(littleMan);
                return false;
            case DO_TEST:
                return doTest(littleMan);
            case DO_ACTION:
                return doConditionalAction(littleMan);
            default:
                throw new AssertionError();
        }
    }

    private void goToTest(GenericLittleMan<?> littleMan) {
        final boolean isComplete = littleMan.goToComputerLocation(littleManTest.getComputerLocation());
        if (isComplete) {
            incrementConditionalActionStep();
        }
    }

    private boolean doTest(GenericLittleMan<?> littleMan) {
        if (littleMan.test(littleManTest)) {
            incrementConditionalActionStep();
            return false;
        } else {
            resetConditionalActionStep();
            return true;
        }
    }

    private boolean doConditionalAction(GenericLittleMan<?> littleMan) {
        boolean isComplete2 = littleManConditionalAction.doAction(littleMan);
        if (isComplete2) {
            resetConditionalActionStep();
        }
        return isComplete2;
    }

    private void incrementConditionalActionStep() {
        conditionalActionStep = conditionalActionStep.getNextStep();
    }

    private void resetConditionalActionStep() {
        conditionalActionStep = ConditionalActionStep.GO_TO_TEST;
    }

    @Override
    public LittleManAction getResetCopy() {
        return new LittleManConditionalAction(littleManTest, littleManConditionalAction.getResetCopy());
    }

}
