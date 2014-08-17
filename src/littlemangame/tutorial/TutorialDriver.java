/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.tutorial;

import littlemangame.tutorial.tutorialnotebookdeveloper.TutorialNotebookDeveloper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import littlemangame.tutorial.gui.TutorialLittleManGui;

/**
 * import littlemangame.tutorial.gui.TutorialLittleManGui;
 *
 *
 * @author brian
 */
public class TutorialDriver {

    static private final String INTRO_TEXT = "Welcome to your new job. We have hired a little man because he is very good at the work we need him to do, but we have quickly found that he is difficult to communicate with. He basically speaks a different language than we do. The words of his language are numbers from 0 to 99. That is where you come in. Your job will be to take our description of what we want the little man to do, and translate it into his strange language. You will write the commands you come up with in a notebook for the little man. After you have finished the notebook, we will review it to confirm that the little man does do what we wanted. You are now looking at the little man's office. Let me now explain to you what is all in the little man's office.";
    static private final String NOTEBOOK_DESCRIPTION = "This is the little man's notebook. Its pages are numbered by words. Each page has enough space for only one word. You will write all the instructions for the little man on this notebook. Once the little man starts his work for the day, he will begin reading the notebook from the first page and doing the instructions written in it. Another use for the notebook is as a place for the little man to write down words he needs to remember. Since the little man has a very bad memory, he can't just keep everything in his head.";
    static private final String NOTEBOOK_PAGE_SHEET_DESCRIPTION = "This is the notebook page sheet. It is a special sheet where the little man writes the notebook page he is currently on as he works on completing all the instructions in the notebook. It starts out at zero because that is the first page.";
    static private final String WORKSHEET_DESCRIPTION = "This is the worksheet. This is where the little man does all his calculations such as adding numbers.";
    static private final String OUTPUT_PANEL_DESCRIPTION = "This is the output panel. This is where the little man can show the results that we want from him.";
    static private final String INPUT_PANEL_DESCRIPTION = "This is the input panel. Here the little man can ask you to tell him a word in the middle of his work. This would be useful, for example, if you wanted the little man to add two numbers, but you wanted him to ask you which two numbers to add.";

    private final TutorialNotebookDeveloper tutorialNotebookDeveloper;
    private final TutorialLittleManGui tutorialLittleManGui;
    private boolean isTutorialStarted = false;
    private final ActionListener endIntroBeginNotebookNotebookListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            tutorialNotebookDeveloper.setIsNotebookArrowShown(true);
            printDialogue(NOTEBOOK_DESCRIPTION);
            tutorialLittleManGui.addDialogueActionListener(endNotebookBeginNotebookPageSheetListener);
            tutorialLittleManGui.removeDialogueActionListener(this);
        }

    };
    private final ActionListener endNotebookBeginNotebookPageSheetListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            tutorialNotebookDeveloper.setIsNotebookArrowShown(false);
            tutorialNotebookDeveloper.setIsNotebookPageSheetArrowShown(true);
            printDialogue(NOTEBOOK_PAGE_SHEET_DESCRIPTION);
            tutorialLittleManGui.addDialogueActionListener(endNotebookPageSheetListenerBeginWorksheet);
            tutorialLittleManGui.removeDialogueActionListener(this);
        }

    };
    private final ActionListener endNotebookPageSheetListenerBeginWorksheet = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            tutorialNotebookDeveloper.setIsNotebookPageSheetArrowShown(false);
            tutorialNotebookDeveloper.setIsWorksheetArrowShown(true);
            printDialogue(WORKSHEET_DESCRIPTION);
            tutorialLittleManGui.addDialogueActionListener(endWorksheetBeginOutputPanel);
            tutorialLittleManGui.removeDialogueActionListener(this);
        }

    };
    private final ActionListener endWorksheetBeginOutputPanel = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            tutorialNotebookDeveloper.setIsWorksheetArrowShown(false);
            tutorialNotebookDeveloper.setIsOutputPanelArrowShown(true);
            printDialogue(OUTPUT_PANEL_DESCRIPTION);
            tutorialLittleManGui.addDialogueActionListener(endOutputPanelBeginInputPanel);
            tutorialLittleManGui.removeDialogueActionListener(this);
        }

    };
    private final ActionListener endOutputPanelBeginInputPanel = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            tutorialNotebookDeveloper.setIsOutputPanelArrowShown(false);
            tutorialNotebookDeveloper.setIsInputPanelArrowShown(true);
            printDialogue(INPUT_PANEL_DESCRIPTION);
            tutorialLittleManGui.addDialogueActionListener(endInputPanel);
            tutorialLittleManGui.removeDialogueActionListener(this);
        }

    };
    private final ActionListener endInputPanel = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            tutorialNotebookDeveloper.setIsInputPanelArrowShown(false);
            tutorialLittleManGui.showSubmissionPanel();
//            submissionControlGui.addActionListener();
            tutorialLittleManGui.removeDialogueActionListener(this);
        }

    };

    public TutorialDriver(TutorialNotebookDeveloper tutorialNotebookDeveloper, TutorialLittleManGui tutorialLittleManGui) {
        this.tutorialNotebookDeveloper = tutorialNotebookDeveloper;
        this.tutorialLittleManGui = tutorialLittleManGui;
    }

    public void printDialogue(String dialogue) {
        tutorialLittleManGui.showDialoguePanel();
        tutorialLittleManGui.printDialogue(dialogue);
    }

    public void doFrames(int numFrames) {
        if (!isTutorialStarted) {
            startTutorial();
        }
        tutorialNotebookDeveloper.doFrames(numFrames);
    }

    private void startTutorial() {
        isTutorialStarted = true;
        hideArrows();
        tutorialLittleManGui.showDialoguePanel();
        printDialogue(INTRO_TEXT);
        tutorialLittleManGui.addDialogueActionListener(endIntroBeginNotebookNotebookListener);
    }

    private void hideArrows() {
        tutorialNotebookDeveloper.setIsInputPanelArrowShown(false);
        tutorialNotebookDeveloper.setIsNotebookArrowShown(false);
        tutorialNotebookDeveloper.setIsNotebookPageSheetArrowShown(false);
        tutorialNotebookDeveloper.setIsOutputPanelArrowShown(false);
        tutorialNotebookDeveloper.setIsWorksheetArrowShown(false);
    }

}
