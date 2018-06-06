package com.samarthgupta.sfa_app.POJO.WT_Processes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Update {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("done")
    @Expose
    private String done;

    public Update() {
        this.time = null;
        this.done = null;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

}