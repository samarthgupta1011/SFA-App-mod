package com.samarthgupta.sfa_app.Activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Job;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.JobTicket;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Task;
import com.samarthgupta.sfa_app.R;

import java.util.StringJoiner;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class TaskDetailsActivity extends AppCompatActivity {
    TextView JobName ;
    TextView DeliveryDate, Priority, Date, Notes ;
    TextView ClientName, ClientNumber ;
    TextView JobType , NoOfCols, printRun, Jobsize, wastage ;
    TextView machinesRequired ;
    TextView papDetails, papLocation, paperBy, papQuality, papQuantity ;
    TextView PlateName, plateType, PlateQuantity ;
    JobTicket Taskdetails ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        JobName = (TextView)findViewById(R.id.tv_jobname) ;

        DeliveryDate = (TextView)findViewById(R.id.tv_delivery_date) ;
        Priority = (TextView)findViewById(R.id.tv_priority) ;
        Date = (TextView)findViewById(R.id.tv_Date) ;
        Notes = (TextView)findViewById(R.id.tv_notes) ;

        ClientName = (TextView)findViewById(R.id.tv_client_name);
        ClientNumber= (TextView)findViewById(R.id.tv_client_number);

        JobType=(TextView)findViewById(R.id.tv_job_type);
        NoOfCols=(TextView)findViewById(R.id.tv_noOfCol) ;
        printRun=(TextView)findViewById(R.id.tv_print_run) ;
        Jobsize=(TextView)findViewById(R.id.tv_size);
        wastage=(TextView)findViewById(R.id.tv_wastage);

        machinesRequired=(TextView)findViewById(R.id.tv_machinesRequired) ;

        papDetails=(TextView)findViewById(R.id.tv_paperDetails);
        papLocation=(TextView)findViewById(R.id.tv_paperLocation);
        paperBy=(TextView)findViewById(R.id.tv_paperBy);
        papQuality=(TextView)findViewById(R.id.tv_quality) ;
        papQuantity=(TextView)findViewById(R.id.tv_quantity);

        PlateName=(TextView)findViewById(R.id.tv_plate_name);
        plateType=(TextView)findViewById(R.id.tv_plate_type);
        PlateQuantity=(TextView)findViewById(R.id.tv_plate_quantity) ;



        Log.i("Ticket", "IN");
        String wt = getIntent().getStringExtra("wt_id") ;
        String url = baseUrl + "/jobticket?wt="+wt;

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Ticket", response);
                Log.i("Ticket", "Tick");
                Taskdetails = new GsonBuilder()
                        .create()
                        .fromJson(response,JobTicket.class);

                Job taskJob = Taskdetails.getJob() ;
                JobName.setText(taskJob.getName());

                DeliveryDate.setText(Taskdetails.getDeliveryDate());
                Priority.setText(Taskdetails.getPriority());
                Date.setText(Taskdetails.getDate());
                Notes.setText(Taskdetails.getNotes());

                ClientName.setText(Taskdetails.getClient().getName());
                ClientNumber.setText(Taskdetails.getClient().getContact());

                JobType.setText(Taskdetails.getJob().getType());
                NoOfCols.setText(Taskdetails.getJob().getNoOfCol());
                printRun.setText(Taskdetails.getJob().getPrintRun());
                Jobsize.setText(Taskdetails.getJob().getSize());
                wastage.setText(Taskdetails.getJob().getWastage());

                //mahcines required
//        StringJoiner joiner = new StringJoiner(",");
                StringBuilder builder = new StringBuilder();
                for(String machine : Taskdetails.getMachine().getMachine()){
                    builder.append(machine + ",");
                }
                builder.deleteCharAt(builder.length()-1);
                String allMachines=builder.toString();
                machinesRequired.setText(allMachines); //SM-72,KB-102,DOM-100

                papDetails.setText(Taskdetails.getPaper().getDetails());
                papLocation.setText(Taskdetails.getPaper().getLocation());
                paperBy.setText(Taskdetails.getPaper().getPaperBy());
                papQuality.setText(Taskdetails.getPaper().getQuality());
                papQuantity.setText(Taskdetails.getPaper().getQuantity());

                PlateName.setText(Taskdetails.getPlate().getName());
                plateType.setText(Taskdetails.getPlate().getPlate());
                PlateQuantity.setText(Taskdetails.getPlate().getQuantity());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));


    }


}
