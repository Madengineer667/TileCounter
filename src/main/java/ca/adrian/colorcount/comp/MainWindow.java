/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.adrian.colorcount.comp;

import ca.adrian.colorcount.entity.Tile;
import ca.adrian.util.GuiUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author bcainne
 */
public class MainWindow extends JFrame {
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private CountPanel countPanel = new CountPanel();
    private ImagePanel imagePanel = new ImagePanel();
    
    
    public MainWindow() {
        initComponents();
    }
    
    private void initComponents() {
        setSize(new Dimension(1024, 870));
        
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenu1.add(jSeparator2);

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem4.setText("Quit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);
        
        imagePanel = new ImagePanel();
        countPanel = new CountPanel();
        this.add(imagePanel, BorderLayout.CENTER );
        this.add(countPanel, BorderLayout.EAST);
    }
    
   private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        File imageFile = GuiUtils.loadFileChoose(new JDialog(), "png", "Load tile image (*.png)");        
        if (imageFile.exists()) {
            countPanel.setCounts(new ArrayList<Tile>());
            imagePanel.load(imageFile, countPanel);
        }
        this.repaint();
    }                                          

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        System.exit(0);
    }                                                  
}
