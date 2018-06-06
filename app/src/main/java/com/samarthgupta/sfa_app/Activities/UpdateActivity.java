package com.samarthgupta.sfa_app.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.WT_Processes.UpdatePF;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class UpdateActivity extends AppCompatActivity {

    Button updateProgress;
    TextView tvTime;
    EditText etDone, etSetsDone;
    LinearLayout llSets, llEnterProgress;
    TextView tvCurrStatusDone, tvCurrStatusTotal;
    String wtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //Set current status and total number to be done
        //Get intent from previous activity


        updateProgress = (Button) findViewById(R.id.bt_update_progress);
        tvTime = (TextView) findViewById(R.id.tv_time);
        etDone = (EditText) findViewById(R.id.et_done);
        etSetsDone = (EditText) findViewById(R.id.et_sets_done);
        llSets = (LinearLayout) findViewById(R.id.ll_sets_forms);
        //For fields where progress is to be entered
        llEnterProgress = (LinearLayout) findViewById(R.id.ll_progress);
        llSets.setVisibility(View.GONE);
        llEnterProgress.setVisibility(View.GONE);
        tvCurrStatusDone = (TextView) findViewById(R.id.tv_curr_status_done);
        tvCurrStatusTotal = (TextView) findViewById(R.id.tv_curr_status_total);
        wtID = getIntent().getStringExtra("wt_id");

        Toast.makeText(this, wtID, Toast.LENGTH_SHORT).show();
        final String empDept = new GsonBuilder().create().fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE).getString("Data", null), Employee.class).getDept();
        ((TextView) findViewById(R.id.tv_emp_job)).setText(empDept);


        //Send values for other jobs and boolean for the 4 jobs
        tvCurrStatusTotal.setText(getIntent().getStringExtra("Total"));
        tvCurrStatusDone.setText(getIntent().getStringExtra("Done"));

        SimpleDateFormat sdfPosted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK);
        Calendar calendar = Calendar.getInstance();
        tvTime.setText(sdfPosted.format(calendar.getTime()));


        if (empDept.equals("printing") || empDept.equals("folding")) {

            llEnterProgress.setVisibility(View.VISIBLE);
            llSets.setVisibility(View.VISIBLE);

            //Post update PF


        } else if (empDept.equals("designing") || empDept.equals("ferro") || empDept.equals("plates")) {


        } else {
            //Post update object
            llEnterProgress.setVisibility(View.VISIBLE);

        }


        updateProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //We will post updatePF object everytime to avoid code, the backend will take the required fields from the object accordingly

                UpdatePF updateObj = new UpdatePF();

                String done = etDone.getText().toString().trim();
                String setsDone = etSetsDone.getText().toString().trim();

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setTitle("Error");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });


                SimpleDateFormat sdfPosted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK);
                Calendar calendar = Calendar.getInstance();
                updateObj.setTime(sdfPosted.format(calendar.getTime()));

                AlertDialog dialog;

                if (!(empDept.equals("designing") || empDept.equals("ferro") || empDept.equals("plates")
                        || empDept.equals("printing")) && done.isEmpty()) {
                    builder.setMessage("Please enter a valid value in the progress");
                    dialog = builder.create();
                    dialog.show();

                } else if ((empDept.equals("printing") || empDept.equals("folding")) && setsDone.isEmpty()) {

                    builder.setMessage("Please enter a valid value in the number of sets");
                    dialog = builder.create();
                    dialog.show();

                } else {

                    updateObj.setDone(done);
                    updateObj.setSetsDone(setsDone);


                    String url = baseUrl + "/update" + "?emp=" + empDept + "&wt=" + wtID;

                    String objStr = new GsonBuilder().create().toJson(updateObj);
                    Log.i("UpdateObj", objStr);
                    JSONObject obj = null;
                    try {
                        obj = new JSONObject(objStr);
                        Volley.newRequestQueue(UpdateActivity.this).add(new JsonObjectRequest(Request.Method.POST,
                                url, obj, new com.android.volley.Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                Log.i("UpdateResponse", response.toString());
                                try {
                                    Boolean status = response.getBoolean("success");

                                    if(status){

                                        Toast.makeText(UpdateActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(UpdateActivity.this, HomeActivity.class));
                                        finish();

                                    } else {
                                        Toast.makeText(UpdateActivity.this, "Please enter a valid value", Toast.LENGTH_LONG).show();
                                        return;
                                    }



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }



                            }
                        }, new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(UpdateActivity.this, "Network Error", Toast.LENGTH_LONG).show();
                            }
                        }));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            }
        });


    }
}
