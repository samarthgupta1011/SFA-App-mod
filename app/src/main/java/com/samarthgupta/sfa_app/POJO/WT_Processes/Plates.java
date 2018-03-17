
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Plates {

    @SerializedName("isRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("isDone")
    @Expose
    private Boolean isDone;

    public Plates() {
        this.isRequired = false;
        this.isDone = false;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

}
