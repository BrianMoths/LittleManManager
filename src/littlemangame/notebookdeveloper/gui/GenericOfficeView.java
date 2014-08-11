/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.gui;

import java.awt.GridLayout;
import java.awt.Point;
import littlemangame.computer.computercomponents.InputPanel;
import littlemangame.computer.computercomponents.OutputPanel;
import littlemangame.littlemancommands.GenericLittleManCommander;

/**
 *
 * @author brian
 * @param <T>
 * @param <U>
 */
public class GenericOfficeView<T extends InputPanel, U extends OutputPanel>
        extends javax.swing.JPanel {

    private final T inputPanel;
    private final U outputPanel;

    /**
     * Creates new form OfficeView
     *
     * @param inputPanel
     * @param outputPanel
     */
    public GenericOfficeView(T inputPanel, U outputPanel) {
        initComponents();
        gameCanvas.doLayout();
        doLayout();
        this.inputPanel = inputPanel;
        inputPanelHolder.setLayout(new GridLayout());
        inputPanelHolder.add(inputPanel);
        final Point inputPanelLocation = inputPanelHolder.getLocation();
        inputPanel.setAnchorPoint(inputPanelLocation);
        this.outputPanel = outputPanel;
        outputPanelHolder.setLayout(new GridLayout());
        outputPanelHolder.add(outputPanel);
        final Point outputPanelLocation = outputPanelHolder.getLocation();
        outputPanel.setAnchorPoint(outputPanelLocation);
    }

    public T getInputPanel() {
        return inputPanel;
    }

    public U getOutputPanel() {
        return outputPanel;
    }

    public void registerLittleManCommander(GenericLittleManCommander<?> littleManCommander) {
        gameCanvas.getRenderer().addDrawable(littleManCommander);
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

        gameCanvas = new GameGui.GameCanvas();
        outputPanelHolder = new javax.swing.JPanel();
        inputPanelHolder = new javax.swing.JPanel();

        javax.swing.GroupLayout outputPanelHolderLayout = new javax.swing.GroupLayout(outputPanelHolder);
        outputPanelHolder.setLayout(outputPanelHolderLayout);
        outputPanelHolderLayout.setHorizontalGroup(
            outputPanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        outputPanelHolderLayout.setVerticalGroup(
            outputPanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout inputPanelHolderLayout = new javax.swing.GroupLayout(inputPanelHolder);
        inputPanelHolder.setLayout(inputPanelHolderLayout);
        inputPanelHolderLayout.setHorizontalGroup(
            inputPanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );
        inputPanelHolderLayout.setVerticalGroup(
            inputPanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gameCanvasLayout = new javax.swing.GroupLayout(gameCanvas);
        gameCanvas.setLayout(gameCanvasLayout);
        gameCanvasLayout.setHorizontalGroup(
            gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameCanvasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(inputPanelHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(outputPanelHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(496, Short.MAX_VALUE))
        );
        gameCanvasLayout.setVerticalGroup(
            gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gameCanvasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outputPanelHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPanelHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(gameCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(gameCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GameGui.GameCanvas gameCanvas;
    private javax.swing.JPanel inputPanelHolder;
    private javax.swing.JPanel outputPanelHolder;
    // End of variables declaration//GEN-END:variables
}
