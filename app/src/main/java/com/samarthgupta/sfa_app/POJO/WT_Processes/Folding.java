
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Folding {

    @SerializedName("isRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("updates")
    @Expose
    private List<Update_> updates = null;

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public List<Update_> getUpdates() {
        return updates;
    }

    public void setUpdates(List<Update_> updates) {
        this.updates = updates;
    }

}
