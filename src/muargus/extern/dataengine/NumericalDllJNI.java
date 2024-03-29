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

public class NumericalDllJNI {
  public final static native void delete_IProgressListener2(long jarg1);
  public final static native void IProgressListener2_UpdateProgress(long jarg1, IProgressListener2 jarg1_, int jarg2);
  public final static native long new_IProgressListener2();
  public final static native void IProgressListener2_director_connect(IProgressListener2 obj, long cptr, boolean mem_own, boolean weak_global);
  public final static native void IProgressListener2_change_ownership(IProgressListener2 obj, long cptr, boolean take_or_release);
  public final static native int MAXRECORDLENGTH_get();
  public final static native int MIC_OK_get();
  public final static native int MIC_OUT_MEM_get();
  public final static native int MIC_ERR_NEL_get();
  public final static native int MIC_ERR_NVAR_get();
  public final static native int MIC_ERR_MEG_get();
  public final static native int MIC_ERR_GOV_get();
  public final static native int MIC_ERR_VPG_get();
  public final static native int MIC_ERR_COL_get();
  public final static native int MIC_ERR_STD_get();
  public final static native long new_Numerical();
  public final static native void Numerical_DoRankSwap(long jarg1, Numerical jarg1_, String jarg2, String jarg3, String jarg4, int jarg5, int jarg6, int[] jarg7);
  public final static native void Numerical_DoMicroAggregation(long jarg1, Numerical jarg1_, String jarg2, String jarg3, String jarg4, int jarg5, int jarg6, int jarg7, int jarg8, int[] jarg9, int[] jarg10);
  public final static native void Numerical_SetProgressListener(long jarg1, Numerical jarg1_, long jarg2, IProgressListener2 jarg2_);
  public final static native void delete_Numerical(long jarg1);

  public static void SwigDirector_IProgressListener2_UpdateProgress(IProgressListener2 self, int perc) {
    self.UpdateProgress(perc);
  }

  private final static native void swig_module_init();
  static {
    swig_module_init();
  }
}
