package com.mobileAppWs.demo.models.request;

public class MobileCheckRequestModel {
    private String CUST_NM;
    private String BIRTH_DATE;
    private String SEX;
    private String MOBILE_NO1;
    private String MOBILE_NO2;
    private String MOBILE_NO3;

    public String getCUST_NM() {
        return CUST_NM;
    }

    public void setCUST_NM(String CUST_NM) {
        this.CUST_NM = CUST_NM;
    }

    public String getBIRTH_DATE() {
        return BIRTH_DATE;
    }

    public void setBIRTH_DATE(String BIRTH_DATE) {
        this.BIRTH_DATE = BIRTH_DATE;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getMOBILE_NO1() {
        return MOBILE_NO1;
    }

    public void setMOBILE_NO1(String MOBILE_NO1) {
        this.MOBILE_NO1 = MOBILE_NO1;
    }

    public String getMOBILE_NO2() {
        return MOBILE_NO2;
    }

    public void setMOBILE_NO2(String MOBILE_NO2) {
        this.MOBILE_NO2 = MOBILE_NO2;
    }

    public String getMOBILE_NO3() {
        return MOBILE_NO3;
    }

    public void setMOBILE_NO3(String MOBILE_NO3) {
        this.MOBILE_NO3 = MOBILE_NO3;
    }
}
