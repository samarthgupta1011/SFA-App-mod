package com.samarthgupta.sfa_app.POJO.WT_Processes;



import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Book;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Box;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Cover;

public class Processes {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("ticket_id")
    @Expose
    private String ticket_id;
    @SerializedName("job-type")
    @Expose
    private String jobType;
    @SerializedName("total-number")
    @Expose
    private String totalNumber;
    @SerializedName("total-sets")
    @Expose
    private String totalSets;
    @SerializedName("total-forms")
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

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
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
