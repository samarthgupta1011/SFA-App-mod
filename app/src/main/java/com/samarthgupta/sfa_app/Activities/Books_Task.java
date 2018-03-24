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

public class Books_Task extends AppCompatActivity {
    TextView jobName ;

    TextView design, ferro, plates, printing, folding, gathering, perfect, sewing, centerPin,  finishing, packing, dispatch, challan, bill ;
    TextView designDone, ferroDone, platesDone, printingDone, foldingDone, gatheringDone, perfectDone, sewingDone, centerPinDone,  finishingDone, packingDone, dispatchDone, challanDone, billDone ;

    TextView setsDoneRatio, formsDoneRatio ;

    CardView cv_designing, cv_ferro, cv_plates, cv_printing, cv_folding, cv_gathering, cv_perfect, cv_sewing, cv_centrepin, cv_finishing, cv_packing, cv_dispatch, cv_challan, cv_bill ;

    Button bt_book_UpdateProgress ;
    Processes processes ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books__task);
        Bundle bundle = getIntent().getExtras() ;
        String bookProcesses = bundle.getString("BookProcesses") ;
        String BookjobName = bundle.getString("BookJobName") ;

        jobName = (TextView)findViewById(R.id.tv_jobname) ;

        design = (TextView) findViewById(R.id.tv_designing);
        ferro = (TextView) findViewById(R.id.tv_ferro);
        plates = (TextView) findViewById(R.id.tv_plates);
        printing = (TextView) findViewById(R.id.tv_printing);
        folding = (TextView) findViewById(R.id.tv_folding);
        gathering = (TextView) findViewById(R.id.tv_gathering);
        sewing = (TextView) findViewById(R.id.tv_sewing);
        perfect = (TextView) findViewById(R.id.tv_perfect);
        centerPin = (TextView) findViewById(R.id.tv_centrepin);
        finishing = (TextView) findViewById(R.id.tv_finishing);
        packing = (TextView) findViewById(R.id.tv_packing);
        dispatch = (TextView) findViewById(R.id.tv_dispatch);
        challan = (TextView) findViewById(R.id.tv_challan);
        bill = (TextView) findViewById(R.id.tv_bill);

        cv_designing = (CardView)findViewById(R.id.cv_design) ;
        cv_ferro = (CardView)findViewById(R.id.cv_ferro) ;
        cv_plates = (CardView)findViewById(R.id.cv_plates) ;
        cv_printing = (CardView)findViewById(R.id.cv_printing) ;
        cv_folding = (CardView)findViewById(R.id.cv_folding) ;
        cv_gathering = (CardView)findViewById(R.id.cv_gathering) ;
        cv_perfect = (CardView)findViewById(R.id.cv_perfect) ;
        cv_sewing = (CardView)findViewById(R.id.cv_sewing) ;
        cv_centrepin = (CardView)findViewById(R.id.cv_centrepin) ;
        cv_finishing = (CardView)findViewById(R.id.cv_finishing) ;
        cv_packing = (CardView)findViewById(R.id.cv_packing) ;
        cv_dispatch = (CardView)findViewById(R.id.cv_dispatch) ;
        cv_challan = (CardView)findViewById(R.id.cv_challan) ;
        cv_bill = (CardView)findViewById(R.id.cv_bill) ;


        designDone = (TextView) findViewById(R.id.tv_designDone);
        ferroDone = (TextView) findViewById(R.id.tv_ferroDone);
        platesDone = (TextView) findViewById(R.id.tv_platesDone);
        printingDone = (TextView) findViewById(R.id.tv_printingdone);
        foldingDone = (TextView) findViewById(R.id.tv_foldingdone);
        gatheringDone = (TextView) findViewById(R.id.tv_gatheringdone);
        sewingDone = (TextView) findViewById(R.id.tv_sewingdone);
        perfectDone = (TextView) findViewById(R.id.tv_perfectdone);
        centerPinDone = (TextView) findViewById(R.id.tv_centrepindone);
        finishingDone = (TextView) findViewById(R.id.tv_finishingdone);
        packingDone = (TextView) findViewById(R.id.tv_packingdone);
        dispatchDone = (TextView) findViewById(R.id.tv_dispatchingdone);
        challanDone = (TextView) findViewById(R.id.tv_challandone);
        billDone = (TextView) findViewById(R.id.tv_billdone);

        setsDoneRatio = (TextView)findViewById(R.id.tv_setsDoneRatio) ;
        formsDoneRatio = (TextView)findViewById(R.id.tv_formsDoneRatio) ;

        bt_book_UpdateProgress = (Button)findViewById(R.id.bt_book_update) ;

        processes = (Processes) new GsonBuilder().create().fromJson( bookProcesses, Processes.class);

        if (processes.getBook().getDesigning().getIsRequired()){
            cv_designing.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getDesigning().getIsRequired()){
            cv_designing.setVisibility(View.GONE);
        }
        if (processes.getBook().getFerro().getIsRequired()){
            cv_ferro.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getFerro().getIsRequired()){
            cv_ferro.setVisibility(View.GONE);
        }
        if (processes.getBook().getPlates().getIsRequired()){
            cv_plates.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getPlates().getIsRequired()){
            cv_plates.setVisibility(View.GONE);
        }
        if (processes.getBook().getPrinting().getIsRequired()){
            cv_printing.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getPrinting().getIsRequired()){
            cv_printing.setVisibility(View.GONE);
        }
        if (processes.getBook().getFolding().getIsRequired()){
            cv_folding.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getFolding().getIsRequired()){
            cv_folding.setVisibility(View.GONE);
        }
        if (processes.getBook().getGathering().getIsRequired()){
            cv_gathering.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getGathering().getIsRequired()){
            cv_gathering.setVisibility(View.GONE);
        }
        if (processes.getBook().getPerfect().getIsRequired()){
            cv_perfect.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getPerfect().getIsRequired()){
            cv_perfect.setVisibility(View.GONE);
        }
        if (processes.getBook().getSewing().getIsRequired()){
            cv_sewing.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getSewing().getIsRequired()){
            cv_sewing.setVisibility(View.GONE);
        }
        if (processes.getBook().getCentrePin().getIsRequired()){
            cv_centrepin.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getCentrePin().getIsRequired()){
            cv_centrepin.setVisibility(View.GONE);
        }
        if (processes.getBook().getFinishing().getIsRequired()){
            cv_finishing.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getFinishing().getIsRequired()){
            cv_finishing.setVisibility(View.GONE);
        }
        if (processes.getBook().getPacking().getIsRequired()){
            cv_packing.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getPacking().getIsRequired()){
            cv_packing.setVisibility(View.GONE);
        }
        if (processes.getBook().getDispatch().getIsRequired()){
            cv_dispatch.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getDispatch().getIsRequired()){
            cv_dispatch.setVisibility(View.GONE);
        }
        if (processes.getBook().getChallan().getIsRequired()){
            cv_challan.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getChallan().getIsRequired()){
            cv_challan.setVisibility(View.GONE);
        }
        if (processes.getBook().getBill().getIsRequired()){
            cv_bill.setVisibility(View.VISIBLE);
        }else if (!processes.getBook().getBill().getIsRequired()){
            cv_bill.setVisibility(View.GONE);
        }

//displaying the JOB NAME
        jobName.setText(BookjobName);


        if (processes.getBook().getDesigning().getIsDone()){
            designDone.setText("Done") ;
        }else if (!processes.getBook().getDesigning().getIsDone()){
            designDone.setText("Not Done");
        }

        if (processes.getBook().getFerro().getIsDone()){
            ferroDone.setText("Done");
        }else if (!processes.getBook().getFerro().getIsDone()){
            ferroDone.setText("Not Done");
        }

        if (processes.getBook().getPlates().getIsDone()){
            platesDone.setText("Done");
        }else if (!processes.getBook().getPlates().getIsDone()){
            platesDone.setText("Not Done");
        }
        List<UpdatePF> updateprinting = processes.getBook().getPrinting().getUpdates() ;
        //setting the total done in printing
        String printingJobDone = updateprinting.get(updateprinting.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        printingDone.setText(printingJobDone) ;
        //setting the number of sets in printing
        String printingSetsDone = updateprinting.get(updateprinting.size()-1).getSetsDone()+ "/" + processes.getTotalSets() ;
        setsDoneRatio.setText(printingSetsDone);


        List<UpdatePF> updatefolding = processes.getBook().getFolding().getUpdates() ;
        //setting the total done in folding
        String foldingJobDone = updatefolding.get(updatefolding.size()-1).getDone()+ "/" +processes.getTotalNumber() ;
        foldingDone.setText(foldingJobDone);
        //setting the number of forms in folding
        String foldingFormsDone = updatefolding.get(updatefolding.size()-1).getDone() + "/" + processes.getTotalForms() ;
        formsDoneRatio.setText(foldingFormsDone);

        List<Update> updateGathering = processes.getBook().getGathering().getUpdates() ;
        String gatheringJobDone = updateGathering.get(updateGathering.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        gatheringDone.setText(gatheringJobDone);

        List<Update> updatePerfect = processes.getBook().getPerfect().getUpdates() ;
        String perfectJobDone = updatePerfect.get(updatePerfect.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        perfectDone.setText(perfectJobDone);

        List<Update> updateSewing = processes.getBook().getSewing().getUpdates() ;
        String SewingJobDone = updateSewing.get(updateSewing.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        sewingDone.setText(SewingJobDone);

        List<Update> updateCentrepin = processes.getBook().getCentrePin().getUpdates() ;
        String CentrePinJobDone = updateCentrepin.get(updateCentrepin.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        centerPinDone.setText(CentrePinJobDone);

        List<Update> updateFinishing = processes.getBook().getFinishing().getUpdates() ;
        String finishingJobDone = updateFinishing.get(updateFinishing.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        finishingDone.setText(finishingJobDone);

        List<Update> updatePacking = processes.getBook().getPacking().getUpdates() ;
        String PackingJobDone = updatePacking.get(updatePacking.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        packingDone.setText(PackingJobDone);

        List<Update> updateDispatch = processes.getBook().getDispatch().getUpdates() ;
        String DispatchJobDone = updateDispatch.get(updateDispatch.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        dispatchDone.setText(DispatchJobDone);

        List<Update> updateChallan = processes.getBook().getChallan().getUpdates() ;
        String ChallanJobDone = updateChallan.get(updateChallan.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        challanDone.setText(ChallanJobDone);

        List<Update> updateBill = processes.getBook().getBill().getUpdates() ;
        String BillJobDone = updateBill.get(updateBill.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        billDone.setText(BillJobDone);
    }
}
