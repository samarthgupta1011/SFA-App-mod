package com.samarthgupta.sfa_app.Activities.Tasks;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class Cover_Task extends AppCompatActivity {
    TextView jobName;
    Button bt_taskDetails;
    TextView design, ferro, plates, printing, lamination, creasing, binding, packing, dispatch, challan, bill;
    TextView designDone, ferroDone, platesDone, printingDone, laminationDone, creasingDone, bindingDone, packingDone, dispatchDone, challanDone, billDone;
    String printing2ndlast, setsDone, lamination2ndlast, creasing2ndlast, binding2ndlast, packing2ndlast, dispatch2ndlast, challan2ndLast, bill2ndLast;
    TextView setsDoneRatio;

    CardView cv_designing, cv_ferro, cv_plates, cv_printing, cv_lamination, cv_creasing, cv_binding, cv_packing, cv_dispatch, cv_challan, cv_bill;
    Button btUpdateProgress;
    Processes processes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover__task);
        Bundle bundle = getIntent().getExtras();
        String coverProcesses = bundle.getString("CoverProcesses");
        final String CoverJobName = bundle.getString("CoverJbName");
        final String wt = getIntent().getStringExtra("wt_id");

        jobName = (TextView) findViewById(R.id.tv_jobname);
        bt_taskDetails = (Button) findViewById(R.id.bt_task_details);
        btUpdateProgress = (Button) findViewById(R.id.bt_cover_update);

        design = (TextView) findViewById(R.id.tv_designing);
        ferro = (TextView) findViewById(R.id.tv_ferro);
        plates = (TextView) findViewById(R.id.tv_plates);
        printing = (TextView) findViewById(R.id.tv_printing);
        lamination = (TextView) findViewById(R.id.tv_lamination);
        creasing = (TextView) findViewById(R.id.tv_creasing);
        binding = (TextView) findViewById(R.id.tv_binding);
        packing = (TextView) findViewById(R.id.tv_packing);
        dispatch = (TextView) findViewById(R.id.tv_dispatch);
        challan = (TextView) findViewById(R.id.tv_challan);
        bill = (TextView) findViewById(R.id.tv_bill);


        cv_designing = (CardView) findViewById(R.id.cv_design);
        cv_ferro = (CardView) findViewById(R.id.cv_ferro);
        cv_plates = (CardView) findViewById(R.id.cv_plates);
        cv_printing = (CardView) findViewById(R.id.cv_printing);
        cv_lamination = (CardView) findViewById(R.id.cv_lamination);
        cv_creasing = (CardView) findViewById(R.id.cv_creasing);
        cv_binding = (CardView) findViewById(R.id.cv_binding);
        cv_packing = (CardView) findViewById(R.id.cv_packing);
        cv_dispatch = (CardView) findViewById(R.id.cv_dispatch);
        cv_challan = (CardView) findViewById(R.id.cv_challan);
        cv_bill = (CardView) findViewById(R.id.cv_bill);

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

        setsDoneRatio = (TextView) findViewById(R.id.tv_setsDoneRatio);
        processes = (Processes) new GsonBuilder().create().fromJson(coverProcesses, Processes.class);
        Log.d("process", coverProcesses);
        if (processes.getCover().getDesigning().getIsRequired()) {
            cv_designing.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getDesigning().getIsRequired()) {
            cv_designing.setVisibility(View.GONE);
        }
        if (processes.getCover().getFerro().getIsRequired()) {
            cv_ferro.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getFerro().getIsRequired()) {
            cv_ferro.setVisibility(View.GONE);
        }
        if (processes.getCover().getPlates().getIsRequired()) {
            cv_plates.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getPlates().getIsRequired()) {
            cv_plates.setVisibility(View.GONE);
        }
        if (processes.getCover().getPrinting().getIsRequired()) {
            cv_printing.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getPrinting().getIsRequired()) {
            cv_printing.setVisibility(View.GONE);
        }

        if (processes.getCover().getLamination().getIsRequired()) {
            cv_lamination.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getLamination().getIsRequired()) {
            cv_lamination.setVisibility(View.GONE);
        }
        if (processes.getCover().getCreasing().getIsRequired()) {
            cv_creasing.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getCreasing().getIsRequired()) {
            cv_creasing.setVisibility(View.GONE);
        }
        if (processes.getCover().getBinding().getIsRequired()) {
            cv_binding.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getBinding().getIsRequired()) {
            cv_binding.setVisibility(View.GONE);
        }
        if (processes.getCover().getPacking().getIsRequired()) {
            cv_packing.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getPacking().getIsRequired()) {
            cv_packing.setVisibility(View.GONE);
        }
        if (processes.getCover().getDispatch().getIsRequired()) {
            cv_dispatch.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getDispatch().getIsRequired()) {
            cv_dispatch.setVisibility(View.GONE);
        }
        if (processes.getCover().getChallan().getIsRequired()) {
            cv_challan.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getChallan().getIsRequired()) {
            cv_challan.setVisibility(View.GONE);
        }
        if (processes.getCover().getBill().getIsRequired()) {
            cv_bill.setVisibility(View.VISIBLE);
        } else if (!processes.getCover().getBill().getIsRequired()) {
            cv_bill.setVisibility(View.GONE);
        }

        //JOB NAME DISPLAY
        jobName.setText(CoverJobName);

        if (processes.getCover().getDesigning().getIsDone()) {
            designDone.setText(R.string.Done);
        } else if (!processes.getCover().getDesigning().getIsDone()) {
            designDone.setText(R.string.NotDone);
        }
        if (processes.getCover().getFerro().getIsDone()) {
            ferroDone.setText(R.string.Done);
        } else if (!processes.getCover().getFerro().getIsDone()) {
            ferroDone.setText(R.string.NotDone);
        }

        if (processes.getCover().getPlates().getIsDone()) {
            platesDone.setText(R.string.Done);
        } else if (!processes.getCover().getPlates().getIsDone()) {
            platesDone.setText(R.string.NotDone);
        }
        final List<UpdatePF> updateprinting = processes.getCover().getPrinting().getUpdates();
        //setting the total done in printing
        final String printingJobDone = updateprinting.get(updateprinting.size() - 1).getDone() + "/" + processes.getTotalNumber();
        printingDone.setText(printingJobDone);
        if (updateprinting.size() >= 2) {
            printing2ndlast = updateprinting.get(updateprinting.size() - 2).getDone() + "/" + processes.getTotalNumber();
            setsDone = updateprinting.get(updateprinting.size() - 2).getSetsDone() + "/" + processes.getTotalNumber();
        }
        //setting the number of sets in printing
        final String printingSetsDone = updateprinting.get(updateprinting.size() - 1).getSetsDone() + "/" + processes.getTotalSets();
        setsDoneRatio.setText(printingSetsDone);

        final List<Update> updateLaminaiton = processes.getCover().getLamination().getUpdates();
        final String LaminationJobDone = updateLaminaiton.get(updateLaminaiton.size() - 1).getDone() + "/" + processes.getTotalNumber();
        laminationDone.setText(LaminationJobDone);
        if (updateLaminaiton.size() >= 2) {
            lamination2ndlast = updateLaminaiton.get(updateLaminaiton.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateCreasing = processes.getCover().getCreasing().getUpdates();
        final String CreasingJobDone = updateCreasing.get(updateCreasing.size() - 1).getDone() + "/" + processes.getTotalNumber();
        creasingDone.setText(CreasingJobDone);
        if (updateCreasing.size() >= 2) {
            creasing2ndlast = updateCreasing.get(updateCreasing.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateBinding = processes.getCover().getBinding().getUpdates();
        final String BindingJobDone = updateBinding.get(updateBinding.size() - 1).getDone() + "/" + processes.getTotalNumber();
        bindingDone.setText(BindingJobDone);
        if (updateBinding.size() >= 2) {
            binding2ndlast = updateBinding.get(updateBinding.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updatePacking = processes.getCover().getPacking().getUpdates();
        final String PackingJobDone = updatePacking.get(updatePacking.size() - 1).getDone() + "/" + processes.getTotalNumber();
        packingDone.setText(PackingJobDone);
        if (updatePacking.size() >= 2) {
            packing2ndlast = updatePacking.get(updatePacking.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateDispatch = processes.getCover().getDispatch().getUpdates();
        final String DispatchJobDone = updateDispatch.get(updateDispatch.size() - 1).getDone() + "/" + processes.getTotalNumber();
        dispatchDone.setText(DispatchJobDone);
        if (updateDispatch.size() >= 2) {
            dispatch2ndlast = updateDispatch.get(updateDispatch.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateChallan = processes.getCover().getChallan().getUpdates();
        final String ChallanJobDone = updateChallan.get(updateChallan.size() - 1).getDone() + "/" + processes.getTotalNumber();
        challanDone.setText(ChallanJobDone);
        if (updateChallan.size() >= 2) {
            challan2ndLast = updateChallan.get(updateChallan.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateBill = processes.getCover().getBill().getUpdates();
        final String BillJobDone = updateBill.get(updateBill.size() - 1).getDone() + "/" + processes.getTotalNumber();
        billDone.setText(BillJobDone);
        if (updateBill.size() >= 2) {
            bill2ndLast = updateBill.get(updateBill.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        //changing  the color for particular depatment worker
        final Employee employee = new GsonBuilder()
                .create()
                .fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE)
                        .getString("Data", null), Employee.class);
        Log.i("Employee dept", employee.getDept());

        if (employee.getDept().equals("designing")) {
            cv_designing.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("ferro")) {
            cv_ferro.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("plates")) {
            cv_plates.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("printing")) {
            cv_printing.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("lamination")) {
            cv_lamination.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("creasing")) {
            cv_creasing.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("binding")) {
            cv_binding.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("packing")) {
            cv_packing.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("dispatch")) {
            cv_dispatch.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("challan")) {
            cv_challan.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("bill")) {
            cv_bill.setBackgroundColor(Color.GREEN);
        }


        bt_taskDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cover_Task.this, TaskDetailsActivity.class);
                intent.putExtra("wt_id", wt);
                startActivity(intent);
            }
        });

        cv_designing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Designing Progress 📈");
                if (processes.getCover().getDesigning().getIsDone()) {
                    builder.setMessage("Designing Done 👍 ");
                } else if (!processes.getCover().getDesigning().getIsDone()){
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Ferro Progress 📈");
                if (processes.getCover().getFerro().getIsDone()) {
                    builder.setMessage("Ferro Done 👍 ");
                } else if (!processes.getCover().getFerro().getIsDone()){
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Plates Progress 📈");
                if (processes.getCover().getPlates().getIsDone()) {
                    builder.setMessage("Plates Done 👍");
                } else if (!processes.getCover().getPlates().getIsDone()){
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Printing Progress 📈");
                if (updateprinting.size() >= 2) {
                    builder.setMessage("Printing Done: " + printing2ndlast + " to " + printingJobDone + "\nSets Done: " + setsDone + " to " + printingSetsDone + "\n\n-- Last Updated : " + updateprinting.get(updateprinting.size() - 1).getTime());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Lamination Progress 📈");
                if (updateLaminaiton.size() >= 2) {
                    builder.setMessage("Lamination Done: " + lamination2ndlast + " to " + LaminationJobDone + "\n\n -- Last Updated : " + updateLaminaiton.get(updateLaminaiton.size() - 1).getTime());
                } else {
                    builder.setMessage("Lamination Done: " + LaminationJobDone + "\n\n -- Last Updated: " + updateLaminaiton.get(updateLaminaiton.size() - 1).getTime());
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

        cv_creasing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Creasing Progress 📈");
                if (updateDispatch.size() >= 2) {
                    builder.setMessage("Creasing Done: " + creasing2ndlast + " to " + CreasingJobDone + "\n\n -- Last Updated : " + updateCreasing.get(updateCreasing.size() - 1).getTime());
                } else {
                    builder.setMessage("Creasing Done: " + CreasingJobDone + "\n\n -- Last Updated: " + updateCreasing.get(updateCreasing.size() - 1).getTime());
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
        cv_binding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Binding Progress 📈");
                if (updateBinding.size() >= 2) {
                    builder.setMessage("Binding Done: " + binding2ndlast + " to " + BindingJobDone + "\n\n -- Last Updated : " + updateBinding.get(updateBinding.size() - 1).getTime());
                } else {
                    builder.setMessage("Binding Done: " + BindingJobDone + "\n\n -- Last Updated: " + updateBinding.get(updateBinding.size() - 1).getTime());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Packing Progress 📈");
                if (updatePacking.size() >= 2) {
                    builder.setMessage("Packing Done: " + packing2ndlast + " to " + PackingJobDone + "\n\n -- Last Updated : " + updatePacking.get(updatePacking.size() - 1).getTime());
                } else {
                    builder.setMessage("Packing Done: " + PackingJobDone + "\n\n -- Last Updated: " + updatePacking.get(updatePacking.size() - 1).getTime());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Dispatching Progress 📈");
                if (updateDispatch.size() >= 2) {
                    builder.setMessage("Dispatching Done: " + dispatch2ndlast + " to " + DispatchJobDone + "\n\n -- Last Updated : " + updateDispatch.get(updateDispatch.size() - 1).getTime());
                } else {
                    builder.setMessage("Dispatching Done: " + DispatchJobDone + "\n\n -- Last Updated: " + updateDispatch.get(updateDispatch.size() - 1).getTime());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Challan Progress 📈");
                if (updateChallan.size() >= 2) {
                    builder.setMessage("Challan Done: " + dispatch2ndlast + " to " + ChallanJobDone + "\n\n -- Last Updated : " + updateChallan.get(updateChallan.size() - 1).getTime());
                } else {
                    builder.setMessage("Challan Done: " + ChallanJobDone + "\n\n -- Last Updated: " + updateChallan.get(updateChallan.size() - 1).getTime());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Cover_Task.this);
                builder.setTitle("Billing Progress 📈");
                if (updateBill.size() >= 2) {
                    builder.setMessage("Bill Done: " + bill2ndLast + " to " + BillJobDone + "\n\n -- Last Updated : " + updateBill.get(updateBill.size() - 1).getTime());
                } else {
                    builder.setMessage("Bill Done: " + BillJobDone + "\n\n -- Last Updated: " + updateBill.get(updateBill.size() - 1).getTime());
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

                Intent intent = new Intent(Cover_Task.this, UpdateActivity.class);
                intent.putExtra("wt_id", wt);
                startActivity(intent);

            }
        });
    }
}
