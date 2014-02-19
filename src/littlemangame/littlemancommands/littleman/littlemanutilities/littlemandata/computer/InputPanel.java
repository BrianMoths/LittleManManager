/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer;

import java.awt.Point;
import littlemangame.word.Word;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author brian
 */
public class InputPanel extends javax.swing.JPanel {

    private final static String[] STRINGS = makeWordStrings();

    static private String[] makeWordStrings() {
        final int numStrings = 100;
        String[] strings = new String[numStrings];
        Word word = Word.ZERO_WORD;
        for (int i = 0; i < numStrings; i++) {
            strings[i] = word.toString();
            word = word.incrementedWord();
        }
        return strings;
    }

    private boolean isValueSelected;
    private Word value;

    /**
     * Creates new form InputPanel
     */
    public InputPanel() {
        initComponents();
        isValueSelected = false;
        initWordModel();
        disablePanel();
    }

    private void initWordModel() {
        wordSelector.setModel(new javax.swing.DefaultComboBoxModel(STRINGS));
        wordSelector.setEditable(false);
        AutoCompleteDecorator.decorate(wordSelector);
    }

    public void disablePanel() {
        wordSelector.setEnabled(false);
        submitButton.setEnabled(false);
        isValueSelected = false;
    }

    public void enablePanel() {
        wordSelector.setEnabled(true);
        submitButton.setEnabled(true);
    }

    public boolean isPanelEnabled() {
        return wordSelector.isEnabled();
    }

    public Word getLastSelectedWord() {
        disablePanel();
        return value;
    }

    public boolean isValueSelected() {
        return isValueSelected;
    }

    public Point getAccessLocation() {
        final int x = getX() + getWidth() + 4;
        final int y = getY() + getHeight() / 2;
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

        wordSelector = new javax.swing.JComboBox();
        submitButton = new javax.swing.JButton();

        wordSelector.setEditable(true);
        wordSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Apple", "Peach", "Cherry", "Plum" }));

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
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(wordSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wordSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        value = Word.valueOfLastDigitsOfInteger(Integer.parseInt((String) wordSelector.getSelectedItem()));
        isValueSelected = true;
    }//GEN-LAST:event_submitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton submitButton;
    private javax.swing.JComboBox wordSelector;
    // End of variables declaration//GEN-END:variables

}
