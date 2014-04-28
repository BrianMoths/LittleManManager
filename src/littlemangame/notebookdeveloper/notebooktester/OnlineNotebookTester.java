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

//    private final Queue<InputOutputEventType> inputOutputEventTypes;
//    private final Queue<Word> inputWords;
//    private final Queue<WordPredicate> outputWords;
    private final ExpectedProgramBehavior expectedProgramBehavior;
    private boolean isCorrectSoFar;
    private boolean isHalted;
    private StringBuilder errorStringBuilder;

    public OnlineNotebookTester() {
//        inputOutputEventTypes = new ArrayDeque<>();
//        inputWords = new ArrayDeque<>();
//        outputWords = new ArrayDeque<>();
        expectedProgramBehavior = new ExpectedProgramBehavior();
    }

    @Override
    public boolean isNotebookCorrect(Memory memory) {
        LittleManCommanderMock littleManCommanderMock = initialize(memory);
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
                isCorrectSoFar = expectedProgramBehavior.testOutputEvent(new OutputEvent(actualOutputWord));
            }

        };
    }

    private InputProducerMock makeInputProducerMock() {
        return new InputProducerMock() {

            @Override
            public Word getInputWord() {
                isCorrectSoFar = expectedProgramBehavior.testInputEvent(new InputEvent());
                return expectedProgramBehavior.pollInputWord();
            }

        };
    }

    private HaltListener makeHaltListener() {
        return new HaltListener() {

            @Override
            public void acceptHalt() {
                isCorrectSoFar = expectedProgramBehavior.testHaltEvent(new HaltEvent());
                isHalted = true;
            }

        };
    }

    public void addInputEvent(Word inputWord) {
        expectedProgramBehavior.addInputEvent(inputWord);
    }

    public void addOutputEvent(Word outputWord) {
        expectedProgramBehavior.addOutputEvent(outputWord);
    }

    public void addHaltEvent() {
        expectedProgramBehavior.addHaltEvent();
    }

    @Override
    public String getMessageFromTest() {
        if (isCorrectSoFar) {
            return SUCCESS_STRING;
        } else {
            return expectedProgramBehavior.getErrorString();
        }
    }

}
