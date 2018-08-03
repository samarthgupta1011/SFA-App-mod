package com.samarthgupta.sfa_app.Activities.Settings;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.Activities.HomeActivity;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.Progress;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class ChangePassword extends AppCompatActivity {

    private EditText mOldPass, mNewPass, mConfirmPass;
    private String old_pass, new_pass, new_confirmpass;
    private Button submit;
    private final String API_URL = baseUrl + "/login/update";
    Employee data;
    private String mobile = "";
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        mOldPass = (EditText) findViewById(R.id.old_password);
        mNewPass = (EditText) findViewById(R.id.new_password);
        mConfirmPass = (EditText) findViewById(R.id.new_confirmPassword);
        submit = (Button) findViewById(R.id.submit);
        pb = (ProgressBar) findViewById(R.id.pb_pass_change);

        data = new GsonBuilder().create().fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE).getString("Data", null), Employee.class);
        mobile = data.getMobile();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                old_pass = mOldPass.getText().toString();
                new_pass = mNewPass.getText().toString();
                new_confirmpass = mConfirmPass.getText().toString();
                boolean ready = true;

                if (old_pass.isEmpty()) {
                    mOldPass.setError("Current Password cannot be empty!");
                    ready = false;
                }
                if (new_pass.isEmpty()) {
                    mOldPass.setError("New Password cannot be empty!");
                    ready = false;
                }
                if (new_confirmpass.isEmpty()) {
                    mOldPass.setError("Confirm Password cannot be empty!");
                    ready = false;
                }

                if (!new_pass.equals(new_confirmpass)) {
                    mConfirmPass.setError("Passwords do not match");
                    ready = false;
                }

                if (ready) {
                    requestChange(mobile, old_pass, new_confirmpass);
                }

            }
        });
    }

    private void requestChange(final String mobile, final String old, final String new_pass) {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                submit.setVisibility(View.GONE);

                final JSONObject obj = new JSONObject();
                try {
                    obj.put("mobile", mobile);
                    obj.put("pass", old_pass);
                    obj.put("update", new_pass);

                    Volley.newRequestQueue(ChangePassword.this).add(new JsonObjectRequest(Request.Method.POST, baseUrl + "/login/update", obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("obj.", obj.toString());
                            try {
                                if (response.getBoolean("success")) {
                                    Toast.makeText(ChangePassword.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent intent = new Intent(ChangePassword.this, HomeActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    pb.setVisibility(View.GONE);
                                    submit.setVisibility(View.VISIBLE);
                                    Toast.makeText(ChangePassword.this, "Can't change Password due to network error", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pb.setVisibility(View.GONE);
                            submit.setVisibility(View.VISIBLE);
                            Toast.makeText(ChangePassword.this, "Can't change Password due to network error", Toast.LENGTH_SHORT).show();

                        }
                    }));


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
