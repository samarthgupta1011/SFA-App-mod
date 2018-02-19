package com.samarthgupta.sfa_app.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.DataInterface;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etMobLogin, etPassLogin;
    TextView tvRegister;
    ProgressBar pb;
    Button btLogin;

    String mob, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(getSharedPreferences("Login", Context.MODE_PRIVATE).getString("Data",null)!=null){
            startActivity(new Intent(SignInActivity.this, HomeActivity.class));
            finish();
        }

        setContentView(R.layout.activity_sign_in);


        etMobLogin = (EditText) findViewById(R.id.et_login_email);
        etPassLogin = (EditText) findViewById(R.id.et_login_password);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        pb = (ProgressBar) findViewById(R.id.pb_login);
        pb.setVisibility(View.GONE);
        btLogin = (Button) findViewById(R.id.bt_login);
        btLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:

                mob = etMobLogin.getText().toString().trim();
                pass = etPassLogin.getText().toString().trim();

                if (TextUtils.isEmpty(mob)) {
                    Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                btLogin.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);

                Employee emp = new Employee(mob, pass);
                Log.d("Login", mob + " "+pass);
                Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
                DataInterface client = retrofit.create(DataInterface.class);
                Call<Employee> call = client.empLogin(emp);

                call.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        Log.i("resp",response.body().isStatus()+" ");

                        //Status is false
                        if (!response.body().isStatus()) {
                            Toast.makeText(SignInActivity.this, "Incorrect mob address entered", Toast.LENGTH_SHORT).show();
                            btLogin.setVisibility(View.VISIBLE);
                            pb.setVisibility(View.GONE);
                            return;

                        } else {
                            getSharedPreferences("Login", Context.MODE_PRIVATE).edit().
                                    putString("Data",new GsonBuilder().create().toJson(response.body())).apply();
                            startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {

                        Log.i("err",t.toString());
                        pb.setVisibility(View.GONE);
                        btLogin.setVisibility(View.VISIBLE);
                        AlertDialog dial = new AlertDialog.Builder(SignInActivity.this).
                                setTitle("Error").setMessage("Network error").setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create();
                        dial.show();

                    }
                });
                break;

            case R.id.tv_register:
                startActivity(new Intent(SignInActivity.this, RegisterActivity.class));


        }

    }
}


