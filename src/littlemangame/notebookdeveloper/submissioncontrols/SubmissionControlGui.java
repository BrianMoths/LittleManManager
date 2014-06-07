/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.submissioncontrols;

import java.awt.event.ActionListener;
import javax.swing.JLayeredPane;
import littlemangame.notebookdeveloper.speedcontroller.SpeedControllerGui;

/**
 *
 * @author brian
 */
public class SubmissionControlGui extends javax.swing.JPanel {

    /**
     * Creates new form SubmissionControlGui
     */
    public SubmissionControlGui() {
        initComponents();
        baseLayeredPane.setLayer(speedPanel, JLayeredPane.DEFAULT_LAYER, 0);
        baseLayeredPane.setLayer(submissionPanel, JLayeredPane.DEFAULT_LAYER, 1);
        showSubmissionPanel();
    }

    public void setSubmitAction(ActionListener l) {
        submitButton.addActionListener(l);
    }

    public void setObjectiveAction(ActionListener l) {
        objectiveBtn.addActionListener(l);
    }

    public void setEditAction(ActionListener l) {
        editMemoryButton.addActionListener(l);
    }

    public void setTestAction(ActionListener l) {
        testButton.addActionListener(l);
    }

    public void printMessage(String message) {
        consoleTextArea.append(message);
    }

    public final void showSpeedPanel() {
        speedPanel.setVisible(true);
        submissionPanel.setVisible(false);
    }

    public final void showSubmissionPanel() {
        speedPanel.setVisible(false);
        submissionPanel.setVisible(true);
    }

    public void setObjectiveButtonEnabled(boolean isEnabled) {
        objectiveBtn.setEnabled(isEnabled);
    }

    public void setEditMemoryEnabled(boolean isEnabled) {
        editMemoryButton.setEnabled(isEnabled);
    }

    public void setSubmitButtonEnabled(boolean isEnabled) {
        submitButton.setEnabled(isEnabled);
    }

    public void setTestButtonEnabled(boolean isEnabled) {
        testButton.setEnabled(isEnabled);
    }

    public SpeedControllerGui getSpeedControllerGui() {
        return speedControllerGui;
    }

    final protected JLayeredPane getBaseLayeredPane() {
        return baseLayeredPane;
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        baseLayeredPane = new javax.swing.JLayeredPane();
        speedPanel = new javax.swing.JPanel();
        speedControllerGui = new littlemangame.notebookdeveloper.speedcontroller.SpeedControllerGui();
        submissionPanel = new javax.swing.JPanel();
        submitButton = new javax.swing.JButton();
        objectiveBtn = new javax.swing.JButton();
        editMemoryButton = new javax.swing.JButton();
        testButton = new javax.swing.JButton();
        consoleTextScrollPane = new javax.swing.JScrollPane();
        consoleTextArea = new javax.swing.JTextArea();

        javax.swing.GroupLayout speedPanelLayout = new javax.swing.GroupLayout(speedPanel);
        speedPanel.setLayout(speedPanelLayout);
        speedPanelLayout.setHorizontalGroup(
            speedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(speedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(speedControllerGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(542, Short.MAX_VALUE))
        );
        speedPanelLayout.setVerticalGroup(
            speedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(speedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(speedControllerGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        submitButton.setText("Submit");

        objectiveBtn.setText("Objective");

        editMemoryButton.setText("Edit Memory");

        testButton.setText("Test");

        consoleTextArea.setEditable(false);
        consoleTextArea.setColumns(20);
        consoleTextArea.setLineWrap(true);
        consoleTextArea.setRows(3);
        consoleTextScrollPane.setViewportView(consoleTextArea);

        javax.swing.GroupLayout submissionPanelLayout = new javax.swing.GroupLayout(submissionPanel);
        submissionPanel.setLayout(submissionPanelLayout);
        submissionPanelLayout.setHorizontalGroup(
            submissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
            .addGroup(submissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(submissionPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(submissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(submissionPanelLayout.createSequentialGroup()
                            .addComponent(editMemoryButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(submissionPanelLayout.createSequentialGroup()
                            .addComponent(objectiveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(testButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(consoleTextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        submissionPanelLayout.setVerticalGroup(
            submissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
            .addGroup(submissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(submissionPanelLayout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(submissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(consoleTextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(submissionPanelLayout.createSequentialGroup()
                            .addGroup(submissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(testButton)
                                .addComponent(objectiveBtn))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(submissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(editMemoryButton)
                                .addComponent(submitButton))))
                    .addContainerGap(23, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout baseLayeredPaneLayout = new javax.swing.GroupLayout(baseLayeredPane);
        baseLayeredPane.setLayout(baseLayeredPaneLayout);
        baseLayeredPaneLayout.setHorizontalGroup(
            baseLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseLayeredPaneLayout.createSequentialGroup()
                .addComponent(submissionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(baseLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(speedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        baseLayeredPaneLayout.setVerticalGroup(
            baseLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(submissionPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(baseLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(speedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        baseLayeredPane.setLayer(speedPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        speedPanel.getAccessibleContext().setAccessibleName("");
        baseLayeredPane.setLayer(submissionPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(baseLayeredPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(baseLayeredPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane baseLayeredPane;
    private javax.swing.JTextArea consoleTextArea;
    private javax.swing.JScrollPane consoleTextScrollPane;
    private javax.swing.JButton editMemoryButton;
    private javax.swing.JButton objectiveBtn;
    private littlemangame.notebookdeveloper.speedcontroller.SpeedControllerGui speedControllerGui;
    private javax.swing.JPanel speedPanel;
    private javax.swing.JPanel submissionPanel;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton testButton;
    // End of variables declaration//GEN-END:variables
}