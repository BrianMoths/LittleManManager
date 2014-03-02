/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.notebooktester;

import java.util.ArrayDeque;
import java.util.Queue;
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
public class InstanceNotebookTester implements NotebookTester { //I should break this into two classes, one for the queues, one for testing.

    static private final String SUCCESS_STRING = "Test successful!";

    private final Queue<InputOutputEventType> inputOutputEventTypes;
    private final Queue<Word> inputWords;
    private final Queue<Word> outputWords;
    private boolean isCorrectSoFar;
    private boolean isHalted;
    private StringBuilder errorStringBuilder;

    public InstanceNotebookTester() {
        inputOutputEventTypes = new ArrayDeque<>();
        inputWords = new ArrayDeque<>();
        outputWords = new ArrayDeque<>();
    }

    @Override
    public boolean isNotebookCorrect(Memory memory) {
        LittleManMock littleManMock = new LittleManMock(makeComputerMock(), makeHaltListener());
        LittleManCommanderMock littleManCommanderMock = new LittleManCommanderMock(littleManMock);
        littleManCommanderMock.loadCopyOfMemory(memory);
        errorStringBuilder = new StringBuilder();
        isCorrectSoFar = true;
        isHalted = false;
        while (isCorrectSoFar && !isHalted) {
            littleManCommanderMock.doCycle();
        }
        return isCorrectSoFar;
    }

    private Computer makeComputerMock() {
        return new Computer(new ComputerOutputterMock(makeOutputEventListener()), new ComputerInputterMock(makeInputProducerMock()));
    }

    private OutputEventListener makeOutputEventListener() {
        return new OutputEventListener() {

            @Override
            public void acceptOutput(Word actualOutputWord) {
                final InputOutputEventType expectedInputOutputEventType = inputOutputEventTypes.poll();
                final InputOutputEventType actualInputOutputEventType = InputOutputEventType.OUTPUT;
                final Word expectedOutputWord = outputWords.peek();
                if (expectedInputOutputEventType != actualInputOutputEventType || expectedOutputWord != actualOutputWord) {
                    errorStringBuilder.append(makeErrorLineStringBuilder(expectedInputOutputEventType, actualInputOutputEventType));
                    isCorrectSoFar = false;
                } else {
                    errorStringBuilder.append(getActualActionStringBuilder(actualInputOutputEventType));
                }
                outputWords.poll();
            }

        };
    }

    private InputProducerMock makeInputProducerMock() {
        return new InputProducerMock() {

            @Override
            public Word getInputWord() {
                final InputOutputEventType expectedInputOutputEventType = inputOutputEventTypes.poll();
                final InputOutputEventType actualInputOutputEventType = InputOutputEventType.INPUT;
                final Word inputWord;
                if (expectedInputOutputEventType != actualInputOutputEventType) {
                    errorStringBuilder.append(makeErrorLineStringBuilder(expectedInputOutputEventType, actualInputOutputEventType));
                    isCorrectSoFar = false;
                    inputWord = Word.ZERO_WORD;
                } else {
                    errorStringBuilder.append(getActualActionStringBuilder(actualInputOutputEventType));
                    Word nextInputWord = inputWords.poll();
                    inputWord = nextInputWord == null ? Word.ZERO_WORD : nextInputWord;
                }
                return inputWord;
            }

        };
    }

    private StringBuilder getActualActionStringBuilder(final InputOutputEventType actualInputOutputEventType) {
        return new StringBuilder(actualInputOutputEventType.getActualActionString(this)).append(".\n");
    }

    private HaltListener makeHaltListener() {
        return new HaltListener() {

            @Override
            public void acceptHalt() {
                InputOutputEventType expectedEventType = inputOutputEventTypes.poll();
                InputOutputEventType actualEventType = InputOutputEventType.HALT;
                if (expectedEventType != actualEventType && isCorrectSoFar) {
                    errorStringBuilder.append(makeErrorLineStringBuilder(expectedEventType, actualEventType));
                    isCorrectSoFar = false;
                } //if it halts correctly, then the log will not be displayed. Otherwise this needs fix.
                isHalted = true;
            }

        };
    }

    private StringBuilder makeErrorLineStringBuilder(InputOutputEventType expectedEventType, InputOutputEventType actualEventType) {
        StringBuilder errorLineStringBuilder = new StringBuilder();
        errorLineStringBuilder.append(actualEventType.getActualActionString(this))
                .append(", but ")
                .append(expectedEventType.getExpectedActionString(this))
                .append(".\n");
        return errorLineStringBuilder;
    }

    public void addInputEvent(Word inputWord) {
        inputOutputEventTypes.add(InputOutputEventType.INPUT);
        inputWords.add(inputWord);
    }

    public void addOutputEvent(Word outputWord) {
        inputOutputEventTypes.add(InputOutputEventType.OUTPUT);
        outputWords.add(outputWord);
    }

    public void addHaltEvent() {
        inputOutputEventTypes.add(InputOutputEventType.HALT);
    }

    @Override
    public String getMessageFromTest() {
        if (isCorrectSoFar) {
            return SUCCESS_STRING;
        } else {
            return errorStringBuilder.toString();
        }
    }

    Word peekAtOutput() {
        return outputWords.peek();
    }

}
