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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Task;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Update;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class TasksActivity extends AppCompatActivity {

    RecyclerView rv;
    ProgressBar pb;
    RequestQueue rq ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        rv = (RecyclerView) findViewById(R.id.rv_job_tickets);
        pb = (ProgressBar) findViewById(R.id.pb_tasks);
        pb.setVisibility(View.VISIBLE);
        rq = Volley.newRequestQueue(this) ;

        sendJsonrequest();
//        StringRequest task = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("GETrequest",url+"success" ) ;
//                Task tasks[] = new GsonBuilder().create().fromJson(response, Task[].class);
//                pb.setVisibility(View.INVISIBLE);
//                rv.setVisibility(View.VISIBLE);
//                rv.setAdapter(new TasksAdapter(tasks));
//                rv.setLayoutManager(new LinearLayoutManager(TasksActivity.this));
//                rv.setHasFixedSize(true);
//            }
//
//
//        },
//                new com.android.volley.Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) ;
//        RequestQueue requestQueue = Volley.newRequestQueue(this) ;
//        requestQueue.add(task) ;

                //       Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
//        DataInterface client = retrofit.create(DataInterface.class);

// Task in place of JobTicket.
//        Employee emp =  new GsonBuilder().create().fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE).getString("Data",null),
//                Employee.class);
//
//        Log.d("Tasks",emp.getDept());
//        Call<List<Task>> call = client.getEmpTask(emp.getDept());//write in DataIntereface(GET) for Task Activity.
//        call.enqueue(new Callback<List<Task>>() {
//            @Override
//            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
//                Log.d("Tasks",response.toString());
//                rv.setAdapter(new TicketAdapter(response.body()));
//
//                pb.setVisibility(View.INVISIBLE);
//                rv.setLayoutManager(new LinearLayoutManager(TasksActivity.this));
//                rv.setHasFixedSize(true);
//            }
//
//            @Override
//            public void onFailure(Call<List<Task>> call, Throwable t) {
//
//            }
//        });

    }

    public void sendJsonrequest(){
        final Employee emp =  new GsonBuilder().create().fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE).getString                               ("Data",null), Employee.class);

        final String url = baseUrl + "/task?emp="+emp.getDept();
        Log.d("DepartmentUrl",url) ;
        JsonObjectRequest task = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GETrequest", url + "success");
                Task tasks[] = new GsonBuilder().create().fromJson(String.valueOf(response), Task[].class);
                pb.setVisibility(View.INVISIBLE);
                rv.setVisibility(View.VISIBLE);
                rv.setAdapter(new TasksAdapter(tasks));
                rv.setLayoutManager(new LinearLayoutManager(TasksActivity.this));
                rv.setHasFixedSize(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) ;

        rq.add(task) ;

    }
    class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksHolder>{

        Task taskList[];

        public TasksAdapter(Task[] tasks){
            taskList = tasks;
        }

        @Override
        public TasksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           return new TasksHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tasks_recyclers,parent,false));


        }

        @Override
        public void onBindViewHolder(TasksAdapter.TasksHolder holder, int position) {
            holder.clientName.setText(taskList[position].getClient().getName());
            holder.priority.setText(taskList[position].getPriority());
            holder.deliveryDate.setText(taskList[position].getDeliveryDate());
        }

        @Override
        public int getItemCount() {
            return taskList.length;
        }


        public class TasksHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView clientName, priority,deliveryDate;
            public TasksHolder(View itemView) {
                super(itemView);
                clientName = (TextView)itemView.findViewById(R.id.tv_client_name);
                priority = (TextView)itemView.findViewById(R.id.tv_priority) ;
                deliveryDate = (TextView)itemView.findViewById(R.id.tv_delivery_date) ;
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
                    startActivity(intent);
                }else if(jobType.equals("Box")){
                    Intent intent = new Intent(TasksActivity.this,Box_Task.class) ;
                    intent.putExtra("BoxProcesses", ConvertTostring) ;
                    intent.putExtra("BoxJobName", jobName) ;
                    startActivity(intent);
                }else if (jobType.equals("Cover")){
                    Intent intent = new Intent(TasksActivity.this,Cover_Task.class) ;
                    intent.putExtra("CoverProcesses", ConvertTostring) ;
                    intent.putExtra("CoverJbName", jobName) ;
                    startActivity(intent);
                }
//                List<Update> updates = process.get(0).getBook().getCentrePin().getUpdates();
//                Update up = updates.get(updates.size()-1);

            }
        }
    }


}
