package com.samarthgupta.sfa_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class ReportActivity extends AppCompatActivity {
    String issue_type, issue_details;
    EditText et_details;
    Button bt_report;
    Employee data;
    SimpleDateFormat dateFormat;
    long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        issue_type = getIntent().getStringExtra("prob_type");
        et_details = (EditText) findViewById(R.id.et_details);
        bt_report = (Button) findViewById(R.id.bt_report);
        data = new GsonBuilder().create().fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE).getString("Data", null), Employee.class);


        date = System.currentTimeMillis();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK);

        bt_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                issue_details = et_details.getText().toString();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("type", issue_type);
                    obj.put("date", dateFormat.format(date));
                    obj.put("details", issue_details);
                    obj.put("reportedBy", data.getMobile());
                    Log.d("obj_", obj.toString());
                    Volley.newRequestQueue(ReportActivity.this).add(new JsonObjectRequest(Request.Method.POST, baseUrl + "/report", obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Report", response.toString());
                            try {
                                if (response.getBoolean("success")) {
                                    Toast.makeText(ReportActivity.this, "Issue reported successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ReportActivity.this, HomeActivity.class) ;
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(ReportActivity.this, "Can't report due to network error", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ReportActivity.this, "Can't report due to network error", Toast.LENGTH_SHORT).show();
                            Log.d("Report_Error", error.toString());
                        }
                    }));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }





}
