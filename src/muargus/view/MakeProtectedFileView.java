package muargus.view;

import java.awt.Color;
import java.io.File;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import muargus.controller.MakeProtectedFileController;
import muargus.model.ProtectedFile;
import muargus.model.MetadataMu;

/**
 * View class of the MakeProtectedFile screen.
 *
 * @author Statistics Netherlands
 */
public class MakeProtectedFileView extends DialogBase<MakeProtectedFileController> {

    ProtectedFile model;
    private TableModel tableModel;
    private int selectedRow;

    /**
     * Creates new form MakeProtectedFileView.
     *
     * @param parent the Frame of the mainFrame.
     * @param modal boolean to set the modal status
     * @param controller the controller of this view.
     */
    public MakeProtectedFileView(java.awt.Frame parent, boolean modal, MakeProtectedFileController controller) {
        super(parent, modal, controller);
        initComponents();
        setLocationRelativeTo(null);
        this.suppressionWeightTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Initializes the data. Enables/disables elements, sets the table values
     * and sets the selected row to zero.
     */
    @Override
    public void initializeData() {
        this.model = getMetadata().getCombinations().getProtectedFile();
        this.getSuppressionRadioButton().setSelected(true);
        this.model.setHouseholdData(getMetadata().isHouseholdData());
        this.hhIdentifierPanel.setEnabled(this.model.isHouseholdData());
        this.keepInSafeFileRadioButton.setEnabled(this.model.isHouseholdData());
        this.changeIntoSequenceNumberRadioButton.setEnabled(this.model.isHouseholdData());
        this.removeFromSafeFileRadioButton.setEnabled(this.model.isHouseholdData());
        this.addRiskToOutputFileCheckBox.setVisible(this.model.isRiskModel());
        this.writeRecordRandomOrderCheckBox.setEnabled(getMetadata().getDataFileType() == MetadataMu.DATA_FILE_TYPE_FIXED);
        this.selectedRow = 0;
        this.suppressionWeightTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    handleSelectionChanged();
                }
            }
        });

        this.tableModel = new DefaultTableModel(this.model.getData(), this.model.getColumnames()) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        this.suppressionWeightTable.setModel(this.tableModel);
        this.suppressionWeightTable.getSelectionModel().setSelectionInterval(this.selectedRow, this.selectedRow);
        this.suppressionWeightSlider.setValue(Integer.parseInt(this.model.getData()[this.selectedRow][1]));

        enableSuppressionTable();
    }

    /**
     * Event handler for when the selection changes.
     */
    private void handleSelectionChanged() {
        this.selectedRow = this.suppressionWeightTable.getSelectedRow();
        this.suppressionWeightSlider.setValue(Integer.parseInt(this.model.getData()[this.selectedRow][1]));
    }

    /**
     * Enables/disables the suppressionTable, slider and label and changes the
     * backgroundcolor.
     */
    private void enableSuppressionTable() {
        boolean suppression = this.useWeightRadioButton.isSelected();
        this.suppressionWeightPerVariableLabel.setEnabled(suppression);
        this.suppressionWeightScrollPane.setEnabled(suppression);
        this.suppressionWeightSlider.setEnabled(suppression);
        this.suppressionWeightTable.setEnabled(suppression);

        // different color when table is selected or not
        if (suppression) {
            this.suppressionWeightTable.setBackground(Color.white);
        } else {
            this.suppressionWeightTable.setBackground(new Color(240, 240, 240));
        }
    }

    /**
     * Sets the progress bar's current value to the give value.
     *
     * @param progress Integer containing the value of the progress.
     */
    @Override
    public void setProgress(int progress) {
        this.progressbar.setValue(progress);
    }

    /**
     * Gets the radioButton belonging to the suppressiontype.
     *
     * @return JRadioButton belonging tot the suppressiontype.
     */
    private JRadioButton getSuppressionRadioButton() {
        switch (this.model.getSuppressionType()) {
            case (0):
                return this.noSuppressionRadioButton;
            case (1):
                return this.useWeightRadioButton;
            case (2):
                return this.useEntropyRadioButton;
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        suppressionButtonGroup = new javax.swing.ButtonGroup();
        hhIdentifierButtonGroup = new javax.swing.ButtonGroup();
        suppressionPanel = new javax.swing.JPanel();
        noSuppressionRadioButton = new javax.swing.JRadioButton();
        useWeightRadioButton = new javax.swing.JRadioButton();
        useEntropyRadioButton = new javax.swing.JRadioButton();
        separator = new javax.swing.JSeparator();
        suppressionWeightPerVariableLabel = new javax.swing.JLabel();
        suppressionWeightScrollPane = new javax.swing.JScrollPane();
        suppressionWeightTable = new javax.swing.JTable();
        suppressionWeightSlider = new javax.swing.JSlider();
        hhIdentifierPanel = new javax.swing.JPanel();
        keepInSafeFileRadioButton = new javax.swing.JRadioButton();
        changeIntoSequenceNumberRadioButton = new javax.swing.JRadioButton();
        removeFromSafeFileRadioButton = new javax.swing.JRadioButton();
        writeRecordRandomOrderCheckBox = new javax.swing.JCheckBox();
        progressbar = new javax.swing.JProgressBar();
        cancelButton = new javax.swing.JButton();
        makeFileButton = new javax.swing.JButton();
        addRiskToOutputFileCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Make Protected File");
        setResizable(false);

        suppressionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Suppression"));

        suppressionButtonGroup.add(noSuppressionRadioButton);
        noSuppressionRadioButton.setText("No suppression");
        noSuppressionRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                noSuppressionRadioButtonItemStateChanged(evt);
            }
        });

        suppressionButtonGroup.add(useWeightRadioButton);
        useWeightRadioButton.setText("Use weights");
        useWeightRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                useWeightRadioButtonItemStateChanged(evt);
            }
        });

        suppressionButtonGroup.add(useEntropyRadioButton);
        useEntropyRadioButton.setSelected(true);
        useEntropyRadioButton.setText("Use entropy");
        useEntropyRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                useEntropyRadioButtonItemStateChanged(evt);
            }
        });

        suppressionWeightPerVariableLabel.setText("Suppression weight per variable");

        suppressionWeightTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Variables", "Priority"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        suppressionWeightScrollPane.setViewportView(suppressionWeightTable);

        suppressionWeightSlider.setMajorTickSpacing(100);
        suppressionWeightSlider.setMinorTickSpacing(5);
        suppressionWeightSlider.setPaintTicks(true);
        suppressionWeightSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                suppressionWeightSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout suppressionPanelLayout = new javax.swing.GroupLayout(suppressionPanel);
        suppressionPanel.setLayout(suppressionPanelLayout);
        suppressionPanelLayout.setHorizontalGroup(
            suppressionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separator)
            .addGroup(suppressionPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(suppressionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(noSuppressionRadioButton, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(useWeightRadioButton, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(useEntropyRadioButton, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(suppressionWeightPerVariableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, suppressionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(suppressionWeightScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(suppressionWeightSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        suppressionPanelLayout.setVerticalGroup(
            suppressionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suppressionPanelLayout.createSequentialGroup()
                .addComponent(noSuppressionRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(useWeightRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(useEntropyRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(suppressionWeightPerVariableLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(suppressionWeightScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(suppressionWeightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        hhIdentifierPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("HH Identifier"));

        hhIdentifierButtonGroup.add(keepInSafeFileRadioButton);
        keepInSafeFileRadioButton.setSelected(true);
        keepInSafeFileRadioButton.setText("Keep in safe file");
        keepInSafeFileRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                keepInSafeFileRadioButtonItemStateChanged(evt);
            }
        });

        hhIdentifierButtonGroup.add(changeIntoSequenceNumberRadioButton);
        changeIntoSequenceNumberRadioButton.setText("Change into sequence number");
        changeIntoSequenceNumberRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                changeIntoSequenceNumberRadioButtonItemStateChanged(evt);
            }
        });

        hhIdentifierButtonGroup.add(removeFromSafeFileRadioButton);
        removeFromSafeFileRadioButton.setText("Remove from safe file");
        removeFromSafeFileRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                removeFromSafeFileRadioButtonItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout hhIdentifierPanelLayout = new javax.swing.GroupLayout(hhIdentifierPanel);
        hhIdentifierPanel.setLayout(hhIdentifierPanelLayout);
        hhIdentifierPanelLayout.setHorizontalGroup(
            hhIdentifierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hhIdentifierPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hhIdentifierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keepInSafeFileRadioButton)
                    .addComponent(changeIntoSequenceNumberRadioButton)
                    .addComponent(removeFromSafeFileRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        hhIdentifierPanelLayout.setVerticalGroup(
            hhIdentifierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hhIdentifierPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(keepInSafeFileRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeIntoSequenceNumberRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeFromSafeFileRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        writeRecordRandomOrderCheckBox.setText("Write records in random order");
        writeRecordRandomOrderCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                writeRecordRandomOrderCheckBoxItemStateChanged(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        makeFileButton.setText("Make file");
        makeFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeFileButtonActionPerformed(evt);
            }
        });

        addRiskToOutputFileCheckBox.setText("Add Risk to Output File ");
        addRiskToOutputFileCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addRiskToOutputFileCheckBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(suppressionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(writeRecordRandomOrderCheckBox)
                    .addComponent(hhIdentifierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(cancelButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(makeFileButton))
                        .addComponent(progressbar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addRiskToOutputFileCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(suppressionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hhIdentifierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(writeRecordRandomOrderCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addRiskToOutputFileCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(progressbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelButton)
                            .addComponent(makeFileButton))
                        .addGap(17, 17, 17))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void makeFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeFileButtonActionPerformed
        String filePath = showFileDialog("Make safe micro file", true, new String[]{"Safefile (*.saf)|saf"});
        if (filePath != null) {
            getController().makeFile(new File(filePath));
        }
    }//GEN-LAST:event_makeFileButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void suppressionWeightSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_suppressionWeightSliderStateChanged
        this.model.setPriority(this.selectedRow, this.suppressionWeightSlider.getValue());
        this.suppressionWeightTable.setValueAt(this.suppressionWeightSlider.getValue(), this.selectedRow, 1);
    }//GEN-LAST:event_suppressionWeightSliderStateChanged

    private void useEntropyRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_useEntropyRadioButtonItemStateChanged
        if (this.useEntropyRadioButton.isSelected()) {
            this.model.setSuppressionType(ProtectedFile.USE_ENTROPY);
            enableSuppressionTable();
        }
    }//GEN-LAST:event_useEntropyRadioButtonItemStateChanged

    private void useWeightRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_useWeightRadioButtonItemStateChanged
        if (this.useWeightRadioButton.isSelected()) {
            this.model.setSuppressionType(ProtectedFile.USE_WEIGHT);
            enableSuppressionTable();
        }
    }//GEN-LAST:event_useWeightRadioButtonItemStateChanged

    private void noSuppressionRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_noSuppressionRadioButtonItemStateChanged
        if (this.noSuppressionRadioButton.isSelected()) {
            this.model.setSuppressionType(ProtectedFile.NO_SUPPRESSION);
            enableSuppressionTable();
        }
    }//GEN-LAST:event_noSuppressionRadioButtonItemStateChanged

    private void keepInSafeFileRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_keepInSafeFileRadioButtonItemStateChanged
        if (this.keepInSafeFileRadioButton.isSelected()) {
            this.model.setHouseholdType(ProtectedFile.KEEP_IN_SAFE_FILE);
        }
    }//GEN-LAST:event_keepInSafeFileRadioButtonItemStateChanged

    private void changeIntoSequenceNumberRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_changeIntoSequenceNumberRadioButtonItemStateChanged
        if (this.changeIntoSequenceNumberRadioButton.isSelected()) {
            this.model.setHouseholdType(ProtectedFile.CHANGE_INTO_SEQUENCE_NUMBER);
        }
    }//GEN-LAST:event_changeIntoSequenceNumberRadioButtonItemStateChanged

    private void removeFromSafeFileRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_removeFromSafeFileRadioButtonItemStateChanged
        if (this.removeFromSafeFileRadioButton.isSelected()) {
            this.model.setHouseholdType(ProtectedFile.REMOVE_FROM_SAFE_FILE);
        }
    }//GEN-LAST:event_removeFromSafeFileRadioButtonItemStateChanged

    private void addRiskToOutputFileCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addRiskToOutputFileCheckBoxItemStateChanged
        this.model.setPrintBHR(this.addRiskToOutputFileCheckBox.isSelected());
        if (this.addRiskToOutputFileCheckBox.isSelected()) {
            this.writeRecordRandomOrderCheckBox.setSelected(false);
        }
        this.writeRecordRandomOrderCheckBox.setEnabled(!this.addRiskToOutputFileCheckBox.isSelected());
    }//GEN-LAST:event_addRiskToOutputFileCheckBoxItemStateChanged

    private void writeRecordRandomOrderCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_writeRecordRandomOrderCheckBoxItemStateChanged
        this.model.setRandomizeOutput(this.writeRecordRandomOrderCheckBox.isSelected());
    }//GEN-LAST:event_writeRecordRandomOrderCheckBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox addRiskToOutputFileCheckBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton changeIntoSequenceNumberRadioButton;
    private javax.swing.ButtonGroup hhIdentifierButtonGroup;
    private javax.swing.JPanel hhIdentifierPanel;
    private javax.swing.JRadioButton keepInSafeFileRadioButton;
    private javax.swing.JButton makeFileButton;
    private javax.swing.JRadioButton noSuppressionRadioButton;
    private javax.swing.JProgressBar progressbar;
    private javax.swing.JRadioButton removeFromSafeFileRadioButton;
    private javax.swing.JSeparator separator;
    private javax.swing.ButtonGroup suppressionButtonGroup;
    private javax.swing.JPanel suppressionPanel;
    private javax.swing.JLabel suppressionWeightPerVariableLabel;
    private javax.swing.JScrollPane suppressionWeightScrollPane;
    private javax.swing.JSlider suppressionWeightSlider;
    private javax.swing.JTable suppressionWeightTable;
    private javax.swing.JRadioButton useEntropyRadioButton;
    private javax.swing.JRadioButton useWeightRadioButton;
    private javax.swing.JCheckBox writeRecordRandomOrderCheckBox;
    // End of variables declaration//GEN-END:variables
}
