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

    private final Queue<InputOutputEventType> inputOutputEventTypes;
    private final Queue<Word> inputWords;
    private final Queue<Word> outputWords;
    private boolean isCorrectSoFar;
    private boolean isHalted;

    public InstanceNotebookTester() {
        inputOutputEventTypes = new ArrayDeque<>();
        inputWords = new ArrayDeque<>();
        outputWords = new ArrayDeque<>();
    }

    @Override
    public boolean isNotebookCorrect(Memory memory) {
        LittleManMock littleManMock = new LittleManMock(makeComputerMock(), makeHaltListener());
        LittleManCommanderMock littleManCommanderMock = new LittleManCommanderMock(littleManMock);
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
            public void acceptOutput(Word outputtedWord) {
                final InputOutputEventType inputOutputEventType = inputOutputEventTypes.poll();
                final Word outputWord = outputWords.poll();
                isCorrectSoFar &= inputOutputEventType == InputOutputEventType.OUTPUT && outputWord.equals(outputtedWord);
            }

        };
    }

    private InputProducerMock makeInputProducerMock() {
        return new InputProducerMock() {

            @Override
            public Word getInputWord() {
                InputOutputEventType inputOutputEventType = inputOutputEventTypes.poll();
                Word inputWord = inputWords.poll();
                if (inputWord == null) {
                    inputWord = Word.ZERO_WORD;
                }
                isCorrectSoFar &= inputOutputEventType == InputOutputEventType.INPUT;
                return inputWord;
            }

        };
    }

    private HaltListener makeHaltListener() {
        return new HaltListener() {

            @Override
            public void acceptHalt() {
                isCorrectSoFar &= inputOutputEventTypes.poll() == InputOutputEventType.HALT;
                isHalted = true;
            }

        };
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

}
