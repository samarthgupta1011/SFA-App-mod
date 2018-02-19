package com.samarthgupta.sfa_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.DataInterface;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.JobTicket;
import com.samarthgupta.sfa_app.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class TasksActivity extends AppCompatActivity {

    RecyclerView rv;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        rv = (RecyclerView) findViewById(R.id.rv_job_tickets);
        pb = (ProgressBar) findViewById(R.id.pb_tasks);

        pb.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        DataInterface client = retrofit.create(DataInterface.class);


        Employee emp =  new GsonBuilder().create().fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE).getString("Data",null),
                Employee.class);

        Log.d("Tasks",emp.getDept());
        Call<List<JobTicket>> call = client.getEmpTickets(emp.getDept());
        call.enqueue(new Callback<List<JobTicket>>() {
            @Override
            public void onResponse(Call<List<JobTicket>> call, Response<List<JobTicket>> response) {
                Log.d("Tasks",response.toString());
                rv.setAdapter(new TicketAdapter(response.body()));

                pb.setVisibility(View.INVISIBLE);
                rv.setLayoutManager(new LinearLayoutManager(TasksActivity.this));
                rv.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<JobTicket>> call, Throwable t) {

            }
        });

    }

    class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketHolder> {

        List<JobTicket> list;

        public TicketAdapter(List<JobTicket> listJob){
            list = listJob;
        }

        @Override
        public TicketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TicketHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tasks_recyclers,parent,false));
        }

        @Override
        public void onBindViewHolder(TicketHolder holder, int position) {
            holder.tvClientName.setText(list.get(position).getClient().getName());
            holder.tvPriority.setText(list.get(position).getPriority());
            holder.tvDelDate.setText(list.get(position).getDeliveryDate());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        public class TicketHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView tvClientName, tvPriority, tvDelDate;
            public TicketHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                tvClientName = (TextView) itemView.findViewById(R.id.tv_client_name);
                tvPriority = (TextView) itemView.findViewById(R.id.tv_priority);
                tvDelDate = (TextView) itemView.findViewById(R.id.tv_delivery_date);
            }

            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();



                //CHANGE HERE
//                Intent intent = new Intent(TasksActivity.this,UpdateProgressActivity.class).
//                        putExtra("A-use",list.get(pos).getProcesses().getA().isUse()).
//                        putExtra("B-use",list.get(pos).getProcesses().getB().isUse()).
//                        putExtra("A-percent",roundOffNumber(list.get(pos).getProcesses().getA().getPercentageComp())).
//                        putExtra("B-percent",roundOffNumber(list.get(pos).getProcesses().getB().getPercentageComp())).
//                        putExtra("wt",list.get(pos).getWt());
//                        startActivity(intent);


            }

            double roundOffNumber(float f){
                return Math.round(f * 100.0) / 100.0;
            }

        }
    }


}
