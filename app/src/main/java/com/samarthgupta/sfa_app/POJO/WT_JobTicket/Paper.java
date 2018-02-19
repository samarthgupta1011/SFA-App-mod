package com.samarthgupta.sfa_app.POJO.WT_JobTicket;

/**
 * Created by samarthgupta on 28/07/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paper {

    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("paperBy")
    @Expose
    private String paperBy; //Self stocked or client
    @SerializedName("location")
    @Expose
    private String location;

    public Paper(String details, String quality, String quantity, String paperBy, String location) {
        this.details = details;
        this.quality = quality;
        this.quantity = quantity;
        this.paperBy = paperBy;
        this.location = location;

        if(this.details==null){
            this.details="";
        }
        if(this.quality==null){
            this.quality="";
        }
        if(this.quantity==null){
            this.quantity="";
        }
        if(this.paperBy==null){
            this.paperBy="";
        }
        if(this.location==null){
            this.location="";
        }


    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPaperBy() {
        return paperBy;
    }

    public void setPaperBy(String paperBy) {
        this.paperBy = paperBy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}