package muargus.controller;

import argus.model.ArgusException;
import muargus.model.GlobalRecode;
import muargus.model.MetadataMu;
import muargus.model.RecodeMu;
import muargus.view.GlobalRecodeView;

/**
 * Controller class of the GlobalRecode screen.
 *
 * @author Statistics Netherlands
 */
public class GlobalRecodeController extends ControllerBase<GlobalRecode> {

    //private final MetadataMu metadata;
    /**
     * Constructor for the GlobalRecodeController. This constructor makes a new
     * view and sets the metadata for the view.
     *
     * @param parentView the Frame of the mainFrame that is given to the
     * ShowTableCollectionView.
     * @param metadata the orginal metadata.
     */
    public GlobalRecodeController(java.awt.Frame parentView, MetadataMu metadata) {
        super.setView(new GlobalRecodeView(parentView, true, this));
        //this.metadata = metadata;
        getView().setMetadata(metadata);
        //this.selectCombinationsModel = selectCombinationsModel;
        //this.view = view;
    }

    /**
     * Shows the view and sets the selected variable in the GlobalRecodeView
     * equal to the selected variable in the main frame.
     *
     * @param selectedRowIndex Integer containing the index of the selected
     * variable in the main frame.
     */
    public void showView(int selectedRowIndex) {
        getGlobalRecodeView().setSelectedIndex(selectedRowIndex);
        getView().setVisible(true);
    }

    /**
     * Closes the view by setting its visibility to false.
     */
    public void close() {
        getView().setVisible(false);
    }

    /**
     * Gets the index of the selected variable.
     *
     * @return Integer containing the index of the selected variable.
     */
    public int getSelectedVariableIndex() {
        return getGlobalRecodeView().getSelectedIndex();
    }

    /**
     * Gets the GlobalRecodeView.
     *
     * @return GlobalRecodeView.
     */
    private GlobalRecodeView getGlobalRecodeView() {
        return (GlobalRecodeView) getView();
    }

    /**
     *
     */
//    public void codelistRecode() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setFileFilter(new FileNameExtensionFilter("Codelist (*.cdl)", "cdl"));
//        String hs = SystemUtils.getRegString("general", "datadir", "");
//        if (!hs.equals("")){
//            File file = new File(hs); 
//            fileChooser.setCurrentDirectory(file);
//        }        
//        fileChooser.showOpenDialog(null);
//
//        String filename;
//        File f = fileChooser.getSelectedFile();
//        if (fileChooser.getSelectedFile() == null) {
//            filename = "";
//        } else {
//            filename = f.getAbsolutePath();
//        }
//        view.setCodelistText(filename);
//    }

    /**
     * Apply's the recoding on the selected variable.
     *
     * @param recode RecodeMu containing the information on the variable for
     * which the recoding will be done.
     * @throws ArgusException Throws an ArgusException when an error occurs
     * during recoding.
     */
    public void apply(RecodeMu recode) throws ArgusException {
        String warning = getCalculationService().doRecode(recode);
        getGlobalRecodeView().showWarning(warning);
        getCalculationService().applyRecode();
        recode.setTruncated(false);
        recode.setRecoded(true);
    }

    /**
     * Undo's the recoding of the selected variable.
     *
     * @param recode RecodeMu containing the information on the variable for
     * which the recoding needs to be undone.
     * @throws ArgusException Throws an ArgusException when an error occurs
     * while undoing the recoding.
     */
    public void undo(RecodeMu recode) throws ArgusException {
        getCalculationService().undoRecode(recode);
        recode.setRecoded(false);
        recode.setTruncated(false);
    }

    /**
     * Truncates the selected variable.
     *
     * @param recode RecodeMu containing the information on the variable that
     * will be truncated.
     * @param positions Integer containing the number of positions that will be
     * truncated.
     * @throws ArgusException Throws an ArgusException when an error occurs
     * during truncating.
     */
    public void truncate(RecodeMu recode, int positions) throws ArgusException {
        getCalculationService().truncate(recode, positions);
        recode.setRecoded(false);
        recode.setTruncated(true);
        recode.setPositionsTruncated(Integer.toString(positions));
    }

}
