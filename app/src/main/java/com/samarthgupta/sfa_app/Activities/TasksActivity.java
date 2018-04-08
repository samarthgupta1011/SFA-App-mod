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
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Task;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;
import com.samarthgupta.sfa_app.R;
import java.util.List;
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

        final Employee emp =  new GsonBuilder()
                .create()
                .fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE)
                .getString("Data",null), Employee.class);
        Log.d("Tasks",emp.getDept());
        String url = baseUrl + "/task?emp="+emp.getDept();

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("TASK", response);
                Task tasks[] = new GsonBuilder().create().fromJson(response, Task[].class);
                rv.setAdapter(new TasksAdapter(tasks)) ;
                pb.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
                rv.setLayoutManager(new LinearLayoutManager(TasksActivity.this));
                rv.setHasFixedSize(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));

    }

    class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksHolder>{

        Task taskList[];
        public TasksAdapter(Task[] tasks){
            taskList = tasks;
        }

        @Override
        public TasksAdapter.TasksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TasksHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tasks_recyclers,parent,false));
        }

        @Override
        public void onBindViewHolder(TasksAdapter.TasksHolder holder, int position) {
            holder.clientName.setText(taskList[position].getClient().getName());
            holder.priority.setText(taskList[position].getPriority());
            holder.deliveryDate.setText(taskList[position].getDeliveryDate());
            holder.tvJobType.setText(taskList[position].getJob().getType());
            holder.tvJobName.setText(taskList[position].getJob().getName());
        }

        @Override
        public int getItemCount() {
            return taskList.length;
        }


        public class TasksHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView clientName, priority,deliveryDate, tvJobType, tvJobName;
            public TasksHolder(View itemView) {
                super(itemView);
                clientName = (TextView)itemView.findViewById(R.id.tv_client_name);
                priority = (TextView)itemView.findViewById(R.id.tv_priority) ;
                deliveryDate = (TextView)itemView.findViewById(R.id.tv_delivery_date) ;
                tvJobName = (TextView) itemView.findViewById(R.id.tv_job_name);
                tvJobType = (TextView) itemView.findViewById(R.id.tv_job_type);
                itemView.setOnClickListener(this);
            }
            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                String jobType = taskList[pos].getJob().getType();
                String jobName = taskList[pos].getJob().getName() ;
                List<Processes> process = taskList[pos].getProcesses();

                String ConvertTostring = new GsonBuilder().create().toJson(process.get(0));

                if (jobType.equals("Book")){
                    Intent intent = new Intent(TasksActivity.this,Books_Task.class) ;
                    intent.putExtra("BookProcesses", ConvertTostring) ;
                    intent.putExtra("BookJobName", jobName) ;
                    intent.putExtra("wt_id", taskList[pos].getWt());
                    startActivity(intent);
                }else if(jobType.equals("Box")){
                    Intent intent = new Intent(TasksActivity.this,Box_Task.class) ;
                    intent.putExtra("BoxProcesses", ConvertTostring) ;
                    intent.putExtra("BoxJobName", jobName) ;
                    intent.putExtra("wt_id", taskList[pos].getWt());
                    startActivity(intent);
                }else if (jobType.equals("Cover")){
                    Intent intent = new Intent(TasksActivity.this,Cover_Task.class) ;
                    intent.putExtra("CoverProcesses", ConvertTostring) ;
                    intent.putExtra("CoverJbName", jobName) ;
                    intent.putExtra("wt_id", taskList[pos].getWt());
                    startActivity(intent);
                }


            }
        }
    }


}
