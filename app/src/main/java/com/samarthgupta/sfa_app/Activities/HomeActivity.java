package com.samarthgupta.sfa_app.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.JobTicket;
import com.samarthgupta.sfa_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;
import static com.samarthgupta.sfa_app.POJO.GlobalAccess.jobTicket;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tvEmpDept;
    Employee data;
    SimpleDateFormat dateFormat;
    long date;
    TextView tvNoticeBody, tvNoticeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        data = new GsonBuilder().create().fromJson(getSharedPreferences("Login", Context.MODE_PRIVATE).getString("Data", null), Employee.class);
        Log.e("Data", data.getDept());

        tvEmpDept = (TextView) findViewById(R.id.tv_emp_dept);
        String empDep = data.getDept().toUpperCase();
        tvEmpDept.setText(empDep);

//        getLatestNotice();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ClientDetailsActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //For testing purpose only
        final EditText et = (EditText) findViewById(R.id.et_dept);
        Button bt = (Button) findViewById(R.id.bt_save_dept);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dept = et.getText().toString().trim();
                data.setDept(dept);
                String emp = new GsonBuilder().create().toJson(data);
                getSharedPreferences("Login", Context.MODE_PRIVATE).edit().putString("Data", emp).apply();
                Toast.makeText(HomeActivity.this, "Employee dept changed to "+ dept, Toast.LENGTH_SHORT).show();
                tvEmpDept.setText(dept.toUpperCase());
            }
        });


    }

    void getLatestNotice(){
        Volley.newRequestQueue(HomeActivity.this).add(new JsonArrayRequest(Request.Method.GET, baseUrl + "/admindata", new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("Notice",response.toString());
                int size = response.length();
                try {
                    JSONObject latestNotice = response.getJSONObject(size-1);
                    tvNoticeBody = (TextView) findViewById(R.id.tv_notice);
                    tvNoticeDate = (TextView) findViewById(R.id.tv_notice_resp_date);
                    tvNoticeBody.setText(latestNotice.getString("notice"));
                    tvNoticeDate.setText(latestNotice.getString("dated"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_logout) {

            getSharedPreferences("Login", Context.MODE_PRIVATE).edit().remove("Data").apply();
            startActivity(new Intent(HomeActivity.this, SignInActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create_wt) {
            startActivity(new Intent(HomeActivity.this, ClientDetailsActivity.class));
        } else if (id == R.id.nav_tasks) {
            startActivity(new Intent(HomeActivity.this, TasksActivity.class));
        } else if (id == R.id.nav_report_issue) {

        } else if(id == R.id.nav_notices){

            //Compare with admin, if it matches admin - provide access, otherwise, goodbye biro
            String empName = data.getName();
            String empDept = data.getDept();

            if(empName.equals("Lalit Sayal")||empDept.equals("Admin")||empDept.equals("admin")){
                //Show dialogue
                final Dialog dialog = new Dialog(HomeActivity.this);
                dialog.setContentView(R.layout.layout_add_notice);

                TextView tvNoticeDate = (TextView) dialog.findViewById(R.id.tv_notice_date);
                Button btSubmit = (Button) dialog.findViewById(R.id.bt_post_notice);
                final EditText etNoticeBody = (EditText) dialog.findViewById(R.id.et_notice_body);

                date = System.currentTimeMillis();
                dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
                tvNoticeDate.setText(dateFormat.format(date));

                dialog.setCancelable(true);
                dialog.show();
                btSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String noticeBody = etNoticeBody.getText().toString();
                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("notice",noticeBody);
                            obj.put("dated",dateFormat.format(date));
                            obj.put("by",data.getName());

                            Volley.newRequestQueue(HomeActivity.this).add(new JsonObjectRequest(Request.Method.POST, baseUrl + "/admindata", obj, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("Notice",response.toString());
                                    try {
                                        if(response.getBoolean("success")){
                                            Toast.makeText(HomeActivity.this, "Notice posted successfully", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }

                                        else {
                                            Toast.makeText(HomeActivity.this, "Can't post due to network error", Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(HomeActivity.this, "Can't post due to network error", Toast.LENGTH_SHORT).show();
                                    Log.d("Notice",error.toString());
                                }
                            }));



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                });


            }

            else {
                Toast.makeText(this, "Access for admin only", Toast.LENGTH_SHORT).show();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
