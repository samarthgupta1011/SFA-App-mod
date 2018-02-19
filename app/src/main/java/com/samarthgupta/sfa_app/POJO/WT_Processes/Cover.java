
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cover {

    @SerializedName("designing")
    @Expose
    private Designing_ designing;
    @SerializedName("ferro")
    @Expose
    private Ferro_ ferro;
    @SerializedName("plates")
    @Expose
    private Plates_ plates;
    @SerializedName("printing")
    @Expose
    private Printing_ printing;
    @SerializedName("lamination")
    @Expose
    private Lamination lamination;
    @SerializedName("creasing")
    @Expose
    private Creasing creasing;
    @SerializedName("binding")
    @Expose
    private Binding binding;
    @SerializedName("packing")
    @Expose
    private Packing_ packing;
    @SerializedName("dispatch")
    @Expose
    private Dispatch_ dispatch;
    @SerializedName("challan")
    @Expose
    private Challan_ challan;
    @SerializedName("bill")
    @Expose
    private Bill_ bill;

    public Designing_ getDesigning() {
        return designing;
    }

    public void setDesigning(Designing_ designing) {
        this.designing = designing;
    }

    public Ferro_ getFerro() {
        return ferro;
    }

    public void setFerro(Ferro_ ferro) {
        this.ferro = ferro;
    }

    public Plates_ getPlates() {
        return plates;
    }

    public void setPlates(Plates_ plates) {
        this.plates = plates;
    }

    public Printing_ getPrinting() {
        return printing;
    }

    public void setPrinting(Printing_ printing) {
        this.printing = printing;
    }

    public Lamination getLamination() {
        return lamination;
    }

    public void setLamination(Lamination lamination) {
        this.lamination = lamination;
    }

    public Creasing getCreasing() {
        return creasing;
    }

    public void setCreasing(Creasing creasing) {
        this.creasing = creasing;
    }

    public Binding getBinding() {
        return binding;
    }

    public void setBinding(Binding binding) {
        this.binding = binding;
    }

    public Packing_ getPacking() {
        return packing;
    }

    public void setPacking(Packing_ packing) {
        this.packing = packing;
    }

    public Dispatch_ getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch_ dispatch) {
        this.dispatch = dispatch;
    }

    public Challan_ getChallan() {
        return challan;
    }

    public void setChallan(Challan_ challan) {
        this.challan = challan;
    }

    public Bill_ getBill() {
        return bill;
    }

    public void setBill(Bill_ bill) {
        this.bill = bill;
    }

}
