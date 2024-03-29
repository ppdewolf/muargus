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

import argus.view.SpssSelectVariablesView;
import argus.utils.SingleListSelectionModel;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import muargus.VariableNameCellRenderer;
import muargus.model.MetadataMu;
import muargus.controller.SpecifyMetadataController;
import argus.model.SpssVariable;
import muargus.MuARGUS;
import muargus.model.VariableMu;

/**
 * View class of the SpecifyMetadata screen.
 *
 * @author Statistics Netherlands
 */
public class SpecifyMetadataView extends DialogBase<SpecifyMetadataController> {

    private ArrayList<VariableMu> related;
    private final String[] idLevel = {"0", "1", "2", "3", "4", "5"};
    private final String[] format = {"Fixed format", "Free format", "Free with meta", "SPSS system file"};
    private String[] suppressionPriority;
    private final VariableMu dummyVar = new VariableMu();
    private final java.awt.Frame parent;

    private DefaultListModel variableListModel;

    /**
     * Creates new form SpecifyMetadataView
     *
     * @param parent the Frame of the mainFrame.
     * @param modal boolean to set the modal status
     * @param controller the controller of this view.
     */
    public SpecifyMetadataView(java.awt.Frame parent, boolean modal, SpecifyMetadataController controller) {
        super(parent, modal, controller);
        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);
        this.variablesList.setSelectionModel(new SingleListSelectionModel());
        this.variablesList.setCellRenderer(new VariableNameCellRenderer());
        this.relatedToComboBox.setRenderer(new VariableNameCellRenderer());
    }

    /**
     * Initializes the data for the SpecifyMetadataView.
     */
    @Override
    public void initializeData() {
        this.suppressionPriority = new String[101];

        // adds the variables
        this.variableListModel = new DefaultListModel<>();
        for (VariableMu variable : getMetadata().getVariables()) {
            this.variableListModel.addElement(variable);
        }
        this.variablesList.setModel(this.variableListModel);

        // makes the list of suppressionPriorities for the suppressionPriorityJComboBox
        for (int i = 0; i < this.suppressionPriority.length; i++) {
            this.suppressionPriority[i] = Integer.toString(i);
        }

        // add lists of names to the ComboBoxes
        this.identificationComboBox.setModel(new DefaultComboBoxModel(this.idLevel));
        this.priorityLocalSuppressionComboBox.setModel(new DefaultComboBoxModel(this.suppressionPriority));

        // set the format choise options
        if (getMetadata().isSpss()) {
            String[] tempFormat = {format[3]};
            this.formatComboBox.setModel(new DefaultComboBoxModel(tempFormat));
        } else {
            String[] tempFormat = {format[0], format[1], format[2]};
            this.formatComboBox.setModel(new DefaultComboBoxModel(tempFormat));
        }

        // selects the first variable if there are variables
        if (this.variableListModel.getSize() > 0) {
            this.variablesList.setSelectedIndex(0);
        }
        enableControls();
        calculateButtonStates();

        this.variablesList.requestFocus();
    }

    /**
     * Method setting the values for when the SPSS mode is set.
     */
    private void setSpss() {
        // make invisible
        this.separatorLabel.setVisible(false);
        this.separatorTextField.setVisible(false);
        this.startingPositionLabel.setVisible(false);
        this.startingPositionTextField.setVisible(false);

        // disable or enable
        enableControls(this.attributesPanel, false);
        enableControls(this.missingsPanel, false);
        this.codelistfileCheckBox.setEnabled(false);
        this.codelistfileButton.setEnabled(false);
        this.codelistfileTextField.setEnabled(false);
        this.newButton.setEnabled(false);
        this.generateButton.setEnabled(true);
    }

    /**
     * Method setting the values for when the SPSS mode is set or not.
     *
     * @param b Boolean indicating whether the SPSS mode is set.
     */
    private void setFixed() {
        // set the datatype in case it has been changed
        getMetadata().setDataFileType(MetadataMu.DATA_FILE_TYPE_FIXED);

        // make (in)visible
        this.startingPositionLabel.setVisible(true);
        this.startingPositionTextField.setVisible(true);
        this.separatorLabel.setVisible(false);
        this.separatorTextField.setVisible(false);

        // disable or enable
        this.generateButton.setEnabled(false);

        // set selected data type
        this.formatComboBox.setSelectedIndex(MetadataMu.DATA_FILE_TYPE_FIXED - 1);
    }

    /**
     * Method setting the values for when free format data is used.
     */
    private void setFree() {
        // set the datatype in case it has been changed
        getMetadata().setDataFileType(MetadataMu.DATA_FILE_TYPE_FREE);

        // make (in)visible
        this.startingPositionLabel.setVisible(false);
        this.startingPositionTextField.setVisible(false);
        this.separatorLabel.setVisible(true);
        this.separatorTextField.setVisible(true);

        // disable or enable
        this.generateButton.setEnabled(false);

        // set selected data type
        this.formatComboBox.setSelectedIndex(MetadataMu.DATA_FILE_TYPE_FREE - 1);
    }

    /**
     * Method setting the values for when free format data whith metadata is
     * used. In free with meta data, the first row of the data file contains the
     * variable names.
     */
    private void setFreeWithMeta() {
        // set the datatype in case it has been changed
        getMetadata().setDataFileType(MetadataMu.DATA_FILE_TYPE_FREE_WITH_META);

        // make (in)visible
        this.startingPositionLabel.setVisible(false);
        this.startingPositionTextField.setVisible(false);
        this.separatorLabel.setVisible(true);
        this.separatorTextField.setVisible(true);

        // disable or enable
        this.generateButton.setEnabled(true);

        // set selected data type
        this.formatComboBox.setSelectedIndex(MetadataMu.DATA_FILE_TYPE_FREE_WITH_META - 1);
    }

    /**
     * Updates the values.
     */
    private void updateValues() {
        VariableMu selected = getSelectedVariable();
        this.nameTextField.setText(selected.getName());
        this.identificationComboBox.setSelectedIndex(selected.getIdLevel());
        this.decimalsTextField.setText(Integer.toString(selected.getDecimals()));
        this.truncationAllowedCheckBox.setSelected(selected.isTruncable());
        this.codelistfileCheckBox.setSelected(selected.isCodelist());
        this.codelistfileTextField.setText(selected.getCodeListFile());
        this.priorityLocalSuppressionComboBox.setSelectedIndex(selected.getSuppressPriority());
        this.categoricalCheckBox.setSelected(selected.isCategorical());
        this.numericalCheckBox.setSelected(selected.isNumeric());
        this.weightRadioButton.setSelected(selected.isWeight());
        this.hhIdentifierRadioButton.setSelected(selected.isHouse_id());
        this.hhvariableRadioButton.setSelected(selected.isHousehold());
        this.weightRadioButton.setSelected(selected.isWeight());
        this.otherRadioButton.setSelected(selected.isOther());
        this.missing1TextField.setText(selected.getMissing(0));
        this.missing2TextField.setText(selected.getMissing(1));
        this.startingPositionTextField.setText(Integer.toString(selected.getStartingPosition()));
        this.lengthTextField.setText(Integer.toString(selected.getVariableLength()));
        this.separatorTextField.setText(getMetadata().getSeparator());

        // Fills the relatedToComboBox with all variables except the selected one
        this.related = new ArrayList<>();
        this.related.add(new VariableMu("--none--"));
        for (Object o : this.variableListModel.toArray()) {
            if (!o.equals(selected)) {
                this.related.add((VariableMu) o);
            }
        }
        this.relatedToComboBox.setModel(
                new javax.swing.DefaultComboBoxModel(this.related.toArray()));

        if (selected.isRelated()) {
            this.relatedToComboBox.setSelectedItem(selected.getRelatedVariable());
        } else {
            this.relatedToComboBox.setSelectedIndex(0);
        }
    }

    /**
     * Sets the button states enabled if it is required for the selected
     * data-format.
     */
    private void calculateButtonStates() {
        int index = this.variablesList.getSelectedIndex();
        this.deleteButton.setEnabled(index != -1);
        this.moveUpButton.setEnabled(index > 0);
        this.moveDownButton.setEnabled((index != -1)
                && (index < this.variableListModel.getSize() - 1));
    }

    /**
     * Enables/disables the controls. If there are no variables, everything
     * except the buttons are disabled. If there are variables, the components
     * are enabled/disabled depending on the metadata from the selected
     * variable.
     */
    private void enableControls() {
        if (getMetadata().getVariables().isEmpty()) {
            enableAllControls(false);
            dataFormatCheck();
        } else {
            enableAllControls(true);
            this.codelistfileCheckBox.setEnabled(this.categoricalCheckBox.isSelected());
            this.codelistfileTextField.setEnabled(this.codelistfileCheckBox.isSelected()
                    && this.categoricalCheckBox.isSelected());
            this.codelistfileButton.setEnabled(this.codelistfileCheckBox.isSelected()
                    && this.categoricalCheckBox.isSelected());
            this.codelistfileTextField.setEnabled(this.codelistfileCheckBox.isSelected());
            this.decimalsTextField.setEnabled(this.numericalCheckBox.isSelected());
            this.categoricalCheckBox.setEnabled(!(this.weightRadioButton.isSelected()
                    || this.hhIdentifierRadioButton.isSelected()));
            this.numericalCheckBox.setEnabled(!this.hhIdentifierRadioButton.isSelected());
            this.missingsPanel.setEnabled(!this.weightRadioButton.isSelected());
            this.optionsArgusPanel.setEnabled(this.categoricalCheckBox.isSelected());
            this.truncationAllowedCheckBox.setEnabled(this.categoricalCheckBox.isSelected());
            dataFormatCheck();
        }
    }

    /**
     * Enables/Disables all controls.
     *
     * @param enable Boolean indicating whether all controls should be enabled.
     */
    private void enableAllControls(boolean enable) {
        enableControls(this.attributesPanel, enable);
        enableControls(this.categoriesPanel, enable);
        enableControls(this.optionsArgusPanel, enable);
        enableControls(this.variableTypePanel, enable);
        enableControls(this.relatedToPanel, enable);
        enableControls(this.relatedToComboBox, enable);
        enableControls(this.missingsPanel, enable);
        this.separatorLabel.setEnabled(enable);
        this.separatorTextField.setEnabled(enable);
    }

    /**
     * Enables/Disables all controls and sub controls from a component.
     *
     * @param control Component for which all sub-components should be
     * enabled/disabled.
     * @param enable Boolean indicating whether the component and its
     * sub-components should be enabled.
     */
    private void enableControls(Component control, boolean enable) {
        if (!(control instanceof JComponent) && !(control instanceof JDialog)) {
            return;
        }
        control.setEnabled(enable);
        if (control instanceof Container) {
            for (Component c : ((Container) control).getComponents()) {
                enableControls(c, enable);
            }
        }
    }

    /**
     * Sets components (in)visible and buttons enabled, depending on the format
     * of the data.
     */
    private void dataFormatCheck() {
        switch (getMetadata().getDataFileType()) {
            case MetadataMu.DATA_FILE_TYPE_FIXED:
                setFixed();
                break;
            case MetadataMu.DATA_FILE_TYPE_FREE:
                setFree();
                break;
            case MetadataMu.DATA_FILE_TYPE_FREE_WITH_META:
                setFreeWithMeta();
                break;
            case MetadataMu.DATA_FILE_TYPE_SPSS:
                setSpss();
                break;
        }
    }

    /**
     * Gets the selected variable.
     *
     * @return VariableMu instance of the selected variable.
     */
    private VariableMu getSelectedVariable() {
        VariableMu selected = (VariableMu) this.variablesList.getSelectedValue();
        return selected == null ? this.dummyVar : selected;
    }

    /**
     * Updates the metadata. The existing variables in the metadata are removed
     * and only the variables in the variable list are added.
     */
    private void updateMetadata() {
        getMetadata().getVariables().clear();
        for (Object o : this.variableListModel.toArray()) {
            getMetadata().getVariables().add((VariableMu) o);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        variableTypeButtonGroup = new javax.swing.ButtonGroup();
        variablesPanel = new javax.swing.JPanel();
        formatComboBox = new javax.swing.JComboBox();
        variablesScrollPane = new javax.swing.JScrollPane();
        variablesList = new javax.swing.JList<>();
        moveUpButton = new javax.swing.JButton();
        moveDownButton = new javax.swing.JButton();
        separatorTextField = new javax.swing.JTextField();
        separatorLabel = new javax.swing.JLabel();
        generateButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        attributesPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        startingPositionLabel = new javax.swing.JLabel();
        startingPositionTextField = new javax.swing.JTextField();
        lengthLabel = new javax.swing.JLabel();
        lengthTextField = new javax.swing.JTextField();
        decimalsLabel = new javax.swing.JLabel();
        decimalsTextField = new javax.swing.JTextField();
        variableTypePanel = new javax.swing.JPanel();
        hhIdentifierRadioButton = new javax.swing.JRadioButton();
        hhvariableRadioButton = new javax.swing.JRadioButton();
        weightRadioButton = new javax.swing.JRadioButton();
        otherRadioButton = new javax.swing.JRadioButton();
        categoricalCheckBox = new javax.swing.JCheckBox();
        numericalCheckBox = new javax.swing.JCheckBox();
        optionsArgusPanel = new javax.swing.JPanel();
        identificationLevelLabel = new javax.swing.JLabel();
        priorityLocalSuppressionLabel = new javax.swing.JLabel();
        identificationComboBox = new javax.swing.JComboBox();
        priorityLocalSuppressionComboBox = new javax.swing.JComboBox();
        categoriesPanel = new javax.swing.JPanel();
        truncationAllowedCheckBox = new javax.swing.JCheckBox();
        codelistfileCheckBox = new javax.swing.JCheckBox();
        codelistfileTextField = new javax.swing.JTextField();
        codelistfileButton = new javax.swing.JButton();
        missingsPanel = new javax.swing.JPanel();
        missing1Label = new javax.swing.JLabel();
        missing1TextField = new javax.swing.JTextField();
        missing2Label = new javax.swing.JLabel();
        missing2TextField = new javax.swing.JTextField();
        relatedToPanel = new javax.swing.JLabel();
        relatedToComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Specify Metadata");
        setMinimumSize(new java.awt.Dimension(560, 490));

        formatComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fixed format", "Free format", "Free with meta", "SPSS system file" }));
        formatComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatComboBoxActionPerformed(evt);
            }
        });

        variablesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        variablesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                variablesListValueChanged(evt);
            }
        });
        variablesScrollPane.setViewportView(variablesList);

        moveUpButton.setText("↑");
        moveUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUpButtonActionPerformed(evt);
            }
        });

        moveDownButton.setText("↓");
        moveDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownButtonActionPerformed(evt);
            }
        });

        separatorTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                separatorTextFieldCaretUpdate(evt);
            }
        });

        separatorLabel.setText("Separator");

        javax.swing.GroupLayout variablesPanelLayout = new javax.swing.GroupLayout(variablesPanel);
        variablesPanel.setLayout(variablesPanelLayout);
        variablesPanelLayout.setHorizontalGroup(
            variablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(variablesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(variablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(variablesPanelLayout.createSequentialGroup()
                        .addComponent(moveUpButton)
                        .addGap(20, 20, 20)
                        .addComponent(moveDownButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(formatComboBox, 0, 113, Short.MAX_VALUE)
                    .addComponent(variablesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, variablesPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(separatorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separatorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        variablesPanelLayout.setVerticalGroup(
            variablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(variablesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(variablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(separatorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(separatorLabel))
                .addGap(8, 8, 8)
                .addComponent(variablesScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(variablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moveUpButton)
                    .addComponent(moveDownButton))
                .addContainerGap())
        );

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        newButton.setText("New");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        attributesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Attributes"));

        nameLabel.setText("Name:");

        nameTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                nameTextFieldCaretUpdate(evt);
            }
        });

        startingPositionLabel.setText("Starting position");

        startingPositionTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                startingPositionTextFieldCaretUpdate(evt);
            }
        });

        lengthLabel.setText("Length:");

        lengthTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                lengthTextFieldCaretUpdate(evt);
            }
        });

        decimalsLabel.setText("Decimals:");

        decimalsTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                decimalsTextFieldCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout attributesPanelLayout = new javax.swing.GroupLayout(attributesPanel);
        attributesPanel.setLayout(attributesPanelLayout);
        attributesPanelLayout.setHorizontalGroup(
            attributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attributesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(attributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel)
                    .addComponent(startingPositionLabel)
                    .addComponent(lengthLabel)
                    .addComponent(decimalsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(attributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lengthTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addComponent(decimalsTextField, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(startingPositionTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        attributesPanelLayout.setVerticalGroup(
            attributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attributesPanelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(attributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startingPositionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startingPositionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lengthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lengthLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decimalsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decimalsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        variableTypePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Variable Type"));

        variableTypeButtonGroup.add(hhIdentifierRadioButton);
        hhIdentifierRadioButton.setText("HH Identifier");
        hhIdentifierRadioButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                hhIdentifierRadioButtonStateChanged(evt);
            }
        });

        variableTypeButtonGroup.add(hhvariableRadioButton);
        hhvariableRadioButton.setText("HH Variable");
        hhvariableRadioButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                hhvariableRadioButtonStateChanged(evt);
            }
        });

        variableTypeButtonGroup.add(weightRadioButton);
        weightRadioButton.setText("Weight");
        weightRadioButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                weightRadioButtonStateChanged(evt);
            }
        });

        variableTypeButtonGroup.add(otherRadioButton);
        otherRadioButton.setSelected(true);
        otherRadioButton.setText("Other");
        otherRadioButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                otherRadioButtonStateChanged(evt);
            }
        });

        categoricalCheckBox.setText("Categorical");
        categoricalCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                categoricalCheckBoxStateChanged(evt);
            }
        });

        numericalCheckBox.setText("Numerical");
        numericalCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                numericalCheckBoxStateChanged(evt);
            }
        });

        javax.swing.GroupLayout variableTypePanelLayout = new javax.swing.GroupLayout(variableTypePanel);
        variableTypePanel.setLayout(variableTypePanelLayout);
        variableTypePanelLayout.setHorizontalGroup(
            variableTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(variableTypePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(variableTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hhvariableRadioButton)
                    .addComponent(hhIdentifierRadioButton)
                    .addComponent(weightRadioButton)
                    .addComponent(otherRadioButton)
                    .addComponent(categoricalCheckBox)
                    .addComponent(numericalCheckBox))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        variableTypePanelLayout.setVerticalGroup(
            variableTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(variableTypePanelLayout.createSequentialGroup()
                .addComponent(hhIdentifierRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hhvariableRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(weightRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(otherRadioButton)
                .addGap(18, 18, 18)
                .addComponent(categoricalCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numericalCheckBox))
        );

        optionsArgusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Options for Argus"));

        identificationLevelLabel.setText("Identification Level:");

        priorityLocalSuppressionLabel.setText("Priority for local suppression:");

        identificationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        identificationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identificationComboBoxActionPerformed(evt);
            }
        });

        priorityLocalSuppressionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        priorityLocalSuppressionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priorityLocalSuppressionComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionsArgusPanelLayout = new javax.swing.GroupLayout(optionsArgusPanel);
        optionsArgusPanel.setLayout(optionsArgusPanelLayout);
        optionsArgusPanelLayout.setHorizontalGroup(
            optionsArgusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsArgusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsArgusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(identificationLevelLabel)
                    .addComponent(priorityLocalSuppressionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(optionsArgusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(identificationComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priorityLocalSuppressionComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        optionsArgusPanelLayout.setVerticalGroup(
            optionsArgusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsArgusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsArgusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(identificationLevelLabel)
                    .addComponent(identificationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(optionsArgusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priorityLocalSuppressionLabel)
                    .addComponent(priorityLocalSuppressionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        categoriesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Categories"));

        truncationAllowedCheckBox.setText("Truncation allowed");
        truncationAllowedCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                truncationAllowedCheckBoxStateChanged(evt);
            }
        });

        codelistfileCheckBox.setText("Codelistfile");
        codelistfileCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                codelistfileCheckBoxStateChanged(evt);
            }
        });

        codelistfileTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                codelistfileTextFieldCaretUpdate(evt);
            }
        });

        codelistfileButton.setText("...");
        codelistfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codelistfileButtonActionPerformed(evt);
            }
        });

        missingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Missings"));

        missing1Label.setText("1:");

        missing1TextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                missing1TextFieldCaretUpdate(evt);
            }
        });

        missing2Label.setText("2:");

        missing2TextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                missing2TextFieldCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout missingsPanelLayout = new javax.swing.GroupLayout(missingsPanel);
        missingsPanel.setLayout(missingsPanelLayout);
        missingsPanelLayout.setHorizontalGroup(
            missingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(missingsPanelLayout.createSequentialGroup()
                .addGroup(missingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(missingsPanelLayout.createSequentialGroup()
                        .addComponent(missing1Label)
                        .addGap(18, 18, 18)
                        .addComponent(missing1TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                    .addGroup(missingsPanelLayout.createSequentialGroup()
                        .addComponent(missing2Label)
                        .addGap(18, 18, 18)
                        .addComponent(missing2TextField)))
                .addGap(10, 10, 10))
        );
        missingsPanelLayout.setVerticalGroup(
            missingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(missingsPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(missingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(missing1Label)
                    .addComponent(missing1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(missingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(missing2Label)
                    .addComponent(missing2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout categoriesPanelLayout = new javax.swing.GroupLayout(categoriesPanel);
        categoriesPanel.setLayout(categoriesPanelLayout);
        categoriesPanelLayout.setHorizontalGroup(
            categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoriesPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(categoriesPanelLayout.createSequentialGroup()
                        .addGroup(categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(truncationAllowedCheckBox)
                            .addComponent(codelistfileCheckBox))
                        .addGap(107, 107, 107)
                        .addComponent(missingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(categoriesPanelLayout.createSequentialGroup()
                        .addComponent(codelistfileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codelistfileButton)))
                .addGap(61, 61, 61))
        );
        categoriesPanelLayout.setVerticalGroup(
            categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoriesPanelLayout.createSequentialGroup()
                .addGroup(categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(categoriesPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(truncationAllowedCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(codelistfileCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(categoriesPanelLayout.createSequentialGroup()
                        .addComponent(missingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codelistfileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codelistfileButton))
                .addContainerGap())
        );

        relatedToPanel.setText("Related to:");

        relatedToComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--none--" }));
        relatedToComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatedToComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(variablesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(generateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(categoriesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 399, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(optionsArgusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(attributesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(variableTypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(relatedToPanel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(relatedToComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(variablesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(variableTypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(relatedToPanel)
                            .addComponent(relatedToComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(attributesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(optionsArgusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(categoriesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton)
                    .addComponent(deleteButton)
                    .addComponent(newButton)
                    .addComponent(generateButton))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        if (getMetadata().isSpss()) {
            this.generateButton.setEnabled(false);
            List<SpssVariable> variables = MuARGUS.getSpssUtils().getSpssVariables();
            SpssSelectVariablesView selectView = new SpssSelectVariablesView(this.parent, true);
            selectView.showVariables(variables);
            selectView.setVisible(true);
            MuARGUS.getSpssUtils().setVariablesSpss(variables, getController().getMetadataClone());
            initializeData();
        } else {
            GenerateParameters generateView = new GenerateParameters(this.parent, true);
            generateView.setSeparator(this.separatorTextField.getText());
            generateView.setVisible(true);
            if (generateView.isOk()) {
                getMetadata().setSeparator(generateView.getSeparator());
                getController().generateFromHeader(getMetadata(),
                        generateView.getDefaultLength(), generateView.getDefaultMissing());
                getMetadata().setDataFileType(MetadataMu.DATA_FILE_TYPE_FREE_WITH_META);
                enableAllControls(!getMetadata().getVariables().isEmpty());
                initializeData();
                updateValues();
            }
        }
    }//GEN-LAST:event_generateButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        updateMetadata();
        getController().ok();
    }//GEN-LAST:event_okButtonActionPerformed

    private void moveUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpButtonActionPerformed
        int index = this.variablesList.getSelectedIndex();
        VariableMu variable = (VariableMu) this.variableListModel.get(index);
        this.variableListModel.set(index, this.variableListModel.get(index - 1));
        this.variableListModel.set(index - 1, variable);
        this.variablesList.setSelectedIndex(index - 1);
        calculateButtonStates();
    }//GEN-LAST:event_moveUpButtonActionPerformed

    private void formatComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatComboBoxActionPerformed
        switch (this.formatComboBox.getSelectedIndex()) {
            case MetadataMu.DATA_FILE_TYPE_FIXED - 1:
                setFixed();
                break;
            case MetadataMu.DATA_FILE_TYPE_FREE - 1:
                setFree();
                break;
            case MetadataMu.DATA_FILE_TYPE_FREE_WITH_META - 1:
                setFreeWithMeta();
                break;
            case MetadataMu.DATA_FILE_TYPE_SPSS - 1:
                setSpss();
                break;
        }
    }//GEN-LAST:event_formatComboBoxActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        this.newButton.setEnabled(false);
        int index = this.variablesList.getSelectedIndex() + 1;

        VariableMu variable = new VariableMu("New");
        this.variableListModel.add(index, variable);
        enableAllControls(true);
        this.variablesList.setSelectedIndex(index);
        updateValues();
        this.newButton.setEnabled(true);
    }//GEN-LAST:event_newButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int index = this.variablesList.getSelectedIndex();
        /* set the selection to an item that still exists after deletion
         if not done before removal the remove button will loose focus*/
        if (index == this.variableListModel.getSize() - 1) {
            this.variablesList.setSelectedIndex(index - 1);
        } else {
            this.variablesList.setSelectedIndex(index + 1);
        }
        try {
            this.variableListModel.remove(index);
        } catch (ArrayIndexOutOfBoundsException ex) { 
            // do nothing
        }
        calculateButtonStates();
        if (this.variablesList.getSelectedIndex() > -1) {
            updateValues();
        } else {
            enableAllControls(false);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        updateMetadata();
        getController().cancel();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void codelistfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codelistfileButtonActionPerformed
        String filePath = showFileDialog("Open Codelist File", false, new String[]{"Codelist (*.cdl)|cdl"});
        if (filePath != null) {
            if (!filePath.substring(filePath.lastIndexOf(".")).toLowerCase().equals(".cdl")) {
                filePath += ".cdl";
            }
            this.codelistfileTextField.setText(filePath);
        }
    }//GEN-LAST:event_codelistfileButtonActionPerformed

    private void moveDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownButtonActionPerformed
        int index = this.variablesList.getSelectedIndex();
        VariableMu variable = (VariableMu) this.variableListModel.get(index);
        this.variableListModel.set(index, this.variableListModel.get(index + 1));
        this.variableListModel.set(index + 1, variable);
        this.variablesList.setSelectedIndex(index + 1);
        calculateButtonStates();
    }//GEN-LAST:event_moveDownButtonActionPerformed

    private void hhIdentifierRadioButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_hhIdentifierRadioButtonStateChanged
        getSelectedVariable().setHouse_id(this.hhIdentifierRadioButton.isSelected());
        if (this.hhIdentifierRadioButton.isSelected()) {
            this.categoricalCheckBox.setSelected(false);
            this.categoricalCheckBox.setEnabled(false);
            this.numericalCheckBox.setEnabled(true);
            dataFormatCheck();
        }
    }//GEN-LAST:event_hhIdentifierRadioButtonStateChanged

    private void hhvariableRadioButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_hhvariableRadioButtonStateChanged
        getSelectedVariable().setHousehold(this.hhvariableRadioButton.isSelected());
        if (this.hhvariableRadioButton.isSelected()) {
            this.categoricalCheckBox.setEnabled(true);
            this.numericalCheckBox.setEnabled(true);
            dataFormatCheck();
        }
    }//GEN-LAST:event_hhvariableRadioButtonStateChanged

    private void weightRadioButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_weightRadioButtonStateChanged
        getSelectedVariable().setWeight(this.weightRadioButton.isSelected());
        if (this.weightRadioButton.isSelected()) {
            this.categoricalCheckBox.setSelected(false);
            this.categoricalCheckBox.setEnabled(false);
            this.numericalCheckBox.setSelected(true);
            this.numericalCheckBox.setEnabled(false);
        }
        enableControls(this.missingsPanel, !this.weightRadioButton.isSelected());
        dataFormatCheck();
    }//GEN-LAST:event_weightRadioButtonStateChanged

    private void otherRadioButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_otherRadioButtonStateChanged
        getSelectedVariable().setOther(this.otherRadioButton.isSelected());
        if (this.otherRadioButton.isSelected()) {
            this.categoricalCheckBox.setEnabled(true);
            this.numericalCheckBox.setEnabled(true);
            dataFormatCheck();
        }
    }//GEN-LAST:event_otherRadioButtonStateChanged

    private void categoricalCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_categoricalCheckBoxStateChanged
        getSelectedVariable().setCategorical(this.categoricalCheckBox.isSelected());
        this.codelistfileCheckBox.setEnabled(this.categoricalCheckBox.isSelected());
        this.codelistfileTextField.setEnabled(this.categoricalCheckBox.isSelected()
                && this.codelistfileCheckBox.isSelected());
        this.codelistfileButton.setEnabled(this.codelistfileCheckBox.isSelected()
                && this.categoricalCheckBox.isSelected());
        enableControls(this.optionsArgusPanel, this.categoricalCheckBox.isSelected());
        this.truncationAllowedCheckBox.setEnabled(this.categoricalCheckBox.isSelected());
        dataFormatCheck();
    }//GEN-LAST:event_categoricalCheckBoxStateChanged

    private void numericalCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_numericalCheckBoxStateChanged
        getSelectedVariable().setNumeric(this.numericalCheckBox.isSelected());
        this.decimalsTextField.setEnabled(this.numericalCheckBox.isSelected());
        dataFormatCheck();
    }//GEN-LAST:event_numericalCheckBoxStateChanged

    private void truncationAllowedCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_truncationAllowedCheckBoxStateChanged
        getSelectedVariable().setTruncable(this.truncationAllowedCheckBox.isSelected());
    }//GEN-LAST:event_truncationAllowedCheckBoxStateChanged

    private void codelistfileCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_codelistfileCheckBoxStateChanged
        getSelectedVariable().setCodelist(this.codelistfileCheckBox.isSelected());
        this.codelistfileButton.setEnabled(this.codelistfileCheckBox.isSelected());
        this.codelistfileTextField.setEnabled(this.codelistfileCheckBox.isSelected());
        dataFormatCheck();
    }//GEN-LAST:event_codelistfileCheckBoxStateChanged

    private void variablesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_variablesListValueChanged
        if (!evt.getValueIsAdjusting()) {
            VariableMu value = (VariableMu) this.variablesList.getSelectedValue();
            if (value == null) {
                enableAllControls(false);
                return;
            }
            this.nameTextField.setText(value.getName());
            calculateButtonStates();
            updateValues();
        }
    }//GEN-LAST:event_variablesListValueChanged

    private void startingPositionTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_startingPositionTextFieldCaretUpdate
        try {
            getSelectedVariable().setStartingPosition(Integer.parseInt(this.startingPositionTextField.getText()));
        } catch (NumberFormatException e) {
        }
    }//GEN-LAST:event_startingPositionTextFieldCaretUpdate

    private void lengthTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lengthTextFieldCaretUpdate
        try {
            getSelectedVariable().setVariableLength(Integer.parseInt(this.lengthTextField.getText()));
        } catch (NumberFormatException e) {
        }
    }//GEN-LAST:event_lengthTextFieldCaretUpdate

    private void decimalsTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_decimalsTextFieldCaretUpdate
        try {
            getSelectedVariable().setDecimals(Integer.parseInt(this.decimalsTextField.getText()));
        } catch (NumberFormatException e) {
        }
    }//GEN-LAST:event_decimalsTextFieldCaretUpdate

    private void missing1TextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_missing1TextFieldCaretUpdate
        getSelectedVariable().setMissing(0, this.missing1TextField.getText());
    }//GEN-LAST:event_missing1TextFieldCaretUpdate

    private void missing2TextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_missing2TextFieldCaretUpdate
        getSelectedVariable().setMissing(1, this.missing2TextField.getText());
    }//GEN-LAST:event_missing2TextFieldCaretUpdate

    private void codelistfileTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_codelistfileTextFieldCaretUpdate
        getSelectedVariable().setCodeListFile(this.codelistfileTextField.getText());
    }//GEN-LAST:event_codelistfileTextFieldCaretUpdate

    private void separatorTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_separatorTextFieldCaretUpdate
        getMetadata().setSeparator(this.separatorTextField.getText());
    }//GEN-LAST:event_separatorTextFieldCaretUpdate

    private void identificationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identificationComboBoxActionPerformed
        try {
            getSelectedVariable().setIdLevel(Integer.parseInt((String) this.identificationComboBox.getSelectedItem()));
        } catch (NumberFormatException e) {
        }
    }//GEN-LAST:event_identificationComboBoxActionPerformed

    private void priorityLocalSuppressionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priorityLocalSuppressionComboBoxActionPerformed
        try {
            getSelectedVariable().setSuppressionPriority(Integer.parseInt((String) this.priorityLocalSuppressionComboBox.getSelectedItem()));
        } catch (NumberFormatException e) {
        }
    }//GEN-LAST:event_priorityLocalSuppressionComboBoxActionPerformed

    private void relatedToComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatedToComboBoxActionPerformed
        if (this.relatedToComboBox.getSelectedIndex() != 0) {
            getSelectedVariable().setRelatedVariable((VariableMu) this.relatedToComboBox.getSelectedItem());
        } else {
            getSelectedVariable().setRelatedVariable(null);
        }
    }//GEN-LAST:event_relatedToComboBoxActionPerformed

    private void nameTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_nameTextFieldCaretUpdate
        getSelectedVariable().setName(this.nameTextField.getText());
        this.variablesList.updateUI();
    }//GEN-LAST:event_nameTextFieldCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel attributesPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox categoricalCheckBox;
    private javax.swing.JPanel categoriesPanel;
    private javax.swing.JButton codelistfileButton;
    private javax.swing.JCheckBox codelistfileCheckBox;
    private javax.swing.JTextField codelistfileTextField;
    private javax.swing.JLabel decimalsLabel;
    private javax.swing.JTextField decimalsTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox formatComboBox;
    private javax.swing.JButton generateButton;
    private javax.swing.JRadioButton hhIdentifierRadioButton;
    private javax.swing.JRadioButton hhvariableRadioButton;
    private javax.swing.JComboBox identificationComboBox;
    private javax.swing.JLabel identificationLevelLabel;
    private javax.swing.JLabel lengthLabel;
    private javax.swing.JTextField lengthTextField;
    private javax.swing.JLabel missing1Label;
    private javax.swing.JTextField missing1TextField;
    private javax.swing.JLabel missing2Label;
    private javax.swing.JTextField missing2TextField;
    private javax.swing.JPanel missingsPanel;
    private javax.swing.JButton moveDownButton;
    private javax.swing.JButton moveUpButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton newButton;
    private javax.swing.JCheckBox numericalCheckBox;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel optionsArgusPanel;
    private javax.swing.JRadioButton otherRadioButton;
    private javax.swing.JComboBox priorityLocalSuppressionComboBox;
    private javax.swing.JLabel priorityLocalSuppressionLabel;
    private javax.swing.JComboBox relatedToComboBox;
    private javax.swing.JLabel relatedToPanel;
    private javax.swing.JLabel separatorLabel;
    private javax.swing.JTextField separatorTextField;
    private javax.swing.JLabel startingPositionLabel;
    private javax.swing.JTextField startingPositionTextField;
    private javax.swing.JCheckBox truncationAllowedCheckBox;
    private javax.swing.ButtonGroup variableTypeButtonGroup;
    private javax.swing.JPanel variableTypePanel;
    private javax.swing.JList variablesList;
    private javax.swing.JPanel variablesPanel;
    private javax.swing.JScrollPane variablesScrollPane;
    private javax.swing.JRadioButton weightRadioButton;
    // End of variables declaration//GEN-END:variables
}
