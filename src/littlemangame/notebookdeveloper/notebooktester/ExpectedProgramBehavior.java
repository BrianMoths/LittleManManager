/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.notebooktester;

import java.util.ArrayDeque;
import java.util.Queue;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class ExpectedProgramBehavior {

    private final Queue<InputOutputEventType> inputOutputEventTypes;
    private final Queue<Word> inputWords;
    private final Queue<WordPredicate> outputWordPredicates;
    private final StringBuilder errorStringBuilder;

    public ExpectedProgramBehavior() {
        inputOutputEventTypes = new ArrayDeque<>();
        inputWords = new ArrayDeque<>();
        outputWordPredicates = new ArrayDeque<>();
        errorStringBuilder = new StringBuilder();
    }

    public ExpectedProgramBehavior(ExpectedProgramBehavior expectedProgramBehavior) {
        inputOutputEventTypes = new ArrayDeque<>(expectedProgramBehavior.inputOutputEventTypes);
        inputWords = new ArrayDeque<>(expectedProgramBehavior.inputWords);
        outputWordPredicates = new ArrayDeque<>(expectedProgramBehavior.outputWordPredicates);
        errorStringBuilder = new StringBuilder(expectedProgramBehavior.errorStringBuilder);
    }

    public void copy(ExpectedProgramBehavior expectedProgramBehavior) {
        inputOutputEventTypes.clear();
        inputOutputEventTypes.addAll(expectedProgramBehavior.inputOutputEventTypes);
        inputWords.clear();
        inputWords.addAll(expectedProgramBehavior.inputWords);
        outputWordPredicates.clear();
        outputWordPredicates.addAll(expectedProgramBehavior.outputWordPredicates);
        errorStringBuilder.setLength(0);
        errorStringBuilder.append(expectedProgramBehavior.errorStringBuilder);
    }

    public void addInputEvent(Word inputWord) {
        inputOutputEventTypes.add(InputOutputEventType.INPUT);
        inputWords.add(inputWord);
    }

    public void addOutputEvent(Word outputWord) {
        inputOutputEventTypes.add(InputOutputEventType.OUTPUT);
        outputWordPredicates.add(WordPredicate.makeEqualsWordPredicate(outputWord));
    }

    public void addHaltEvent() {
        inputOutputEventTypes.add(InputOutputEventType.HALT);
    }

    public boolean testInputEvent(InputEvent actualOutputEvent) {
        final InputOutputEventType expectedInputOutputEventType = inputOutputEventTypes.poll();
        boolean isCorrectSoFar;
        if (expectedInputOutputEventType != actualOutputEvent.getEventType()) {
            errorStringBuilder.append(makeErrorLineStringBuilder(expectedInputOutputEventType, actualOutputEvent));
            isCorrectSoFar = false;
        } else {
            errorStringBuilder.append(getActualActionStringBuilder(actualOutputEvent));
            isCorrectSoFar = true;
        }
        return isCorrectSoFar;
    }

    public Word pollInputWord() {
        return inputWords.poll();
    }

    public boolean testHaltEvent(HaltEvent actualOutputEvent) {
        final InputOutputEventType expectedInputOutputEventType = inputOutputEventTypes.poll();
        boolean isCorrectSoFar;
        if (expectedInputOutputEventType != actualOutputEvent.getEventType()) {
            errorStringBuilder.append(makeErrorLineStringBuilder(expectedInputOutputEventType, actualOutputEvent));
            isCorrectSoFar = false;
        } else {
            errorStringBuilder.append(getActualActionStringBuilder(actualOutputEvent));
            isCorrectSoFar = true;
        }
        return isCorrectSoFar;
    }

    public boolean testOutputEvent(OutputEvent actualOutputEvent) {
        final InputOutputEventType expectedInputOutputEventType = inputOutputEventTypes.poll();
        final WordPredicate wordPredicate = outputWordPredicates.poll();
        boolean isCorrectSoFar;
        if (expectedInputOutputEventType != actualOutputEvent.getEventType() || !wordPredicate.isCorrect(actualOutputEvent.getWord())) {
            errorStringBuilder.append(makeErrorLineStringBuilder(expectedInputOutputEventType, actualOutputEvent));
            isCorrectSoFar = false;
        } else {
            errorStringBuilder.append(getActualActionStringBuilder(actualOutputEvent));
            isCorrectSoFar = true;
        }
        return isCorrectSoFar;
    }

    private StringBuilder makeErrorLineStringBuilder(InputOutputEventType expectedEventType, InputOutputEvent actualEvent) {
        StringBuilder errorLineStringBuilder = new StringBuilder();
        errorLineStringBuilder.append(actualEvent.getActualActionDescription())
                .append(", but ")
                .append(getExpectedActionString(expectedEventType))
                .append(".\n");
        return errorLineStringBuilder;
    }

    private StringBuilder getActualActionStringBuilder(final InputOutputEvent actualInputOutputEvent) {
        return new StringBuilder(actualInputOutputEvent.getActualActionDescription()).append(".\n");
    }

    private CharSequence getExpectedActionString(InputOutputEventType expectedEventType) {
        if (expectedEventType == InputOutputEventType.HALT) {
            return new HaltEvent().getExpectedActionDescription();
        } else if (expectedEventType == InputOutputEventType.INPUT) {
            return new InputEvent().getExpectedActionDescription();
        } else if (expectedEventType == InputOutputEventType.OUTPUT) {
            return outputWordPredicates.peek().expectedWordString(); //check if peek will cause error from queue being empty.
        } else {
            throw new AssertionError();
        }

    }

    public String getErrorString() {
        return errorStringBuilder.toString();
    }

}