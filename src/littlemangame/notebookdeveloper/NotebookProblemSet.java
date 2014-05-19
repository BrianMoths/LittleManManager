/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import littlemangame.computer.Memory;
import littlemangame.notebookdevelopmentproblems.HaltProblem;
import littlemangame.notebookdevelopmentproblems.NotebookDevelopmentProblem;
import littlemangame.notebookdevelopmentproblems.Output42;
import littlemangame.notebookdevelopmentproblems.OutputAnything;

/**
 *
 * @author brian
 */
public class NotebookProblemSet {

    public static NotebookProblemSet makeDefaultNotebookProblemSet() {
        List<NotebookDevelopmentProblem> notebookDevelopmentProblems = new ArrayList<>();
        notebookDevelopmentProblems.add(new HaltProblem());
        notebookDevelopmentProblems.add(new OutputAnything());
        notebookDevelopmentProblems.add(new Output42());
        return new NotebookProblemSet(notebookDevelopmentProblems);
    }

    private final ListIterator<NotebookDevelopmentProblem> notebookDevelopmentProblemIterator;
    private NotebookDevelopmentProblem notebookDevelopmentProblem;

    public NotebookProblemSet(Collection<? extends NotebookDevelopmentProblem> notebookDevelopmentProblems) {
        final List<NotebookDevelopmentProblem> notebookDevelopmentProblemsCopy = new ArrayList<>(notebookDevelopmentProblems);
        notebookDevelopmentProblemIterator = notebookDevelopmentProblemsCopy.listIterator();
    }

    public boolean verifyNotebook(Memory memory) {
        notebookDevelopmentProblem.testNotebook(memory);
        return notebookDevelopmentProblem.wasLastTestCorrect();

    }

    public boolean hasNextProblem() {
        return notebookDevelopmentProblemIterator.hasNext();
    }

    public void beginNextProblem() {
        notebookDevelopmentProblem = notebookDevelopmentProblemIterator.next();
    }

    public String getCurrentProblemDescription() {
        return notebookDevelopmentProblem.getProblemDescription();
    }

    public String getMessageFromLastTest() {
        return notebookDevelopmentProblem.getMessageFromLastTest();
    }

}
