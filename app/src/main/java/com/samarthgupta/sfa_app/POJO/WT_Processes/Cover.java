
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cover {

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
    @SerializedName("creasing")
    @Expose
    private Creasing creasing;
    @SerializedName("binding")
    @Expose
    private Binding binding;
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
