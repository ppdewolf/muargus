/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package muargus.view;

import argus.model.ArgusException;
import argus.utils.SystemUtils;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import muargus.MuARGUS;

/**
 *
 * @author pibd05
 */
public class DialogBase extends javax.swing.JDialog {

    /**
     * Creates new form DialogBase
     */
    public DialogBase(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, MuARGUS.getMessageTitle(), JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void showErrorMessage(ArgusException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), MuARGUS.getMessageTitle(), JOptionPane.ERROR_MESSAGE);
    }
    
    public boolean showConfirmDialog(String message) {
        return (JOptionPane.showConfirmDialog(null, message, MuARGUS.getMessageTitle(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
    
    public String showFileDialog(String title, boolean forSaving, String[] filter) {
        JFileChooser fileChooser = new JFileChooser();
        String hs = SystemUtils.getRegString("general", "datadir", "");
        if (!hs.equals("")){
            File file = new File(hs); 
            fileChooser.setCurrentDirectory(file);
        }
        fileChooser.setDialogTitle(title);
        fileChooser.resetChoosableFileFilters();
        String[] firstFilter = splitFilter(filter[0]);
        fileChooser.setFileFilter(new FileNameExtensionFilter(firstFilter[0], firstFilter[1]));
        for (int index=1; index < filter.length; index++) {
            String[] otherFilter = splitFilter(filter[index]);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(otherFilter[0], otherFilter[1]));
        }
        int result = forSaving ? fileChooser.showSaveDialog(this) : fileChooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        hs = fileChooser.getSelectedFile().getParent();
        if (!"".equals(hs)){
            SystemUtils.putRegString("general", "datadir", hs);
        }
        return fileChooser.getSelectedFile().getPath();
    } 
    
    private String[] splitFilter(String filter) {
        return filter.split("\\|");
    }
    
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
            java.util.logging.Logger.getLogger(DialogBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogBase dialog = new DialogBase(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
