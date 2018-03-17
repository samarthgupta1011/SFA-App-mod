
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {

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
    @SerializedName("folding")
    @Expose
    private Folding folding;
    @SerializedName("gathering")
    @Expose
    private Gathering gathering;
    @SerializedName("perfect")
    @Expose
    private Perfect perfect;
    @SerializedName("sewing")
    @Expose
    private Sewing sewing;
    @SerializedName("centre-pin")
    @Expose
    private CentrePin centrePin;
    @SerializedName("finishing")
    @Expose
    private Finishing finishing;
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

    public Book() {

        this.designing = new Designing();
        this.ferro = new Ferro();
        this.plates = new Plates();
        this.printing = new Printing();
        this.folding = new Folding();
        this.gathering = new Gathering();
        this.perfect = new Perfect();
        this.sewing = new Sewing();
        this.centrePin = new CentrePin();
        this.finishing = new Finishing();
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

    public Folding getFolding() {
        return folding;
    }

    public void setFolding(Folding folding) {
        this.folding = folding;
    }

    public Gathering getGathering() {
        return gathering;
    }

    public void setGathering(Gathering gathering) {
        this.gathering = gathering;
    }

    public Perfect getPerfect() {
        return perfect;
    }

    public void setPerfect(Perfect perfect) {
        this.perfect = perfect;
    }

    public Sewing getSewing() {
        return sewing;
    }

    public void setSewing(Sewing sewing) {
        this.sewing = sewing;
    }

    public CentrePin getCentrePin() {
        return centrePin;
    }

    public void setCentrePin(CentrePin centrePin) {
        this.centrePin = centrePin;
    }

    public Finishing getFinishing() {
        return finishing;
    }

    public void setFinishing(Finishing finishing) {
        this.finishing = finishing;
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
