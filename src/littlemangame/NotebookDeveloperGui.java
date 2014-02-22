/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame;

import GameGui.GameCanvas;
import java.awt.event.ActionListener;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.InputPanel;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.OutputPanel;

/**
 *
 * @author brian
 */
public class NotebookDeveloperGui extends javax.swing.JPanel {

    public SpeedControllerGui getSpeedControllerGui() {
        return speedControllerGui;
    }

    /**
     * Creates new form NotebookDeveloperGui
     */
    public NotebookDeveloperGui() {
        initComponents();
    }

    public void setAbortAction(ActionListener l) {
        abortButton.addActionListener(l);
    }

    public void setEditMemoryAction(ActionListener l) {
        editMemoryButton.addActionListener(l);
    }

    public void setExecuteAction(ActionListener l) {
        executeButton.addActionListener(l);
    }

    public void setSubmitAction(ActionListener l) {
        submitButton.addActionListener(l);
    }

    public void setEnabledAbort(boolean isEnabled) {
        abortButton.setEnabled(isEnabled);
    }

    public void setEnabledEditMemory(boolean isEnabled) {
        editMemoryButton.setEnabled(isEnabled);
    }

    public void setEnabledExecute(boolean isEnabled) {
        executeButton.setEnabled(isEnabled);
    }

    public void setEnabledSubmit(boolean isEnabled) {
        submitButton.setEnabled(isEnabled);
    }

    public InputPanel getInputPanel() {
        return inputPanel;
    }

    public OutputPanel getOutputPanel() {
        return outputPanel;
    }

    public GameCanvas getGameCanvas() {
        return gameCanvas;
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

        jPanel1 = new javax.swing.JPanel();
        gameCanvas = new GameGui.GameCanvas();
        inputPanel = new littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.InputPanel();
        outputPanel = new littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.OutputPanel();
        speedControllerGui = new littlemangame.SpeedControllerGui();
        executeButton = new javax.swing.JButton();
        abortButton = new javax.swing.JButton();
        editMemoryButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();

        javax.swing.GroupLayout gameCanvasLayout = new javax.swing.GroupLayout(gameCanvas);
        gameCanvas.setLayout(gameCanvasLayout);
        gameCanvasLayout.setHorizontalGroup(
            gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameCanvasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(outputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(355, Short.MAX_VALUE))
        );
        gameCanvasLayout.setVerticalGroup(
            gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gameCanvasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        executeButton.setText("Execute");

        abortButton.setText("Abort");

        editMemoryButton.setText("Edit Memory");

        submitButton.setText("Submit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gameCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(speedControllerGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(executeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(abortButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editMemoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(speedControllerGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(executeButton)
                            .addComponent(editMemoryButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(abortButton)
                            .addComponent(submitButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gameCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abortButton;
    private javax.swing.JButton editMemoryButton;
    private javax.swing.JButton executeButton;
    private GameGui.GameCanvas gameCanvas;
    private littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.InputPanel inputPanel;
    private javax.swing.JPanel jPanel1;
    private littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer.OutputPanel outputPanel;
    private littlemangame.SpeedControllerGui speedControllerGui;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
