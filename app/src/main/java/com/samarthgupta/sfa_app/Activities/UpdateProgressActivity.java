package com.samarthgupta.sfa_app.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.Activities.HomeActivity;
import com.samarthgupta.sfa_app.DataInterface;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.UpdateObject;
import com.samarthgupta.sfa_app.R;

import java.sql.Time;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class UpdateProgressActivity extends AppCompatActivity {

    CardView cvA, cvB;
    TextView tvProcessA, tvProcessB;
    Button updateProgress;
    String wt;   String empId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_progress);

        cvA = (CardView) findViewById(R.id.cv_process_A);
        cvB = (CardView) findViewById(R.id.cv_process_B);
        updateProgress = (Button) findViewById(R.id.bt_update);

        boolean A_use = getIntent().getBooleanExtra("A-use",false);
        boolean B_use = getIntent().getBooleanExtra("B-use",false);
        double A_perc = getIntent().getDoubleExtra("A-percent",0);
        double B_perc = getIntent().getDoubleExtra("B-percent",0);

        wt = getIntent().getStringExtra("wt");
        empId = new GsonBuilder().create().fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE).getString("Data",null), Employee.class).getDept();


        if(!A_use){
            cvA.setVisibility(View.GONE);
        }

        else {
            tvProcessA = (TextView) findViewById(R.id.tv_process_comp_A);
            tvProcessA.setText(A_perc+"%");
        }

        if(!B_use){
            cvB.setVisibility(View.GONE);
        }

        else {
            tvProcessB = (TextView) findViewById(R.id.tv_process_comp_B);
            tvProcessB.setText(B_perc+"%");
        }

        updateProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dial = new Dialog(UpdateProgressActivity.this);
                dial.setContentView(R.layout.layout_dialogue_update);
                dial.show();
                TextView tvTime = (TextView) dial.findViewById(R.id.tv_time);
                final EditText etDone = (EditText) dial.findViewById(R.id.et_done);
                Button postUpdate = (Button) dial.findViewById(R.id.bt_update_progress);

                Time time = new Time(System.currentTimeMillis());
                tvTime.setText(time.toString());

                postUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(!etDone.getText().toString().isEmpty()){
                            UpdateObject updateObject = new UpdateObject((new Time(System.currentTimeMillis())).toString(),etDone.getText().toString());
                            Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
                            DataInterface client = retrofit.create(DataInterface.class);
                            Call<UpdateObject> call = client.updateProgress(wt,empId,updateObject);
                            final ProgressDialog pd = new ProgressDialog(UpdateProgressActivity.this);
                            pd.setTitle("Updating");
                            pd.setMessage("Please wait");
                            pd.show();

                            call.enqueue(new Callback<UpdateObject>() {
                                @Override
                                public void onResponse(Call<UpdateObject> call, Response<UpdateObject> response) {
//                                    Toast.makeText(UpdateProgressActivity.this, "Updated! Close and open to refresh", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                    dial.dismiss();
                                    startActivity(new Intent(UpdateProgressActivity.this, HomeActivity.class));
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<UpdateObject> call, Throwable t) {
                                    Toast.makeText(UpdateProgressActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }
                            });
                        }

                        else {
                            Toast.makeText(UpdateProgressActivity.this, "Enter a number", Toast.LENGTH_SHORT).show();

                        }




                    }
                });








//                Call<UpdateObject> call = client.updateProgress(wt,empId);


            }
        });


    }
}
