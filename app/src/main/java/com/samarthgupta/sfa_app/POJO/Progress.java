package com.samarthgupta.sfa_app.POJO;

import java.util.ArrayList;
import java.util.List;

//NO USE

/**
 * Created by samarthgupta on 11/10/17.
 */

public class Progress {

    boolean use;
    float percentageComp;
    List<UpdateObject> updates;

    public Progress(){
        use = false;
        percentageComp = 0;
        updates = new ArrayList<>();
    }

    public Progress(boolean b){
        use = b;
        percentageComp = 0;
        updates = new ArrayList<>();
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public float getPercentageComp() {
        return percentageComp;
    }

    public void setPercentageComp(float percentageComp) {
        this.percentageComp = percentageComp;
    }

    public List<UpdateObject> getUpdates() {
        return updates;
    }

    public void setUpdates(List<UpdateObject> updates) {
        this.updates = updates;
    }

    //
//    {
//        "use": true,
//            "percentageComp": 0,
//            "updates": []
//    }
}
