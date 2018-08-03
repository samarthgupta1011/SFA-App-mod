package com.samarthgupta.sfa_app.Activities.SignIn_Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.samarthgupta.sfa_app.DataInterface;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.R;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPass, etMobile;
    TextView tvDept;
    ProgressBar pb;
    Button btRegister;

    String name, email, pass, mob, dept;
    Spinner spSelectDept;
    String deptSelected = "---";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.et_reg_name);
        etPass = (EditText) findViewById(R.id.et_reg_pass);
        etEmail = (EditText) findViewById(R.id.et_reg_email);
        etMobile = (EditText) findViewById(R.id.et_reg_mob);
        pb = (ProgressBar) findViewById(R.id.pb_reg);
        tvDept = (TextView) findViewById(R.id.tv_select_dept);
        btRegister = (Button) findViewById(R.id.bt_reg);
        pb.setVisibility(View.INVISIBLE);

        spSelectDept = (Spinner) findViewById(R.id.sp_select_dept);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dept_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSelectDept.setAdapter(adapter);

        final List<String> options = Arrays.asList(getResources().getStringArray(R.array.dept_list));




        spSelectDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deptSelected = options.get(position);
                if (position != 0) {
                    btRegister.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                name = etName.getText().toString();
                email = etEmail.getText().toString();
                pass = etPass.getText().toString();
                mob = etMobile.getText().toString();

                if(deptSelected.equals("---")){
                    Toast.makeText(RegisterActivity.this, "Select your department", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    deptSelected = deptSelected.toLowerCase();
                }

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


                btRegister.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);

                Employee emp = new Employee(name, mob, email, pass, deptSelected);

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



