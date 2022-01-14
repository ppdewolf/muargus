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

import argus.model.DataFilePair;
import argus.utils.SystemUtils;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import muargus.controller.ControllerBase;
import org.apache.commons.io.FilenameUtils;

/**
 * View class of the open microdata screen.
 *
 * @author Statistics Netherlands
 */
public class OpenMicrodataView extends DialogBase<ControllerBase> {

    private boolean canSelectSpss;

    // ***** Dialog Return Values *****
    public static final int CANCEL_OPTION = 1;
    public static final int APPROVE_OPTION = 0;

    private int returnValue = OpenMicrodataView.CANCEL_OPTION;

    /**
     * Creates new form DialogOpenMicrodata.
     *
     * @param parent the Frame of the main frame.
     * @param modal boolean to set the modal status.
     */
    public OpenMicrodataView(java.awt.Frame parent, boolean modal) {
        super(parent, modal, null);
        initComponents();
        setLocationRelativeTo(parent);
    }

    /**
     * Sets the data file names.
     *
     * @param dataFileName String containing the data file name.
     * @param metadataFileName String containing the metadata file name.
     */
    public void setDataFileNames(String dataFileName, String metadataFileName) {
        this.textFieldMicrodata.setText(dataFileName);
        this.textFieldMetadata.setText(metadataFileName);
    }

    /**
     * Sets the data field labels.
     *
     * @param dataFileLabel String containing the data file label.
     * @param metadataFileLabel String containing the metadata file label.
     */
    public void setDataFieldLabels(String dataFileLabel, String metadataFileLabel) {
        this.labelMicrodata.setText(dataFileLabel);
        this.labelMetadata.setText(metadataFileLabel);
    }

    /**
     * Shows the dialog.
     *
     * @return Integer containig the return value.
     */
    public int showDialog() {
        setVisible(true);
        return this.returnValue;
    }

    /**
     * Gets te microdata file pair.
     *
     * @return DataFilePair containing the microdata file pair.
     */
    public DataFilePair getMicrodataFilePair() {
        return new DataFilePair(this.textFieldMicrodata.getText(), this.textFieldMetadata.getText());
    }

    /**
     * Sets whether SPSS selection is allowed.
     *
     * @param allowed Boolean indicating whether SPSS selection is allowed.
     */
    public void selectSpssAllowed(boolean allowed) {
        this.canSelectSpss = allowed;
    }

    /* 
     * In many situations the metadata filename has the same name as the 
     * microdata filename, only with another extension.
     */
    private void setMetadataFileNameIfPossible() {
        String fileName = this.textFieldMicrodata.getText();
        int extensionIndex = FilenameUtils.indexOfExtension(fileName);
        String baseFileName;
        if (extensionIndex == -1) {
            baseFileName = fileName;
        } else {
            baseFileName = fileName.substring(0, extensionIndex);
        }
        String metadataFileName = baseFileName + ".rda";
        File file = new File(metadataFileName);
        if (file.exists() && file.isFile()) {
            this.textFieldMetadata.setText(metadataFileName);
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

        fileChooser = new javax.swing.JFileChooser();
        labelMicrodata = new javax.swing.JLabel();
        textFieldMicrodata = new javax.swing.JTextField();
        buttonMicrodata = new javax.swing.JButton();
        labelMetadata = new javax.swing.JLabel();
        textFieldMetadata = new javax.swing.JTextField();
        buttonMetadata = new javax.swing.JButton();
        buttonOK = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        fileChooser.setDialogTitle("");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Open Microdata");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                DialogClosing(evt);
            }
        });

        labelMicrodata.setLabelFor(textFieldMicrodata);
        labelMicrodata.setText("Microdata:");

        buttonMicrodata.setText("...");
        buttonMicrodata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMicrodataActionPerformed(evt);
            }
        });

        labelMetadata.setLabelFor(textFieldMetadata);
        labelMetadata.setText("Metadata (optional): ");

        buttonMetadata.setText("...");
        buttonMetadata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMetadataActionPerformed(evt);
            }
        });

        buttonOK.setText("OK");
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelMicrodata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelMetadata, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textFieldMetadata, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldMicrodata, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonMicrodata, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(buttonMetadata, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 418, Short.MAX_VALUE)
                        .addComponent(buttonOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonCancel, buttonOK});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonMicrodata, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelMicrodata)
                        .addComponent(textFieldMicrodata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetadata)
                    .addComponent(textFieldMetadata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonMetadata))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOK)
                    .addComponent(buttonCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonMicrodataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMicrodataActionPerformed
        String hs = SystemUtils.getRegString("general", "datadir", "");
        if (!hs.equals("")) {
            File file = new File(hs);
            this.fileChooser.setCurrentDirectory(file);
        }
        this.fileChooser.setDialogTitle("Open Microdata");
        this.fileChooser.setSelectedFile(new File(""));
        this.fileChooser.resetChoosableFileFilters();
        // filters are shown in order of declaration, setFileFilter sets the default filter
        this.fileChooser.setFileFilter(new FileNameExtensionFilter("Microdata (*.asc)", "asc"));
        this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Comma Separated File (*.csv)", "csv"));
        this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microdata (*.dat)", "dat"));
        if (this.canSelectSpss) {
            this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("SPSS system file (*.sav)", "sav"));
        }
        if (this.fileChooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            this.textFieldMicrodata.setText(this.fileChooser.getSelectedFile().toString());
            hs = this.fileChooser.getSelectedFile().getPath();
            if (!hs.equals("")) {
                SystemUtils.putRegString("general", "datadir", hs);
            }
            setMetadataFileNameIfPossible();
        }
    }//GEN-LAST:event_buttonMicrodataActionPerformed

    private void buttonMetadataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMetadataActionPerformed
        String hs = SystemUtils.getRegString("general", "datadir", "");
        if (!hs.equals("")) {
            File file = new File(hs);
            this.fileChooser.setCurrentDirectory(file);
        }
        this.fileChooser.setDialogTitle("Open Metadata");
        this.fileChooser.setSelectedFile(new File(""));
        this.fileChooser.resetChoosableFileFilters();
        this.fileChooser.setFileFilter(new FileNameExtensionFilter("Metadata (*.rda)", "rda"));
        if (this.fileChooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            this.textFieldMetadata.setText(this.fileChooser.getSelectedFile().toString());
            hs = this.fileChooser.getSelectedFile().getPath();
            if (!hs.equals("")) {
                SystemUtils.putRegString("general", "datadir", hs);
            }
        }
    }//GEN-LAST:event_buttonMetadataActionPerformed

    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed
        if (this.textFieldMicrodata.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please specify microdata file.");
            return;
        }

        if (!new File(this.textFieldMicrodata.getText()).exists()) {
            JOptionPane.showMessageDialog(this, "Microdata file " + this.textFieldMicrodata.getText() + " does not exist.");
            return;
        }

        if (!this.textFieldMetadata.getText().trim().equals("")) {
            if (!new File(this.textFieldMetadata.getText()).exists()) {
                JOptionPane.showMessageDialog(this, "Metadata file " + this.textFieldMetadata.getText() + " does not exist.");
                return;
            }
        }
        SystemUtils.writeLogbook("Microdata file: " + this.textFieldMicrodata.getText() + " has been opened");
        if (!this.textFieldMetadata.getText().trim().equals("")) {
            SystemUtils.writeLogbook("Metadata file: " + this.textFieldMetadata.getText() + " has been opened");
        }
        this.returnValue = OpenMicrodataView.APPROVE_OPTION;
        setVisible(false);
    }//GEN-LAST:event_buttonOKActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        setVisible(false);
        this.returnValue = OpenMicrodataView.CANCEL_OPTION;
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void DialogClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DialogClosing
        setVisible(false);
    }//GEN-LAST:event_DialogClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonMetadata;
    private javax.swing.JButton buttonMicrodata;
    private javax.swing.JButton buttonOK;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel labelMetadata;
    private javax.swing.JLabel labelMicrodata;
    private javax.swing.JTextField textFieldMetadata;
    private javax.swing.JTextField textFieldMicrodata;
    // End of variables declaration//GEN-END:variables

}
