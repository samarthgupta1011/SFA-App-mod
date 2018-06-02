package com.samarthgupta.sfa_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class TasksActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView rv;
    ProgressBar pb;
    String filterOption ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        rv = (RecyclerView) findViewById(R.id.rv_job_tickets);
        pb = (ProgressBar) findViewById(R.id.pb_tasks);

        pb.setVisibility(View.VISIBLE);
        VolleyRequest(null,null) ;

    }

    private void VolleyRequest( String clientName, String jobName ) {
        final Employee emp = new GsonBuilder()
                .create()
                .fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE)
                        .getString("Data", null), Employee.class);
        Log.d("Tasks", emp.getDept());
        String url = null;
        if (clientName == null && jobName == null) {
            //Simple Volley request
            url = baseUrl + "/task?emp=" + emp.getDept();
        } else if (clientName != null && jobName == null) {
            // client name
            url = baseUrl + "/task/client?emp="+emp.getDept()+"&reg="+clientName ;
        } else if (clientName == null && jobName != null) {
            //job name
            url = baseUrl+ "/task/jobname?emp="+emp.getDept()+"&reg="+jobName ;
            Log.d("response not found", url);
        }


        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("TASK", response);
                Task tasks[] = new GsonBuilder().create().fromJson(response, Task[].class);
                rv.setAdapter(new TasksAdapter(tasks));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_menu_items,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_searchByClientName) {
            Toast.makeText(this, "Enter Client Name", Toast.LENGTH_SHORT).show();
            filterOption = "ClientName" ;
            return true;
        }

        if (id == R.id.action_searchByJobName) {
            Toast.makeText(this, "Enter Job Name", Toast.LENGTH_SHORT).show();
            filterOption="JobName";
           return true ;
        }

        return super.onOptionsItemSelected(item);
    }






    @Override
    public boolean onQueryTextChange(String newText) {
        newText.toLowerCase();
        if (filterOption==null){
            Toast.makeText(this, "Select Search By", Toast.LENGTH_SHORT).show();
        }
        else if (filterOption=="ClientName"){
            VolleyRequest(newText,null);
        }else if(filterOption=="JobName"){
            VolleyRequest(null,newText);
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    // RECYCLERVIEW ADAPTER BELOW


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
