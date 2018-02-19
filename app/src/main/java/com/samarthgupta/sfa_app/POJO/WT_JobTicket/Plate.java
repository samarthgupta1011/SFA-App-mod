package com.samarthgupta.sfa_app.POJO.WT_JobTicket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by samarthgupta on 28/07/17.
 */

public class Plate {

    @SerializedName("plate")
    @Expose
    private String plate;
    @SerializedName("name")
    @Expose
    private String name; //NOT USED
    @SerializedName("quantity")
    @Expose
    private String quantity;

    public Plate(String plate, String name, String quantity) {
        this.plate = plate;
        this.name = name;
        this.quantity = quantity;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}