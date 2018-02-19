
package com.samarthgupta.sfa_app.POJO.WT_Processes;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sewing {

    @SerializedName("isRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("updates")
    @Expose
    private List<Update____> updates = null;

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public List<Update____> getUpdates() {
        return updates;
    }

    public void setUpdates(List<Update____> updates) {
        this.updates = updates;
    }

}
