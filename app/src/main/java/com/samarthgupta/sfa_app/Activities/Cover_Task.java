package com.samarthgupta.sfa_app.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Update;
import com.samarthgupta.sfa_app.POJO.WT_Processes.UpdatePF;
import com.samarthgupta.sfa_app.R;

import java.util.List;

public class Cover_Task extends AppCompatActivity {
    TextView jobName ;

    TextView design, ferro, plates, printing, lamination, creasing, binding, packing, dispatch, challan, bill ;
    TextView designDone, ferroDone, platesDone, printingDone, laminationDone, creasingDone, bindingDone, packingDone, dispatchDone, challanDone, billDone ;

    TextView setsDoneRatio ;

    CardView cv_designing, cv_ferro, cv_plates, cv_printing, cv_lamination, cv_creasing, cv_binding, cv_packing, cv_dispatch, cv_challan, cv_bill ;

    Button bt_cover_updateProgress ;

    Processes processes ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover__task);
        Bundle bundle = getIntent().getExtras() ;
        String coverProcesses = bundle.getString("CoverProcesses") ;
        String CoverJobName = bundle.getString("CoverJbName") ;

        jobName = (TextView)findViewById(R.id.tv_jobname) ;

        design = (TextView) findViewById(R.id.tv_designing);
        ferro = (TextView) findViewById(R.id.tv_ferro);
        plates = (TextView) findViewById(R.id.tv_plates);
        printing = (TextView) findViewById(R.id.tv_printing);
        lamination = (TextView)findViewById(R.id.tv_lamination) ;
        creasing = (TextView)findViewById(R.id.tv_creasing) ;
        binding = (TextView)findViewById(R.id.tv_binding) ;
        packing = (TextView) findViewById(R.id.tv_packing);
        dispatch = (TextView) findViewById(R.id.tv_dispatch);
        challan = (TextView) findViewById(R.id.tv_challan);
        bill = (TextView) findViewById(R.id.tv_bill) ;
        
        
        
        cv_designing = (CardView)findViewById(R.id.cv_design) ;
        cv_ferro = (CardView)findViewById(R.id.cv_ferro) ;
        cv_plates = (CardView)findViewById(R.id.cv_plates) ;
        cv_printing = (CardView)findViewById(R.id.cv_printing) ;
        cv_lamination = (CardView)findViewById(R.id.cv_lamination) ;
        cv_creasing = (CardView)findViewById(R.id.cv_creasing) ;
        cv_binding = (CardView)findViewById(R.id.cv_binding) ;
        cv_packing = (CardView)findViewById(R.id.cv_packing) ;
        cv_dispatch = (CardView)findViewById(R.id.cv_dispatch) ;
        cv_challan = (CardView)findViewById(R.id.cv_challan) ;
        cv_bill = (CardView)findViewById(R.id.cv_bill) ;

        designDone = (TextView) findViewById(R.id.tv_designDone);
        ferroDone = (TextView) findViewById(R.id.tv_ferroDone);
        platesDone = (TextView) findViewById(R.id.tv_platesDone);
        printingDone = (TextView) findViewById(R.id.tv_printingdone);
        laminationDone = (TextView) findViewById(R.id.tv_laminationdone);
        creasingDone = (TextView) findViewById(R.id.tv_creasingdone);
        bindingDone = (TextView) findViewById(R.id.tv_bindingdone);
        packingDone = (TextView) findViewById(R.id.tv_packingdone);
        dispatchDone = (TextView) findViewById(R.id.tv_dispatchingdone);
        challanDone = (TextView) findViewById(R.id.tv_challandone);
        billDone = (TextView) findViewById(R.id.tv_billdone);

        setsDoneRatio = (TextView)findViewById(R.id.tv_setsDoneRatio);
        bt_cover_updateProgress = (Button)findViewById(R.id.bt_cover_update) ;

        processes = (Processes)new GsonBuilder().create().fromJson(coverProcesses,Processes.class) ;

        if (processes.getCover().getDesigning().getIsRequired()){
            cv_designing.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getDesigning().getIsRequired()){
            cv_designing.setVisibility(View.GONE);
        }
        if (processes.getCover().getFerro().getIsRequired()){
            cv_ferro.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getFerro().getIsRequired()){
            cv_ferro.setVisibility(View.GONE);
        }
        if (processes.getCover().getPlates().getIsRequired()){
            cv_plates.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getPlates().getIsRequired()){
            cv_plates.setVisibility(View.GONE);
        }
        if (processes.getCover().getPrinting().getIsRequired()){
            cv_printing.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getPrinting().getIsRequired()){
            cv_printing.setVisibility(View.GONE);
        }

        if (processes.getCover().getLamination().getIsRequired()){
            cv_lamination.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getLamination().getIsRequired()){
            cv_lamination.setVisibility(View.GONE);
        }
        if (processes.getCover().getCreasing().getIsRequired()){
            cv_creasing.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getCreasing().getIsRequired()){
            cv_creasing.setVisibility(View.GONE);
        }
        if (processes.getCover().getBinding().getIsRequired()){
            cv_binding.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getBinding().getIsRequired()){
            cv_binding.setVisibility(View.GONE);
        }
        if (processes.getCover().getPacking().getIsRequired()){
            cv_packing.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getPacking().getIsRequired()){
            cv_packing.setVisibility(View.GONE);
        }
        if (processes.getCover().getDispatch().getIsRequired()){
            cv_dispatch.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getDispatch().getIsRequired()){
            cv_dispatch.setVisibility(View.GONE);
        }
        if (processes.getCover().getChallan().getIsRequired()){
            cv_challan.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getChallan().getIsRequired()){
            cv_challan.setVisibility(View.GONE);
        }
        if (processes.getCover().getBill().getIsRequired()){
            cv_bill.setVisibility(View.VISIBLE);
        }else if (!processes.getCover().getBill().getIsRequired()){
            cv_bill.setVisibility(View.GONE);
        }

        //JOB NAME DISPLAY
        jobName.setText(CoverJobName);

        if (processes.getCover().getDesigning().getIsDone()){
            designDone.setText("Done");
        }else if (!processes.getCover().getDesigning().getIsDone()){
            designDone.setText("Not Done");
        }
        if (processes.getCover().getFerro().getIsDone()){
            ferroDone.setText("Done");
        }else if (!processes.getCover().getFerro().getIsDone()){
            ferroDone.setText("Not Done");
        }

        if (processes.getCover().getPlates().getIsDone()){
            platesDone.setText("Done");
        }else if (!processes.getCover().getPlates().getIsDone()){
            platesDone.setText("Not Done");
        }
        List<UpdatePF> updateprinting = processes.getCover().getPrinting().getUpdates() ;
        //setting the total done in printing
        String printingJobDone = updateprinting.get(updateprinting.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        printingDone.setText(printingJobDone) ;
        //setting the number of sets in printing
        String printingSetsDone = updateprinting.get(updateprinting.size()-1).getSetsDone()+ "/" + processes.getTotalSets() ;
        setsDoneRatio.setText(printingSetsDone);

        List<Update> updateLaminaiton = processes.getCover().getLamination().getUpdates() ;
        String LaminationJobDone = updateLaminaiton.get(updateLaminaiton.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        laminationDone.setText(LaminationJobDone);

        List<Update> updateCreasing = processes.getCover().getCreasing().getUpdates() ;
        String CreasingJobDone = updateCreasing.get(updateCreasing.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        creasingDone.setText(CreasingJobDone);

        List<Update> updateBinding = processes.getCover().getBinding().getUpdates() ;
        String BindingJobDone = updateBinding.get(updateBinding.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        bindingDone.setText(BindingJobDone);

        List<Update> updatePacking = processes.getCover().getPacking().getUpdates() ;
        String PackingJobDone = updatePacking.get(updatePacking.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        packingDone.setText(PackingJobDone);

        List<Update> updateDispatch = processes.getCover().getDispatch().getUpdates() ;
        String DispatchJobDone = updateDispatch.get(updateDispatch.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        dispatchDone.setText(DispatchJobDone);

        List<Update> updateChallan = processes.getCover().getChallan().getUpdates() ;
        String ChallanJobDone = updateChallan.get(updateChallan.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        challanDone.setText(ChallanJobDone);

        List<Update> updateBill = processes.getCover().getBill().getUpdates() ;
        String BillJobDone = updateBill.get(updateBill.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        billDone.setText(BillJobDone);
    }
}
