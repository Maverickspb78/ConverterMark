package com.andreev.properties;

public class Properties implements Prop{

    private String dateOfManufacture;
    private String inn;
    private String codeTNVD;
    private String numberDoc;
    private String dataPerDoc;
    private String dataOut;

    public Properties() {
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getCodeTNVD() {
        return codeTNVD;
    }

    public void setCodeTNVD(String codeTNVD) {
        this.codeTNVD = codeTNVD;
    }

    public String getNumberDoc() {
        return numberDoc;
    }

    public void setNumberDoc(String numberDoc) {
        this.numberDoc = numberDoc;
    }

    public String getDataPerDoc() {
        return dataPerDoc;
    }

    public void setDataPerDoc(String dataPerDoc) {
        this.dataPerDoc = dataPerDoc;
    }

    public String getDataOut() {
        return dataOut;
    }

    public void setDataOut(String dataOut) {
        this.dataOut = dataOut;
    }
}