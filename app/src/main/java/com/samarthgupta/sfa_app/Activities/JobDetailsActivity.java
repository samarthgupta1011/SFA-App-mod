package com.samarthgupta.sfa_app.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Job;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Machine;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Paper;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Plate;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Book;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;
import static com.samarthgupta.sfa_app.POJO.GlobalAccess.jobTicket;

public class JobDetailsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    //JobClass
    EditText etJobName;
    RadioGroup rgJobType;
    RadioButton rbBook, rbBox, rbCover, rbPoster, rbLeaflet;
    EditText etPrintRun, etWastage, etNumOfCol, etJobSize;
    String jobType, jobName, printRun, wastage, numOfCol, jobSize;

    //Machine Class
    CheckBox cbMachKba, cbMachSm72, cbMachSm102, cbMachDome, cbMachOther;
    EditText etMachOther;
    List<String> machines;

    //Plate class
    RadioGroup rgPlateType;
    EditText etPlateQuan, etNotes;
    RadioButton cbPlatePS, cbPlateCTP, cbPlateWipeon;
    String plateType, plateQuantity;

    //Paper
    String papDetails, papQuan, papQuality, papBy, papLocation;
    RadioGroup rgPaperBy;
    RadioButton rbPaperClient, rbPaperSelf;


    Job job;
    Button btProceed;

    SimpleDateFormat dateFormat;
    long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        Log.i("DET", jobTicket.getClient().getContact());
        btProceed = (Button) findViewById(R.id.bt_proceed_job);

        //Job class
        rgJobType = (RadioGroup) findViewById(R.id.rg_job_type);
        etJobName = (EditText) findViewById(R.id.et_job_name);
        rgJobType.setOnCheckedChangeListener(this);
        etPrintRun = (EditText) findViewById(R.id.et_print_run);
        etWastage = (EditText) findViewById(R.id.et_paper_wastage);
        etNumOfCol = (EditText) findViewById(R.id.et_number_col);
        etJobSize = (EditText) findViewById(R.id.et_job_size);

        //Machine
        cbMachDome = (CheckBox) findViewById(R.id.cb_mach_dom);
        cbMachSm102 = (CheckBox) findViewById(R.id.cb_mach_sm102);
        cbMachKba = (CheckBox) findViewById(R.id.cb_mach_kba);
        cbMachSm72 = (CheckBox) findViewById(R.id.cb_mach_sm72);
        cbMachOther = (CheckBox) findViewById(R.id.cb_mach_other);
        etMachOther = (EditText) findViewById(R.id.et_mach_other);
        machines = new ArrayList<>();

        btProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                date = System.currentTimeMillis();
                dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
                jobTicket.setDate(dateFormat.format(date));
                jobTicket.setNotes(((EditText) findViewById(R.id.et_notes)).getText().toString());
                jobTicket.setImage("");
                jobTicket.setPriority("5");

                job = new Job(etJobName.getText().toString(), jobType, etPrintRun.getText().toString(),
                        etWastage.getText().toString(), etJobSize.getText().toString(), etNumOfCol.getText().toString());

                if (job.getName().isEmpty() || job.getType().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JobDetailsActivity.this);
                    builder.setTitle("Enter job details");
                    builder.setMessage("Please enter job name and type");
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
                } else {
                    jobTicket.setJob(job);

                    String wt = (job.getName() + dateFormat.format(date)).trim().replace(" ", "").replace(",", "").replace(":", "");
                    jobTicket.setWt(wt);
                    //To be corrected
                    jobTicket.setDeliveryDate("AAJ");
                }


                Machine machine = new Machine(machines, etMachOther.getText().toString());
                if (machine.getMachine().isEmpty() && machine.getName().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JobDetailsActivity.this);
                    builder.setTitle("Enter job details");
                    builder.setMessage("Please select machine");
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
                } else {
                    jobTicket.setMachine(machine);
                }

                //Plate name is null. To be corrected

                Plate plate = new Plate(plateType, "Plate", etPlateQuan.getText().toString());
                if (plate.getPlate() != null && plate.getPlate().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JobDetailsActivity.this);
                    builder.setTitle("Enter job details");
                    builder.setMessage("Please enter plate");
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
                } else {
                    jobTicket.setPlate(plate);
                }


                papDetails = ((EditText) findViewById(R.id.et_paper_details)).getText().toString();
                papQuality = ((EditText) findViewById(R.id.et_paper_quality)).getText().toString();
                papQuan = ((EditText) findViewById(R.id.et_paper_quantity)).getText().toString();
                papLocation = ((EditText) findViewById(R.id.et_paper_loc)).getText().toString();
                Paper paper = new Paper(papDetails, papQuality, papQuan, papBy, papLocation);
                if (paper.getQuantity() != null && paper.getPaperBy() != null && (paper.getQuantity().isEmpty() || paper.getQuality().isEmpty() || paper.getPaperBy().isEmpty() || paper.getLocation().isEmpty())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JobDetailsActivity.this);
                    builder.setTitle("Enter job details");
                    builder.setMessage("Please enter paper details");
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
                } else {
                    jobTicket.setPaper(paper);
                }


                //Post ticket here
                //start act in onResponse
                //Get ticket id and success status in response
                //Send to next act


                final String res = new GsonBuilder().create().toJson(jobTicket);
                try {

                    //Ticket object to be posted
                    JSONObject ticketObj = new JSONObject(res);

                    String url = baseUrl + "/ticket";
                    Volley.newRequestQueue(JobDetailsActivity.this).add(new JsonObjectRequest(Request.Method.POST,
                            url, ticketObj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
//                            Response
//                            {
//                                "success": true,
//                                    "wt_id": "asdfghj"
//                            }

                            JSONObject resp = null;
                            try {
                                resp = new JSONObject(String.valueOf(response));
                                boolean status = resp.getBoolean("success");

                                if (status) {

                                    String wtId = resp.getString("wt_id");

                                    if (jobType.equals("Book")) {
                                        Intent intent = new Intent(JobDetailsActivity.this, Books_Processes.class);
                                        intent.putExtra("wt_id", wtId);
                                        intent.putExtra("total_number", job.getNoOfCol());
                                        startActivity(intent);
                                        finish();

                                    } else if (jobType.equals("Box")) {
                                        Intent intent = new Intent(JobDetailsActivity.this, Box_Processes.class);
                                        intent.putExtra("wt_id", wtId);
                                        startActivity(intent);
                                        finish();

                                    } else if (jobType.equals("Cover")) {
                                        Intent intent = new Intent(JobDetailsActivity.this, Cover_Processes.class);
                                        intent.putExtra("wt_id", wtId);
                                        startActivity(intent);
                                        finish();
                                    }


                                } else {

                                    Toast.makeText(JobDetailsActivity.this, "Job ticket couldn't be created", Toast.LENGTH_SHORT).show();
                                    return;

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }));


                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        });


        cbMachDome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbMachDome.isChecked()) {
                    machines.add(cbMachDome.getText().toString());
                } else {
                    machines.remove(cbMachDome.getText().toString());
                }
            }
        });

        cbMachKba.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbMachKba.isChecked()) {
                    machines.add(cbMachKba.getText().toString());
                } else {
                    machines.remove(cbMachKba.getText().toString());
                }
            }
        });

        cbMachSm72.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbMachSm72.isChecked()) {
                    machines.add(cbMachSm72.getText().toString());
                } else {
                    machines.remove(cbMachSm72.getText().toString());
                }
            }
        });

        cbMachSm102.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbMachSm102.isChecked()) {
                    machines.add(cbMachSm102.getText().toString());
                } else {
                    machines.remove(cbMachSm102.getText().toString());
                }
            }
        });

        cbMachOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbMachOther.isChecked()) {
                    machines.add(etMachOther.getText().toString());
                } //else statement not added ?
            }
        });

        //Plate
        rgPlateType = (RadioGroup) findViewById(R.id.rg_plate_type);
        etPlateQuan = (EditText) findViewById(R.id.et_plate_quantity);
        etNotes = (EditText) findViewById(R.id.et_notes);
        rgPlateType.setOnCheckedChangeListener(this);
        cbPlateCTP = (RadioButton) findViewById(R.id.rb_plate_ctp);
        cbPlatePS = (RadioButton) findViewById(R.id.rb_plate_ps);
        cbPlateWipeon = (RadioButton) findViewById(R.id.rb_plate_wipeon);


        //Paper
        rgPaperBy = (RadioGroup) findViewById(R.id.rg_paper_by);
        rgPaperBy.setOnCheckedChangeListener(this);
        rbPaperClient = (RadioButton) findViewById(R.id.rb_paper_client);
        rbPaperSelf = (RadioButton) findViewById(R.id.rb_paper_self);


        //Radio
        rgJobType = (RadioGroup) findViewById(R.id.rg_job_type);
        rbBook = (RadioButton) findViewById(R.id.rb_job_book);
        rbBox = (RadioButton) findViewById(R.id.rb_job_box);
        rbCover = (RadioButton) findViewById(R.id.rb_job_cover);
        rbPoster = (RadioButton) findViewById(R.id.rb_job_poster);
        rbLeaflet = (RadioButton) findViewById(R.id.rb_job_leaflet);


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {

            //Job type
            case R.id.rb_job_book:
                jobType = rbBook.getText().toString();
                break;
            case R.id.rb_job_box:
                jobType = rbBox.getText().toString();
                break;
            case R.id.rb_job_leaflet:
                jobType = rbLeaflet.getText().toString();
                break;
            case R.id.rb_job_cover:
                jobType = rbCover.getText().toString();
                break;
            case R.id.rb_job_poster:
                jobType = rbPoster.getText().toString();
                break;

            //Plate type
            case R.id.rb_plate_ps:
                plateType = cbPlatePS.getText().toString();
                break;
            case R.id.rb_plate_ctp:
                plateType = cbPlateCTP.getText().toString();
                break;
            case R.id.rb_plate_wipeon:
                plateType = cbPlateWipeon.getText().toString();
                break;

            //Paper by
            case R.id.rb_paper_client:
                papBy = rbPaperClient.getText().toString();
                break;

            case R.id.rb_paper_self:
                papBy = rbPaperSelf.getText().toString();
                break;

        }
    }
}
