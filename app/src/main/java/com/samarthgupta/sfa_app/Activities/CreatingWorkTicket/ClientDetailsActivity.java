package com.samarthgupta.sfa_app.Activities.CreatingWorkTicket;

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

import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Client;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.JobTicket;
import com.samarthgupta.sfa_app.R;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.jobTicket;

public class ClientDetailsActivity extends AppCompatActivity {

    public EditText clientName, clientContact;
    String clName;
    String clContact;
    Button btProceed;

    JobTicket clientClone;
    String responseToClone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        clientName = (EditText) findViewById(R.id.et_cl_name);
        clientContact = (EditText) findViewById(R.id.et_cl_contact);
        btProceed = (Button) findViewById(R.id.bt_proceed_client);
        if (getIntent().getStringExtra("task_response") != null) {
            responseToClone = getIntent().getStringExtra("task_response");
            setDataToClone();
            Log.i("response_", responseToClone);
        }


        btProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clName = clientName.getText().toString().trim();
                clContact = clientContact.getText().toString().trim();
                Log.d("client", clName + clContact);
                if (TextUtils.isEmpty(clName) || TextUtils.isEmpty(clContact) || clContact.length() != 10) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ClientDetailsActivity.this);
                    builder.setTitle("Enter client details");
                    builder.setMessage("Please enter client details");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    jobTicket = new JobTicket();
                    Client client = new Client(clName, clContact);
                    jobTicket.setClient(client);
                    Intent intent = new Intent(ClientDetailsActivity.this, JobDetailsActivity.class);
                    intent.putExtra("task_response", responseToClone);
                    intent.putExtra("clName", clName);
                    intent.putExtra("clContact", clContact);
                    startActivity(intent);
//                    finish();
                }


            }
        });

    }

    private void setDataToClone() {
        clientClone = new GsonBuilder()
                .create()
                .fromJson(responseToClone, JobTicket.class);
        clientName.setText(clientClone.getClient().getName());
        clientContact.setText(clientClone.getClient().getContact());
        Log.d("debug 1", clientClone.getClient().getName() + "-" + clientClone.getClient().getContact());
    }
}
