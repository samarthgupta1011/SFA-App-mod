
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Box {

    @SerializedName("designing")
    @Expose
    private Designing designing;
    @SerializedName("ferro")
    @Expose
    private Ferro ferro;
    @SerializedName("plates")
    @Expose
    private Plates plates;
    @SerializedName("printing")
    @Expose
    private Printing printing;
    @SerializedName("lamination")
    @Expose
    private Lamination lamination;
    @SerializedName("uv")
    @Expose
    private Uv uv;
    @SerializedName("embossing")
    @Expose
    private Embossing embossing;
    @SerializedName("foiling")
    @Expose
    private Foiling foiling;
    @SerializedName("die_cut")
    @Expose
    private DieCut dieCut;
    @SerializedName("pasting")
    @Expose
    private Pasting pasting;
    @SerializedName("packing")
    @Expose
    private Packing packing;
    @SerializedName("dispatch")
    @Expose
    private Dispatch dispatch;
    @SerializedName("challan")
    @Expose
    private Challan challan;
    @SerializedName("bill")
    @Expose
    private Bill bill;

    public Box() {
        this.designing = new Designing();
        this.ferro = new Ferro();
        this.plates = new Plates();
        this.printing = new Printing();
        this.lamination = new Lamination();
        this.uv = new Uv();
        this.embossing = new Embossing();
        this.foiling = new Foiling();
        this.dieCut = new DieCut();
        this.pasting = new Pasting();
        this.packing = new Packing();
        this.dispatch = new Dispatch();
        this.challan = new Challan();
        this.bill = new Bill();
    }

    public Designing getDesigning() {
        return designing;
    }

    public void setDesigning(Designing designing) {
        this.designing = designing;
    }

    public Ferro getFerro() {
        return ferro;
    }

    public void setFerro(Ferro ferro) {
        this.ferro = ferro;
    }

    public Plates getPlates() {
        return plates;
    }

    public void setPlates(Plates plates) {
        this.plates = plates;
    }

    public Printing getPrinting() {
        return printing;
    }

    public void setPrinting(Printing printing) {
        this.printing = printing;
    }

    public Lamination getLamination() {
        return lamination;
    }

    public void setLamination(Lamination lamination) {
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

    public Packing getPacking() {
        return packing;
    }

    public void setPacking(Packing packing) {
        this.packing = packing;
    }

    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    public Challan getChallan() {
        return challan;
    }

    public void setChallan(Challan challan) {
        this.challan = challan;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

}
