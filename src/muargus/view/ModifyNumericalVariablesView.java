/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muargus.view;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import muargus.controller.ModifyNumericalVariablesController;
import muargus.model.MetadataMu;
import muargus.model.ModifyNumericalVariables;
import muargus.model.ModifyNumericalVariablesSpec;

/**
 *
 * @author ambargus
 */
public class ModifyNumericalVariablesView extends DialogBase {

    MetadataMu metadataMu;
    ModifyNumericalVariablesController controller;
    ModifyNumericalVariables model;
    private final int[] variablesColumnWidth = {20, 80};
    private int selectedRow = 0;

    /**
     *
     * @param parent
     * @param modal
     * @param controller
     */
    public ModifyNumericalVariablesView(java.awt.Frame parent, boolean modal, ModifyNumericalVariablesController controller) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.controller = controller;
        this.variablesTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void setMetadataMu(MetadataMu metadataMu) {
        this.metadataMu = metadataMu;
        this.model = this.metadataMu.getCombinations().getModifyNumericalVariables();
        this.controller.setModel(this.model);
        initializeData();
    }

    public void initializeData() {
        this.controller.setModifyNumericalVariablesSpecs();
        makeVariablesTable();
        updateValues();
    }

    public void updateValues() {
        double[] min_max = this.controller.getMinMax(
                this.model.getModifyNumericalVariablesSpec().get(this.variablesTable.getSelectedRow()).getVariable());

        ModifyNumericalVariablesSpec selected = this.model.getModifyNumericalVariablesSpec().get(this.selectedRow);
        selected.setMin_max(min_max);
        this.minimumTextField.setText(this.controller.getMin(selected));
        this.maximumTextField.setText(this.controller.getMax(selected));
        this.weightNoisePanel.setEnabled(selected.getVariable().isWeight());
        this.percentageLabel.setEnabled(selected.getVariable().isWeight());
        this.percentageTextField.setEnabled(selected.getVariable().isWeight());

        if (selected.isModified()) {
            System.out.println("modified");
            this.bottomValueTextField.setText(this.controller.getBottomValue(selected));
            this.bottomCodingReplacementTextField.setText(selected.getBottomReplacement());
            this.topValueTextField.setText(this.controller.getTopValue(selected));
            this.topCodingReplacementTextField.setText(selected.getTopReplacement());
            this.roundingBaseTextField.setText(this.controller.getRoundingBase(selected));
            this.percentageTextField.setText(this.controller.getWeightNoisePercentage(selected));
        }
    }

    /**
     *
     */
    public void makeVariablesTable() {
        this.controller.setVariablesData();

        TableModel variablesTableModel = new DefaultTableModel(this.model.getVariablesData(), this.model.getVariablesColumnNames()) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        this.variablesTable.setModel(variablesTableModel);

        for (int i = 0; i < this.variablesColumnWidth.length; i++) {
            this.variablesTable.getColumnModel().getColumn(i).setMinWidth(this.variablesColumnWidth[i]);
            this.variablesTable.getColumnModel().getColumn(i).setPreferredWidth(this.variablesColumnWidth[i]);
        }

        this.variablesTable.getSelectionModel().setSelectionInterval(0, 0);
        this.variablesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    variablesSelectionChanged();
                }
            }
        });
    }

    public void variablesSelectionChanged() {
        //int newSelectedRow = this.variablesTable.getSelectedRow();
        updateValues();
        if (valueEntered()) {
            ModifyNumericalVariablesSpec selected = this.model.getModifyNumericalVariablesSpec().get(this.selectedRow);
            String message = this.controller.setValues(selected, this.bottomValueTextField.getText(), this.topValueTextField.getText(),
                    this.bottomCodingReplacementTextField.getText(), this.topCodingReplacementTextField.getText(),
                    this.roundingBaseTextField.getText(), this.percentageTextField.getText());
            if (!message.equals("")) {
                JOptionPane.showMessageDialog(null, message);
                this.variablesTable.getSelectionModel().setSelectionInterval(this.selectedRow, this.selectedRow);
            } else {
                setModified(true);
                this.selectedRow = this.variablesTable.getSelectedRow();
            }
        } else {
            setModified(false);
            this.selectedRow = this.variablesTable.getSelectedRow();
        }

    }

    public void setModified(boolean modified) {
        this.model.setModified(this.selectedRow, modified);
        String modifiedText = this.model.getModifiedText(this.selectedRow, this.model.isModified(this.selectedRow));
        this.variablesTable.setValueAt(modifiedText, this.selectedRow, 0);
    }

    public boolean valueEntered() {
        boolean valueEntered = false;
        if (!this.bottomValueTextField.getText().equals("")) {
            valueEntered = true;
        }
        if (!this.bottomCodingReplacementTextField.getText().equals("")) {
            valueEntered = true;
        }
        if (!this.topValueTextField.getText().equals("")) {
            valueEntered = true;
        }
        if (!this.topCodingReplacementTextField.getText().equals("")) {
            valueEntered = true;
        }
        if (!this.roundingBaseTextField.getText().equals("")) {
            valueEntered = true;
        }
        if (!this.percentageTextField.getText().equals("")) {
            valueEntered = true;
        }
        return valueEntered;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        variablesScrollPane = new javax.swing.JScrollPane();
        variablesTable = new javax.swing.JTable();
        bottomCodingPanel = new javax.swing.JPanel();
        minimumLabel = new javax.swing.JLabel();
        minimumTextField = new javax.swing.JTextField();
        bottomValueLabel = new javax.swing.JLabel();
        bottomValueTextField = new javax.swing.JTextField();
        bottomCodingReplacementLabel = new javax.swing.JLabel();
        bottomCodingReplacementTextField = new javax.swing.JTextField();
        topCodingPanel = new javax.swing.JPanel();
        maximumLabel = new javax.swing.JLabel();
        maximumTextField = new javax.swing.JTextField();
        topValueLabel = new javax.swing.JLabel();
        topValueTextField = new javax.swing.JTextField();
        topCodingReplacementLabel = new javax.swing.JLabel();
        topCodingReplacementTextField = new javax.swing.JTextField();
        roundPanel = new javax.swing.JPanel();
        roundingBaseLabel = new javax.swing.JLabel();
        roundingBaseTextField = new javax.swing.JTextField();
        weightNoisePanel = new javax.swing.JPanel();
        percentageLabel = new javax.swing.JLabel();
        percentageTextField = new javax.swing.JTextField();
        applyButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Numerical Variables");

        variablesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "M", "Variable"
            }
        ));
        variablesScrollPane.setViewportView(variablesTable);
        if (variablesTable.getColumnModel().getColumnCount() > 0) {
            variablesTable.getColumnModel().getColumn(0).setPreferredWidth(8);
        }

        bottomCodingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Bottom Coding"));

        minimumLabel.setText("Minimum:");

        minimumTextField.setEnabled(false);

        bottomValueLabel.setText("Bottom Value:");

        bottomCodingReplacementLabel.setText("Replacement");

        javax.swing.GroupLayout bottomCodingPanelLayout = new javax.swing.GroupLayout(bottomCodingPanel);
        bottomCodingPanel.setLayout(bottomCodingPanelLayout);
        bottomCodingPanelLayout.setHorizontalGroup(
            bottomCodingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomCodingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomCodingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bottomCodingReplacementLabel)
                    .addComponent(bottomValueLabel)
                    .addComponent(minimumLabel)
                    .addComponent(minimumTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(bottomValueTextField)
                    .addComponent(bottomCodingReplacementTextField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bottomCodingPanelLayout.setVerticalGroup(
            bottomCodingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomCodingPanelLayout.createSequentialGroup()
                .addComponent(minimumLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minimumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bottomValueLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bottomCodingReplacementLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomCodingReplacementTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        topCodingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Top Coding"));

        maximumLabel.setText("Maximum:");

        maximumTextField.setEnabled(false);

        topValueLabel.setText("Top Value:");

        topCodingReplacementLabel.setText("Replacement:");

        javax.swing.GroupLayout topCodingPanelLayout = new javax.swing.GroupLayout(topCodingPanel);
        topCodingPanel.setLayout(topCodingPanelLayout);
        topCodingPanelLayout.setHorizontalGroup(
            topCodingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topCodingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topCodingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topCodingPanelLayout.createSequentialGroup()
                        .addGroup(topCodingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(topValueLabel)
                            .addComponent(topCodingReplacementLabel)
                            .addComponent(maximumLabel))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(maximumTextField)
                    .addComponent(topValueTextField)
                    .addComponent(topCodingReplacementTextField))
                .addContainerGap())
        );
        topCodingPanelLayout.setVerticalGroup(
            topCodingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topCodingPanelLayout.createSequentialGroup()
                .addComponent(maximumLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maximumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(topValueLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(topCodingReplacementLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topCodingReplacementTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Round"));

        roundingBaseLabel.setText("Rounding base:");

        javax.swing.GroupLayout roundPanelLayout = new javax.swing.GroupLayout(roundPanel);
        roundPanel.setLayout(roundPanelLayout);
        roundPanelLayout.setHorizontalGroup(
            roundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundingBaseLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundingBaseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanelLayout.setVerticalGroup(
            roundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundingBaseLabel)
                    .addComponent(roundingBaseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        weightNoisePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Weight Noise"));

        percentageLabel.setText("Percentage:");

        javax.swing.GroupLayout weightNoisePanelLayout = new javax.swing.GroupLayout(weightNoisePanel);
        weightNoisePanel.setLayout(weightNoisePanelLayout);
        weightNoisePanelLayout.setHorizontalGroup(
            weightNoisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(weightNoisePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(percentageLabel)
                .addGap(27, 27, 27)
                .addComponent(percentageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        weightNoisePanelLayout.setVerticalGroup(
            weightNoisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(weightNoisePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(weightNoisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(percentageLabel)
                    .addComponent(percentageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(variablesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(applyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(roundPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(bottomCodingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(topCodingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(weightNoisePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bottomCodingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(topCodingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weightNoisePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(closeButton)
                            .addComponent(applyButton)))
                    .addComponent(variablesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.controller.close();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        variablesSelectionChanged();
//        ModifyNumericalVariablesSpec selected = this.model.getModifyNumericalVariablesSpec().get(this.previousSelected);
//        String message = this.controller.setValues(selected, this.bottomValueTextField.getText(), this.topValueTextField.getText(),
//                this.bottomCodingReplacementTextField.getText(), this.topCodingReplacementTextField.getText(),
//                this.roundingBaseTextField.getText(), this.percentageTextField.getText());
//        if (!message.equals("")) {
//            JOptionPane.showMessageDialog(null, message);
//        } else {
//            setModified();
//        }
    }//GEN-LAST:event_applyButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JPanel bottomCodingPanel;
    private javax.swing.JLabel bottomCodingReplacementLabel;
    private javax.swing.JTextField bottomCodingReplacementTextField;
    private javax.swing.JLabel bottomValueLabel;
    private javax.swing.JTextField bottomValueTextField;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel maximumLabel;
    private javax.swing.JTextField maximumTextField;
    private javax.swing.JLabel minimumLabel;
    private javax.swing.JTextField minimumTextField;
    private javax.swing.JLabel percentageLabel;
    private javax.swing.JTextField percentageTextField;
    private javax.swing.JPanel roundPanel;
    private javax.swing.JLabel roundingBaseLabel;
    private javax.swing.JTextField roundingBaseTextField;
    private javax.swing.JPanel topCodingPanel;
    private javax.swing.JLabel topCodingReplacementLabel;
    private javax.swing.JTextField topCodingReplacementTextField;
    private javax.swing.JLabel topValueLabel;
    private javax.swing.JTextField topValueTextField;
    private javax.swing.JScrollPane variablesScrollPane;
    private javax.swing.JTable variablesTable;
    private javax.swing.JPanel weightNoisePanel;
    // End of variables declaration//GEN-END:variables
}
