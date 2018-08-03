package com.samarthgupta.sfa_app.Activities.Settings;

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
import com.samarthgupta.sfa_app.Activities.HomeActivity;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class QueryActivity extends AppCompatActivity {
    private String name, number, query;
    private EditText et_name, et_phone, et_query;
    private Button submit;
    private Employee data;
    SimpleDateFormat dateFormat;
    long date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        et_name = (EditText) findViewById(R.id.name);
        et_phone = (EditText) findViewById(R.id.contact);
        et_query = (EditText) findViewById(R.id.query);
        submit = (Button) findViewById(R.id.submit);

        data = new GsonBuilder().create().fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE).getString("Data", null), Employee.class);
        name = data.getName();
        number = data.getMobile();

        et_name.setText(name);
        et_phone.setText(number);

        date = System.currentTimeMillis();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = et_query.getText().toString();
                if (query.equals("")) {
                    Toast.makeText(QueryActivity.this, "Please Enter your Query", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("type", "query");
                        obj.put("date", dateFormat.format(date));
                        obj.put("details", query);
                        obj.put("reportedBy", number);
                        Log.d("obj_", obj.toString());
                        Volley.newRequestQueue(QueryActivity.this).add(new JsonObjectRequest(Request.Method.POST, baseUrl + "/report", obj, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Query", response.toString());
                                try {
                                    if (response.getBoolean("success")) {
                                        Toast.makeText(QueryActivity.this, "Query posted successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(QueryActivity.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(QueryActivity.this, "Can't send query due to network error", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(QueryActivity.this, "Can't send query due to network error", Toast.LENGTH_SHORT).show();
                                Log.d("Query_Error", error.toString());
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
