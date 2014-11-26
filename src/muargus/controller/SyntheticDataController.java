package muargus.controller;

import argus.model.ArgusException;
import java.io.IOException;
import java.util.List;
import muargus.io.MetaWriter;
import muargus.model.MetadataMu;
import muargus.model.ReplacementFile;
import muargus.model.SyntheticDataSpec;
import muargus.model.VariableMu;
import muargus.view.SyntheticDataView;

/**
 *
 * @author pibd05
 */
public class SyntheticDataController extends ControllerBase<SyntheticDataSpec> {

    private final MetadataMu metadata;
    //private final static String pathRexe = "C:\\Program Files\\R\\R-3.1.2\\bin\\RScript.exe";

    /**
     * Constructor for the RController.
     *
     * @param parentView the Frame of the mainFrame.
     * @param metadata the orginal metadata.
     */
    public SyntheticDataController(java.awt.Frame parentView, MetadataMu metadata) {
        super.setView(new SyntheticDataView(parentView, true, this));
        this.metadata = metadata;
        setModel(this.metadata.getCombinations().getSyntheticData());
        fillModel();
        getView().setMetadata(this.metadata);
    }

    /**
     * Closes the view by setting its visibility to false.
     */
    public void close() {
        getView().setVisible(false);
    }

//    public Object[][] getSensitiveData() {
//        Object[][] data = new Object[getSensitiveVariables().size()][2];
//        for (int i = 0; i < getSensitiveVariables().size(); i++) {
//            data[i][0] = getSensitiveVariables().get(i);
//            data[i][1] = getSensitiveVariables().get(i).getAlpha();
//        }
//        return data;
//    }
    public void runSyntheticData() {
        try {
            SyntheticDataSpec syntheticData = getModel();

            syntheticData.getSensitiveVariables().addAll(getSensitiveVariables());
            syntheticData.getNonSensitiveVariables().addAll(getNonSensitiveVariables());
            syntheticData.setReplacementFile(new ReplacementFile("SyntheticData"));
            this.metadata.getReplacementSpecs().add(syntheticData);

            /* synthetic data: sensitive variables are numbered from x1 to xn,
             non-sensitive variables are numbered from s1 to sn.
             */
            MetaWriter.writeAlpha(syntheticData);
            MetaWriter.writeSynthetic(syntheticData);
            writeSyntheticData();
            MetaWriter.writeBatSynthetic(syntheticData);
            close();
        } catch (ArgusException ex) {
//            Logger.getLogger(SyntheticDataController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<VariableMu> getSensitiveVariables() {
        return ((SyntheticDataView) getView()).getSelectedSensitiveVariables();
    }

    private List<VariableMu> getNonSensitiveVariables() {
        return ((SyntheticDataView) getView()).getSelectedNonSensitiveVariables();
    }

    @Override
    protected void doNextStep(boolean success) {
        //Add header
        MetaWriter.adjustSyntheticData(getModel());
        runBat();
        MetaWriter.adjustSyntheticOutputFile(getModel());
        //Run the R script
        //RunRScript()
    }

    private void runBat() {
        try {
            String cmd = getModel().getRunRFileFile().getAbsolutePath();
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
        } catch (IOException | ArgusException | InterruptedException ex) {
            //Logger.getLogger(SyntheticDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeSyntheticData() {
        getCalculationService().makeReplacementFile(this);
    }

//    public void adjustSyntheticData() {
//        //Adds a header containing the variable names that the R script expects
//        File inputFile = new File(getModel().getReplacementFile().getInputFilePath());
//        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
//            String line = "";
//            for (int i = 0; i < getModel().getSensitiveVariables().size(); i++) {
//                line += "x" + (i + 1) + " ,";
//            }
//            for (int i = 0; i < getModel().getNonSensitiveVariables().size(); i++) {
//                line += "s" + (i + 1) + " ,";
//            }
//            line = line.substring(0, line.length() - 1);
//            File outputFile = new File(getModel().getReplacementFile().getInputFilePath() + "2");
//            outputFile.deleteOnExit();
//            try (PrintWriter writer = new PrintWriter(outputFile)) {
//                writer.println(line);
//                while ((line = reader.readLine()) != null) {
//                    writer.println(line);
//                }
//            }
//        } catch (IOException ex) {
//            //throw new ArgusException("Error during reading file. Error message: " + ex.getMessage());
//        }
//    }

    /**
     * Gets the model and fills the model with the numeric variables if the
     * model is empty.
     */
    private void fillModel() {
        if (getModel().getAllVariables().isEmpty()) {
            for (VariableMu variable : this.metadata.getVariables()) {
                if (variable.isNumeric()) {
                    getModel().getAllVariables().add(variable);
                }
            }
        }
    }

}