/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.computer;

import java.awt.Point;

/**
 *
 * @author brian
 */
public class OutputPanel extends javax.swing.JPanel {

    /**
     * Creates new form OutputPanel
     */
    public OutputPanel() {
        initComponents();
    }

    public void append(String str) {
        jTextArea1.append(str);
    }

    public void newLine() {
        append("\n");
    }

    public void clear() {
        jTextArea1.setText("");
    }

    public Point getAccessLocation() {
        final int x = getX() + getWidth() + 4;
        final int y = getY() + getHeight() / 2;
        return new Point(x, y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(14);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
