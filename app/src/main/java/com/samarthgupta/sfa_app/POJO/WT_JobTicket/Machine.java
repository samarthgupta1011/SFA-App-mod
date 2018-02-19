package com.samarthgupta.sfa_app.POJO.WT_JobTicket;

/**
 * Created by samarthgupta on 28/07/17.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Machine {

    @SerializedName("machine")
    @Expose
    private List<String> machine = null;
    @SerializedName("name")
    @Expose
    private String name;

    public Machine(List<String> machine, String name) {
        this.machine = machine;
        this.name = name;
    }

    public List<String> getMachine() {
        return machine;
    }

    public void setMachine(List<String> machine) {
        this.machine = machine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}