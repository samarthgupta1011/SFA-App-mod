package com.samarthgupta.sfa_app.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.samarthgupta.sfa_app.R;

public class ReportTypeListActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_app_crashing, tv_ticket_not_found, tv_filter_options, tv_creatingWT, tv_login, tv_other;
    private String problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_type_list);
        tv_app_crashing = (TextView) findViewById(R.id.tv_app_crashing);
        tv_ticket_not_found = (TextView) findViewById(R.id.tv_ticket_not_found);
        tv_filter_options = (TextView) findViewById(R.id.tv_ticket_filter);
        tv_creatingWT = (TextView) findViewById(R.id.tv_creating_WT_problem);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_other = (TextView) findViewById(R.id.tv_other);

        tv_app_crashing.setOnClickListener(this);
        tv_ticket_not_found.setOnClickListener(this);
        tv_filter_options.setOnClickListener(this);
        tv_creatingWT.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_other.setOnClickListener(this);
//        tv_app_crashing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                problem = tv_app_crashing.getText().toString() ;
//            }
//        });
//        tv_ticket_not_found.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                problem = tv_ticket_not_found.getText().toString();
//            }
//        });
//        tv_filter_options.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                problem = tv_filter_options.getText().toString();
//
//            }
//        });
//        tv_creatingWT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pro
//            }
//        });
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_app_crashing) {
            problem = tv_app_crashing.getText().toString();
            reportProblem();
        } else if (view.getId() == R.id.tv_ticket_not_found) {
            problem = tv_ticket_not_found.getText().toString();
            reportProblem();
        } else if (view.getId() == R.id.tv_ticket_filter) {
            problem = tv_filter_options.getText().toString();
            reportProblem();
        } else if (view.getId() == R.id.tv_creating_WT_problem) {
            problem = tv_creatingWT.getText().toString();
            reportProblem();
        } else if (view.getId() == R.id.tv_login) {
            problem = tv_login.getText().toString();
            reportProblem();
        } else if (view.getId() == R.id.tv_other) {
            problem = tv_other.getText().toString();
            reportProblem();
        }
    }

    private void reportProblem() {
        Intent intent = new Intent(ReportTypeListActivity.this, ReportActivity.class);
        intent.putExtra("prob_type", problem);
        startActivity(intent);
    }
}
