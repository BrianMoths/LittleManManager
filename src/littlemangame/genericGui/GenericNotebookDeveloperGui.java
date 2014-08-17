/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.genericGui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import littlemangame.notebookdeveloper.GenericNotebookDeveloper;
import littlemangame.notebookdeveloper.gui.GenericOfficeView;
import littlemangame.notebookdeveloper.speedcontroller.SpeedController;
import littlemangame.notebookdeveloper.submissioncontrols.SubmissionControlGui;

/**
 *
 * @author brian
 * @param <T>
 * @param <U>
 * @param <V>
 */
public class GenericNotebookDeveloperGui<T extends SubmissionControlGui<V>, U extends GenericOfficeView<?, ?>, V extends GenericNotebookDeveloper<?>>
        extends javax.swing.JPanel {

    protected final T submissionControllerGui;
    private final U officeView;

    /**
     * Creates new form NotebookDeveloperGui
     *
     * @param submissionControllerGui
     * @param officeView
     */
    public GenericNotebookDeveloperGui(T submissionControllerGui, U officeView) {
        initComponents();
        this.submissionControllerGui = submissionControllerGui;
        this.officeView = officeView;
        doCustomLayout(submissionControllerGui, officeView);
//        officeView.registerNotebookDeveloper(notebookDeveloper);
    }

    private void doCustomLayout(T submissionControllerGui, U officeView) {
        submissionControllerGuiHolder.setLayout(new GridLayout());
        submissionControllerGuiHolder.add(submissionControllerGui);
        officeViewHolder.setLayout(new GridLayout());
        officeViewHolder.add(officeView);
    }

    public U getOfficeView() {
        return officeView;
    }

//    public T getSubmissionControlGui() {
//        return submissionControllerGui;
//    }
    public void setEditMemoryAction(ActionListener actionListener) {
        submissionControllerGui.setEditMemoryAction(actionListener);
    }

    public void regsiterNotebookDeveloper(V notebookDeveloper) {
        officeView.registerNotebookDeveloper(notebookDeveloper);
        submissionControllerGui.registerNotebookDeveloper(notebookDeveloper);
    }

    public void registerSpeedController(SpeedController speedController) {
        submissionControllerGui.registerSpeedController(speedController);
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

        submissionControllerGuiHolder = new javax.swing.JPanel();
        officeViewHolder = new javax.swing.JPanel();

        javax.swing.GroupLayout submissionControllerGuiHolderLayout = new javax.swing.GroupLayout(submissionControllerGuiHolder);
        submissionControllerGuiHolder.setLayout(submissionControllerGuiHolderLayout);
        submissionControllerGuiHolderLayout.setHorizontalGroup(
            submissionControllerGuiHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
        );
        submissionControllerGuiHolderLayout.setVerticalGroup(
            submissionControllerGuiHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout officeViewHolderLayout = new javax.swing.GroupLayout(officeViewHolder);
        officeViewHolder.setLayout(officeViewHolderLayout);
        officeViewHolderLayout.setHorizontalGroup(
            officeViewHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 649, Short.MAX_VALUE)
        );
        officeViewHolderLayout.setVerticalGroup(
            officeViewHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(officeViewHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(submissionControllerGuiHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(officeViewHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submissionControllerGuiHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel officeViewHolder;
    private javax.swing.JPanel submissionControllerGuiHolder;
    // End of variables declaration//GEN-END:variables
}
