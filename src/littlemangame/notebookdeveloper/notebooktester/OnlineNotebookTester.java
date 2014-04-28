/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.notebooktester;

import littlemangame.computer.Computer;
import littlemangame.computer.Memory;
import littlemangame.notebookdeveloper.notebooktester.littleManMock.ComputerInputterMock;
import littlemangame.notebookdeveloper.notebooktester.littleManMock.ComputerOutputterMock;
import littlemangame.notebookdeveloper.notebooktester.littleManMock.HaltListener;
import littlemangame.notebookdeveloper.notebooktester.littleManMock.InputProducerMock;
import littlemangame.notebookdeveloper.notebooktester.littleManMock.LittleManCommanderMock;
import littlemangame.notebookdeveloper.notebooktester.littleManMock.LittleManMock;
import littlemangame.notebookdeveloper.notebooktester.littleManMock.OutputEventListener;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class OnlineNotebookTester implements NotebookTester { //I should break this into two classes, one for the queues, one for testing.

    static private final String SUCCESS_STRING = "Test successful!";
    private final ExpectedProgramBehavior expectedProgramBehaviorOriginal;
    private final ExpectedProgramBehavior expectedProgramBehaviorCopy;
    private boolean isCorrectSoFar;
    private boolean isHalted;
    private StringBuilder errorStringBuilder;

    public OnlineNotebookTester() {
        expectedProgramBehaviorOriginal = new ExpectedProgramBehavior();
        expectedProgramBehaviorCopy = new ExpectedProgramBehavior();
    }

    @Override
    public boolean isNotebookCorrect(Memory memory) {
        LittleManCommanderMock littleManCommanderMock = initialize(memory);
        expectedProgramBehaviorCopy.copy(expectedProgramBehaviorOriginal);
        while (isCorrectSoFar && !isHalted) {
            littleManCommanderMock.doCycle();
        }
        return isCorrectSoFar;
    }

    private LittleManCommanderMock initialize(Memory memory) {
        LittleManMock littleManMock = new LittleManMock(makeComputerMock(), makeHaltListener());
        LittleManCommanderMock littleManCommanderMock = new LittleManCommanderMock(littleManMock);
        littleManCommanderMock.loadCopyOfMemory(memory);
        errorStringBuilder = new StringBuilder();
        isCorrectSoFar = true;
        isHalted = false;
        return littleManCommanderMock;
    }

    private Computer makeComputerMock() {
        return new Computer(new ComputerOutputterMock(makeOutputEventListener()), new ComputerInputterMock(makeInputProducerMock()));
    }

    private OutputEventListener makeOutputEventListener() {
        return new OutputEventListener() {

            @Override
            public void acceptOutput(Word actualOutputWord) {
                isCorrectSoFar = expectedProgramBehaviorCopy.testOutputEvent(new OutputEvent(actualOutputWord));
            }

        };
    }

    private InputProducerMock makeInputProducerMock() {
        return new InputProducerMock() {

            @Override
            public Word getInputWord() {
                isCorrectSoFar = expectedProgramBehaviorOriginal.testInputEvent(new InputEvent());
                return expectedProgramBehaviorCopy.pollInputWord();
            }

        };
    }

    private HaltListener makeHaltListener() {
        return new HaltListener() {

            @Override
            public void acceptHalt() {
                isCorrectSoFar = expectedProgramBehaviorCopy.testHaltEvent(new HaltEvent());
                isHalted = true;
            }

        };
    }

    public void addInputEvent(Word inputWord) {
        expectedProgramBehaviorOriginal.addInputEvent(inputWord);
    }

    public void addOutputEvent(Word outputWord) {
        expectedProgramBehaviorOriginal.addOutputEvent(outputWord);
    }

    public void addOutputEvent(WordPredicate wordPredicate) {
        expectedProgramBehaviorOriginal.addOutputEvent(wordPredicate);
    }

    public void addHaltEvent() {
        expectedProgramBehaviorOriginal.addHaltEvent();
    }

    @Override
    public String getMessageFromTest() {
        if (isCorrectSoFar) {
            return SUCCESS_STRING;
        } else {
            return expectedProgramBehaviorCopy.getErrorString();
        }
    }

}
