/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.computer.computercomponents;

import java.awt.Point;
import littlemangame.word.Word;

/**
 * This is an implementation of the ComputterInputter interface that allows a
 * user to enter data through a JPanel.
 *
 * @author brian
 */
public class InputPanel extends javax.swing.JPanel implements OfficeInputter {

    private Word value;
    private InputPanelState inputPanelState;
    private Point anchorPoint;

    /**
     * Creates new InputPanel. The panel is initially in the disabled state as
     * if cancelInputRequest has just been called.
     */
    public InputPanel() {
        initComponents();
        wordSelector.setEditable(false);
        cancelInputRequest();
        anchorPoint = new Point(0, 0);
    }

    public void setAnchorPoint(Point anchorPoint) {
        this.anchorPoint = new Point(anchorPoint);
    }

    @Override
    public final void cancelInputRequest() {
        wordSelector.setEnabled(false);
        submitButton.setEnabled(false);
        inputPanelState = InputPanelState.DISABLED;
    }

    @Override
    public void requestInput() {
        wordSelector.setEnabled(true);
        submitButton.setEnabled(true);
        inputPanelState = InputPanelState.AWAITING_INPUT;
    }

    @Override
    public Word getEnteredWord() {
        if (inputPanelState != InputPanelState.HAS_INPUT) {
            throw new IllegalStateException("getEnteredWordCalled before user entered input. Use isWordEntered to ensure the user has entered input before getEnteredWord is called.");
        }
        cancelInputRequest();
        return value;
    }

    @Override
    public boolean isWordEntered() {
        return inputPanelState == InputPanelState.HAS_INPUT;
    }

    @Override
    public boolean isDisabled() {
        return inputPanelState == InputPanelState.DISABLED;
    }

    @Override
    public boolean isAwaitingInput() {
        return inputPanelState == InputPanelState.AWAITING_INPUT;
    }

    @Override
    public Point getAccessLocation() {
        final int x = anchorPoint.x + getX() + getWidth() + 4;
        final int y = anchorPoint.y + getY() + getHeight() / 2;
        return new Point(x, y);
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

        submitButton = new javax.swing.JButton();
        wordSelector = new littlemangame.notebookdeveloper.gui.WordSelector();

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(wordSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(wordSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        if (inputPanelState != InputPanelState.AWAITING_INPUT) {
            throw new AssertionError("User entered input when input was not being requested.");
        }
        value = wordSelector.getLastSelectedWord();
        inputPanelState = InputPanelState.HAS_INPUT;
    }//GEN-LAST:event_submitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton submitButton;
    private littlemangame.notebookdeveloper.gui.WordSelector wordSelector;
    // End of variables declaration//GEN-END:variables

}
