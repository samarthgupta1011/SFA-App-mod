
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Printing {

    @SerializedName("isRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("updates")
    @Expose
    private List<UpdatePF> updates = null;

    public Printing() {
        this.isRequired = false;
        this.updates = new ArrayList<>();
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public List<UpdatePF> getUpdates() {
        return updates;
    }

    public void setUpdates(List<UpdatePF> updates) {
        this.updates = updates;
    }

}
