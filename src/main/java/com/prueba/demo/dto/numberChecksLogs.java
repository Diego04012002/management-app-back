package com.prueba.demo.dto;

public class numberChecksLogs {
  public Long checkIns;

  public Long checkOuts;

  public numberChecksLogs(Long checIns, Long checkOuts) {
    this.checkIns = checIns;
    this.checkOuts = checkOuts;
  }

  public Long getChecIns() {
    return checkIns;
  }

  public Long getCheckOuts() {
    return checkOuts;
  }

  public void setChecIns(Long checIns) {
    this.checkIns = checIns;
  }

  public void setCheckOuts(Long checkOuts) {
    this.checkOuts = checkOuts;
  }

}
