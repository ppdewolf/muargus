package muargus.unusedClasses;

import javax.swing.JOptionPane;
import muargus.model.Combinations;
import muargus.view.DialogBase;

/**
 *
 * @author Statistics Netherlands
 */
public class ArgusInput extends DialogBase {

    private final boolean isThreshold;
    private boolean okButtonPressed = false;
    private int previousThreshold = 1;

    /**
     * Creates new form ArgusInput
     *
     * @param parent the Frame of the mainFrame.
     * @param modal boolean to set the modal status
     * @param model
     * @param isThreshold
     */
    public ArgusInput(java.awt.Frame parent, boolean modal, Combinations model, boolean isThreshold) {
        super(parent, modal, null);
        this.isThreshold = isThreshold;
        initComponents();
        this.textField.setText(Integer.toString(model.getThreshold()));
        this.setLocationRelativeTo(null);
    }

    /**
     *
     * @param text
     */
    public void setLabelText(String text) {
        this.label.setText(text);
    }

    /**
     *
     * @return
     */
    public String getTextField() {
        return textField.getText();
    }

    /**
     *
     * @param text
     */
    public void setTextField(String text) {
        this.textField.setText(text);
    }

    /**
     *
     * @param previousThreshold
     */
    public void setPreviousThreshold(int previousThreshold) {
        this.previousThreshold = previousThreshold;
    }

    /**
     *
     * @return
     */
    public boolean isOkButtonPressed() {
        return okButtonPressed;
    }

    /**
     *
     * @return
     */
    public boolean isThresholdValid() {
        boolean valid = true;

        try {
            int threshold = Integer.parseInt(this.textField.getText());
            if (threshold <= 0) {
                JOptionPane.showMessageDialog(this, "Illegal value for the threshold, threshold cannot be smaller than 1");
                valid = false;
            } else if (this.previousThreshold > threshold) {
                JOptionPane.showMessageDialog(this, "The threshold needs to be equal to or larger than " + this.previousThreshold);
                valid = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Illegal value for the threshold, please enter a positive integer");
            valid = false;
        }
        return valid;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        textField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Input");

        label.setText("label");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textField)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label)
                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (this.isThreshold) {
            if (this.isThresholdValid()) {
                this.okButtonPressed = true;
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel label;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}