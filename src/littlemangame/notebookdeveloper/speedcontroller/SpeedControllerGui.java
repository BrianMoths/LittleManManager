/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.speedcontroller;

import java.awt.event.ActionListener;

/**
 *
 * @author brian
 */
public class SpeedControllerGui extends javax.swing.JPanel {

//    private final SpeedController speedController;
    /**
     * Creates new form SpeedControllerGui
     */
    public SpeedControllerGui() {
        initComponents();
    }

    public void setResumeAction(ActionListener l) {
        resumeButton.addActionListener(l);
    }

    public void setFasterAction(ActionListener l) {
        fasterButton.addActionListener(l);
    }

    public void setSlowerAction(ActionListener l) {
        slowerButton.addActionListener(l);
    }

    public void setPauseAction(ActionListener l) {
        pauseButton.addActionListener(l);
    }

    public void setEndTestAction(ActionListener l) {
        endTestButton.addActionListener(l);
    }

    public void setText(String text) {
        speedLabel.setText(text);
    }

    private void setEnabledButtons(boolean isEnabled) {
        pauseButton.setEnabled(isEnabled);
        slowerButton.setEnabled(isEnabled);
        fasterButton.setEnabled(isEnabled);
        resumeButton.setEnabled(isEnabled);
        endTestButton.setEnabled(isEnabled);
    }

    public void setEnabledFasterButton(boolean isEnabled) {
        fasterButton.setEnabled(isEnabled);
    }

    public void setEnabledSlowerButton(boolean isEnabled) {
        slowerButton.setEnabled(isEnabled);
    }

    public void setEnabledPauseButton(boolean isEnabled) {
        pauseButton.setEnabled(isEnabled);
    }

    public void setEnabledResumeButton(boolean isEnabled) {
        resumeButton.setEnabled(isEnabled);
    }

    public void setEnabledEndTestButton(boolean isEnabled) {
        endTestButton.setEnabled(isEnabled);
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

        pauseButton = new javax.swing.JButton();
        slowerButton = new javax.swing.JButton();
        speedLabel = new javax.swing.JLabel();
        fasterButton = new javax.swing.JButton();
        resumeButton = new javax.swing.JButton();
        endTestButton = new javax.swing.JButton();

        pauseButton.setText("Pause");

        slowerButton.setText("Slower");

        speedLabel.setText("1x");

        fasterButton.setText("Faster");

        resumeButton.setText("Resume");

        endTestButton.setText("End Test");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pauseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(slowerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(speedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resumeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fasterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(endTestButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(endTestButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pauseButton)
                            .addComponent(resumeButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(slowerButton)
                            .addComponent(fasterButton)
                            .addComponent(speedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton endTestButton;
    private javax.swing.JButton fasterButton;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton resumeButton;
    private javax.swing.JButton slowerButton;
    private javax.swing.JLabel speedLabel;
    // End of variables declaration//GEN-END:variables

}
