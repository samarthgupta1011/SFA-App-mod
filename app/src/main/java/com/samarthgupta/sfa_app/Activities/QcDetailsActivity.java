package com.samarthgupta.sfa_app.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.DataInterface;
import com.samarthgupta.sfa_app.POJO.ProcessesNO;
import com.samarthgupta.sfa_app.POJO.Progress;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.JobTicket;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Lamination;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Printing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;
import com.samarthgupta.sfa_app.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;
import static com.samarthgupta.sfa_app.POJO.GlobalAccess.jobTicket;

public class QcDetailsActivity extends AppCompatActivity {

    Button btProceed;
    Progress A,B;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qc_details);

        dialog = new Dialog(QcDetailsActivity.this);
        dialog.setContentView(R.layout.layout_alert_processes);
        dialog.setCancelable(false);

        btProceed = (Button) findViewById(R.id.bt_proceed_qc);
        btProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                A = new Progress();
                B = new Progress();
                dialog.show();

                final CheckBox cbA = (CheckBox) dialog.findViewById(R.id.cb_A);
                final CheckBox cbB = (CheckBox) dialog.findViewById(R.id.cb_B);
                ((Button)dialog.findViewById(R.id.bt_done)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(cbA.isChecked()){
                            A = new Progress(true);
                        }

                        if(cbB.isChecked()){
                            B = new Progress(true);
                        }

                        ProcessesNO p = new ProcessesNO(A,B);
                      //  jobTicket.setProcesses(A,B) ;
                        Log.e("Json",new GsonBuilder().create().toJson( jobTicket));



/*
                        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
                        DataInterface client = retrofit.create(DataInterface.class);
                        Call<JobTicket> call = client.postTicket(jobTicket);
                        call.enqueue(new Callback<JobTicket>() {
                            @SuppressLint("WrongConstant")
                            @Override
                            public void onResponse(Call<JobTicket> call, Response<JobTicket> response) {
                                Toast.makeText(QcDetailsActivity.this, "Job ticket created", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(QcDetailsActivity.this,HomeActivity.class );
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<JobTicket> call, Throwable t) {
                                Toast.makeText(QcDetailsActivity.this,"Error",Toast.LENGTH_LONG);
                            }
                        });
*/


                    }
                });









            }
        });
    }
}
