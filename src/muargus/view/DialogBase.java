package muargus.view;

import argus.model.ArgusException;
import argus.utils.SystemUtils;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import muargus.MuARGUS;
import muargus.model.MetadataMu;

/**
 *
 * @author Statistics Netherlands
 * @param <T>
 */
public class DialogBase<T> extends javax.swing.JDialog {

    private MetadataMu metadata;
    private final T controller;
    
    /**
     * Creates new form DialogBase
     * @param parent the Frame of the mainFrame.
     * @param modal boolean to set the modal status
     * @param controller the controller of the view.
     */
    public DialogBase(java.awt.Frame parent, boolean modal, T controller) {
        super(parent, modal);
        this.controller = controller;
        initComponents();
        setHelpAction();
    }
    
    /**
     * 
     * @param metadata 
     */
    public void setMetadata(MetadataMu metadata) {
        this.metadata = metadata;
        initializeData();
    }

    /**
     * 
     * @return 
     */
    protected MetadataMu getMetadata() {
        return this.metadata;
    }
    
    /**
     * 
     */
    protected void initializeData() {
        //Base class implementation is empty
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
    
    /**
     * 
     * @param ex 
     */
    public void showErrorMessage(ArgusException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), MuARGUS.getMessageTitle(), JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * 
     * @param message
     * @return 
     */
    public boolean showConfirmDialog(String message) {
        return (JOptionPane.showConfirmDialog(null, message, MuARGUS.getMessageTitle(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
    
    /**
     * 
     * @param message 
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, MuARGUS.getMessageTitle(), JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * 
     * @param title
     * @param forSaving
     * @param filter
     * @return 
     */
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
    
    /**
     * 
     * @param filter
     * @return 
     */
    private String[] splitFilter(String filter) {
        return filter.split("\\|");
    }
    
    private void setHelpAction() {
        
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHelp();
            }
        };
        this.rootPane.getActionMap().put("f1action", action);
        this.rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
                KeyStroke.getKeyStroke("F1"), "f1action");
    }

    protected String getHelpNamedDestination() {
        return ContextHelp.fromClassName(this.getClass().getName());
    }
    
    private void showHelp() {
        MuARGUS.showHelp(getHelpNamedDestination());
    }
    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DialogBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DialogBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DialogBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DialogBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                DialogBase dialog = new DialogBase(new javax.swing.JFrame(), true, null);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    /**
     * 
     * @param stepName 
     */
    public void showStepName(String stepName) {
        //Base class implementation is empty
    }

    /**
     * 
     * @param progress 
     */
    public void setProgress(int progress) {
        //Base class implementation is empty
    }

    /**
     * 
     * @return 
     */
    protected T getController() {
        return this.controller;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
