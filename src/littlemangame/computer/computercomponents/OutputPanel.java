/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.computer.computercomponents;

import java.awt.Point;
import littlemangame.word.Word;

/**
 * Implementation of {@link OfficeOutputter} that uses a jTextArea to print
 * the output.
 *
 * @author brian
 */
public class OutputPanel extends javax.swing.JPanel implements OfficeOutputter {

    private Point anchorPoint;

    /**
     * Creates new form OutputPanel. The output panel is initially blank.
     */
    public OutputPanel() {
        initComponents();
        anchorPoint = new Point(0, 0);
    }

    public void setAnchorPoint(Point anchorPoint) {
        this.anchorPoint = new Point(anchorPoint);
    }

    private void append(String str) {
        jTextArea1.append(str);
    }

    private void newLine() {
        append("\n");
    }

    @Override
    public void printlnUnsigned(Word word) {
        append(word.toStringUnsigned());
        newLine();
    }

    @Override
    public void clear() {
        jTextArea1.setText("");
    }

    @Override
    public Point getAccessLocation() {
        final int x = anchorPoint.x + getX() + getWidth() + 4;
        final int y = anchorPoint.y + getY() + getHeight() / 2;
//        final int x = getX() + getPreferredSize().width + 4 + 10;
//        final int y = getY() + getPreferredSize().height / 3;
//        return SwingUtilities.convertPoint(null, x, y, this);
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
