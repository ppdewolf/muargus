/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muargus.model;

import java.util.ArrayList;

/**
 *
 * @author ambargus
 */
public class GlobalRecodeModel {
    private ArrayList<VariableMu> variables;
    private ArrayList<RecodeMu> recodeMus;
    private final String[] columnNames;

    public GlobalRecodeModel() {
        this.variables = new ArrayList<>();
        this.recodeMus = new ArrayList<>();
        this.columnNames = new String[]{"R", "Variables"};
    }
    
    public void setVariables(ArrayList<VariableMu> variables){
        this.variables = variables;
    }

    public ArrayList<VariableMu> getVariables() {
        return variables;
    }
    
    public void addRecodeMu(RecodeMu recodeMu){
        this.recodeMus.add(recodeMu);
    }

    public ArrayList<RecodeMu> getRecodeMus() {
        return recodeMus;
    }

    public String[] getColumnNames() {
        return columnNames;
    }
    
    
    
    

    

    
}
