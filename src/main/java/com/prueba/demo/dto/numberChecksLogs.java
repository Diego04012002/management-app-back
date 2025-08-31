package com.prueba.demo.dto;

public class numberChecksLogs {
  public Long checIns;

  public Long checkOuts;

  public numberChecksLogs(Long checIns, Long checkOuts) {
    this.checIns = checIns;
    this.checkOuts = checkOuts;
  }

  public Long getChecIns() {
    return checIns;
  }

  public Long getCheckOuts() {
    return checkOuts;
  }

  public void setChecIns(Long checIns) {
    this.checIns = checIns;
  }

  public void setCheckOuts(Long checkOuts) {
    this.checkOuts = checkOuts;
  }

}
