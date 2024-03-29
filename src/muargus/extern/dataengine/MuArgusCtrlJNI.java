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
package muargus.extern.dataengine;

public class MuArgusCtrlJNI {
  public final static native void delete_IProgressListener(long jarg1);
  public final static native void IProgressListener_UpdateProgress(long jarg1, IProgressListener jarg1_, int jarg2);
  public final static native long new_IProgressListener();
  public final static native void IProgressListener_director_connect(IProgressListener obj, long cptr, boolean mem_own, boolean weak_global);
  public final static native void IProgressListener_change_ownership(IProgressListener obj, long cptr, boolean take_or_release);
  public final static native long new_CMuArgCtrl();
  public final static native void delete_CMuArgCtrl(long jarg1);
  public final static native void CMuArgCtrl_SetProgressListener(long jarg1, CMuArgCtrl jarg1_, long jarg2, IProgressListener jarg2_);
  public final static native boolean CMuArgCtrl_ComputeBIRRateThreshold(long jarg1, CMuArgCtrl jarg1_, int jarg2, double jarg3, double[] jarg4);
  public final static native int CMuArgCtrl_NumberOfHouseholds(long jarg1, CMuArgCtrl jarg1_);
  public final static native boolean CMuArgCtrl_CalculateBHRFreq(long jarg1, CMuArgCtrl jarg1_, int jarg2, boolean jarg3, int jarg4, int jarg5, double[] jarg6, int[] jarg7);
  public final static native boolean CMuArgCtrl_CalculateBIRFreq(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3, double[] jarg4, int[] jarg5);
  public final static native boolean CMuArgCtrl_SetBHRThreshold(long jarg1, CMuArgCtrl jarg1_, int jarg2, double jarg3, int[] jarg4, int[] jarg5);
  public final static native boolean CMuArgCtrl_GetBHRHistogramData(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3, double[] jarg4, int[] jarg5, int[] jarg6);
  public final static native boolean CMuArgCtrl_CalculateBaseHouseholdRisk(long jarg1, CMuArgCtrl jarg1_, int[] jarg2);
  public final static native boolean CMuArgCtrl_MakeFileSafe(long jarg1, CMuArgCtrl jarg1_, String jarg2, boolean jarg3, boolean jarg4, int jarg5, boolean jarg6, boolean jarg7);
  public final static native boolean CMuArgCtrl_GetBIRHistogramData(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3, double[] jarg4, double[] jarg5, int[] jarg6);
  public final static native boolean CMuArgCtrl_MakeFileSafeClearOptions(long jarg1, CMuArgCtrl jarg1_);
  public final static native boolean CMuArgCtrl_SetWeightNoise(long jarg1, CMuArgCtrl jarg1_, int jarg2, double jarg3, boolean jarg4);
  public final static native boolean CMuArgCtrl_SetSuppressPrior(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3);
  public final static native boolean CMuArgCtrl_SetRound(long jarg1, CMuArgCtrl jarg1_, int jarg2, double jarg3, int jarg4, boolean jarg5);
  public final static native boolean CMuArgCtrl_SetChangeFile(long jarg1, CMuArgCtrl jarg1_, int jarg2, String jarg3, int jarg4, int[] jarg5, String jarg6);
  public final static native boolean CMuArgCtrl_GetVarProperties(long jarg1, CMuArgCtrl jarg1_, int jarg2, int[] jarg3, int[] jarg4, int[] jarg5, double[] jarg6, int[] jarg7, String[] jarg8, String[] jarg9, int[] jarg10, int[] jarg11);
  public final static native boolean CMuArgCtrl_GetVarCode(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3, String[] jarg4, int[] jarg5);
  public final static native boolean CMuArgCtrl_GetTableUC(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3, boolean[] jarg4, int[] jarg5, int[] jarg6);
  public final static native boolean CMuArgCtrl_SetPramValue(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3);
  public final static native boolean CMuArgCtrl_SetPramVar(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3, boolean jarg4);
  public final static native boolean CMuArgCtrl_ClosePramVar(long jarg1, CMuArgCtrl jarg1_, int jarg2);
  public final static native boolean CMuArgCtrl_GetMinMaxValue(long jarg1, CMuArgCtrl jarg1_, int jarg2, double[] jarg3, double[] jarg4);
  public final static native boolean CMuArgCtrl_SetCodingBottom(long jarg1, CMuArgCtrl jarg1_, int jarg2, double jarg3, String jarg4, boolean jarg5);
  public final static native boolean CMuArgCtrl_SetCodingTop(long jarg1, CMuArgCtrl jarg1_, int jarg2, double jarg3, String jarg4, boolean jarg5);
  public final static native boolean CMuArgCtrl_SetBirThreshold(long jarg1, CMuArgCtrl jarg1_, int jarg2, double jarg3, int[] jarg4);
  public final static native boolean CMuArgCtrl_SetNumberOfChangeFiles(long jarg1, CMuArgCtrl jarg1_, int jarg2);
  public final static native boolean CMuArgCtrl_CleanAll(long jarg1, CMuArgCtrl jarg1_);
  public final static native boolean CMuArgCtrl_SetOutFileInfo(long jarg1, CMuArgCtrl jarg1_, boolean jarg2, String jarg3, String jarg4, boolean jarg5);
  public final static native int CMuArgCtrl_NumberofRecords(long jarg1, CMuArgCtrl jarg1_);
  public final static native boolean CMuArgCtrl_SetInFileInfo(long jarg1, CMuArgCtrl jarg1_, boolean jarg2, String jarg3, boolean jarg4);
  public final static native boolean CMuArgCtrl_WriteVariablesInFile(long jarg1, CMuArgCtrl jarg1_, String jarg2, String jarg3, int jarg4, int[] jarg5, String jarg6, int[] jarg7);
  public final static native boolean CMuArgCtrl_ApplyRecode(long jarg1, CMuArgCtrl jarg1_);
  public final static native boolean CMuArgCtrl_DoTruncate(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3);
  public final static native boolean CMuArgCtrl_UndoRecode(long jarg1, CMuArgCtrl jarg1_, int jarg2);
  public final static native boolean CMuArgCtrl_DoRecode(long jarg1, CMuArgCtrl jarg1_, int jarg2, String jarg3, String jarg4, String jarg5, int[] jarg6, int[] jarg7, int[] jarg8, String[] jarg9);
  public final static native boolean CMuArgCtrl_UnsafeVariableClose(long jarg1, CMuArgCtrl jarg1_, int jarg2);
  public final static native boolean CMuArgCtrl_UnsafeVariableCodes(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3, int[] jarg4, int[] jarg5, String[] jarg6, int[] jarg7, int[] jarg8);
  public final static native boolean CMuArgCtrl_UnsafeVariablePrepare(long jarg1, CMuArgCtrl jarg1_, int jarg2, int[] jarg3);
  public final static native boolean CMuArgCtrl_UnsafeVariable(long jarg1, CMuArgCtrl jarg1_, int jarg2, int[] jarg3, int[] jarg4);
  public final static native int CMuArgCtrl_GetMaxnUC(long jarg1, CMuArgCtrl jarg1_);
  public final static native boolean CMuArgCtrl_ComputeTables(long jarg1, CMuArgCtrl jarg1_, int[] jarg2, int[] jarg3);
  public final static native boolean CMuArgCtrl_BaseIndividualRisk(long jarg1, CMuArgCtrl jarg1_, int jarg2, double jarg3, double[] jarg4);
  public final static native boolean CMuArgCtrl_SetTable(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3, int jarg4, int[] jarg5, boolean jarg6, int jarg7);
  public final static native boolean CMuArgCtrl_SetNumberTab(long jarg1, CMuArgCtrl jarg1_, int jarg2);
  public final static native boolean CMuArgCtrl_ExploreFile(long jarg1, CMuArgCtrl jarg1_, String jarg2, int[] jarg3, int[] jarg4, int[] jarg5);
  public final static native boolean CMuArgCtrl_SetVariable(long jarg1, CMuArgCtrl jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, String jarg6, String jarg7, boolean jarg8, boolean jarg9, boolean jarg10, boolean jarg11, boolean jarg12, int jarg13);
  public final static native boolean CMuArgCtrl_SetNumberVar(long jarg1, CMuArgCtrl jarg1_, int jarg2);
  public final static native boolean CMuArgCtrl_GetErrorString(long jarg1, CMuArgCtrl jarg1_, int jarg2, String[] jarg3);

  public static void SwigDirector_IProgressListener_UpdateProgress(IProgressListener self, int perc) {
    self.UpdateProgress(perc);
  }

  private final static native void swig_module_init();
  static {
    swig_module_init();
  }
}
