/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muargus.view;

import javax.swing.JFileChooser;
import muargus.controller.OpenMicrodataController;
import muargus.model.OpenMicrodataModel;
/**
 *
 * @author ambargus
 */
public class OpenMicrodataView extends javax.swing.JDialog {

    OpenMicrodataController controller;
    /**
     * Creates new form OpenMicrodataView
     */
    public OpenMicrodataView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        controller = new OpenMicrodataController(this);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        microdataLabel = new javax.swing.JLabel();
        microdataTextField = new javax.swing.JTextField();
        microdataButton = new javax.swing.JButton();
        metadataLabel = new javax.swing.JLabel();
        metadataTextField = new javax.swing.JTextField();
        metadataButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        instructionPanel = new javax.swing.JPanel();
        instructionLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();

        fileChooser.setCurrentDirectory(new java.io.File("C:\\Program Files\\MU_ARGUS\\data"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Open Microdata");
        setMinimumSize(new java.awt.Dimension(489, 280));

        microdataLabel.setText("Microdata");

        microdataTextField.setText(OpenMicrodataModel.getMicrodataPath());

        microdataButton.setText("...");
        microdataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                microdataButtonActionPerformed(evt);
            }
        });

        metadataLabel.setText("Metadata (optional)");

        metadataTextField.setText(OpenMicrodataModel.getMetadataPath());

        metadataButton.setText("...");
        metadataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metadataButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        instructionPanel.setBackground(new java.awt.Color(230, 230, 230));
        instructionPanel.setMinimumSize(new java.awt.Dimension(336, 50));

        instructionLabel.setForeground(new java.awt.Color(255, 0, 0));
        instructionLabel.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        instructionLabel.setMinimumSize(new java.awt.Dimension(95, 80));
        instructionLabel.setName(""); // NOI18N
        instructionLabel.setVisible(false);

        javax.swing.GroupLayout instructionPanelLayout = new javax.swing.GroupLayout(instructionPanel);
        instructionPanel.setLayout(instructionPanelLayout);
        instructionPanelLayout.setHorizontalGroup(
            instructionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instructionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instructionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        instructionPanelLayout.setVerticalGroup(
            instructionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instructionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instructionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(metadataTextField)
                            .addComponent(microdataTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(microdataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(metadataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11))
                    .addComponent(instructionPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(microdataLabel)
                            .addComponent(metadataLabel))
                        .addGap(328, 384, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(microdataLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(microdataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(microdataButton))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(metadataLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(metadataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metadataButton))
                        .addGap(18, 18, 18)
                        .addComponent(instructionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 81, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelButton)
                            .addComponent(clearButton)
                            .addComponent(okButton))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        controller.clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        controller.finish();
    }//GEN-LAST:event_okButtonActionPerformed

    private void metadataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metadataButtonActionPerformed
        controller.chooseMetadataFile();
    }//GEN-LAST:event_metadataButtonActionPerformed

    private void microdataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_microdataButtonActionPerformed
        controller.chooseMicrodataFile();
    }//GEN-LAST:event_microdataButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        controller.cancel();
    }//GEN-LAST:event_cancelButtonActionPerformed

    
    /**
     * 
     * @param text 
     */
    public void setMicrodataText(String text){
        microdataTextField.setText(text);
    }
    
    /**
     * 
     * @return 
     */
    public String getMicrodataText(){
        return microdataTextField.getText();
    }
    
    /**
     * 
     * @param text 
     */
    public void setMetadataText(String text){
        metadataTextField.setText(text);
    }
    
    /**
     * 
     * @return 
     */
    public String getMetadataText(){
        return metadataTextField.getText();
    }
    
    public JFileChooser getFileChooser(){
        return fileChooser;
    }
      
    /**
     * 
     * @param b 
     */
    public void setInstructionVisible(boolean b){
        instructionLabel.setVisible(b);
    }
    
    /** Main method for testing. 
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
            java.util.logging.Logger.getLogger(OpenMicrodataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpenMicrodataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpenMicrodataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpenMicrodataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OpenMicrodataView view = new OpenMicrodataView(new javax.swing.JFrame(), true);
                view.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                view.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel instructionLabel;
    private javax.swing.JPanel instructionPanel;
    private javax.swing.JButton metadataButton;
    private javax.swing.JLabel metadataLabel;
    private javax.swing.JTextField metadataTextField;
    private javax.swing.JButton microdataButton;
    private javax.swing.JLabel microdataLabel;
    private javax.swing.JTextField microdataTextField;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
