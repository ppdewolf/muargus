package muargus.view;

/**
 *
 * @author Statistics Netherlands
 */
public class AboutView extends DialogBase {

    /**
     * Creates new form HelpAbout
     *
     * @param parent the Frame of the mainFrame.
     * @param modal boolean to set the modal status
     */
    public AboutView(java.awt.Frame parent, boolean modal) {
        super(parent, modal, null);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        muArguslabel = new javax.swing.JLabel();
        versionLabel = new javax.swing.JLabel();
        buildLabel = new javax.swing.JLabel();
        sdcLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        copyrightLabel = new javax.swing.JLabel();
        creditsLabel = new javax.swing.JLabel();
        legalPane = new javax.swing.JPanel();
        legalLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        systemInfoButton = new javax.swing.JButton();
        iconLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About");

        muArguslabel.setText("<html><P STYLE=\"font-size: 40pt;color: red\"> µ-ARGUS");

        versionLabel.setText("Version 4.2.0");

        buildLabel.setText("build: 1");

        sdcLabel.setText("Statistical Disclosure Control of microdata");

        copyrightLabel.setText("Copyright: Statistics Netherlands (2002)");

        creditsLabel.setText("<html>\nThis software has been developed as<br>\npart of the CASC-project, partly<br>\nsubsidised by the EU under grant no:<br>\nIST-2000-25069");

        legalPane.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        legalLabel.setText("<html>\nThe Statistics Netherlands and its CASC Partners provide this software \"as is\", without<br>\nany warranty, expressed or implied, or assume any legal liability or responsibility for the<br>\naccuracy, completeness, or usefulness of this software. The Statistics Netherlands and<br>\nits CASC Partners will provide at their discretion only limited consultation on problems<br>\nencountered with this software. Improvements or changes to this software may be made<br>\nat any time withouth an obligation to inform users.");

        javax.swing.GroupLayout legalPaneLayout = new javax.swing.GroupLayout(legalPane);
        legalPane.setLayout(legalPaneLayout);
        legalPaneLayout.setHorizontalGroup(
            legalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(legalPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(legalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        legalPaneLayout.setVerticalGroup(
            legalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(legalPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(legalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        systemInfoButton.setText("Systen Info...");

        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/mu.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(iconLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sdcLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(versionLabel)
                                .addGap(37, 37, 37)
                                .addComponent(buildLabel))
                            .addComponent(muArguslabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(creditsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(copyrightLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(systemInfoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separator)
                            .addComponent(legalPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(muArguslabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconLabel))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(versionLabel)
                    .addComponent(buildLabel))
                .addGap(18, 18, 18)
                .addComponent(sdcLabel)
                .addGap(18, 18, 18)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(copyrightLabel)
                    .addComponent(okButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(systemInfoButton)
                    .addComponent(creditsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(legalPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buildLabel;
    private javax.swing.JLabel copyrightLabel;
    private javax.swing.JLabel creditsLabel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel legalLabel;
    private javax.swing.JPanel legalPane;
    private javax.swing.JLabel muArguslabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel sdcLabel;
    private javax.swing.JSeparator separator;
    private javax.swing.JButton systemInfoButton;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables
}
