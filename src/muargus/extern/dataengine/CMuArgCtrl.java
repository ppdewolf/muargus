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

public class CMuArgCtrl {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected CMuArgCtrl(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CMuArgCtrl obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        MuArgusCtrlJNI.delete_CMuArgCtrl(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public CMuArgCtrl() {
    this(MuArgusCtrlJNI.new_CMuArgCtrl(), true);
  }

  public void SetProgressListener(IProgressListener ProgressListener) {
    MuArgusCtrlJNI.CMuArgCtrl_SetProgressListener(swigCPtr, this, IProgressListener.getCPtr(ProgressListener), ProgressListener);
  }

  public boolean ComputeBIRRateThreshold(int TableIndex, double Risk, double[] ReIdentRate) {
    return MuArgusCtrlJNI.CMuArgCtrl_ComputeBIRRateThreshold(swigCPtr, this, TableIndex, Risk, ReIdentRate);
  }

  public int NumberOfHouseholds() {
    return MuArgusCtrlJNI.CMuArgCtrl_NumberOfHouseholds(swigCPtr, this);
  }

  public boolean CalculateBHRFreq(int TableIndex, boolean UseNumOfHH, int nUnsafeHH, int nUnsafeRec, double[] ResBHR, int[] ErrCode) {
    return MuArgusCtrlJNI.CMuArgCtrl_CalculateBHRFreq(swigCPtr, this, TableIndex, UseNumOfHH, nUnsafeHH, nUnsafeRec, ResBHR, ErrCode);
  }

  public boolean CalculateBIRFreq(int TableIndex, int nUnsafe, double[] BIRResult, int[] ErrorCode) {
    return MuArgusCtrlJNI.CMuArgCtrl_CalculateBIRFreq(swigCPtr, this, TableIndex, nUnsafe, BIRResult, ErrorCode);
  }

  public boolean SetBHRThreshold(int TableIndex, double BHRThreshold, int[] nUnsafeHH, int[] nUnsafeRec) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetBHRThreshold(swigCPtr, this, TableIndex, BHRThreshold, nUnsafeHH, nUnsafeRec);
  }

  public boolean GetBHRHistogramData(int TableIndex, int nClasses, double[] ClassLeftValue, int[] HHFrequency, int[] RecFrequency) {
    return MuArgusCtrlJNI.CMuArgCtrl_GetBHRHistogramData(swigCPtr, this, TableIndex, nClasses, ClassLeftValue, HHFrequency, RecFrequency);
  }

  public boolean CalculateBaseHouseholdRisk(int[] ErrorCode) {
    return MuArgusCtrlJNI.CMuArgCtrl_CalculateBaseHouseholdRisk(swigCPtr, this, ErrorCode);
  }

  public boolean MakeFileSafe(String FileName, boolean WithPrior, boolean WithEntropy, int HHIdentOption, boolean RandomizeOutput, boolean PrintBHR) {
    return MuArgusCtrlJNI.CMuArgCtrl_MakeFileSafe(swigCPtr, this, FileName, WithPrior, WithEntropy, HHIdentOption, RandomizeOutput, PrintBHR);
  }

  public boolean GetBIRHistogramData(int TabIndex, int nClasses, double[] ClassLeftValue, double[] Ksi, int[] Frequency) {
    return MuArgusCtrlJNI.CMuArgCtrl_GetBIRHistogramData(swigCPtr, this, TabIndex, nClasses, ClassLeftValue, Ksi, Frequency);
  }

  public boolean MakeFileSafeClearOptions() {
    return MuArgusCtrlJNI.CMuArgCtrl_MakeFileSafeClearOptions(swigCPtr, this);
  }

  public boolean SetWeightNoise(int VarIndex, double WeightNoise, boolean Undo) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetWeightNoise(swigCPtr, this, VarIndex, WeightNoise, Undo);
  }

  public boolean SetSuppressPrior(int VarIndex, int Priority) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetSuppressPrior(swigCPtr, this, VarIndex, Priority);
  }

  public boolean SetRound(int VarIndex, double RoundBase, int nDec, boolean Undo) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetRound(swigCPtr, this, VarIndex, RoundBase, nDec, Undo);
  }

  public boolean SetChangeFile(int FileIndex, String FileName, int nVar, int[] VarIndex, String FileSeperator) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetChangeFile(swigCPtr, this, FileIndex, FileName, nVar, VarIndex, FileSeperator);
  }

  public boolean GetVarProperties(int VarIndex, int[] StartPos, int[] nPos, int[] nSuppress, double[] Entropy, int[] BandWidth, String[] Missing1, String[] Missing2, int[] NofCodes, int[] NofMissing) {
    return MuArgusCtrlJNI.CMuArgCtrl_GetVarProperties(swigCPtr, this, VarIndex, StartPos, nPos, nSuppress, Entropy, BandWidth, Missing1, Missing2, NofCodes, NofMissing);
  }

  public boolean GetVarCode(int VarIndex, int CodeIndex, String[] Code, int[] PramPerc) {
    return MuArgusCtrlJNI.CMuArgCtrl_GetVarCode(swigCPtr, this, VarIndex, CodeIndex, Code, PramPerc);
  }

  public boolean GetTableUC(int nDim, int Index, boolean[] BaseTable, int[] nUC, int[] VarList) {
    return MuArgusCtrlJNI.CMuArgCtrl_GetTableUC(swigCPtr, this, nDim, Index, BaseTable, nUC, VarList);
  }

  public boolean SetPramValue(int CodeIndex, int Value) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetPramValue(swigCPtr, this, CodeIndex, Value);
  }

  public boolean SetPramVar(int VarIndex, int BandWidth, boolean Undo) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetPramVar(swigCPtr, this, VarIndex, BandWidth, Undo);
  }

  public boolean ClosePramVar(int VarIndex) {
    return MuArgusCtrlJNI.CMuArgCtrl_ClosePramVar(swigCPtr, this, VarIndex);
  }

  public boolean GetMinMaxValue(int VarIndex, double[] Min, double[] Max) {
    return MuArgusCtrlJNI.CMuArgCtrl_GetMinMaxValue(swigCPtr, this, VarIndex, Min, Max);
  }

  public boolean SetCodingBottom(int VarIndex, double BottomLevel, String BottomString, boolean BottomUndo) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetCodingBottom(swigCPtr, this, VarIndex, BottomLevel, BottomString, BottomUndo);
  }

  public boolean SetCodingTop(int VarIndex, double TopLevel, String TopString, boolean TopUndo) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetCodingTop(swigCPtr, this, VarIndex, TopLevel, TopString, TopUndo);
  }

  public boolean SetBirThreshold(int TabIndex, double Threshold, int[] nUnsafe) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetBirThreshold(swigCPtr, this, TabIndex, Threshold, nUnsafe);
  }

  public boolean SetNumberOfChangeFiles(int nFiles) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetNumberOfChangeFiles(swigCPtr, this, nFiles);
  }

  public boolean CleanAll() {
    return MuArgusCtrlJNI.CMuArgCtrl_CleanAll(swigCPtr, this);
  }

  public boolean SetOutFileInfo(boolean IsFixedFormat, String Seperator, String FirstLine, boolean StringsInQuotes) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetOutFileInfo(swigCPtr, this, IsFixedFormat, Seperator, FirstLine, StringsInQuotes);
  }

  public int NumberofRecords() {
    return MuArgusCtrlJNI.CMuArgCtrl_NumberofRecords(swigCPtr, this);
  }

  public boolean SetInFileInfo(boolean IsFixedFormat, String Seperator, boolean IgnoreFirstLine) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetInFileInfo(swigCPtr, this, IsFixedFormat, Seperator, IgnoreFirstLine);
  }

  public boolean WriteVariablesInFile(String FileNameMicro, String FileNameOut, int nVar, int[] VarIndexes, String seperator, int[] ErrorCode) {
    return MuArgusCtrlJNI.CMuArgCtrl_WriteVariablesInFile(swigCPtr, this, FileNameMicro, FileNameOut, nVar, VarIndexes, seperator, ErrorCode);
  }

  public boolean ApplyRecode() {
    return MuArgusCtrlJNI.CMuArgCtrl_ApplyRecode(swigCPtr, this);
  }

  public boolean DoTruncate(int VarIndex, int nPos) {
    return MuArgusCtrlJNI.CMuArgCtrl_DoTruncate(swigCPtr, this, VarIndex, nPos);
  }

  public boolean UndoRecode(int VarIndex) {
    return MuArgusCtrlJNI.CMuArgCtrl_UndoRecode(swigCPtr, this, VarIndex);
  }

  public boolean DoRecode(int VarIndex, String RecodeString, String eMissing1, String eMissing2, int[] ErrorType, int[] ErrorLine, int[] ErrorPos, String[] WarningString) {
    return MuArgusCtrlJNI.CMuArgCtrl_DoRecode(swigCPtr, this, VarIndex, RecodeString, eMissing1, eMissing2, ErrorType, ErrorLine, ErrorPos, WarningString);
  }

  public boolean UnsafeVariableClose(int VarIndex) {
    return MuArgusCtrlJNI.CMuArgCtrl_UnsafeVariableClose(swigCPtr, this, VarIndex);
  }

  public boolean UnsafeVariableCodes(int VarIndex, int CodeIndex, int[] IsMissing, int[] Freq, String[] Code, int[] Count, int[] UCArray) {
    return MuArgusCtrlJNI.CMuArgCtrl_UnsafeVariableCodes(swigCPtr, this, VarIndex, CodeIndex, IsMissing, Freq, Code, Count, UCArray);
  }

  public boolean UnsafeVariablePrepare(int VarIndex, int[] nCode) {
    return MuArgusCtrlJNI.CMuArgCtrl_UnsafeVariablePrepare(swigCPtr, this, VarIndex, nCode);
  }

  public boolean UnsafeVariable(int VarIndex, int[] Count, int[] UCArray) {
    return MuArgusCtrlJNI.CMuArgCtrl_UnsafeVariable(swigCPtr, this, VarIndex, Count, UCArray);
  }

  public int GetMaxnUC() {
    return MuArgusCtrlJNI.CMuArgCtrl_GetMaxnUC(swigCPtr, this);
  }

  public boolean ComputeTables(int[] ErrorCode, int[] TableIndex) {
    return MuArgusCtrlJNI.CMuArgCtrl_ComputeTables(swigCPtr, this, ErrorCode, TableIndex);
  }

  public boolean BaseIndividualRisk(int fk, double Fk, double[] risk) {
    return MuArgusCtrlJNI.CMuArgCtrl_BaseIndividualRisk(swigCPtr, this, fk, Fk, risk);
  }

  public boolean SetTable(int TabIndex, int Threshold, int nDim, int[] VarList, boolean IsBIR, int BIRWeightVarIndex) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetTable(swigCPtr, this, TabIndex, Threshold, nDim, VarList, IsBIR, BIRWeightVarIndex);
  }

  public boolean SetNumberTab(int nTab) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetNumberTab(swigCPtr, this, nTab);
  }

  public boolean ExploreFile(String FileName, int[] ErrorCode, int[] LineNumber, int[] VarIndex) {
    return MuArgusCtrlJNI.CMuArgCtrl_ExploreFile(swigCPtr, this, FileName, ErrorCode, LineNumber, VarIndex);
  }

  public boolean SetVariable(int Index, int bPos, int nPos, int nDec, String Missing1, String Missing2, boolean IsHHIdent, boolean IsHHVar, boolean IsCategorical, boolean IsNumeric, boolean IsWeight, int RelatedVar) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetVariable(swigCPtr, this, Index, bPos, nPos, nDec, Missing1, Missing2, IsHHIdent, IsHHVar, IsCategorical, IsNumeric, IsWeight, RelatedVar);
  }

  public boolean SetNumberVar(int nvar) {
    return MuArgusCtrlJNI.CMuArgCtrl_SetNumberVar(swigCPtr, this, nvar);
  }

  public boolean GetErrorString(int ErrorCode, String[] ErrorString) {
    return MuArgusCtrlJNI.CMuArgCtrl_GetErrorString(swigCPtr, this, ErrorCode, ErrorString);
  }

}
