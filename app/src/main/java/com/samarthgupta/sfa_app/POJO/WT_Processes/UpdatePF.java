
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePF {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("done")
    @Expose
    private String done;
    @SerializedName("sets_done")
    @Expose
    private String setsDone;

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

    public String getSetsDone() {
        return setsDone;
    }

    public void setSetsDone(String setsDone) {
        this.setsDone = setsDone;
    }

}
