

package com.samarthgupta.sfa_app.POJO.WT_JobTicket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;

public class JobTicket {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("Client")
    @Expose
    private Client client;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("deliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("wt")
    @Expose
    private String wt;
    @SerializedName("priority")
    @Expose
    private String priority;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("Job")
    @Expose
    private Job job;
    @SerializedName("Machine")
    @Expose
    private Machine machine;
    @SerializedName("Paper")
    @Expose
    private Paper paper;
    @SerializedName("Plate")
    @Expose
    private Plate plate;
    @SerializedName("processes")
    @Expose
    private Processes processes ;
    @SerializedName("isDelivered")
    @Expose
    private boolean isDelivered ;


    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public void setProcesses(Processes processes) {  this.processes = processes;
    }
}
