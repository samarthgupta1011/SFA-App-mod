package com.samarthgupta.sfa_app.Activities.Tasks;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.Activities.TaskDetailsActivity;
import com.samarthgupta.sfa_app.Activities.UpdateActivity;
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
    String printing2ndLast, setDone, lamination2ndLast,uv2ndLast, embossing2ndLast, foiling2ndLast, diecut2ndLast, pasting2ndLast, packing2ndLast, dispatch2ndLast, challan2ndLast, bill2ndLast;
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

        final List<UpdatePF> updateprinting = processes.getBox().getPrinting().getUpdates() ;
        //setting the total done in printing
        final String printingJobDone = updateprinting.get(updateprinting.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        printingDone.setText(printingJobDone) ;
        if (updateprinting.size() >= 2) {
            printing2ndLast = updateprinting.get(updateprinting.size() - 2).getDone() + "/" + processes.getTotalNumber();
            setDone = updateprinting.get(updateprinting.size() - 2).getSetsDone() + "/" + processes.getTotalNumber();
        }
        //setting the number of sets in printing
        final String printingSetsDone = updateprinting.get(updateprinting.size()-1).getSetsDone()+ "/" + processes.getTotalSets() ;
        setsDoneRatio.setText(printingSetsDone);

        final List<Update> updateLaminaiton = processes.getBox().getLamination().getUpdates() ;
        final String laminationJobDone = updateLaminaiton.get(updateLaminaiton.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        laminationDone.setText(laminationJobDone);
        if (updateLaminaiton.size() >= 2) {
            lamination2ndLast = updateLaminaiton.get(updateLaminaiton.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateUV = processes.getBox().getUv().getUpdates() ;
        final String uvJobDone = updateUV.get(updateUV.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        uvDone.setText(uvJobDone);
        if (updateUV.size() >= 2) {
            uv2ndLast = updateUV.get(updateUV.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateEmbossing = processes.getBox().getEmbossing().getUpdates() ;
        final String embossingJobDone = updateEmbossing.get(updateEmbossing.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        embossingDone.setText(embossingJobDone);
        if (updateEmbossing.size() >= 2) {
            embossing2ndLast = updateEmbossing.get(updateEmbossing.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateFoiling = processes.getBox().getFoiling().getUpdates() ;
        final String foilingJobDone = updateFoiling.get(updateFoiling.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        foilingDone.setText(foilingJobDone);
        if (updateFoiling.size() >= 2) {
            foiling2ndLast = updateFoiling.get(updateFoiling.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateDieCut = processes.getBox().getDieCut().getUpdates() ;
        final String dieCutJobDone = updateDieCut.get(updateDieCut.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        dieCutDone.setText(dieCutJobDone);
        if (updateDieCut.size() >= 2) {
            diecut2ndLast = updateDieCut.get(updateDieCut.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updatePasting = processes.getBox().getPasting().getUpdates() ;
        final String pastingJobDone = updatePasting.get(updatePasting.size()-1).getDone()+"/"+processes.getTotalNumber() ;
        pastingDone.setText(pastingJobDone);
        if (updatePasting.size() >= 2) {
            pasting2ndLast = updatePasting.get(updatePasting.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updatePacking = processes.getBox().getPacking().getUpdates() ;
        final String packingJobDone = updatePacking.get(updatePacking.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        packingDone.setText(packingJobDone);
        if (updatePacking.size() >= 2) {
            packing2ndLast = updatePacking.get(updatePacking.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateDispatch = processes.getBox().getDispatch().getUpdates() ;
        final String dispatchJobDone = updateDispatch.get(updateDispatch.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        dispatchDone.setText(dispatchJobDone);
        if (updateDispatch.size() >= 2) {
            dispatch2ndLast = updateDispatch.get(updateDispatch.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateChallan = processes.getBox().getChallan().getUpdates() ;
        final String challanJobDone = updateChallan.get(updateChallan.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        challanDone.setText(challanJobDone);
        if (updateChallan.size() >= 2) {
            challan2ndLast = updateChallan.get(updateChallan.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }


        final List<Update> updateBill = processes.getBox().getBill().getUpdates() ;
        final String billJobDone = updateBill.get(updateBill.size()-1).getDone() + "/" + processes.getTotalNumber() ;
        billDone.setText(billJobDone);
        if (updateBill.size() >= 2) {
            bill2ndLast = updateBill.get(updateBill.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

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
        }else if(employee.getDept().equals("die_cut")){
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

        cv_designing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Designing Progress 📈");
                if (processes.getBox().getDesigning().getIsDone()) {
                    builder.setMessage("Designing Done 👍 ");
                } else if (!processes.getBox().getDesigning().getIsDone()){
                    builder.setMessage("Not Done 👷️ 🛠‍");
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        cv_ferro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Ferro Progress 📈");
                if (processes.getBox().getDesigning().getIsDone()) {
                    builder.setMessage("Ferro Done 👍 ");
                } else if (!processes.getBox().getDesigning().getIsDone()){
                    builder.setMessage("Not Done 👷️ 🛠");
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        cv_plates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Plates Progress 📈");
                if (processes.getBox().getDesigning().getIsDone()) {
                    builder.setMessage("Plates Done 👍 ");
                } else if (!processes.getBox().getDesigning().getIsDone()){
                    builder.setMessage("Not Done 👷️ 🛠");
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        cv_printing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Printing Progress 📈");
                if (updateprinting.size() >= 2) {
                    builder.setMessage("Printing Done: " + printing2ndLast + " to " + printingJobDone + "\nSets Done: " + setDone + " to " + printingSetsDone + "\n\n-- Last Updated : " + updateprinting.get(updateprinting.size() - 1).getTime());
                } else {
                    builder.setMessage("Printing Done: " + printingJobDone + "\nSets Done: " + printingSetsDone + "\n\n -- Last Updated: " + updateprinting.get(updateprinting.size() - 1).getTime());
                }

                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });

        cv_lamination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Lamination Progress 📈");
                if (updateLaminaiton.size() >= 2) {
                    builder.setMessage("Lamination Done: " + lamination2ndLast + " to " + laminationJobDone + "\n\n -- Last Updated : " + updateLaminaiton.get(updateLaminaiton.size() - 1).getTime());
                } else {
                    builder.setMessage("Lamination Done: " + laminationJobDone + "\n\n -- Last Updated: " + updateLaminaiton.get(updateLaminaiton.size() - 1).getTime());
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        cv_uv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("UV Progress 📈");
                if (updateUV.size() >= 2) {
                    builder.setMessage("UV Done: " + uv2ndLast + " to " + uvJobDone + "\n\n -- Last Updated : " + updateUV.get(updateUV.size() - 1).getTime());
                } else {
                    builder.setMessage("UV Done: " + uvJobDone + "\n\n -- Last Updated: " + updateUV.get(updateUV.size() - 1).getTime());
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });

        cv_embossing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Embossing Progress 📈");
                if (updateEmbossing.size() >= 2) {
                    builder.setMessage("Embossing Done: " + embossing2ndLast + " to " + embossingJobDone + "\n\n -- Last Updated : " + updateEmbossing.get(updateEmbossing.size() - 1).getTime());
                } else {
                    builder.setMessage("Embossing Done: " + embossingJobDone + "\n\n -- Last Updated: " + updateEmbossing.get(updateEmbossing.size() - 1).getTime());
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        cv_foiling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Foiling Progress 📈");
                if (updateFoiling.size() >= 2) {
                    builder.setMessage("Foiling Done: " + foiling2ndLast + " to " + foilingJobDone + "\n\n -- Last Updated : " + updateFoiling.get(updateFoiling.size() - 1).getTime());
                } else {
                    builder.setMessage("Foiling Done: " + foilingJobDone + "\n\n -- Last Updated: " + updateFoiling.get(updateFoiling.size() - 1).getTime());
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        cv_pasting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Pasting Progress 📈");
                if (updateFoiling.size() >= 2) {
                    builder.setMessage("Pasting Done: " + pasting2ndLast + " to " + packingJobDone + "\n\n -- Last Updated : " + updatePasting.get(updatePasting.size() - 1).getTime());
                } else {
                    builder.setMessage("Pasting Done: " + pastingJobDone + "\n\n -- Last Updated: " + updatePasting.get(updatePasting.size() - 1).getTime());
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        cv_dieCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("DieCut Progress 📈");
                if (updateFoiling.size() >= 2) {
                    builder.setMessage("DieCut Done: " + diecut2ndLast + " to " + dieCutJobDone + "\n\n -- Last Updated : " + updateDieCut.get(updateDieCut.size() - 1).getTime());
                } else {
                    builder.setMessage("DieCut Done: " + dieCutJobDone + "\n\n -- Last Updated: " + updateDieCut.get(updateDieCut.size() - 1).getTime());
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        cv_packing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Packing Progress 📈");
                if (updatePacking.size() >= 2) {
                    builder.setMessage("Packing Done: " + packing2ndLast + " to " + packingJobDone + "\n\n -- Last Updated : " + updatePacking.get(updatePacking.size() - 1).getTime());
                } else {
                    builder.setMessage("Packing Done: " + packingJobDone + "\n\n -- Last Updated: " + updatePacking.get(updatePacking.size() - 1).getTime());
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });

        cv_dispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Dispatching Progress 📈");
                if (updateDispatch.size() >= 2) {
                    builder.setMessage("Dispatching Done: " + dispatch2ndLast + " to " + dispatchJobDone + "\n\n -- Last Updated : " + updateDispatch.get(updateDispatch.size() - 1).getTime());
                } else {
                    builder.setMessage("Dispatching Done: " + dispatchJobDone + "\n\n -- Last Updated: " + updateDispatch.get(updateDispatch.size() - 1).getTime());
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        cv_challan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Challan Progress 📈");
                if (updateChallan.size() >= 2) {
                    builder.setMessage("Challan Done: " + dispatch2ndLast + " to " + challanJobDone + "\n\n -- Last Updated : " + updateChallan.get(updateChallan.size() - 1).getTime());
                } else {
                    builder.setMessage("Challan Done: " + challanJobDone + "\n\n -- Last Updated: " + updateChallan.get(updateChallan.size() - 1).getTime());
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        cv_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Box_Task.this);
                builder.setTitle("Billing Progress 📈");
                if (updateBill.size() >= 2) {
                    builder.setMessage("Bill Done: " + bill2ndLast + " to " + billJobDone + "\n\n -- Last Updated : " + updateBill.get(updateBill.size() - 1).getTime());
                } else {
                    builder.setMessage("Bill Done: " + billJobDone + "\n\n -- Last Updated: " + updateBill.get(updateBill.size() - 1).getTime());
                }
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        });
        
        btUpdateProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Box_Task.this, UpdateActivity.class);
                intent.putExtra("wt_id", wt);
                //Send the status of the job according to the employee
                startActivity(intent);
                finish();
            }
        });
    }

}
