package com.samarthgupta.sfa_app.POJO.WT_Processes;



import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class Processes {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("job_type")
    @Expose
    private String jobType;
    @SerializedName("wt_id")
    @Expose
    private String wtId;
    @SerializedName("total_number")
    @Expose
    private String totalNumber;
    @SerializedName("total_sets")
    @Expose
    private String totalSets;
    @SerializedName("total_forms")
    @Expose
    private String totalForms;
    @SerializedName("book")
    @Expose
    private Book book;
    @SerializedName("cover")
    @Expose
    private Cover cover;
    @SerializedName("box")
    @Expose
    private Box box;

    public Processes() {
        this.jobType = null;
        this.wtId = null;
        this.totalNumber = null;
        this.totalSets = null;
        this.totalForms = null;
        this.book = null;
        this.cover = null;
        this.box = null;
    }

    public String getWtId() {
        return wtId;
    }

    public void setWtId(String wtId) {
        this.wtId = wtId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getTotalSets() {
        return totalSets;
    }

    public void setTotalSets(String totalSets) {
        this.totalSets = totalSets;
    }

    public String getTotalForms() {
        return totalForms;
    }

    public void setTotalForms(String totalForms) {
        this.totalForms = totalForms;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

}
