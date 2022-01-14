/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muargus.controller;

import argus.model.ArgusException;
import argus.utils.SystemUtils;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import muargus.CalculationService;
import muargus.MuARGUS;
import muargus.extern.dataengine.Numerical;
import muargus.model.MetadataMu;
import muargus.model.TargetSwappingSpec;
import muargus.model.ReplacementFile;
import muargus.model.ReplacementSpec;
import muargus.model.TargetedRecordSwapping;
import muargus.model.VariableMu;
import muargus.view.TargetedRecordSwappingView;

/**
 *
 * @author pwof
 */
public class TargetedRecordSwappingController extends ControllerBase<TargetedRecordSwapping>{
     private final MetadataMu metadata;

    /**
     * Constructor for the TargetedRecordSwappingController.
     *
     * @param parentView the Frame of the mainFrame.
     * @param metadata the original metadata.
     */
    public TargetedRecordSwappingController(java.awt.Frame parentView, MetadataMu metadata) {
        super.setView(new TargetedRecordSwappingView(parentView, true, this));
        this.metadata = metadata;
        fillModel();
        getView().setMetadata(metadata);
    }

    /**
     * Gets the model and fills the model with the categorical variables if the
     * model is empty.
     */
    private void fillModel() {
        TargetedRecordSwapping model = metadata.getCombinations().getTargetedRecordSwapping();
        //TargetedRecordSwapping model = metadata.getTargetedRecordSwapping();
        if (model.getVariables().isEmpty()) {
            for (VariableMu variable : this.metadata.getVariables()) {
                if (!variable.isNumeric()) {
                    model.getVariables().add(variable);
                }
            }
        }
        setModel(model);
    }

    /**
     * Closes the view by setting its visibility to false.
     */
    public void close() {
        getView().setVisible(false);
    }

    /**
     * Does the next step if the previous step was successful.
     *
     * @param success Boolean indicating whether the previous step was
     * successful.
     */
    @Override
    protected void doNextStep(boolean success) {
        TargetSwappingSpec swapping = getModel().getTargetSwappings().get(getModel().getTargetSwappings().size() - 1);
        Numerical num = new Numerical(); // instance of the library-class
        int[] errorCode = new int[1];
        int[] count_HID = new int[1];
        int[] count_record = new int[1];

        // Remove logfile from previous run
        File f1 = new File(MuARGUS.getTempDir()+"\\NonSwappedHID.txt");
        f1.delete();
        
        num.DoTargetedRecordSwap(swapping.getReplacementFile().getInputFilePath(),
                                 swapping.getReplacementFile().getOutputFilePath(),
                                 muargus.MuARGUS.getDefaultSeparator(),
                                 swapping.getOutputVariables().size(),
                                 swapping.getSwaprate(),
                                 swapping.getSimilarIndexes(),
                                 swapping.getNSim(),
                                 swapping.getHierarchyIndexes(),
                                 swapping.getNHier(),
                                 swapping.getRiskIndexes(),
                                 swapping.getNRisk(),
                                 swapping.getCarryIndexes(),
                                 swapping.getNCarry(),
                                 swapping.getHHID(),
                                 swapping.getkThreshold(),
                                 count_record,
                                 count_HID,
                                 swapping.getSeed(),
                                 errorCode,
                                 MuARGUS.getTempDir() + "\\NonSwappedHID.txt"
                                 );

        if (errorCode[0] != 0) {
            getView().showErrorMessage(new ArgusException("Error during targeted record swapping"));
            this.metadata.getReplacementSpecs().remove(swapping);
            getModel().getTargetSwappings().remove(swapping);
        } else {
            if (f1.exists()){
                try{
                    BufferedReader br = new BufferedReader(new FileReader(f1));
                    String[] Words = br.readLine().split(" ");
                    br.close();
                    swapping.setCountNoDonor(Integer.parseInt(Words[0]));
                    String Message = "WARNING: ";
                    // First word in the file is number of non-swapped households
                    Message += Words[0] + " households could not be swapped because no donor could be found (" + 
                               count_HID[0] + " households did get swapped).\n";
                    Message += "\nSee \"" + MuARGUS.getTempDir()+"\\NonSwappedHID.txt\" for the HID of households without donor.\n";
                    getView().showMessage(Message);
                    SystemUtils.writeLogbook(Message);
                }
                catch (Exception ex) {getView().showMessage(ex.getMessage());} 
            }
            else{
                getView().showMessage("TargetedRecordSwapping successfully completed.\n" + count_HID[0] + " households are swapped.");
            }

            SystemUtils.writeLogbook("Tageted record swapping has been done.\n" + count_HID[0] + " households are swapped.");
            swapping.setCountSwappedHID(count_HID[0]);
            swapping.setCountSwappedRecords(count_record[0]);
        }
        getView().setProgress(0);
        getView().showStepName("");
        getTargetedRecordSwappingView().updateVariableRows(swapping);
        swapping.setIsCalculated(true);
        getTargetedRecordSwappingView().enableMoveButtons(false);
    }

    /**
     * Gets the NumericalRankSwapping view.
     *
     * @return TargetedRecordSwappingView
     */
    private TargetedRecordSwappingView getTargetedRecordSwappingView() {
        return (TargetedRecordSwappingView) getView();
    }

    /**
     * Checks whether the value for the percentage is valid.
     *
     * @return Boolean indicating whether the value for the percentage is valid.
     */
    private boolean checkFields() {
        double percentage = getTargetedRecordSwappingView().getSwaprate();
        if (Double.isNaN(percentage) || percentage < 0 || percentage > 1) {
            getView().showErrorMessage(new ArgusException("Illegal value for the swaprate (should be > 0 and < 1)"));
            return false;
        }
        return true;
    }

    /**
     * Undo the applied Targeted Record Swapping
     */
    public void undo() {
        ArrayList<VariableMu> selected = new ArrayList<>();
        selected.addAll(getTargetedRecordSwappingView().getSelectedSimilarVariables());
        for (VariableMu variable : getTargetedRecordSwappingView().getSelectedHierarchyVariables()){
            if (!selected.contains(variable)) selected.add(variable);
        }
        for (VariableMu variable : getTargetedRecordSwappingView().getSelectedRiskVariables()){
            if (!selected.contains(variable)) selected.add(variable);
        }
        for (VariableMu variable : getTargetedRecordSwappingView().getSelectedCarryVariables()){
            if (!selected.contains(variable)) selected.add(variable);
        }
        
        if (selected.isEmpty()) {
            getView().showMessage(String.format("Nothing to Undo: Targeted Record Swapping was not applied yet.\n"));
            return;
        }
        selected.add(getTargetedRecordSwappingView().getHHIDVar());
        
        if (!getView().showConfirmDialog(String.format("The Targeted Record Swapping involving %s will be removed. Continue?",
                VariableMu.printVariableNames(selected)))) {
            return;
        }
        
        String rankSwappings = (getModel().getTargetSwappings().size()>1) ? "s are:" : " is:";
        for (TargetSwappingSpec swapping : getModel().getTargetSwappings()) {
            if (swapping.getOutputVariables().size() == selected.size()) {
                boolean difference = false;
                for (VariableMu variable : swapping.getOutputVariables()) {
                    if (!selected.contains(variable)) {
                        difference = true;
                        break;
                    }
                }
                if (!difference) {
                    getModel().getTargetSwappings().remove(swapping);
                    this.metadata.getReplacementSpecs().remove(swapping);
                    SystemUtils.writeLogbook("Targeted Record Swapping has been undone.");
                    getTargetedRecordSwappingView().updateVariableRows(swapping);
                    swapping.setIsCalculated(false);
                    getTargetedRecordSwappingView().enableMoveButtons(true);                    
                    return;
                }
            }
            rankSwappings += "\n- " + VariableMu.printVariableNames(swapping.getOutputVariables());
        }
        
        getView().showMessage(String.format("Targeted Record Swapping involving %s not found.\n"
                + "The available swapping" + rankSwappings, VariableMu.printVariableNames(selected)));
    }

    /**
     * Calculates the numerical rank swapping.
     */
    public void calculate() {
        if (!checkFields()) {
            return;
        }
        
        ArrayList<VariableMu> selectedSimilarVariables = getTargetedRecordSwappingView().getSelectedSimilarVariables();
        ArrayList<VariableMu> selectedHierarchyVariables = getTargetedRecordSwappingView().getSelectedHierarchyVariables();
        ArrayList<VariableMu> selectedRiskVariables = getTargetedRecordSwappingView().getSelectedRiskVariables();
        ArrayList<VariableMu> selectedCarryVariables = getTargetedRecordSwappingView().getSelectedCarryVariables();
        if (variablesAreUsed(selectedSimilarVariables)||variablesAreUsed(selectedHierarchyVariables)||variablesAreUsed(selectedRiskVariables)) {
            if (!getView().showConfirmDialog("One or more of the selected variables are already modified. Continue?")) {
                return;
            }
        }
        
        ArrayList<VariableMu> selectedVariables = new ArrayList<>();
        
        selectedVariables.addAll(selectedSimilarVariables);
        for (VariableMu variable : selectedHierarchyVariables){
            if (!selectedVariables.contains(variable)) selectedVariables.add(variable);
        }
        for (VariableMu variable : selectedRiskVariables){
            if (!selectedVariables.contains(variable)) selectedVariables.add(variable);
        }
        for (VariableMu variable : selectedCarryVariables){
            if (!selectedVariables.contains(variable)) selectedVariables.add(variable);
        }
        selectedVariables.add(getTargetedRecordSwappingView().getHHIDVar());
        
        TargetSwappingSpec targetSwapping = new TargetSwappingSpec(selectedSimilarVariables.size(),
                                                                   selectedHierarchyVariables.size(),
                                                                   selectedRiskVariables.size(),
                                                                   selectedCarryVariables.size(),
                                                                   getTargetedRecordSwappingView().getSwaprate(),
                                                                   getTargetedRecordSwappingView().getkanonThreshold(),
                                                                   getTargetedRecordSwappingView().getSeed());
        try {
            CalculationService service = MuARGUS.getCalculationService();
            targetSwapping.getOutputVariables().addAll(selectedVariables);
            targetSwapping.setReplacementFile(new ReplacementFile("TargetSwapping"));
            targetSwapping.calculateSimilarIndexes(selectedSimilarVariables);
            targetSwapping.calculateHierarchyIndexes(selectedHierarchyVariables);
            targetSwapping.calculateRiskIndexes(selectedRiskVariables);
            targetSwapping.calculateCarryIndexes(selectedCarryVariables);
            targetSwapping.calculateHHIdIndex(getTargetedRecordSwappingView().getHHIDVar());
            this.metadata.getReplacementSpecs().add(targetSwapping);
            // Begin Calculation Service heeft mogelijk nog niet voldoende metadata, als TRS wordt aangeroepen vóór combinations gezet zijn
            //service.setMetadata(this.metadata);
            //service.exploreFile(this);
            // End Calculation Service heeft mogelijk nog niet voldoende metadata, als TRS wordt aangeroepen vóór combinations gezet zijn
            service.makeReplacementFile(this);
            getModel().getTargetSwappings().add(targetSwapping);
        } catch (ArgusException ex) {
            this.metadata.getReplacementSpecs().remove(targetSwapping);
            getView().showErrorMessage(ex);
        }
    }

    /**
     * Returns whether at least one variable is used.
     *
     * @param variables Arraylist of VariableMu's.
     * @return Boolean indicating whether at least one variable is used.
     */
    private boolean variablesAreUsed(ArrayList<VariableMu> variables) {
        for (VariableMu variable : variables) {
            for (ReplacementSpec replacement : this.metadata.getReplacementSpecs()) {
                if (replacement.getOutputVariables().contains(variable)) {
                    return true;
                }
            }
        }
        return false;
    }

}
