package com.samarthgupta.sfa_app.POJO.WT_JobTicket;

/**
 * Created by samarthgupta on 28/07/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Job {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("printRun")
    @Expose
    private String printRun;
    @SerializedName("wastage")
    @Expose
    private String wastage;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("noOfCol")
    @Expose
    private String noOfCol;
    @SerializedName("numPages")
    @Expose
    private String numPages;


    public Job(String name, String type, String printRun, String wastage, String size, String noOfCol, String numPages) {
        this.name = name;
        this.type = type;
        this.printRun = printRun;
        this.wastage = wastage;
        this.size = size;
        this.noOfCol = noOfCol;
        this.numPages = numPages;
    }

    public String getNumPages() {
        return numPages;
    }

    public void setNumPages(String numPages) {
        this.numPages = numPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrintRun() {
        return printRun;
    }

    public void setPrintRun(String printRun) {
        this.printRun = printRun;
    }

    public String getWastage() {
        return wastage;
    }

    public void setWastage(String wastage) {
        this.wastage = wastage;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNoOfCol() {
        return noOfCol;
    }

    public void setNoOfCol(String noOfCol) {
        this.noOfCol = noOfCol;
    }

}