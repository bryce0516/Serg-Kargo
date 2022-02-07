package com.mobileAppWs.demo.test.entity;

import javax.persistence.*;

@Entity(name="product")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(nullable = false, unique = true)
  private String certno;

  @Column(nullable = false)
  private String refalph;

  @Column(nullable = false)
  private String refnum;

  @Column(nullable = false)
  private String cd;

  @Column(nullable = false)
  private String cdnm;

  @Column(nullable = false)
  private String plinm;

  @Column(nullable = false)
  private String commt;

  @Column(nullable = false)
  private String qty;

  @Column(nullable = false)
  private String amt;

  @Column(nullable = false)
  private String payback;

  @Column(nullable = false)
  private String maingb;

  @Column(nullable = false)
  private String init;

  @Column(nullable = false)
  private String upsellyn;

  @Column(nullable = false)
  private String assi_prod_cd;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCertno() {
    return certno;
  }

  public void setCertno(String certno) {
    this.certno = certno;
  }

  public String getRefalph() {
    return refalph;
  }

  public void setRefalph(String refalph) {
    this.refalph = refalph;
  }

  public String getRefnum() {
    return refnum;
  }

  public void setRefnum(String refnum) {
    this.refnum = refnum;
  }

  public String getCd() {
    return cd;
  }

  public void setCd(String cd) {
    this.cd = cd;
  }

  public String getCdnm() {
    return cdnm;
  }

  public void setCdnm(String cdnm) {
    this.cdnm = cdnm;
  }

  public String getPlinm() {
    return plinm;
  }

  public void setPlinm(String plinm) {
    this.plinm = plinm;
  }

  public String getCommt() {
    return commt;
  }

  public void setCommt(String commt) {
    this.commt = commt;
  }

  public String getQty() {
    return qty;
  }

  public void setQty(String qty) {
    this.qty = qty;
  }

  public String getAmt() {
    return amt;
  }

  public void setAmt(String amt) {
    this.amt = amt;
  }

  public String getPayback() {
    return payback;
  }

  public void setPayback(String payback) {
    this.payback = payback;
  }

  public String getMaingb() {
    return maingb;
  }

  public void setMaingb(String maingb) {
    this.maingb = maingb;
  }

  public String getInit() {
    return init;
  }

  public void setInit(String init) {
    this.init = init;
  }

  public String getUpsellyn() {
    return upsellyn;
  }

  public void setUpsellyn(String upsellyn) {
    this.upsellyn = upsellyn;
  }

  public String getAssi_prod_cd() {
    return assi_prod_cd;
  }

  public void setAssi_prod_cd(String assi_prod_cd) {
    this.assi_prod_cd = assi_prod_cd;
  }
}
