
package com.samarthgupta.sfa_app.POJO.WT_Processes;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CentrePin {

    @SerializedName("isRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("updates")
    @Expose
    private List<Update> updates = null;

    public CentrePin() {
        this.isRequired = false;
        this.updates = new ArrayList<>();
        Update zeroUpdate = new Update();
        zeroUpdate.setDone("0");

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

    public List<Update> getUpdates() {
        return updates;
    }

    public void setUpdates(List<Update> updates) {
        this.updates = updates;
    }

}
