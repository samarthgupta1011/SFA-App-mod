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

public class Books_Task extends AppCompatActivity {
    TextView jobName;
    Button bt_taskDetails;
    TextView design, ferro, plates, printing, folding, gathering, perfect, sewing, centerPin, finishing, packing, dispatch, challan, bill;
    TextView designDone, ferroDone, platesDone, printingDone, foldingDone, gatheringDone, perfectDone, sewingDone, centerPinDone, finishingDone, packingDone, dispatchDone, challanDone, billDone;
    String printing2ndLast, setDone, folding2ndLast, formsDone, gathering2ndLast, perfect2ndLast, sewing2ndLast, centrePin2ndLast, finishing2ndLast, packing2ndLast, dispatch2ndLast, challan2ndLast, bill2ndLast;

    TextView setsDoneRatio, formsDoneRatio;

    CardView cv_designing, cv_ferro, cv_plates, cv_printing, cv_folding, cv_gathering, cv_perfect, cv_sewing, cv_centrepin, cv_finishing, cv_packing, cv_dispatch, cv_challan, cv_bill;

    Processes processes;
    Button btUpdateProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books__task);

        String bookProcesses = getIntent().getStringExtra("BookProcesses");
        final String BookjobName = getIntent().getStringExtra("BookJobName");
        final String wt = getIntent().getStringExtra("wt_id");

        //   Log.i("Book_task", bookProcesses + BookjobName);

        jobName = (TextView) findViewById(R.id.tv_jobname);
        bt_taskDetails = (Button) findViewById(R.id.bt_task_details);
        btUpdateProgress = (Button) findViewById(R.id.bt_book_update);

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

        cv_designing = (CardView) findViewById(R.id.cv_design);
        cv_ferro = (CardView) findViewById(R.id.cv_ferro);
        cv_plates = (CardView) findViewById(R.id.cv_plates);
        cv_printing = (CardView) findViewById(R.id.cv_printing);
        cv_folding = (CardView) findViewById(R.id.cv_folding);
        cv_gathering = (CardView) findViewById(R.id.cv_gathering);
        cv_perfect = (CardView) findViewById(R.id.cv_perfect);
        cv_sewing = (CardView) findViewById(R.id.cv_sewing);
        cv_centrepin = (CardView) findViewById(R.id.cv_centrepin);
        cv_finishing = (CardView) findViewById(R.id.cv_finishing);
        cv_packing = (CardView) findViewById(R.id.cv_packing);
        cv_dispatch = (CardView) findViewById(R.id.cv_dispatch);
        cv_challan = (CardView) findViewById(R.id.cv_challan);
        cv_bill = (CardView) findViewById(R.id.cv_bill);


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

        setsDoneRatio = (TextView) findViewById(R.id.tv_setsDoneRatio);
        formsDoneRatio = (TextView) findViewById(R.id.tv_formsDoneRatio);

        processes = (Processes) new GsonBuilder().create().fromJson(bookProcesses, Processes.class);

        if (processes.getBook().getDesigning().getIsRequired()) {
            cv_designing.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getDesigning().getIsRequired()) {
            cv_designing.setVisibility(View.GONE);
        }
        if (processes.getBook().getFerro().getIsRequired()) {
            cv_ferro.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getFerro().getIsRequired()) {
            cv_ferro.setVisibility(View.GONE);
        }
        if (processes.getBook().getPlates().getIsRequired()) {
            cv_plates.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getPlates().getIsRequired()) {
            cv_plates.setVisibility(View.GONE);
        }
        if (processes.getBook().getPrinting().getIsRequired()) {
            cv_printing.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getPrinting().getIsRequired()) {
            cv_printing.setVisibility(View.GONE);
        }
        if (processes.getBook().getFolding().getIsRequired()) {
            cv_folding.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getFolding().getIsRequired()) {
            cv_folding.setVisibility(View.GONE);
        }
        if (processes.getBook().getGathering().getIsRequired()) {
            cv_gathering.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getGathering().getIsRequired()) {
            cv_gathering.setVisibility(View.GONE);
        }
        if (processes.getBook().getPerfect().getIsRequired()) {
            cv_perfect.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getPerfect().getIsRequired()) {
            cv_perfect.setVisibility(View.GONE);
        }
        if (processes.getBook().getSewing().getIsRequired()) {
            cv_sewing.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getSewing().getIsRequired()) {
            cv_sewing.setVisibility(View.GONE);
        }
        if (processes.getBook().getCentrePin().getIsRequired()) {
            cv_centrepin.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getCentrePin().getIsRequired()) {
            cv_centrepin.setVisibility(View.GONE);
        }
        if (processes.getBook().getFinishing().getIsRequired()) {
            cv_finishing.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getFinishing().getIsRequired()) {
            cv_finishing.setVisibility(View.GONE);
        }
        if (processes.getBook().getPacking().getIsRequired()) {
            cv_packing.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getPacking().getIsRequired()) {
            cv_packing.setVisibility(View.GONE);
        }
        if (processes.getBook().getDispatch().getIsRequired()) {
            cv_dispatch.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getDispatch().getIsRequired()) {
            cv_dispatch.setVisibility(View.GONE);
        }
        if (processes.getBook().getChallan().getIsRequired()) {
            cv_challan.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getChallan().getIsRequired()) {
            cv_challan.setVisibility(View.GONE);
        }
        if (processes.getBook().getBill().getIsRequired()) {
            cv_bill.setVisibility(View.VISIBLE);
        } else if (!processes.getBook().getBill().getIsRequired()) {
            cv_bill.setVisibility(View.GONE);
        }

        //displaying the JOB NAME
        jobName.setText(BookjobName);


        if (processes.getBook().getDesigning().getIsDone()) {
            designDone.setText(R.string.Done);
        } else if (!processes.getBook().getDesigning().getIsDone()) {
            designDone.setText(R.string.NotDone);
        }

        if (processes.getBook().getFerro().getIsDone()) {
            ferroDone.setText(R.string.Done);
        } else if (!processes.getBook().getFerro().getIsDone()) {
            ferroDone.setText(R.string.NotDone);
        }

        if (processes.getBook().getPlates().getIsDone()) {
            platesDone.setText(R.string.Done);
        } else if (!processes.getBook().getPlates().getIsDone()) {
            platesDone.setText(R.string.NotDone);
        }

        final List<UpdatePF> updateprinting = processes.getBook().getPrinting().getUpdates();
        //setting the total done in printing

        final String printingJobDone = updateprinting.get(updateprinting.size() - 1).getDone() + "/" + processes.getTotalNumber();
        printingDone.setText(printingJobDone);
        if (updateprinting.size() >= 2) {
            printing2ndLast = updateprinting.get(updateprinting.size() - 2).getDone() + "/" + processes.getTotalNumber();
            setDone = updateprinting.get(updateprinting.size() - 2).getSetsDone() + "/" + processes.getTotalNumber();
        }
        //setting the number of sets in printing
        final String printingSetsDone = updateprinting.get(updateprinting.size() - 1).getSetsDone() + "/" + processes.getTotalSets();
        setsDoneRatio.setText(printingSetsDone);


        final List<UpdatePF> updatefolding = processes.getBook().getFolding().getUpdates();
        //setting the total done in folding
        final String foldingJobDone = updatefolding.get(updatefolding.size() - 1).getDone() + "/" + processes.getTotalNumber();
        foldingDone.setText(foldingJobDone);
        if (updatefolding.size() >= 2) {
            folding2ndLast = updatefolding.get(updatefolding.size() - 2).getDone() + "/" + processes.getTotalNumber();
            formsDone = updatefolding.get(updatefolding.size() - 2).getSetsDone() + "/" + processes.getTotalForms();
        }
        //setting the number of forms in folding
        final String foldingFormsDone = updatefolding.get(updatefolding.size() - 1).getSetsDone() + "/" + processes.getTotalForms();
        formsDoneRatio.setText(foldingFormsDone);


        final List<Update> updateGathering = processes.getBook().getGathering().getUpdates();
        final String gatheringJobDone = updateGathering.get(updateGathering.size() - 1).getDone() + "/" + processes.getTotalNumber();
        gatheringDone.setText(gatheringJobDone);
        if (updateGathering.size() >= 2) {
            gathering2ndLast = updateGathering.get(updateGathering.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updatePerfect = processes.getBook().getPerfect().getUpdates();
        final String perfectJobDone = updatePerfect.get(updatePerfect.size() - 1).getDone() + "/" + processes.getTotalNumber();
        perfectDone.setText(perfectJobDone);
        if (updatePerfect.size() >= 2) {
            perfect2ndLast = updatePerfect.get(updatePerfect.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateSewing = processes.getBook().getSewing().getUpdates();
        final String sewingJobDone = updateSewing.get(updateSewing.size() - 1).getDone() + "/" + processes.getTotalNumber();
        sewingDone.setText(sewingJobDone);
        if (updateSewing.size() >= 2) {
            sewing2ndLast = updateSewing.get(updateSewing.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateCentrepin = processes.getBook().getCentrePin().getUpdates();
        final String centrePinJobDone = updateCentrepin.get(updateCentrepin.size() - 1).getDone() + "/" + processes.getTotalNumber();
        centerPinDone.setText(centrePinJobDone);
        if (updateCentrepin.size() >= 2) {
            centrePin2ndLast = updateCentrepin.get(updateCentrepin.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateFinishing = processes.getBook().getFinishing().getUpdates();
        final String finishingJobDone = updateFinishing.get(updateFinishing.size() - 1).getDone() + "/" + processes.getTotalNumber();
        finishingDone.setText(finishingJobDone);
        if (updateFinishing.size() >= 2) {
            finishing2ndLast = updateFinishing.get(updateFinishing.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updatePacking = processes.getBook().getPacking().getUpdates();
        final String packingJobDone = updatePacking.get(updatePacking.size() - 1).getDone() + "/" + processes.getTotalNumber();
        packingDone.setText(packingJobDone);
        if (updatePacking.size() >= 2) {
            packing2ndLast = updatePacking.get(updatePacking.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateDispatch = processes.getBook().getDispatch().getUpdates();
        final String dispatchJobDone = updateDispatch.get(updateDispatch.size() - 1).getDone() + "/" + processes.getTotalNumber();
        dispatchDone.setText(dispatchJobDone);
        if (updateDispatch.size() >= 2) {
            dispatch2ndLast = updateDispatch.get(updateDispatch.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }

        final List<Update> updateChallan = processes.getBook().getChallan().getUpdates();
        final String challanJobDone = updateChallan.get(updateChallan.size() - 1).getDone() + "/" + processes.getTotalNumber();
        challanDone.setText(challanJobDone);
        if (updateChallan.size() >= 2) {
            challan2ndLast = updateChallan.get(updateChallan.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }


        final List<Update> updateBill = processes.getBook().getBill().getUpdates();
        final String billJobDone = updateBill.get(updateBill.size() - 1).getDone() + "/" + processes.getTotalNumber();
        billDone.setText(billJobDone);
        if (updateBill.size() >= 2) {
            bill2ndLast = updateBill.get(updateBill.size() - 2).getDone() + "/" + processes.getTotalNumber();
        }


//changing  the color for particular department worker
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
        } else if (employee.getDept().equals("folding")) {
            cv_folding.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("gathering")) {
            cv_gathering.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("sewing")) {
            cv_sewing.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("perfect")) {
            cv_perfect.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("centre_pin")) {
            cv_centrepin.setBackgroundColor(Color.GREEN);
        } else if (employee.getDept().equals("finishing")) {
            cv_finishing.setBackgroundColor(Color.GREEN);
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
                Intent intent = new Intent(Books_Task.this, TaskDetailsActivity.class);
                intent.putExtra("wt_id", wt);
                startActivity(intent);
            }
        });

        cv_designing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
                builder.setTitle("Designing Progress 📈");
                if (processes.getBook().getDesigning().getIsDone()) {
                    builder.setMessage("Designing Done 👍 ");
                } else if (!processes.getBook().getDesigning().getIsDone()){
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
                builder.setTitle("Ferro Progress 📈");
                if (processes.getBook().getDesigning().getIsDone()) {
                    builder.setMessage("Ferro Done 👍 ");
                } else if (!processes.getBook().getDesigning().getIsDone()){
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
                builder.setTitle("Plates Progress 📈");
                if (processes.getBook().getDesigning().getIsDone()) {
                    builder.setMessage("Plates Done 👍 ");
                } else if (!processes.getBook().getDesigning().getIsDone()){
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
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
        cv_folding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
                builder.setTitle("Folding Progress 📈");
                if (updatefolding.size() >= 2) {
                    builder.setMessage("Folding Done: " + folding2ndLast + " to " + foldingJobDone + "\nForms Done: " + formsDone + " to " + foldingFormsDone + "\n\n-- Last Updated : " + updatefolding.get(updatefolding.size() - 1).getTime());
                } else {
                    builder.setMessage("Folding Done: " + foldingJobDone + "\nForms Done: " + foldingFormsDone + "\n\n -- Last Updated: " + updatefolding.get(updatefolding.size() - 1).getTime());
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
        cv_gathering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
                builder.setTitle("Gathering Progress 📈");
                if (updateGathering.size() >= 2) {
                    builder.setMessage("Gathering Done: " + gathering2ndLast + " to " + gatheringJobDone + "\n\n -- Last Updated : " + updateGathering.get(updateGathering.size() - 1).getTime());
                } else {
                    builder.setMessage("Gathering Done: " + gatheringJobDone + "\n\n -- Last Updated: " + updateGathering.get(updateGathering.size() - 1).getTime());
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
        cv_perfect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
                builder.setTitle("Perfect Progress 📈");
                if (updatePerfect.size() >= 2) {
                    builder.setMessage("Perfect Done: " + perfect2ndLast + " to " + perfectJobDone + "\n\n -- Last Updated : " + updatePerfect.get(updatePerfect.size() - 1).getTime());
                } else {
                    builder.setMessage("Perfect Done: " + perfectJobDone + "\n\n -- Last Updated: " + updatePerfect.get(updatePerfect.size() - 1).getTime());
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
        cv_sewing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
                builder.setTitle("Sewing Progress 📈");
                if (updateSewing.size() >= 2) {
                    builder.setMessage("Sewing Done: " + sewing2ndLast + " to " + sewingJobDone + "\n\n -- Last Updated : " + updateSewing.get(updateSewing.size() - 1).getTime());
                } else {
                    builder.setMessage("Sewing Done: " + sewingJobDone + "\n\n -- Last Updated: " + updateSewing.get(updateSewing.size() - 1).getTime());
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
        cv_centrepin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
                builder.setTitle("Centre Pin Progress 📈");
                if (updateCentrepin.size() >= 2) {
                    builder.setMessage("CentrePin Done: " + centrePin2ndLast + " to " + centrePinJobDone + "\n\n -- Last Updated : " + updateCentrepin.get(updateCentrepin.size() - 1).getTime());
                } else {
                    builder.setMessage("CentrePin Done: " + centrePinJobDone + "\n\n -- Last Updated: " + updateCentrepin.get(updateCentrepin.size() - 1).getTime());
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
        cv_finishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
                builder.setTitle("Finishing Progress 📈");
                if (updateFinishing.size() >= 2) {
                    builder.setMessage("Finishing Done: " + finishing2ndLast + " to " + finishingJobDone + "\n\n -- Last Updated : " + updateFinishing.get(updateFinishing.size() - 1).getTime());
                } else {
                    builder.setMessage("Finishing Done: " + finishingJobDone + "\n\n -- Last Updated: " + updateFinishing.get(updateFinishing.size() - 1).getTime());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Books_Task.this);
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

                Intent intent = new Intent(Books_Task.this, UpdateActivity.class);
                intent.putExtra("wt_id", wt);
                startActivity(intent);
                finish();

            }
        });
    }
}
