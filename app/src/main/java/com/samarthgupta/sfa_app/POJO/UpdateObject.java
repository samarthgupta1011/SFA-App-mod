package com.samarthgupta.sfa_app.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by samarthgupta on 11/10/17.
 */


//NO USE

public class UpdateObject {


    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("done")
    @Expose
    private String done;


    public UpdateObject(String time, String done) {
        this.time = time;
        this.done = done;
    }

//    "time": 5,
//            "done": 20
}
