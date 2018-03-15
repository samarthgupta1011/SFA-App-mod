
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Designing {

    @SerializedName("isRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("isDone")
    @Expose
    private Boolean isDone;

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
        isDone = false ;
        this.isDone = isDone;
    }

}
