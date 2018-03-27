
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dispatch {

    @SerializedName("isRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("updates")
    @Expose
    private List<Update> updates = null;

    public Dispatch() {
        this.isRequired = false;
        this.updates = new ArrayList<>();
        Update zeroUpdate = new Update();
        zeroUpdate.setDone("0");
        Time time = new Time(System.currentTimeMillis());
        zeroUpdate.setTime(time.toString());
        this.updates.add(zeroUpdate);
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public List<Update> getUpdates() {
        return updates;
    }

    public void setUpdates(List<Update> updates) {
        this.updates = updates;
    }

}
