package com.samarthgupta.sfa_app.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.Activities.Tasks.Books_Task;
import com.samarthgupta.sfa_app.Activities.Tasks.Box_Task;
import com.samarthgupta.sfa_app.Activities.Tasks.Cover_Task;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.Task;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;
import com.samarthgupta.sfa_app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class TasksActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    RecyclerView rv;
    ProgressBar pb;
    String filterOption;

    String startDate = null;
    String endDate = null;

    LinearLayout llDateSelect;
    TextView tvStartDate, tvEndDate, tvNextPg, tvPrevPg;
    int pages = 1;
    String perPage ;// default;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        rv = (RecyclerView) findViewById(R.id.rv_job_tickets);
        pb = (ProgressBar) findViewById(R.id.pb_tasks);

        llDateSelect = (LinearLayout) findViewById(R.id.ll_date_select);
        llDateSelect.setVisibility(View.GONE);

        tvStartDate = (TextView) findViewById(R.id.tv_start_date);
        tvEndDate = (TextView) findViewById(R.id.tv_end_date);
        tvStartDate.setOnClickListener(this);
        tvEndDate.setOnClickListener(this);

        tvNextPg = (TextView) findViewById(R.id.tv_next_page);
        tvNextPg.setOnClickListener(this);

        tvPrevPg = (TextView) findViewById(R.id.tv_prev_page);
        tvPrevPg.setOnClickListener(this);

        pb.setVisibility(View.VISIBLE);
        VolleyRequest(null, null);

    }

    private void VolleyRequest(String clientName, String jobName) {
        final Employee emp = new GsonBuilder()
                .create()
                .fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE)
                        .getString("Data", null), Employee.class);
        Log.d("Tasks", emp.getDept());

        String url = null;
        if (clientName == null && jobName == null) {
            SharedPreferences prefs = getSharedPreferences("JOB_PER_PAGE", MODE_PRIVATE);
            String restoredText = prefs.getString("per_page", null);
            if (restoredText != null) {
                perPage = prefs.getString("per_page", "0");

            }
            Log.i("perPage", perPage);
            //Simple Volley request
            url = baseUrl + "/task?page="+pages+ "&perPage="+perPage+"&emp=printing" + emp.getDept();
            Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("TASK", response);
                    Task tasks[] = new GsonBuilder().create().fromJson(response, Task[].class);
                    if (tasks.length!=0){
                        rv.setAdapter(new TasksAdapter(tasks));
                        pb.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                        rv.setLayoutManager(new LinearLayoutManager(TasksActivity.this));
                        rv.setHasFixedSize(true);
                    }else {
                        Toast.makeText(TasksActivity.this, "No More Tasks", Toast.LENGTH_SHORT).show();
                        pages-- ;
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }));
        } else if (clientName != null && jobName == null) {
            // client name
            url = baseUrl + "/task/client?emp=" + emp.getDept() + "&reg=" + clientName;
            ClientAndJobQuery(url) ;
        } else if (clientName == null && jobName != null) {
            //job name
            url = baseUrl + "/task/jobname?emp=" + emp.getDept() + "&reg=" + jobName;
            Log.d("response not found", url);
            ClientAndJobQuery(url) ;
        }

    }

    private void ClientAndJobQuery(String url) {
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
        getMenuInflater().inflate(R.menu.task_menu_items, menu);
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
            filterOption = "ClientName";
            return true;
        }

        if (id == R.id.action_searchByJobName) {
            Toast.makeText(this, "Enter Job Name", Toast.LENGTH_SHORT).show();
            filterOption = "JobName";
            return true;
        }

        if (id == R.id.action_searchByDelDate) {


            llDateSelect.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Please select start and end dates", Toast.LENGTH_SHORT).show();

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        newText.toLowerCase();
        if (filterOption == null) {
            Toast.makeText(this, "Select Search By", Toast.LENGTH_SHORT).show();
        } else if (filterOption == "ClientName") {
            VolleyRequest(newText, null);
        } else if (filterOption == "JobName") {
            VolleyRequest(null, newText);
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void onClick(View view) {


        if (view == tvStartDate) {
            Calendar cal = Calendar.getInstance();

            final DatePickerDialog dialog = new DatePickerDialog(
                    TasksActivity.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                    String mon = null;
                    String d = null;
                    month = month + 1;

                    if (month / 10 == 0) {
                        mon = "0" + Integer.toString(month);
                    } else {
                        mon = Integer.toString(month);
                    }

                    if (day / 10 == 0) {
                        d = "0" + Integer.toString(day);
                    } else {
                        d = Integer.toString(day);
                    }

                    startDate = year + "-" + mon + "-" + d;
                    tvStartDate.setText(startDate);

                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.setTitle("Select start date");
            dialog.show();


        } else if (view == tvEndDate) {

            if (startDate == null || startDate.isEmpty()) {
                Toast.makeText(this, "Please select a start date", Toast.LENGTH_SHORT).show();
            } else {

                Calendar cal = Calendar.getInstance();

                final DatePickerDialog dialog = new DatePickerDialog(
                        TasksActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        String mon = null;
                        String d = null;
                        month = month + 1;

                        if (month / 10 == 0) {
                            mon = "0" + Integer.toString(month);
                        } else {
                            mon = Integer.toString(month);
                        }

                        if (day / 10 == 0) {
                            d = "0" + Integer.toString(day);
                        } else {
                            d = Integer.toString(day);
                        }

                        endDate = year + "-" + mon + "-" + d;
                        tvEndDate.setText(endDate);

                        if (!startDate.isEmpty() && !endDate.isEmpty()) {
                            makeDateQuery(startDate, endDate);
                        }


                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.setTitle("Select End Date");
                dialog.show();

            }


        } else if(view == tvNextPg){
            //on Click increase the increment the page number by one.
            // by default number of task per pages displayed will be 5 now.
            pages++ ;
            VolleyRequest(null,null);

        } else if(view == tvPrevPg){
            pages = pages-1;
            if (pages<1){
                pages = 1 ;
                Toast.makeText(this, "Page 1", Toast.LENGTH_SHORT).show();
            }
            VolleyRequest(null, null);

        }
    }

    private void makeDateQuery(String startDate, String endDate) {
        String url = baseUrl + "/task/date?" + "startDate=" + startDate + "&endDate=" + endDate;
        Log.i("Url", url);

        Volley.newRequestQueue(TasksActivity.this).add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//                Log.i("TASK", response);
                llDateSelect.setVisibility(View.GONE);
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


    // RECYCLERVIEW ADAPTER BELOW


    class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksHolder> {

        Task taskList[];

        public TasksAdapter(Task[] tasks) {
            taskList = tasks;
        }

        @Override
        public TasksAdapter.TasksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TasksHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tasks_recyclers, parent, false));
        }

        @Override
        public void onBindViewHolder(TasksAdapter.TasksHolder holder, int position) {
            holder.clientName.setText(taskList[position].getClient().getName());
            holder.priority.setText(taskList[position].getPriority());

            boolean isDateSet = false;
            String delDate = taskList[position].getDeliveryDate();
            SimpleDateFormat sdfPosted = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
            try {
                Date date = sdfPosted.parse(delDate);
                SimpleDateFormat sdf = new SimpleDateFormat("EEE,dd MMM yyyy", Locale.UK);
                String del = sdf.format(date);
                del = del.replace(",", ", ");
                holder.deliveryDate.setText(del);
                isDateSet = true;
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(!isDateSet){
                Log.e("Msg", "Not set");
                holder.deliveryDate.setText(delDate);
            }

            holder.tvJobType.setText(taskList[position].getJob().getType());
            holder.tvJobName.setText(taskList[position].getJob().getName());
        }

        @Override
        public int getItemCount() {
            return taskList.length;
        }


        public class TasksHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView clientName, priority, deliveryDate, tvJobType, tvJobName;

            public TasksHolder(View itemView) {
                super(itemView);
                clientName = (TextView) itemView.findViewById(R.id.tv_client_name);
                priority = (TextView) itemView.findViewById(R.id.tv_priority);
                deliveryDate = (TextView) itemView.findViewById(R.id.tv_delivery_date);
                tvJobName = (TextView) itemView.findViewById(R.id.tv_job_name);
                tvJobType = (TextView) itemView.findViewById(R.id.tv_job_type);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                String jobType = taskList[pos].getJob().getType();
                String jobName = taskList[pos].getJob().getName();
                List<Processes> process = taskList[pos].getProcesses();

                String ConvertTostring = new GsonBuilder().create().toJson(process.get(0));

                switch (jobType) {
                    case "Book": {
                        Intent intent = new Intent(TasksActivity.this, Books_Task.class);
                        intent.putExtra("BookProcesses", ConvertTostring);
                        intent.putExtra("BookJobName", jobName);
                        intent.putExtra("wt_id", taskList[pos].getWt());
                        startActivity(intent);
                        break;
                    }
                    case "Box": {
                        Intent intent = new Intent(TasksActivity.this, Box_Task.class);
                        intent.putExtra("BoxProcesses", ConvertTostring);
                        intent.putExtra("BoxJobName", jobName);
                        intent.putExtra("wt_id", taskList[pos].getWt());
                        startActivity(intent);
                        break;
                    }
                    case "Cover": {
                        Intent intent = new Intent(TasksActivity.this, Cover_Task.class);
                        intent.putExtra("CoverProcesses", ConvertTostring);
                        intent.putExtra("CoverJbName", jobName);
                        intent.putExtra("wt_id", taskList[pos].getWt());
                        startActivity(intent);
                        break;
                    }
                }


            }
        }
    }


}
