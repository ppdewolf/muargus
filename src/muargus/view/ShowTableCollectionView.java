/*
 * Argus Open Source
 * Software to apply Statistical Disclosure Control techniques
 *
 * Copyright 2014 Statistics Netherlands
 *
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the European Union Public Licence 
 * (EUPL) version 1.1, as published by the European Commission.
 *
 * You can find the text of the EUPL v1.1 on
 * https://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 *
 * This software is distributed on an "AS IS" basis without 
 * warranties or conditions of any kind, either express or implied.
 */
package muargus.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import muargus.VariableNameCellRenderer;
import muargus.controller.ShowTableCollectionController;
import muargus.model.TableCollection;
import muargus.model.VariableMu;

/**
 * The view class of the ShowTableCollection screen.
 *
 * @author Statistics Netherlands
 */
public class ShowTableCollectionView extends DialogBase<ShowTableCollectionController> {

    TableCollection model;
    private DefaultComboBoxModel<VariableMu> variableListModel;
    private TableModel tableModel;

    /**
     * Constructor for the ShowTableCollectionView. This constructor will
     * initialize its components, sets the table to single selection and sets a
     * renderer to the selectVariableCombobox that will read the names of the
     * variables.
     *
     * @param parent the Frame of the mainFrame.
     * @param modal boolean to set the modal status
     * @param controller the controller of this view.
     */
    public ShowTableCollectionView(java.awt.Frame parent, boolean modal, ShowTableCollectionController controller) {
        super(parent, modal, controller);
        initComponents();
        setLocationRelativeTo(null);
        this.table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.selectVariableComboBox.setRenderer(new VariableNameCellRenderer());
    }

    /**
     * Initializes the data. This method sets the original tables in the model
     * and sets the showAllTables to false. The listModel for the
     * selectVariableComboBox is filled with a dummy variable and the variables
     * used in the tables. The dummy variable is used if the tables of all
     * variables should be shown. Finally the controller method for setting all
     * tables is called, the complete data of these tables is added to the model
     * and the table is updated.
     */
    @Override
    public void initializeData() {
        this.model = getMetadata().getCombinations().getTableCollection();
        this.model.setOriginalTables(getMetadata().getCombinations().getTables());
        this.model.setShowAllTables(false);

        this.variableListModel = new DefaultComboBoxModel<>();
        VariableMu all = new VariableMu("all");
        this.variableListModel.addElement(all);
        this.model.setSelectedVariable(all);
        for (VariableMu variable : this.model.getVariables()) {
            this.variableListModel.addElement(variable);
        }
        this.variableListModel.setSelectedItem(all);
        this.selectVariableComboBox.setModel(this.variableListModel);

        getController().setAllTables();
        this.model.setData(getController().getData(this.model.getAllTables()));
        updateTable();
    }

    /**
     * Updates the values of the Table by resetting the table with new values.
     */
    private void updateTable() {
        getController().setSubData(this.model.isShowAllTables());
        this.tableModel = new DefaultTableModel(this.model.getSubdata(), this.model.getColumnNames()) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int i) {
                return i == 0 ? Integer.class : String.class;
            }
        };
        this.table.setModel(this.tableModel);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        this.table.getColumn("# unsafe cells").setCellRenderer(rightRenderer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        showAllTablesCheckBox = new javax.swing.JCheckBox();
        closeButton = new javax.swing.JButton();
        selectVariableLabel = new javax.swing.JLabel();
        selectVariableComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Table Collection");
        setMinimumSize(new java.awt.Dimension(420, 350));
        setPreferredSize(new java.awt.Dimension(420, 350));

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "# unsafe cells", "Var 1", "Var 2", "Var 3", "Var 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);

        showAllTablesCheckBox.setText("Show all tables");
        showAllTablesCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                showAllTablesCheckBoxStateChanged(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        selectVariableLabel.setText("Select variable:");

        selectVariableComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectVariableComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(showAllTablesCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addComponent(selectVariableLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectVariableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 165, Short.MAX_VALUE)
                        .addComponent(closeButton)
                        .addGap(0, 161, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showAllTablesCheckBox)
                    .addComponent(selectVariableLabel)
                    .addComponent(selectVariableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        getController().close();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void showAllTablesCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_showAllTablesCheckBoxStateChanged
        this.model.setShowAllTables(this.showAllTablesCheckBox.isSelected());
        updateTable();
    }//GEN-LAST:event_showAllTablesCheckBoxStateChanged

    private void selectVariableComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectVariableComboBoxActionPerformed
        this.model.setSelectedVariable((VariableMu) this.selectVariableComboBox.getSelectedItem());
        updateTable();
    }//GEN-LAST:event_selectVariableComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JComboBox<VariableMu> selectVariableComboBox;
    private javax.swing.JLabel selectVariableLabel;
    private javax.swing.JCheckBox showAllTablesCheckBox;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
