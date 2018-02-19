
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Foiling {

    @SerializedName("isRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("updates")
    @Expose
    private List<Update_______________________> updates = null;

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public List<Update_______________________> getUpdates() {
        return updates;
    }

    public void setUpdates(List<Update_______________________> updates) {
        this.updates = updates;
    }

}
