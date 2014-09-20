package muargus.view;

import argus.model.ArgusException;
import argus.model.DataFilePair;
import argus.view.DialogOpenMicrodata;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import muargus.CodeTableCellRenderer;
import muargus.MuARGUS;
import muargus.controller.MainFrameController;
import muargus.model.Combinations;
import muargus.model.CodeInfo;
import muargus.model.VariableMu;

/**
 *
 * @author ambargus
 */
public class MainFrameView extends javax.swing.JFrame {

    //private DataFilePair dataFilePair;
    private final MainFrameController controller;
    private Combinations model;

    /**
     * Creates new form MainFrameView
     */
    public MainFrameView() {
        initComponents();
        this.controller = new MainFrameController(this);
        this.setLocationRelativeTo(null);
    }

    public void enableAction(MainFrameController.Action action, boolean enable) {
        switch (action) {
            case SpecifyMetadata:
                doEnable(specifyMetaDataButton, metaDataMenuItem, enable);
                return;
            case SpecifyCombinations:
                doEnable(specifyCombinationsButton, combinationsMenuItem, enable);
                return;
            case GlobalRecode:
                doEnable(globalRecodeButton, globalRecodeMenuItem, enable);
                return;
            case ShowTableCollection:
                doEnable(showTableCollectionButton, showTableCollectionMenuItem, enable);
                return;
            case PramSpecification:
                doEnable(pramSpecificationButton, pramSpecificationMenuItem, enable);
                return;
            case IndividualRiskSpecification:
                doEnable(individualRiskSpecificationButton, individualRiskSpecificationMenuItem, enable);
                return;
            case HouseholdRiskSpecification:
                doEnable(householdRiskSpecificationButton, householdRiskSpecificationMenuItem, enable);
                return;
            case ModifyNumericalVariables:
                doEnable(modifyNumericalVariablesButton, numericalVariablesMenuItem, enable); //TODO: verander naamgeving voor deze klasses
                return;
            case NumericalMicroAggregation:
                doEnable(numericalMicroaggregationButton, numericalMicroaggregationMenuItem, enable);
                return;
            case NumericalRankSwapping:
                doEnable(numericalRankSwappingButton, numericalRankSwappingMenuItem, enable);
                return;
            case MakeProtectedFile:
                doEnable(makeProtectedFileButton, makeProtectedFileMenuItem, enable);
                return;
            case ViewReport:
                doEnable(viewReportButton, viewReportMenuItem, enable);
                return;
            case QualitativeMicroAggregation:
                doEnable(qualitativeMicroaggregationButton, qualitativeMicroaggregationMenuItem, enable);
                return;
                
        }
    }

    private void doEnable(JButton button, JMenuItem item, boolean enable) {
        if (button != null) {
            button.setEnabled(enable);
        }
        if (item != null) {
            item.setEnabled(enable);
        }
    }

    public JTable getUnsafeCombinationsTable() {
        return unsafeCombinationsTable;
    }

    public JTable getVariablesTable() {
        return variablesTable;
    }

    public void setVariableNameLabel(String variableNameLabel) {
        this.variableNameLabel.setText(variableNameLabel);
    }

    public void showErrorMessage(ArgusException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), MuARGUS.getMessageTitle(), JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, MuARGUS.getMessageTitle(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * enables buttons
     */
    public void organise() {
//        specifyMetadataAction.setEnabled(Application.numberOfMetadatas() > 0);
//        specifyTablesAction.setEnabled(Application.numberOfMetadatas() > 0);
//
//        selectTableAction.setEnabled(TableService.numberOfTables() 
//        saveTableAction.setEnabled(TableService.numberOfTables() != 0);
//        viewReportAction.setEnabled(TableService.numberOfTables() != 0);      
//        menuItemWriteBatchFile.setEnabled(Application.numberOfMetadatas() > 0);  
//
//        menuItemProtectJJFormat.setVisible(Application.isAnco());
//        menuItemAncoNews.setVisible(Application.isAnco());
//        menuItemSolverOptions.setVisible(Application.isAnco());
//
//        panelTable.setVisible(TableService.numberOfTables() != 0);
//        if (TableService.numberOfTables() != 0) {panelTable.enableHiddenFeatures(Application.isAnco());}> 1);     
//        menuItemLinkedTables.setEnabled(TableService.numberOfTables() != 0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        openMicrodataButton = new javax.swing.JButton();
        toolBarSeparator1 = new javax.swing.JToolBar.Separator();
        specifyMetaDataButton = new javax.swing.JButton();
        specifyCombinationsButton = new javax.swing.JButton();
        toolBarSeparator2 = new javax.swing.JToolBar.Separator();
        showTableCollectionButton = new javax.swing.JButton();
        globalRecodeButton = new javax.swing.JButton();
        pramSpecificationButton = new javax.swing.JButton();
        individualRiskSpecificationButton = new javax.swing.JButton();
        householdRiskSpecificationButton = new javax.swing.JButton();
        qualitativeMicroaggregationButton = new javax.swing.JButton();
        modifyNumericalVariablesButton = new javax.swing.JButton();
        numericalMicroaggregationButton = new javax.swing.JButton();
        numericalRankSwappingButton = new javax.swing.JButton();
        toolBarSeparator3 = new javax.swing.JToolBar.Separator();
        makeProtectedFileButton = new javax.swing.JButton();
        viewReportButton = new javax.swing.JButton();
        toolBarSeparator4 = new javax.swing.JToolBar.Separator();
        contentsButton = new javax.swing.JButton();
        newsButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        ManualButton = new javax.swing.JButton();
        unsafeCombinationsPanel = new javax.swing.JPanel();
        unsafeCombinationsLabel = new javax.swing.JLabel();
        unsafeCombinationsScrollPane = new javax.swing.JScrollPane();
        unsafeCombinationsTable = new javax.swing.JTable();
        variablesPanel = new javax.swing.JPanel();
        variableLabel = new javax.swing.JLabel();
        variablesScrollPane = new javax.swing.JScrollPane();
        variablesTable = new javax.swing.JTable();
        variableNameLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMicrodataMenuItem = new javax.swing.JMenuItem();
        fileSeparator = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        specifyMenu = new javax.swing.JMenu();
        metaDataMenuItem = new javax.swing.JMenuItem();
        combinationsMenuItem = new javax.swing.JMenuItem();
        modifyMenu = new javax.swing.JMenu();
        showTableCollectionMenuItem = new javax.swing.JMenuItem();
        globalRecodeMenuItem = new javax.swing.JMenuItem();
        modifySeparator1 = new javax.swing.JPopupMenu.Separator();
        pramSpecificationMenuItem = new javax.swing.JMenuItem();
        individualRiskSpecificationMenuItem = new javax.swing.JMenuItem();
        householdRiskSpecificationMenuItem = new javax.swing.JMenuItem();
        modifySeparator2 = new javax.swing.JPopupMenu.Separator();
        numericalVariablesMenuItem = new javax.swing.JMenuItem();
        numericalMicroaggregationMenuItem = new javax.swing.JMenuItem();
        numericalRankSwappingMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        qualitativeMicroaggregationMenuItem = new javax.swing.JMenuItem();
        outputMenu = new javax.swing.JMenu();
        makeProtectedFileMenuItem = new javax.swing.JMenuItem();
        viewReportMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        newsMenuItem = new javax.swing.JMenuItem();
        manualMenuItem = new javax.swing.JMenuItem();
        helpSeparator = new javax.swing.JPopupMenu.Separator();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Frame");

        toolBar.setRollover(true);
        toolBar.setEnabled(false);

        openMicrodataButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/OpenMicrodata.png"))); // NOI18N
        openMicrodataButton.setFocusable(false);
        openMicrodataButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openMicrodataButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openMicrodataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMicrodataMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(openMicrodataButton);
        toolBar.add(toolBarSeparator1);

        specifyMetaDataButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/SpecifyMetadata.png"))); // NOI18N
        specifyMetaDataButton.setEnabled(false);
        specifyMetaDataButton.setFocusable(false);
        specifyMetaDataButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        specifyMetaDataButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        specifyMetaDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metaDataMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(specifyMetaDataButton);

        specifyCombinationsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/SpecifyCombinations.png"))); // NOI18N
        specifyCombinationsButton.setEnabled(false);
        specifyCombinationsButton.setFocusable(false);
        specifyCombinationsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        specifyCombinationsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        specifyCombinationsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combinationsMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(specifyCombinationsButton);
        toolBar.add(toolBarSeparator2);

        showTableCollectionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/ShowTable.png"))); // NOI18N
        showTableCollectionButton.setEnabled(false);
        showTableCollectionButton.setFocusable(false);
        showTableCollectionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        showTableCollectionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        showTableCollectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTableCollectionMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(showTableCollectionButton);

        globalRecodeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/globrec_1_16x16x4.png"))); // NOI18N
        globalRecodeButton.setEnabled(false);
        globalRecodeButton.setFocusable(false);
        globalRecodeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        globalRecodeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        globalRecodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                globalRecodeMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(globalRecodeButton);

        pramSpecificationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/pram_1_16x16x4.png"))); // NOI18N
        pramSpecificationButton.setEnabled(false);
        pramSpecificationButton.setFocusable(false);
        pramSpecificationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pramSpecificationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pramSpecificationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pramSpecificationMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(pramSpecificationButton);

        individualRiskSpecificationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/Risk_1_16x16x4.png"))); // NOI18N
        individualRiskSpecificationButton.setEnabled(false);
        individualRiskSpecificationButton.setFocusable(false);
        individualRiskSpecificationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        individualRiskSpecificationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        individualRiskSpecificationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                individualRiskSpecificationMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(individualRiskSpecificationButton);

        householdRiskSpecificationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/hr.png"))); // NOI18N
        householdRiskSpecificationButton.setEnabled(false);
        householdRiskSpecificationButton.setFocusable(false);
        householdRiskSpecificationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        householdRiskSpecificationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        householdRiskSpecificationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                householdRiskSpecificationMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(householdRiskSpecificationButton);

        qualitativeMicroaggregationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/QM.png"))); // NOI18N
        qualitativeMicroaggregationButton.setEnabled(false);
        qualitativeMicroaggregationButton.setFocusable(false);
        qualitativeMicroaggregationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        qualitativeMicroaggregationButton.setPreferredSize(new java.awt.Dimension(23, 23));
        qualitativeMicroaggregationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        qualitativeMicroaggregationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qualitativeMicroaggregationMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(qualitativeMicroaggregationButton);

        modifyNumericalVariablesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/Numeric_1_16x16x4.png"))); // NOI18N
        modifyNumericalVariablesButton.setEnabled(false);
        modifyNumericalVariablesButton.setFocusable(false);
        modifyNumericalVariablesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        modifyNumericalVariablesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        modifyNumericalVariablesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numericalVariablesMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(modifyNumericalVariablesButton);

        numericalMicroaggregationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/MA.png"))); // NOI18N
        numericalMicroaggregationButton.setEnabled(false);
        numericalMicroaggregationButton.setFocusable(false);
        numericalMicroaggregationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        numericalMicroaggregationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        numericalMicroaggregationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numericalMicroaggregationMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(numericalMicroaggregationButton);

        numericalRankSwappingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/RS.png"))); // NOI18N
        numericalRankSwappingButton.setEnabled(false);
        numericalRankSwappingButton.setFocusable(false);
        numericalRankSwappingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        numericalRankSwappingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        numericalRankSwappingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numericalRankSwappingMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(numericalRankSwappingButton);
        toolBar.add(toolBarSeparator3);

        makeProtectedFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/MakeSafeFile.png"))); // NOI18N
        makeProtectedFileButton.setEnabled(false);
        makeProtectedFileButton.setFocusable(false);
        makeProtectedFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        makeProtectedFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        makeProtectedFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeProtectedFileMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(makeProtectedFileButton);

        viewReportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/ViewReport.png"))); // NOI18N
        viewReportButton.setEnabled(false);
        viewReportButton.setFocusable(false);
        viewReportButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewReportButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        viewReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(viewReportButton);
        toolBar.add(toolBarSeparator4);

        contentsButton.setText("Content");
        contentsButton.setFocusable(false);
        contentsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        contentsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        contentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentsMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(contentsButton);

        newsButton.setText("News");
        newsButton.setFocusable(false);
        newsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newsMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(newsButton);

        aboutButton.setText("About");
        aboutButton.setFocusable(false);
        aboutButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aboutButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        toolBar.add(aboutButton);

        ManualButton.setText("Manual");
        ManualButton.setFocusable(false);
        ManualButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ManualButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ManualButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHelpActionPerformed(evt);
            }
        });
        toolBar.add(ManualButton);

        unsafeCombinationsPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        unsafeCombinationsLabel.setText("# unsafe combinations in each dimension");

        unsafeCombinationsTable.setAutoCreateRowSorter(true);
        unsafeCombinationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Variable", "dim 1", ""
            }
        ));
        unsafeCombinationsTable.setShowHorizontalLines(false);
        unsafeCombinationsTable.setShowVerticalLines(false);
        unsafeCombinationsTable.getTableHeader().setReorderingAllowed(false);
        unsafeCombinationsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unsafeCombinationsTableMouseClicked(evt);
            }
        });
        unsafeCombinationsScrollPane.setViewportView(unsafeCombinationsTable);

        javax.swing.GroupLayout unsafeCombinationsPanelLayout = new javax.swing.GroupLayout(unsafeCombinationsPanel);
        unsafeCombinationsPanel.setLayout(unsafeCombinationsPanelLayout);
        unsafeCombinationsPanelLayout.setHorizontalGroup(
            unsafeCombinationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(unsafeCombinationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(unsafeCombinationsLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, unsafeCombinationsPanelLayout.createSequentialGroup()
                .addComponent(unsafeCombinationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        unsafeCombinationsPanelLayout.setVerticalGroup(
            unsafeCombinationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(unsafeCombinationsPanelLayout.createSequentialGroup()
                .addComponent(unsafeCombinationsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unsafeCombinationsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
        );

        variablesPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        variableLabel.setText("Variable:");

        variablesTable.setAutoCreateRowSorter(true);
        variablesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Label", "Freq", "dim1", ""
            }
        ));
        variablesTable.setShowHorizontalLines(false);
        variablesTable.setShowVerticalLines(false);
        variablesTable.getTableHeader().setReorderingAllowed(false);
        variablesScrollPane.setViewportView(variablesTable);

        javax.swing.GroupLayout variablesPanelLayout = new javax.swing.GroupLayout(variablesPanel);
        variablesPanel.setLayout(variablesPanelLayout);
        variablesPanelLayout.setHorizontalGroup(
            variablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(variablesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(variableLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(variableNameLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(variablesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        variablesPanelLayout.setVerticalGroup(
            variablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(variablesPanelLayout.createSequentialGroup()
                .addGroup(variablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(variableLabel)
                    .addComponent(variableNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(variablesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        openMicrodataMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMicrodataMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/OpenMicrodata.png"))); // NOI18N
        openMicrodataMenuItem.setText("Open micro data");
        openMicrodataMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMicrodataMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMicrodataMenuItem);
        fileMenu.add(fileSeparator);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        specifyMenu.setText("Specify");

        metaDataMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        metaDataMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/SpecifyMetadata.png"))); // NOI18N
        metaDataMenuItem.setText("MetaData");
        metaDataMenuItem.setEnabled(false);
        metaDataMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metaDataMenuItemActionPerformed(evt);
            }
        });
        specifyMenu.add(metaDataMenuItem);

        combinationsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        combinationsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/SpecifyCombinations.png"))); // NOI18N
        combinationsMenuItem.setText("Combinations");
        combinationsMenuItem.setEnabled(false);
        combinationsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combinationsMenuItemActionPerformed(evt);
            }
        });
        specifyMenu.add(combinationsMenuItem);

        menuBar.add(specifyMenu);

        modifyMenu.setText("Modify");

        showTableCollectionMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        showTableCollectionMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/ShowTable.png"))); // NOI18N
        showTableCollectionMenuItem.setText("Show Table Collection");
        showTableCollectionMenuItem.setEnabled(false);
        showTableCollectionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTableCollectionMenuItemActionPerformed(evt);
            }
        });
        modifyMenu.add(showTableCollectionMenuItem);

        globalRecodeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        globalRecodeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/globrec_1_16x16x4.png"))); // NOI18N
        globalRecodeMenuItem.setText("Global Recode");
        globalRecodeMenuItem.setEnabled(false);
        globalRecodeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                globalRecodeMenuItemActionPerformed(evt);
            }
        });
        modifyMenu.add(globalRecodeMenuItem);
        modifyMenu.add(modifySeparator1);

        pramSpecificationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        pramSpecificationMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/pram_1_16x16x4.png"))); // NOI18N
        pramSpecificationMenuItem.setText("PRAM Specification");
        pramSpecificationMenuItem.setEnabled(false);
        pramSpecificationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pramSpecificationMenuItemActionPerformed(evt);
            }
        });
        modifyMenu.add(pramSpecificationMenuItem);

        individualRiskSpecificationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        individualRiskSpecificationMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/Risk_1_16x16x4.png"))); // NOI18N
        individualRiskSpecificationMenuItem.setText("Individual Risk Specification");
        individualRiskSpecificationMenuItem.setEnabled(false);
        individualRiskSpecificationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                individualRiskSpecificationMenuItemActionPerformed(evt);
            }
        });
        modifyMenu.add(individualRiskSpecificationMenuItem);

        householdRiskSpecificationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        householdRiskSpecificationMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/hr.png"))); // NOI18N
        householdRiskSpecificationMenuItem.setText("Household Risk Specification");
        householdRiskSpecificationMenuItem.setEnabled(false);
        householdRiskSpecificationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                householdRiskSpecificationMenuItemActionPerformed(evt);
            }
        });
        modifyMenu.add(householdRiskSpecificationMenuItem);
        modifyMenu.add(modifySeparator2);

        numericalVariablesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        numericalVariablesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/Numeric_1_16x16x4.png"))); // NOI18N
        numericalVariablesMenuItem.setText("Modify Numerical Variables");
        numericalVariablesMenuItem.setEnabled(false);
        numericalVariablesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numericalVariablesMenuItemActionPerformed(evt);
            }
        });
        modifyMenu.add(numericalVariablesMenuItem);

        numericalMicroaggregationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        numericalMicroaggregationMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/MA.png"))); // NOI18N
        numericalMicroaggregationMenuItem.setText("Numerical Micro Aggregation");
        numericalMicroaggregationMenuItem.setEnabled(false);
        numericalMicroaggregationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numericalMicroaggregationMenuItemActionPerformed(evt);
            }
        });
        modifyMenu.add(numericalMicroaggregationMenuItem);

        numericalRankSwappingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        numericalRankSwappingMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/RS.png"))); // NOI18N
        numericalRankSwappingMenuItem.setText("Numerical Rank Swapping");
        numericalRankSwappingMenuItem.setEnabled(false);
        numericalRankSwappingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numericalRankSwappingMenuItemActionPerformed(evt);
            }
        });
        modifyMenu.add(numericalRankSwappingMenuItem);
        modifyMenu.add(jSeparator1);

        qualitativeMicroaggregationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        qualitativeMicroaggregationMenuItem.setText("Qualitative Micro Aggregation");
        qualitativeMicroaggregationMenuItem.setEnabled(false);
        qualitativeMicroaggregationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qualitativeMicroaggregationMenuItemActionPerformed(evt);
            }
        });
        modifyMenu.add(qualitativeMicroaggregationMenuItem);

        menuBar.add(modifyMenu);

        outputMenu.setText("Output");

        makeProtectedFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        makeProtectedFileMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/MakeSafeFile.png"))); // NOI18N
        makeProtectedFileMenuItem.setText("Make protected file");
        makeProtectedFileMenuItem.setEnabled(false);
        makeProtectedFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeProtectedFileMenuItemActionPerformed(evt);
            }
        });
        outputMenu.add(makeProtectedFileMenuItem);

        viewReportMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        viewReportMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/muargus/resources/icons/ViewReport.png"))); // NOI18N
        viewReportMenuItem.setText("View report");
        viewReportMenuItem.setEnabled(false);
        viewReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportMenuItemActionPerformed(evt);
            }
        });
        outputMenu.add(viewReportMenuItem);

        menuBar.add(outputMenu);

        helpMenu.setText("Help");

        contentsMenuItem.setText("Contents");
        contentsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentsMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(contentsMenuItem);

        newsMenuItem.setText("News");
        newsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newsMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(newsMenuItem);

        manualMenuItem.setText("Manual");
        manualMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(manualMenuItem);
        helpMenu.add(helpSeparator);

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(unsafeCombinationsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(variablesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(unsafeCombinationsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(variablesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openMicrodataMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMicrodataMenuItemActionPerformed
        controller.openMicrodata();
    }//GEN-LAST:event_openMicrodataMenuItemActionPerformed

    public DataFilePair showOpenMicrodataDialog(DataFilePair filenames) {
        DialogOpenMicrodata dialog = new DialogOpenMicrodata(this, true);
        dialog.setDataFileNames(filenames.getDataFileName(), filenames.getMetaFileName());
        if (dialog.showDialog() == DialogOpenMicrodata.APPROVE_OPTION) {
            return dialog.getMicrodataFilePair();
        }
        return null;
    }

    public void showUnsafeCombinations(Combinations model, int selectedIndex) {
        this.model = model;
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Variable");
        int nDims = model.getMaxDimsInTables();
        for (int dimNr = 1; dimNr <= nDims; dimNr++) {
            columnNames.add("dim " + dimNr);
        }

        Object[][] data = new Object[model.getVariablesInTables().size()][];
        int rowIndex = 0;
        for (VariableMu variable : model.getVariablesInTables()) {
            data[rowIndex] =  toObjectArray(model, variable); //TODO mooier
            rowIndex++;
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames.toArray()) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        this.unsafeCombinationsTable.setModel(tableModel);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        for (int index=1; index < columnNames.size(); index++) {
            this.unsafeCombinationsTable.getColumn(String.format("dim %d", index)).setCellRenderer(rightRenderer);
        }
        
        this.unsafeCombinationsTable.getSelectionModel().addListSelectionListener(
                new javax.swing.event.ListSelectionListener() {
                    @Override
                    public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                        selectionChanged(evt);
                    }
                });

        this.unsafeCombinationsTable.getSelectionModel().setSelectionInterval(selectedIndex, selectedIndex);
    }

    
    private Object[] toObjectArray(Combinations combinations, VariableMu variable) {
        int nDims = combinations.getMaxDimsInTables();
        Object[] objArr = new Object[nDims + 1];
        objArr[0] = variable.getName();
        for (int dimNr=1; dimNr <= nDims; dimNr++) {
            objArr[dimNr] = combinations.getUnsafeCombinations().get(variable).length < dimNr ?
                    "-" : Integer.toString(combinations.getUnsafeCombinations().get(variable)[dimNr-1]);
        }
        return objArr;
    }
    

    private void selectionChanged(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            return;
        }
        int j = ((ListSelectionModel) evt.getSource()).getMinSelectionIndex();
        if (0 > j || j >= this.model.getVariablesInTables().size()) {
            return;
        }
        j = unsafeCombinationsTable.convertRowIndexToModel(j);
        VariableMu variable = this.model.getVariablesInTables().get(j);
        //UnsafeInfo unsafeInfo = this.model.getUnsafe(variable);
        this.variableNameLabel.setText(variable.getName());

        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Code");
        columnNames.add("Label");
        columnNames.add("Freq");
        int nDims = model.getMaxDimsInTables();
        for (int dimNr = 1; dimNr <= nDims; dimNr++) {
            columnNames.add("dim " + dimNr);
        }

        Object[][] data = new Object[variable.getCodeInfos().size()][];
        int rowIndex = 0;
        for (CodeInfo codeInfo : variable.getCodeInfos()) {
            data[rowIndex] = codeInfo.toObjectArray(nDims);
            rowIndex++;
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames.toArray()) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
            
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return CodeInfo.class;
                    case 1:
                        return String.class;
                    default:
                        return Integer.class;
                }
            }
        };
        variablesTable.setModel(tableModel);
        variablesTable.setDefaultRenderer(Integer.class, new CodeTableCellRenderer());
        variablesTable.setDefaultRenderer(Object.class, new CodeTableCellRenderer());

    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        controller.exit();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void metaDataMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metaDataMenuItemActionPerformed
//        String e = OpenMicrodataModel.getMicrodataPath();
//        System.out.printf("%s\n", e);
//        String f = OpenMicrodataModel.getMetadataPath();
//        System.out.printf("%s\n", f);
//        String c = metadata.dataFile;
//        System.out.printf("%s\n", c);
//        String d = metadata.metaFile;
//        System.out.printf("%s\n", d);
//        String a = dataFilePair.getDataFileName();
//        System.out.printf("%s\n", a);
//        String b = dataFilePair.getMetaFileName();
//        System.out.printf("%s\n", b);

        controller.specifyMetaData();
    }//GEN-LAST:event_metaDataMenuItemActionPerformed

    private void combinationsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combinationsMenuItemActionPerformed
        controller.specifyCombinations();
    }//GEN-LAST:event_combinationsMenuItemActionPerformed

    private void showTableCollectionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTableCollectionMenuItemActionPerformed
        controller.showTableCollection();
    }//GEN-LAST:event_showTableCollectionMenuItemActionPerformed

    private void globalRecodeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_globalRecodeMenuItemActionPerformed
        controller.globalRecode(this.unsafeCombinationsTable.getSelectionModel().getMinSelectionIndex());
    }//GEN-LAST:event_globalRecodeMenuItemActionPerformed

    private void pramSpecificationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pramSpecificationMenuItemActionPerformed
        controller.pramSpecification();
    }//GEN-LAST:event_pramSpecificationMenuItemActionPerformed

    private void individualRiskSpecificationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_individualRiskSpecificationMenuItemActionPerformed
        controller.individualRiskSpecification();
    }//GEN-LAST:event_individualRiskSpecificationMenuItemActionPerformed

    private void householdRiskSpecificationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_householdRiskSpecificationMenuItemActionPerformed
        controller.householdRiskSpecification();
    }//GEN-LAST:event_householdRiskSpecificationMenuItemActionPerformed

    private void numericalVariablesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numericalVariablesMenuItemActionPerformed
        controller.numericalVariables();
    }//GEN-LAST:event_numericalVariablesMenuItemActionPerformed

    private void numericalMicroaggregationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numericalMicroaggregationMenuItemActionPerformed
        controller.numericalMicroaggregation();
    }//GEN-LAST:event_numericalMicroaggregationMenuItemActionPerformed

    private void numericalRankSwappingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numericalRankSwappingMenuItemActionPerformed
        controller.numericalRankSwapping();
    }//GEN-LAST:event_numericalRankSwappingMenuItemActionPerformed

    private void makeProtectedFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeProtectedFileMenuItemActionPerformed
        controller.makeProtectedFile();
    }//GEN-LAST:event_makeProtectedFileMenuItemActionPerformed

    private void viewReportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportMenuItemActionPerformed
        controller.viewReport(false);
    }//GEN-LAST:event_viewReportMenuItemActionPerformed

    private void contentsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentsMenuItemActionPerformed
        controller.contents();
    }//GEN-LAST:event_contentsMenuItemActionPerformed

    private void newsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newsMenuItemActionPerformed
        System.out.println("clicked");
        //this.variablesTable.getColumnModel().removeColumn(this.variablesTable.getColumnModel().getColumn(0));
        //this.variablesTable.updateUI();
        //this.unsafeCombinationsTable.removeAll();

        controller.news();
    }//GEN-LAST:event_newsMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        controller.about();
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void jMenuHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuHelpActionPerformed
        controller.manual();
    }//GEN-LAST:event_jMenuHelpActionPerformed

    private void manualMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualMenuItemActionPerformed
        controller.manual();
    }//GEN-LAST:event_manualMenuItemActionPerformed

    private void unsafeCombinationsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unsafeCombinationsTableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            controller.globalRecode(this.unsafeCombinationsTable.getSelectionModel().getMinSelectionIndex());
        }
    }//GEN-LAST:event_unsafeCombinationsTableMouseClicked

    private void qualitativeMicroaggregationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qualitativeMicroaggregationMenuItemActionPerformed
        controller.qualitativeMicroaggregation();
        // TODO add your handling code here:
    }//GEN-LAST:event_qualitativeMicroaggregationMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrameView().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ManualButton;
    private javax.swing.JButton aboutButton;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem combinationsMenuItem;
    private javax.swing.JButton contentsButton;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator fileSeparator;
    private javax.swing.JButton globalRecodeButton;
    private javax.swing.JMenuItem globalRecodeMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPopupMenu.Separator helpSeparator;
    private javax.swing.JButton householdRiskSpecificationButton;
    private javax.swing.JMenuItem householdRiskSpecificationMenuItem;
    private javax.swing.JButton individualRiskSpecificationButton;
    private javax.swing.JMenuItem individualRiskSpecificationMenuItem;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton makeProtectedFileButton;
    private javax.swing.JMenuItem makeProtectedFileMenuItem;
    private javax.swing.JMenuItem manualMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem metaDataMenuItem;
    private javax.swing.JMenu modifyMenu;
    private javax.swing.JButton modifyNumericalVariablesButton;
    private javax.swing.JPopupMenu.Separator modifySeparator1;
    private javax.swing.JPopupMenu.Separator modifySeparator2;
    private javax.swing.JButton newsButton;
    private javax.swing.JMenuItem newsMenuItem;
    private javax.swing.JButton numericalMicroaggregationButton;
    private javax.swing.JMenuItem numericalMicroaggregationMenuItem;
    private javax.swing.JButton numericalRankSwappingButton;
    private javax.swing.JMenuItem numericalRankSwappingMenuItem;
    private javax.swing.JMenuItem numericalVariablesMenuItem;
    private javax.swing.JButton openMicrodataButton;
    private javax.swing.JMenuItem openMicrodataMenuItem;
    private javax.swing.JMenu outputMenu;
    private javax.swing.JButton pramSpecificationButton;
    private javax.swing.JMenuItem pramSpecificationMenuItem;
    private javax.swing.JButton qualitativeMicroaggregationButton;
    private javax.swing.JMenuItem qualitativeMicroaggregationMenuItem;
    private javax.swing.JButton showTableCollectionButton;
    private javax.swing.JMenuItem showTableCollectionMenuItem;
    private javax.swing.JButton specifyCombinationsButton;
    private javax.swing.JMenu specifyMenu;
    private javax.swing.JButton specifyMetaDataButton;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JToolBar.Separator toolBarSeparator1;
    private javax.swing.JToolBar.Separator toolBarSeparator2;
    private javax.swing.JToolBar.Separator toolBarSeparator3;
    private javax.swing.JToolBar.Separator toolBarSeparator4;
    private javax.swing.JLabel unsafeCombinationsLabel;
    private javax.swing.JPanel unsafeCombinationsPanel;
    private javax.swing.JScrollPane unsafeCombinationsScrollPane;
    private javax.swing.JTable unsafeCombinationsTable;
    private javax.swing.JLabel variableLabel;
    private javax.swing.JLabel variableNameLabel;
    private javax.swing.JPanel variablesPanel;
    private javax.swing.JScrollPane variablesScrollPane;
    private javax.swing.JTable variablesTable;
    private javax.swing.JButton viewReportButton;
    private javax.swing.JMenuItem viewReportMenuItem;
    // End of variables declaration//GEN-END:variables
}
