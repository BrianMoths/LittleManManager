/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import littlemangame.genericGui.GenericSubmissionControllerAdapter;
import littlemangame.tutorial.TutorialNotebookDeveloper;

/**
 *
 * @author brian
 */
public class SubmissionControllerTutorialAdapter extends GenericSubmissionControllerAdapter<SubmissionControllerTutorialGui, TutorialNotebookDeveloper> {

    static private final String NOTEBOOK_DESCRIPTION = "This is the little man's notebook. Its pages are numbered by words. Each page holds one word. At the beginning of a workday you will write all the instructions for the little man on the notebook before giving it to him. Once the little man starts his day, he will begin reading the notebook from the first page and doing the instructions written in it. Since the little man has a very bad memory, it will also be helpful for you to have the little man write things in this notebook to help the little man remember all the words he has to remember to complete his task.";
    static private final String NOTEBOOK_PAGE_SHEET_DESCRIPTION = "This is the notebook page sheet. It is a special sheet where the little man writes the notebook page he is currently on as he works on completing all the instructions in the notebook.";
    static private final String WORKSHEET_DESCRIPTION = "This is the worksheet. This is where the little man does all his calculations such as adding numbers.";
    static private final String OUTPUT_PANEL_DESCRIPTION = "This is the output panel. This is where the little man can show the answer to a problem you ask him to solve.";
    static private final String INPUT_PANEL_DESCRIPTION = "This is the input panel. Here the little man can ask you to tell him a word in the middle of his work. This would be useful, for example, if you wanted the little man to add two numbers, but you wanted him to ask you which two numbers to add.";

    private boolean isTutorialStarted = false;
    private final ActionListener endNotebookBeginNotebookPageSheetListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            getNotebookDeveloper().setIsNotebookArrowShown(false);
            getNotebookDeveloper().setIsNotebookPageSheetArrowShown(true);
            printDialogue(NOTEBOOK_PAGE_SHEET_DESCRIPTION);
            submissionControlGui.addActionListener(endNotebookPageSheetListenerBeginWorksheet);
            submissionControlGui.removeActionListener(this);
        }

    };
    private final ActionListener endNotebookPageSheetListenerBeginWorksheet = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            getNotebookDeveloper().setIsNotebookPageSheetArrowShown(false);
            getNotebookDeveloper().setIsWorksheetArrowShown(true);
            printDialogue(WORKSHEET_DESCRIPTION);
            submissionControlGui.addActionListener(endWorksheetBeginOutputPanel);
            submissionControlGui.removeActionListener(this);
        }

    };
    private final ActionListener endWorksheetBeginOutputPanel = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            getNotebookDeveloper().setIsWorksheetArrowShown(false);
            getNotebookDeveloper().setIsOutputPanelArrowShown(true);
            printDialogue(OUTPUT_PANEL_DESCRIPTION);
            submissionControlGui.addActionListener(endOutputPanelBeginInputPanel);
            submissionControlGui.removeActionListener(this);
        }

    };
    private final ActionListener endOutputPanelBeginInputPanel = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            getNotebookDeveloper().setIsOutputPanelArrowShown(false);
            getNotebookDeveloper().setIsInputPanelArrowShown(true);
            printDialogue(INPUT_PANEL_DESCRIPTION);
//            submissionControlGui.addActionListener();
            submissionControlGui.removeActionListener(this);
        }

    };

    public SubmissionControllerTutorialAdapter(TutorialNotebookDeveloper notebookDeveloper, SubmissionControllerTutorialGui submissionControlGui) {
        super(notebookDeveloper, submissionControlGui);
    }

    public SubmissionControllerTutorialAdapter(TutorialNotebookDeveloperGui notebookDeveloperGui) {
        this(new TutorialNotebookDeveloper(notebookDeveloperGui.getOfficeView()), notebookDeveloperGui.getSubmissionControlGui());
    }

    public void printDialogue(String dialogue) {
        submissionControlGui.showDialoguePanel();
        submissionControlGui.printDialogue(dialogue);
    }

    @Override
    public void doFrames() {
        if (!isTutorialStarted) {
            startTutorial();
        }
    }

    private void startTutorial() {
        isTutorialStarted = true;
        getNotebookDeveloper().setIsInputPanelArrowShown(false);
        getNotebookDeveloper().setIsNotebookArrowShown(false);
        getNotebookDeveloper().setIsNotebookPageSheetArrowShown(false);
        getNotebookDeveloper().setIsOutputPanelArrowShown(false);
        getNotebookDeveloper().setIsWorksheetArrowShown(false);
        submissionControlGui.showDialoguePanel();
        printDialogue(NOTEBOOK_DESCRIPTION);
        getNotebookDeveloper().setIsNotebookArrowShown(true);
        submissionControlGui.addActionListener(endNotebookBeginNotebookPageSheetListener);
    }

}
