/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import littlemangame.computer.Memory;
import littlemangame.instructions.InstructionFromSet;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class MemoryEditor extends javax.swing.JFrame {

    private final List<MemorySlotChooser> memorySlotChoosers;

    /**
     * Creates new form MemoryEditor
     */
    public MemoryEditor() {
        initComponents();
        memorySlotChoosers = new ArrayList<>(Word.NUM_WORDS);
        addMemorySlotChoosers();
        writeDescriptions();
    }

    private void addMemorySlotChoosers() {
        Iterator<Word> wordIterator = Word.getIterator();
        scrollPanePanel.setLayout(new GridLayout(Word.NUM_WORDS, 1, 0, 5));
        while (wordIterator.hasNext()) {
            final MemorySlotChooser memorySlotChooser = new MemorySlotChooser(wordIterator.next());
            scrollPanePanel.add(memorySlotChooser);
            memorySlotChoosers.add(memorySlotChooser);
            revalidate();
        }
        revalidate();
        repaint();
    }

    private void writeDescriptions() {
//        for (InstructionFromSet instruction : InstructionFromSet.values()) {
//            helpText.append(instruction.getDescription());
//            helpText.append("\n");
//        }
        DefaultTreeCellRenderer treeCellRenderer = new DefaultTreeCellRenderer();
        treeCellRenderer.setLeafIcon(null);
        treeCellRenderer.setOpenIcon(null);
        treeCellRenderer.setClosedIcon(null);
        instructionDescriptionTree.setCellRenderer(treeCellRenderer);

        javax.swing.tree.DefaultMutableTreeNode rootNode = new javax.swing.tree.DefaultMutableTreeNode("rootNode");
        javax.swing.tree.DefaultMutableTreeNode haltNoop = new javax.swing.tree.DefaultMutableTreeNode("<html><b>Halt and no operation</b></html>");
        javax.swing.tree.DefaultMutableTreeNode jumps = new javax.swing.tree.DefaultMutableTreeNode("<html><b>Jumps</b></html>");
        javax.swing.tree.DefaultMutableTreeNode dataMovement = new javax.swing.tree.DefaultMutableTreeNode("<html><b>Data movement</b></html>");
        javax.swing.tree.DefaultMutableTreeNode inputOutput = new javax.swing.tree.DefaultMutableTreeNode("<html><b>Input/output</b></html>");
        javax.swing.tree.DefaultMutableTreeNode digitwiseManipulation = new javax.swing.tree.DefaultMutableTreeNode("<html><b>Digitwise manipulation</b></html>");
        javax.swing.tree.DefaultMutableTreeNode digitShifts = new javax.swing.tree.DefaultMutableTreeNode("<html><b>Digit shifts</b></html>");
        javax.swing.tree.DefaultMutableTreeNode arithmetic = new javax.swing.tree.DefaultMutableTreeNode("<html><b>Arithmetic</b></html>");
        List<DefaultMutableTreeNode> topNodes = new ArrayList<>();
        topNodes.add(haltNoop);
        topNodes.add(jumps);
        topNodes.add(dataMovement);
        topNodes.add(inputOutput);
        topNodes.add(digitwiseManipulation);
        topNodes.add(digitShifts);
        topNodes.add(arithmetic);

        for (DefaultMutableTreeNode defaultMutableTreeNode : topNodes) {
            rootNode.add(defaultMutableTreeNode);
        }

        List<Word> lowerLimitWords = new ArrayList<>();
        lowerLimitWords.add(Word.valueOfLastDigitsOfInteger(10));
        lowerLimitWords.add(Word.valueOfLastDigitsOfInteger(20));
        lowerLimitWords.add(Word.valueOfLastDigitsOfInteger(30));
        lowerLimitWords.add(Word.valueOfLastDigitsOfInteger(40));
        lowerLimitWords.add(Word.valueOfLastDigitsOfInteger(60));
        lowerLimitWords.add(Word.valueOfLastDigitsOfInteger(70));
        lowerLimitWords.add(Word.valueOfLastDigitsOfInteger(99));

        int categoryIndex = 0;
        Word lowerLimitOfNextCategory = lowerLimitWords.get(categoryIndex);
        DefaultMutableTreeNode currentTopNode = topNodes.get(categoryIndex);
        for (InstructionFromSet instructionFromSet : InstructionFromSet.values()) {
            if (instructionFromSet.getOpcode().compareToUnsigned(lowerLimitOfNextCategory) != -1) {
                categoryIndex++;
                lowerLimitOfNextCategory = lowerLimitWords.get(categoryIndex);
                currentTopNode = topNodes.get(categoryIndex);
            }
            currentTopNode.add(makeNode(instructionFromSet));
        }

        instructionDescriptionTree.setModel(new javax.swing.tree.DefaultTreeModel(rootNode));
    }

    DefaultMutableTreeNode makeNode(InstructionFromSet instructionFromSet) {
        DefaultMutableTreeNode instructionNode = new DefaultMutableTreeNode(instructionFromSet.getDescription());
        DefaultMutableTreeNode detailsNode = new DefaultMutableTreeNode(instructionFromSet.getDetails());
        instructionNode.add(detailsNode);
        return instructionNode;
    }

    public Memory getMemory() {
        Memory memory = new Memory();
        Iterator<Word> wordIterator = Word.getIterator();
        while (wordIterator.hasNext()) {
            final Word word = wordIterator.next();
            memory.setMemoryAtAddress(word, memorySlotChoosers.get(word.getValue()).getSelectedWord());
        }
        return memory;
    }

    public void setMemory(Memory memory) {
        Iterator<Word> wordIterator = Word.getIterator();
        while (wordIterator.hasNext()) {
            final Word word = wordIterator.next();
            MemorySlotChooser memorySlotChooser = memorySlotChoosers.get(word.getValue());
            memorySlotChooser.setSelectedWord(memory.getMemory(word).getWord());
        }
    }

    public void setSaveAction(ActionListener actionListener) {
        saveButton.addActionListener(actionListener);
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

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scrollPanePanel = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        instructionDescriptionTree = new javax.swing.JTree();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout scrollPanePanelLayout = new javax.swing.GroupLayout(scrollPanePanel);
        scrollPanePanel.setLayout(scrollPanePanelLayout);
        scrollPanePanelLayout.setHorizontalGroup(
            scrollPanePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );
        scrollPanePanelLayout.setVerticalGroup(
            scrollPanePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(scrollPanePanel);

        saveButton.setText("Save");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("JTree");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("<html><b>halt and no operation</b></html>");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("blue");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("violet");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("red");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("yellow");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("sports");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("basketball");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("soccer");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("football");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hockey");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("food");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hot dogs");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("pizza");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("ravioli");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("bananas");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        instructionDescriptionTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        instructionDescriptionTree.setRootVisible(false);
        instructionDescriptionTree.setShowsRootHandles(true);
        jScrollPane2.setViewportView(instructionDescriptionTree);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MemoryEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemoryEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemoryEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemoryEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemoryEditor().setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree instructionDescriptionTree;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel scrollPanePanel;
    // End of variables declaration//GEN-END:variables
}
