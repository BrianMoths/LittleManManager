/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import littlemangame.computer.Computer;
import littlemangame.computer.Memory;
import littlemangame.littlemancommands.LittleManCommander;
import littlemangame.notebookdeveloper.gui.NotebookDeveloperGui;
import littlemangame.notebookdeveloper.submissioncontrols.SubmissionControllerAdapter;
import littlemangame.notebookdevelopmentproblems.HaltProblem;
import littlemangame.notebookdevelopmentproblems.NotebookDevelopmentProblem;
import littlemangame.notebookdevelopmentproblems.Output42;
import littlemangame.notebookdevelopmentproblems.OutputAnything;

/**
 *
 * @author brian
 */
public class NotebookDeveloper {
    
    private final SubmissionControllerAdapter submissionControllerAdapter;
    private final LittleManCommander littleManCommander;
    private final Memory memory;
    private final List<NotebookDevelopmentProblem> notebookDevelopmentProblems;
    private ListIterator<NotebookDevelopmentProblem> notebookDevelopmentProblemIterator;
    private NotebookDevelopmentProblem notebookDevelopmentProblem;
    private boolean isProblemSolved;
    
    public NotebookDeveloper(NotebookDeveloperGui notebookDeveloperGui) {
        notebookDevelopmentProblems = new ArrayList<>();
        submissionControllerAdapter = new SubmissionControllerAdapter(this, notebookDeveloperGui.getSubmissionControlGui());
        final Computer computer = new Computer(notebookDeveloperGui.getOutputPanel(), notebookDeveloperGui.getInputPanel());
        littleManCommander = new LittleManCommander(computer);
        notebookDeveloperGui.getGameCanvas().getRenderer().addDrawable(littleManCommander);
        memory = new Memory();
        addProblem(new HaltProblem());
        addProblem(new OutputAnything());
        addProblem(new Output42());
        notebookDevelopmentProblemIterator = notebookDevelopmentProblems.listIterator();
        notebookDevelopmentProblem = notebookDevelopmentProblemIterator.next();
        isProblemSolved = false;
        submissionControllerAdapter.setEndTestActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                littleManCommander.reset();
            }

        });
    }
    
    public void doFrame() {
        for (int i = 0; i < submissionControllerAdapter.getCurrentSpeed(); i++) {
            littleManCommander.doCycle();
        }
    }
    
    public void submitMemory() {
        notebookDevelopmentProblem.testNotebook(memory);
        isProblemSolved = notebookDevelopmentProblem.wasLastTestCorrect();
        showMessage(notebookDevelopmentProblem.getMessageFromLastTest());
        if (isProblemSolved) {
            if (notebookDevelopmentProblemIterator.hasNext()) {
                notebookDevelopmentProblem = notebookDevelopmentProblemIterator.next();
                isProblemSolved = false;
            } else {
                showMessage("You beat the game!");
            }
        }
    }
    
    private void showMessage(String message) {
        submissionControllerAdapter.printMessage(message);
    }
    
    final public boolean addProblem(NotebookDevelopmentProblem e) {
        return notebookDevelopmentProblems.add(e);
    }
    
    public String getCurrentProblemDescription() {
        return notebookDevelopmentProblem.getProblemDescription();
    }
    
    public Memory getMemory() {
        return new Memory(memory);
    }
    
    public void setMemory(Memory memory) {
        this.memory.loadCopyOfMemory(memory);
        littleManCommander.reset();
        littleManCommander.loadCopyOfMemory(memory);
    }
    
}
