
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Box {

    @SerializedName("designing")
    @Expose
    private Designing__ designing;
    @SerializedName("ferro")
    @Expose
    private Ferro__ ferro;
    @SerializedName("plates")
    @Expose
    private Plates__ plates;
    @SerializedName("printing")
    @Expose
    private Printing__ printing;
    @SerializedName("lamination")
    @Expose
    private Lamination_ lamination;
    @SerializedName("uv")
    @Expose
    private Uv uv;
    @SerializedName("embossing")
    @Expose
    private Embossing embossing;
    @SerializedName("foiling")
    @Expose
    private Foiling foiling;
    @SerializedName("die-cut")
    @Expose
    private DieCut dieCut;
    @SerializedName("pasting")
    @Expose
    private Pasting pasting;
    @SerializedName("packing")
    @Expose
    private Packing__ packing;
    @SerializedName("dispatch")
    @Expose
    private Dispatch__ dispatch;
    @SerializedName("challan")
    @Expose
    private Challan__ challan;
    @SerializedName("bill")
    @Expose
    private Bill__ bill;

    public Designing__ getDesigning() {
        return designing;
    }

    public void setDesigning(Designing__ designing) {
        this.designing = designing;
    }

    public Ferro__ getFerro() {
        return ferro;
    }

    public void setFerro(Ferro__ ferro) {
        this.ferro = ferro;
    }

    public Plates__ getPlates() {
        return plates;
    }

    public void setPlates(Plates__ plates) {
        this.plates = plates;
    }

    public Printing__ getPrinting() {
        return printing;
    }

    public void setPrinting(Printing__ printing) {
        this.printing = printing;
    }

    public Lamination_ getLamination() {
        return lamination;
    }

    public void setLamination(Lamination_ lamination) {
        this.lamination = lamination;
    }

    public Uv getUv() {
        return uv;
    }

    public void setUv(Uv uv) {
        this.uv = uv;
    }

    public Embossing getEmbossing() {
        return embossing;
    }

    public void setEmbossing(Embossing embossing) {
        this.embossing = embossing;
    }

    public Foiling getFoiling() {
        return foiling;
    }

    public void setFoiling(Foiling foiling) {
        this.foiling = foiling;
    }

    public DieCut getDieCut() {
        return dieCut;
    }

    public void setDieCut(DieCut dieCut) {
        this.dieCut = dieCut;
    }

    public Pasting getPasting() {
        return pasting;
    }

    public void setPasting(Pasting pasting) {
        this.pasting = pasting;
    }

    public Packing__ getPacking() {
        return packing;
    }

    public void setPacking(Packing__ packing) {
        this.packing = packing;
    }

    public Dispatch__ getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch__ dispatch) {
        this.dispatch = dispatch;
    }

    public Challan__ getChallan() {
        return challan;
    }

    public void setChallan(Challan__ challan) {
        this.challan = challan;
    }

    public Bill__ getBill() {
        return bill;
    }

    public void setBill(Bill__ bill) {
        this.bill = bill;
    }

}
