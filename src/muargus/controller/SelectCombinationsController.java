/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muargus.controller;

import argus.model.ArgusException;
import argus.utils.SystemUtils;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.logging.Logger;
import muargus.MuARGUS;
import muargus.model.MetadataMu;
import muargus.model.Combinations;
import muargus.view.SelectCombinationsView;

/**
 *
 * @author ambargus
 */
public class SelectCombinationsController extends  ControllerBase {
    
    Combinations modelClone;
    MetadataMu metadata;
        
    private static final Logger logger = Logger.getLogger(SelectCombinationsController.class.getName());

    public SelectCombinationsController(java.awt.Frame parentView, MetadataMu metadata) {
        super.setView(new SelectCombinationsView(parentView, true, this));
        this.metadata = metadata;
        
        getSettings();
        this.modelClone = new Combinations(this.metadata.getCombinations());

        getView().setMetadata(this.metadata); 
        getSelectCombinationsView().setModel(this.modelClone); // the view gets a copy of the current Combinations
    }
    
    public void showView() {
        getView().setVisible(true);
    }
    
    private SelectCombinationsView getSelectCombinationsView() {
        return (SelectCombinationsView)getView();
    }
    
//    public void setList(ArrayList<String> list){
//        DefaultListModel model1 = new DefaultListModel();
//        for(String s: list){
//            model1.addElement(s);
//        }
//    }
    
    /**
     * 
     * @throws argus.model.ArgusException
     */
    public void calculateTables() throws ArgusException {
        getSelectCombinationsView().enableCalculateTables(false);
        saveSettings();
        this.metadata.setCombinations(this.modelClone);
        CalculationService service = MuARGUS.getCalculationService();
        service.setMetadata(this.metadata);
        service.exploreFile(this);        
    }
    
    private void getSettings() {
        int[] thresholds = new int[MuARGUS.MAXDIMS];
        for (int t=1; t <= MuARGUS.MAXDIMS; t++) {
            thresholds[t-1] = SystemUtils.getRegInteger("general", "threshold" + Integer.toString(t), 1);
        }
        this.metadata.getCombinations().setThresholds(thresholds);
    }
    
    private void saveSettings() {
        int[] thresholds = this.metadata.getCombinations().getThresholds();
        if (thresholds == null || thresholds.length < MuARGUS.MAXDIMS) {
            return;
        }
        for (int t=1; t <= MuARGUS.MAXDIMS; t++) {
            SystemUtils.putRegInteger("general", "threshold" + Integer.toString(t), thresholds[t-1]); 
        }
    }
                                                                  

                                                          

    /**
     * 
     */
    public void automaticSpecification() {                                                             
        //het zou mooier zijn als de berekening niet in de view zou gebeuren
    }                                                            

    /**
     * 
     */
    public void cancel() {                                             
        getView().setVisible(false);
    } 

    @Override
    protected void doNextStep(boolean success) {
        if (success && getStepName().equals("ExploreFile")) {
            MuARGUS.getCalculationService().calculateTables(this);
        }
        else {
            getSelectCombinationsView().enableCalculateTables(true);
            if (success) {
                getView().setVisible(false);
            }
        }
    }
 
//    public void clearData(){
//        this.controller.clearDataAfterSelectCombinations();
//    }
}
