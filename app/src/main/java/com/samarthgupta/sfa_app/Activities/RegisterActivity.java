package com.samarthgupta.sfa_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.samarthgupta.sfa_app.DataInterface;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPass, etMobile, etDept;
    ProgressBar pb;
    Button btRegister;

    String name, email, pass, mob, dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.et_reg_name);
        etPass = (EditText) findViewById(R.id.et_reg_pass);
        etEmail = (EditText) findViewById(R.id.et_reg_email);
        etMobile = (EditText) findViewById(R.id.et_reg_mob);
        pb = (ProgressBar) findViewById(R.id.pb_reg);
        etDept = (EditText) findViewById(R.id.et_reg_dept);
        btRegister = (Button) findViewById(R.id.bt_reg);
        pb.setVisibility(View.INVISIBLE);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                name = etName.getText().toString();
                email = etEmail.getText().toString();
                pass = etPass.getText().toString();
                mob = etMobile.getText().toString();
                dept = etDept.getText().toString();

                if (TextUtils.isEmpty(name)) { //personName
                    Toast.makeText(RegisterActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email) && emailValidator(email)) {
                    Toast.makeText(RegisterActivity.this, "Enter your mob", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(RegisterActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(mob)) {
                    Toast.makeText(RegisterActivity.this, "Enter your mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(dept)) {
                    Toast.makeText(RegisterActivity.this, "Enter your department", Toast.LENGTH_SHORT).show();
                    return;
                }

                btRegister.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);

                Employee emp = new Employee(name, mob, email, pass, dept);

                Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
                DataInterface client = retrofit.create(DataInterface.class);
                Call<String> call = client.postEmployee(emp);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, retrofit2.Response<String> response) {

                        Log.e("Register", response.body());
                        btRegister.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.GONE);
                        if (response.body().equals("true")) {
                            Toast.makeText(RegisterActivity.this, "Login to continue!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Mobile already registered", Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("Register", "Err");
                        btRegister.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.GONE);
                    }
                });


            }

        });
    }


    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}


//    String url = "http://ec2-13-126-93-101.ap-south-1.compute.amazonaws.com/login.php";
//                Volley.newRequestQueue(RegisterActivity.this).add(new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//@Override
//public void onResponse(String response) {
//        Log.e("Register", response);
//        btRegister.setVisibility(View.VISIBLE);
//        pb.setVisibility(View.GONE);
//        if (response.equals("true")) {
//        Toast.makeText(RegisterActivity.this, "Login to conitnue!", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
//        finish();
//        } else {
//        Toast.makeText(RegisterActivity.this, "Email address already registered", Toast.LENGTH_LONG).show();
//        }
//        }
//        }, new Response.ErrorListener() {
//@Override
//public void onErrorResponse(VolleyError error) {
//        Log.e("Register", "Err");
//        btRegister.setVisibility(View.VISIBLE);
//        pb.setVisibility(View.GONE);
//        AlertDialog dial = new AlertDialog.Builder(RegisterActivity.this).
//        setTitle("Error").setMessage("Network error").setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        dialogInterface.dismiss();
//        }
//        }).create();
//        dial.show();
//        }
//        }) {
//@Override
//protected Map<String, String> getParams() throws AuthFailureError {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("personname", name);
//        map.put("contact", mob);
//        map.put("mob", mob);
//        map.put("pass", pass);
//        //Department
//        map.put("name", dept);
//
//        return map;
//        }
//        }
//        ).setRetryPolicy(new RetryPolicy() {
//@Override
//public int getCurrentTimeout() {
//        return 0;
//        }
//
//@Override
//public int getCurrentRetryCount() {
//        return 0;
//        }
//
//@Override
//public void retry(VolleyError error) throws VolleyError {
//
//        }
//        });
//
//        }