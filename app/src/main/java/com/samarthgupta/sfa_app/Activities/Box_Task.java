package com.samarthgupta.sfa_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Update;
import com.samarthgupta.sfa_app.POJO.WT_Processes.UpdatePF;
import com.samarthgupta.sfa_app.R;

import java.util.List;

public class Box_Task extends AppCompatActivity {
    TextView jobName ;
    Button bt_taskDetails ;
    TextView design, ferro, plates, printing, lamination,uv, embossing, foiling, dieCut ,  pasting, packing, dispatch, challan, bill ;
    TextView designDone, ferroDone, platesDone, printingDone, laminationDone, uvDone, embossingDone, foilingDone, dieCutDone,
            pastingDone, packingDone, dispatchDone, challanDone, billDone ;

    TextView setsDoneRatio ;
    Button btUpdateProgress;
    CardView cv_designing, cv_ferro, cv_plates, cv_printing, cv_lamination, cv_uv, cv_embossing,
            cv_foiling, cv_dieCut, cv_pasting, cv_packing, cv_dispatch, cv_challan, cv_bill ;
    Processes processes ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box__task);

        Bundle bundle = getIntent().getExtras() ;
        String boxProcesses = bundle.getString("BoxProcesses") ;
        final String BoxjobName = bundle.getString("BoxJobName") ;
        final String wt = getIntent().getStringExtra("wt_id");

        jobName = (TextView)findViewById(R.id.tv_jobname) ;
        bt_taskDetails = (Button) findViewById(R.id.bt_task_details) ;
        btUpdateProgress = (Button) findViewById(R.id.bt_box_update);

        design = (TextView) findViewById(R.id.tv_designing);
        ferro = (TextView) findViewById(R.id.tv_ferro);
        plates = (TextView) findViewById(R.id.tv_plates);
        printing = (TextView) findViewById(R.id.tv_printing);
        lamination = (TextView)findViewById(R.id.tv_lamination) ;
        uv = (TextView)findViewById(R.id.tv_uv) ;
        embossing = (TextView)findViewById(R.id.tv_embossing) ;
        foiling = (TextView)findViewById(R.id.tv_foiling) ;
        dieCut = (TextView)findViewById(R.id.tv_diecut) ;
        pasting = (TextView)findViewById(R.id.tv_pasting) ;
        packing = (TextView) findViewById(R.id.tv_packing);
        dispatch = (TextView) findViewById(R.id.tv_dispatch);
        challan = (TextView) findViewById(R.id.tv_challan);
        bill = (TextView) findViewById(R.id.tv_bill);

        cv_designing = (CardView)findViewById(R.id.cv_design) ;
        cv_ferro = (CardView)findViewById(R.id.cv_ferro) ;
        cv_plates = (CardView)findViewById(R.id.cv_plates) ;
        cv_printing = (CardView)findViewById(R.id.cv_printing) ;
        cv_lamination = (CardView)findViewById(R.id.cv_lamination) ;
        cv_uv = (CardView)findViewById(R.id.cv_uv) ;
        cv_embossing = (CardView)findViewById(R.id.cv_embossing) ;
        cv_foiling = (CardView)findViewById(R.id.cv_foiling) ;
        cv_dieCut = (CardView)findViewById(R.id.cv_diecut) ;
        cv_pasting = (CardView)findViewById(R.id.cv_pasting) ;
        cv_packing = (CardView)findViewById(R.id.cv_packing) ;
        cv_dispatch = (CardView)findViewById(R.id.cv_dispatch) ;
        cv_challan = (CardView)findViewById(R.id.cv_challan) ;
        cv_bill = (CardView)findViewById(R.id.cv_bill) ;

        designDone = (TextView) findViewById(R.id.tv_designDone);
        ferroDone = (TextView) findViewById(R.id.tv_ferroDone);
        platesDone = (TextView) findViewById(R.id.tv_platesDone);
        printingDone = (TextView) findViewById(R.id.tv_printingdone);
        laminationDone = (TextView) findViewById(R.id.tv_laminationdone);
        uvDone = (TextView) findViewById(R.id.tv_uvdone);
        embossingDone = (TextView) findViewById(R.id.tv_embossingdone);
        foilingDone = (TextView) findViewById(R.id.tv_foilingdone);
        dieCutDone = (TextView) findViewById(R.id.tv_diecutdone);
        pastingDone = (TextView) findViewById(R.id.tv_pastingdone);
        packingDone = (TextView) findViewById(R.id.tv_packingdone);
        dispatchDone = (TextView) findViewById(R.id.tv_dispatchingdone);
        challanDone = (TextView) findViewById(R.id.tv_challandone);
        billDone = (TextView) findViewById(R.id.tv_billdone);

        setsDoneRatio = (TextView)findViewById(R.id.tv_setsDoneRatio) ;
        
        processes = (Processes)new GsonBuilder().create().fromJson(boxProcesses,Processes.class) ;

        if (processes.getBox().getDesigning().getIsRequired()){
            cv_designing.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getDesigning().getIsRequired()){
            cv_designing.setVisibility(View.GONE);
        }
        if (processes.getBox().getFerro().getIsRequired()){
            cv_ferro.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getFerro().getIsRequired()){
            cv_ferro.setVisibility(View.GONE);
        }
        if (processes.getBox().getPlates().getIsRequired()){
            cv_plates.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getPlates().getIsRequired()){
            cv_plates.setVisibility(View.GONE);
        }
        if (processes.getBox().getPrinting().getIsRequired()){
            cv_printing.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getPrinting().getIsRequired()){
            cv_printing.setVisibility(View.GONE);
        }

        if (processes.getBox().getLamination().getIsRequired()){
            cv_lamination.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getLamination().getIsRequired()){
            cv_lamination.setVisibility(View.GONE);
        }
        if (processes.getBox().getUv().getIsRequired()){
            cv_uv.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getUv().getIsRequired()){
            cv_uv.setVisibility(View.GONE);
        }
        if (processes.getBox().getEmbossing().getIsRequired()){
            cv_embossing.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getEmbossing().getIsRequired()){
            cv_embossing.setVisibility(View.GONE);
        }
        if (processes.getBox().getFoiling().getIsRequired()){
            cv_foiling.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getFoiling().getIsRequired()){
            cv_foiling.setVisibility(View.GONE);
        }
        if (processes.getBox().getDieCut().getIsRequired()){
            cv_dieCut.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getDieCut().getIsRequired()){
            cv_dieCut.setVisibility(View.GONE);
        }
        if (processes.getBox().getPasting().getIsRequired()){
            cv_pasting.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getPasting().getIsRequired()){
            cv_pasting.setVisibility(View.GONE);
        }
        
        if (processes.getBox().getPacking().getIsRequired()){
            cv_packing.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getPacking().getIsRequired()){
            cv_packing.setVisibility(View.GONE);
        }
        if (processes.getBox().getDispatch().getIsRequired()){
            cv_dispatch.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getDispatch().getIsRequired()){
            cv_dispatch.setVisibility(View.GONE);
        }
        if (processes.getBox().getChallan().getIsRequired()){
            cv_challan.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getChallan().getIsRequired()){
            cv_challan.setVisibility(View.GONE);
        }
        if (processes.getBox().getBill().getIsRequired()){
            cv_bill.setVisibility(View.VISIBLE);
        }else if (!processes.getBox().getBill().getIsRequired()){
            cv_bill.setVisibility(View.GONE);
        }
        
        //JOB NAME DISPLAY
        jobName.setText(BoxjobName);
        
        if (processes.getBox().getDesigning().getIsDone()){
            designDone.setText(R.string.Done);
        }else if (!processes.getBox().getDesigning().getIsDone()){
            designDone.setText(R.string.NotDone);
        }
        if (processes.getBox().getFerro().getIsDone()){
            ferroDone.setText(R.string.Done);
        }else if (!processes.getBox().getFerro().getIsDone()){
            ferroDone.setText(R.string.NotDone);
        }

        if (processes.getBox().getPlates().getIsDone()){
            platesDone.setText(R.string.Done);
        }else if (!processes.getBox().getPlates().getIsDone()){
            platesDone.setText(R.string.NotDone);
        }
        List<UpdatePF> updateprinting = processes.getBox().getPrinting().getUpdates() ;
        //setting the total done in printing
        String printingJobDone = updateprinting.get(updateprinting.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        printingDone.setText(printingJobDone) ;
        //setting the number of sets in printing
        String printingSetsDone = updateprinting.get(updateprinting.size()-1).getSetsDone()+ "/" + processes.getTotalSets() ;
        setsDoneRatio.setText(printingSetsDone);

        List<Update> updateLaminaiton = processes.getBox().getLamination().getUpdates() ;
        String LaminationJobDone = updateLaminaiton.get(updateLaminaiton.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        laminationDone.setText(LaminationJobDone);

        List<Update> updateUV = processes.getBox().getUv().getUpdates() ;
        String UvJobDone = updateUV.get(updateUV.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        uvDone.setText(UvJobDone);

        List<Update> updateEmbossing = processes.getBox().getEmbossing().getUpdates() ;
        String EmbossingJobDone = updateEmbossing.get(updateEmbossing.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        embossingDone.setText(EmbossingJobDone);

        List<Update> updateFoiling = processes.getBox().getFoiling().getUpdates() ;
        String FoilingJobDone = updateFoiling.get(updateFoiling.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        foilingDone.setText(FoilingJobDone);

        List<Update> updateDieCut = processes.getBox().getDieCut().getUpdates() ;
        String DieCutJobDone = updateDieCut.get(updateDieCut.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        dieCutDone.setText(DieCutJobDone);

        List<Update> updatePasting = processes.getBox().getPasting().getUpdates() ;
        String PastingJobDone = updatePasting.get(updatePasting.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        pastingDone.setText(PastingJobDone);

        List<Update> updatePacking = processes.getBox().getPacking().getUpdates() ;
        String PackingJobDone = updatePacking.get(updatePacking.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        packingDone.setText(PackingJobDone);

        List<Update> updateDispatch = processes.getBox().getDispatch().getUpdates() ;
        String DispatchJobDone = updateDispatch.get(updateDispatch.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        dispatchDone.setText(DispatchJobDone);

        List<Update> updateChallan = processes.getBox().getChallan().getUpdates() ;
        String ChallanJobDone = updateChallan.get(updateChallan.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        challanDone.setText(ChallanJobDone);

        List<Update> updateBill = processes.getBox().getBill().getUpdates() ;
        String BillJobDone = updateBill.get(updateBill.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        billDone.setText(BillJobDone);
        //changing  the color for particular depatment worker
        final Employee employee = new GsonBuilder()
                .create()
                .fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE)
                        .getString("Data",null),Employee.class);
        Log.i("Employee dept",employee.getDept());

        if (employee.getDept().equals("designing")){
            cv_designing.setBackgroundColor(Color.GREEN);
        }else if (employee.getDept().equals("ferro")){
            cv_ferro.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("plates")){
            cv_plates.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("printing")){
            cv_printing.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("lamination")){
            cv_lamination.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("uv")){
            cv_uv.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("embossing")){
            cv_embossing.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("foiling")){
            cv_foiling.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("dieCut")){
            cv_dieCut.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("pasting")){
            cv_pasting.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("packing")){
            cv_packing.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("dispatch")){
            cv_dispatch.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("challan")){
            cv_challan.setBackgroundColor(Color.GREEN);
        }else if(employee.getDept().equals("bill")){
            cv_bill.setBackgroundColor(Color.GREEN);
        }

        bt_taskDetails.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Box_Task.this, TaskDetailsActivity.class) ;
                intent.putExtra("wt_id", wt) ;
                startActivity(intent);
            }
        });

        btUpdateProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Box_Task.this, UpdateActivity.class);
                intent.putExtra("wt_id", wt);
                //Send the status of the job according to the employee
                startActivity(intent);

            }
        });
    }
}
