package com.samarthgupta.sfa_app.Activities.CreatingWorkTicket;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.Activities.CreatingWorkTicket.X_Processes.Books_Processes;
import com.samarthgupta.sfa_app.Activities.CreatingWorkTicket.X_Processes.Box_Processes;
import com.samarthgupta.sfa_app.Activities.CreatingWorkTicket.X_Processes.Cover_Processes;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Client;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Job;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.JobTicket;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Machine;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Paper;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Plate;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;
import static com.samarthgupta.sfa_app.POJO.GlobalAccess.jobTicket;

public class JobDetailsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    JobTicket taskDetailsClone;

    //JobClass
    EditText etJobName;
    RadioGroup rgJobType;
    RadioButton rbBook, rbBox, rbCover, rbPoster, rbLeaflet;
    EditText etPrintRun, etWastage, etNumOfCol, etJobSize, etNumberOfPages;
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
    EditText et_papDetails, et_papQuan, et_papQuality, et_papLocation;
    String papDetails, papQuan, papQuality, papBy, papLocation;
    RadioGroup rgPaperBy;
    RadioButton rbPaperClient, rbPaperSelf;
    //date picker
    private static final String TAG = "MainActivity";

    private TextView mDeliveryDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    SimpleDateFormat dateFormat;
    long date;
    TextView tvDeliveryDate;
    String DeldateToPost;

    Job job;
    Button btProceed;
    ProgressBar pb_task_progress;


    String responseToClone;
    Boolean cloningTicket = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

//        Log.i("DET", jobTicket.getClient().getContact());
        btProceed = (Button) findViewById(R.id.bt_proceed_job);
        pb_task_progress = (ProgressBar) findViewById(R.id.pb_progressBar);

        //Job class
        rgJobType = (RadioGroup) findViewById(R.id.rg_job_type);
        etJobName = (EditText) findViewById(R.id.et_job_name);
        rgJobType.setOnCheckedChangeListener(this);
        etPrintRun = (EditText) findViewById(R.id.et_print_run);
        etWastage = (EditText) findViewById(R.id.et_paper_wastage);
        etNumOfCol = (EditText) findViewById(R.id.et_number_col);
        etJobSize = (EditText) findViewById(R.id.et_job_size);
        etNumberOfPages = (EditText) findViewById(R.id.et_number_of_pages);

        //Machine
        cbMachDome = (CheckBox) findViewById(R.id.cb_mach_dom);
        cbMachSm102 = (CheckBox) findViewById(R.id.cb_mach_sm102);
        cbMachKba = (CheckBox) findViewById(R.id.cb_mach_kba);
        cbMachSm72 = (CheckBox) findViewById(R.id.cb_mach_sm72);
        cbMachOther = (CheckBox) findViewById(R.id.cb_mach_other);
        etMachOther = (EditText) findViewById(R.id.et_mach_other);
        machines = new ArrayList<>();

        et_papDetails = ((EditText) findViewById(R.id.et_paper_details));
        et_papQuan = ((EditText) findViewById(R.id.et_paper_quantity));
        et_papQuality = ((EditText) findViewById(R.id.et_paper_quality));
        et_papLocation = ((EditText) findViewById(R.id.et_paper_loc));

        mDeliveryDate = (TextView) findViewById(R.id.tv_delivery_date);
        tvDeliveryDate = (TextView) findViewById(R.id.tv_deliveryDate);

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


        if (getIntent().getStringExtra("task_response") != null) {
            cloningTicket = true;
            responseToClone = getIntent().getStringExtra("task_response");
            setDataToClone();
            Log.i("response_", responseToClone);
        }

        mDeliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        JobDetailsActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);


                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                SimpleDateFormat sdf = new SimpleDateFormat("EEE,dd MMM yyyy", Locale.UK);
                String formattedDate = sdf.format(calendar.getTime());

//                String date = day + "/" + month + "/" + year;

                SimpleDateFormat sdfPosted = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
                String datePosted = sdfPosted.format(calendar.getTime());
                tvDeliveryDate.setText(formattedDate);
                jobTicket.setDeliveryDate(datePosted);

            }
        };

        btProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                date = System.currentTimeMillis();
                SimpleDateFormat sdfPosted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK);
                String currentDate = sdfPosted.format(date);

                jobTicket.setDate(currentDate);

                jobTicket.setNotes(etNotes.getText().toString());
                jobTicket.setImage("");
                jobTicket.setDelivered(false);
                jobTicket.setPriority("5");

                if (jobTicket.getDeliveryDate() == null || jobTicket.getDeliveryDate().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JobDetailsActivity.this);
                    builder.setTitle("Enter job details");
                    builder.setMessage("Please Select Delivery Date");
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

                job = new Job(etJobName.getText().toString().trim(), jobType, etPrintRun.getText().toString().trim(),
                        etWastage.getText().toString().trim(), etJobSize.getText().toString().trim(), etNumOfCol.getText().toString().trim(),
                        etNumberOfPages.getText().toString().trim());

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
                }

                if (job.getNumPages().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JobDetailsActivity.this);
                    builder.setTitle("Enter Job Details");
                    builder.setMessage("Please enter the number of pages");
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

                jobTicket.setJob(job);
                String wt = (job.getName() + currentDate).replaceAll("[^a-zA-Z0-9]", "");
                wt = wt.trim();
                jobTicket.setWt(wt);

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

                if (cloningTicket) {
                    setMachines();
                    String clName = getIntent().getStringExtra("clName");
                    String clContact = getIntent().getStringExtra("clContact");
                    Client client = new Client(clName, clContact);
                    jobTicket.setClient(client);
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


                papDetails = et_papDetails.getText().toString();
                papQuality = et_papQuality.getText().toString();
                papQuan = et_papQuan.getText().toString();
                papLocation = et_papLocation.getText().toString();


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
                Log.d("res__", res);
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
                                btProceed.setVisibility(View.INVISIBLE);
                                pb_task_progress.setVisibility(View.VISIBLE);
                                resp = new JSONObject(String.valueOf(response));
                                boolean status = resp.getBoolean("success");

                                if (status) {

                                    String wtId = resp.getString("wt_id");

                                    switch (jobType) {
                                        case "Book": {
                                            Intent intent = new Intent(JobDetailsActivity.this, Books_Processes.class);
                                            intent.putExtra("wt_id", wtId);
                                            intent.putExtra("total_number", job.getNoOfCol());
                                            startActivity(intent);
                                            finish();

                                            break;
                                        }
                                        case "Box": {
                                            Intent intent = new Intent(JobDetailsActivity.this, Box_Processes.class);
                                            intent.putExtra("wt_id", wtId);
                                            intent.putExtra("total_number", job.getNoOfCol());
                                            startActivity(intent);
                                            finish();

                                            break;
                                        }
                                        case "Cover": {
                                            Intent intent = new Intent(JobDetailsActivity.this, Cover_Processes.class);
                                            intent.putExtra("wt_id", wtId);
                                            intent.putExtra("total_number", job.getNoOfCol());
                                            startActivity(intent);
                                            finish();
                                            break;
                                        }
                                    }


                                } else {

                                    Toast.makeText(JobDetailsActivity.this, "Job ticket couldn't be created", Toast.LENGTH_SHORT).show();
                                    pb_task_progress.setVisibility(View.INVISIBLE);
                                    btProceed.setVisibility(View.VISIBLE);
                                    return;

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(JobDetailsActivity.this, "Job ticket couldn't be created", Toast.LENGTH_SHORT).show();

                        }
                    }));


                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        });


    }

    private void setMachines() {
        if (cbMachDome.isChecked()) {
            machines.add(cbMachDome.getText().toString());
        } else {
            machines.remove(cbMachDome.getText().toString());
        }
        if (cbMachKba.isChecked()) {
            machines.add(cbMachKba.getText().toString());
        } else {
            machines.remove(cbMachKba.getText().toString());
        }
        if (cbMachSm72.isChecked()) {
            machines.add(cbMachSm72.getText().toString());
        } else {
            machines.remove(cbMachSm72.getText().toString());
        }
        if (cbMachSm102.isChecked()) {
            machines.add(cbMachSm102.getText().toString());
        } else {
            machines.remove(cbMachSm102.getText().toString());
        }
        if (cbMachOther.isChecked()) {
            machines.add(etMachOther.getText().toString());
        }
    }


    private void setDataToClone() {
        responseToClone = getIntent().getStringExtra("task_response");
        taskDetailsClone = new GsonBuilder()
                .create()
                .fromJson(responseToClone, JobTicket.class);
        Log.d("task_response", responseToClone);

        jobTicket = new JobTicket();

        etJobName.setText(taskDetailsClone.getJob().getName());


        switch (taskDetailsClone.getJob().getType()) {
            case "Book":
                rbBook.setChecked(true);
                break;
            case "Box":
                rbBox.setChecked(true);
                break;
            case "Cover":
                rbCover.setChecked(true);
                break;
        }
        Machine machineClone = taskDetailsClone.getMachine();
        for (int i = 0; i < machineClone.getMachine().size(); i++) {
            if (machineClone.getMachine().get(i).equals("KBA")) {
                cbMachKba.setChecked(true);
            }
            if (machineClone.getMachine().get(i).equals("SM-102")) {
                cbMachSm102.setChecked(true);
            }
            if (machineClone.getMachine().get(i).equals("SM-72")) {
                cbMachSm72.setChecked(true);
            }
            if (machineClone.getMachine().get(i).equals("Dom1c")) {
                cbMachDome.setChecked(true);
            }
            if (machineClone.getMachine().get(i).equals("")) {
                cbMachOther.setChecked(true);
                etMachOther.setText(taskDetailsClone.getMachine().getName());
            }
        }
        etPrintRun.setText(taskDetailsClone.getJob().getPrintRun());
        etWastage.setText(taskDetailsClone.getJob().getWastage());
        etNumOfCol.setText(taskDetailsClone.getJob().getNoOfCol());

        switch (taskDetailsClone.getPlate().getPlate()) {
            case "CTP":
                cbPlatePS.setChecked(true);
                break;
            case "PS":
                cbPlateCTP.setChecked(true);
                break;
            case "Wipeon":
                cbPlateWipeon.setChecked(true);
                break;
        }

        etPlateQuan.setText(taskDetailsClone.getPlate().getQuantity());
        etNotes.setText(taskDetailsClone.getNotes());

        et_papDetails.setText(taskDetailsClone.getPaper().getDetails());
        et_papQuan.setText(taskDetailsClone.getPaper().getQuantity());
        et_papQuality.setText(taskDetailsClone.getPaper().getQuality());
        etJobSize.setText(taskDetailsClone.getJob().getSize());

        switch (taskDetailsClone.getPaper().getPaperBy()) {
            case "By Client":
                rbPaperClient.setChecked(true);
                break;
            case "Self Stock":
                rbPaperSelf.setChecked(true);
                break;
        }


        et_papLocation.setText(taskDetailsClone.getPaper().getLocation());
        String date, month, year;
        date = taskDetailsClone.getDeliveryDate().substring(8, 10);
        month = taskDetailsClone.getDeliveryDate().substring(5, 7);
        year = taskDetailsClone.getDeliveryDate().substring(0, 4);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));

        SimpleDateFormat sdf = new SimpleDateFormat("EEE,dd MMM yyyy", Locale.UK);
        String formattedDate = sdf.format(calendar.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK);
        DeldateToPost = simpleDateFormat.format(calendar.getTime());

//                String date = day + "/" + month + "/" + year;
        tvDeliveryDate.setText(formattedDate);


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
