
package com.samarthgupta.sfa_app.POJO.WT_Processes;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        UpdatePF zeroUpdate = new UpdatePF();
        zeroUpdate.setDone("0");
        zeroUpdate.setSetsDone("0");
        SimpleDateFormat sdfPosted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK);
        String currentDateTime = sdfPosted.format(System.currentTimeMillis());
        zeroUpdate.setTime(currentDateTime);
        this.updates.add(zeroUpdate);
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
