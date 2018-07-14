package com.samarthgupta.sfa_app.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import com.samarthgupta.sfa_app.Activities.CreatingWorkTicket.ClientDetailsActivity;
import com.samarthgupta.sfa_app.Activities.Settings.SettingsActivity;
import com.samarthgupta.sfa_app.Activities.SignIn_Register.SignInActivity;
import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.Notice;
import com.samarthgupta.sfa_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rv;
    ProgressBar pb;
    TextView tvEmpDept;
    Employee data;
    SimpleDateFormat dateFormat;
    long date;


    private int[] ss = {5, 6, 7, 8, 9, 10, 11, 12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rv = (RecyclerView)findViewById(R.id.rv_notice);
        pb = (ProgressBar) findViewById(R.id.pb_notice);
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
                Toast.makeText(HomeActivity.this, "Employee dept changed to " + dept, Toast.LENGTH_SHORT).show();
                tvEmpDept.setText(dept.toUpperCase());
            }
        });

        pb.setVisibility(View.VISIBLE);
        getLatestNotice();

    }

    void getLatestNotice() {
        String url = baseUrl + "/notice";
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("NOTICE", response);
                Notice notices[] = new GsonBuilder().create().fromJson(response, Notice[].class);
                if (notices.length!=0){
                    rv.setAdapter(new Home_NoticeAdapter(notices));
                    pb.setVisibility(View.GONE);
                    rv.setVisibility(View.VISIBLE);
                    rv.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                    rv.setHasFixedSize(true);
                }else {
                    Toast.makeText(HomeActivity.this, "No Notice", Toast.LENGTH_SHORT).show();

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
            startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
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
        } else if (id == R.id.nav_perPage) {
            setperPage();
        } else if (id == R.id.nav_report_issue) {
            startActivity(new Intent(HomeActivity.this, ReportActivity.class));
        } else if (id == R.id.nav_notices) {
            String[] adminNum = new String[]{"9582184794", "7982884321", "9582857833", "9999999999"};
            for (String anAdminNum : adminNum) {
                if (anAdminNum.equals(data.getMobile())) {
                    //Show dialogue
                    final Dialog dialog = new Dialog(HomeActivity.this);
                    dialog.setContentView(R.layout.layout_add_notice);

                    TextView tvNoticeDate = (TextView) dialog.findViewById(R.id.tv_notice_date);
                    Button btSubmit = (Button) dialog.findViewById(R.id.bt_post_notice);
                    final EditText etNoticeBody = (EditText) dialog.findViewById(R.id.et_notice_body);
                    final EditText etNoticeTitle = (EditText) dialog.findViewById(R.id.et_notice_title);
                    date = System.currentTimeMillis();

                    //for showing on the dialog
                    dateFormat = new SimpleDateFormat("EEE,dd MMM yyyy", Locale.UK);
                    tvNoticeDate.setText(dateFormat.format(date));
                    // for sending to backend: format changes
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK);


                    dialog.setCancelable(true);
                    dialog.show();
                    btSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String noticeBody = etNoticeBody.getText().toString();
                            String noticeTitle = etNoticeTitle.getText().toString();
                            JSONObject obj = new JSONObject();
                            try {
                                obj.put("title", noticeTitle);
                                obj.put("date", dateFormat.format(date));
                                obj.put("body", noticeBody);
                                obj.put("noticeBy", data.getMobile());

                                Volley.newRequestQueue(HomeActivity.this).add(new JsonObjectRequest(Request.Method.POST, baseUrl + "/notice", obj, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.d("Notice", response.toString());
                                        try {
                                            if (response.getBoolean("success")) {
                                                Toast.makeText(HomeActivity.this, "Notice posted successfully", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                                Intent intent = getIntent();
                                                finish();
                                                startActivity(intent);
                                            } else {
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
                                        Log.d("Notice", error.toString());
                                    }
                                }));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });

                    break;

                } else if (adminNum[adminNum.length - 1].equals(anAdminNum)) {
                    Toast.makeText(this, "Access for admin only", Toast.LENGTH_SHORT).show();
                }

            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setperPage() {
        final ArrayAdapter<String> adp = new ArrayAdapter<String>(HomeActivity.this,
                android.R.layout.simple_spinner_item, Arrays.toString(ss).split("[\\[\\]]")[1].split(", "));
        final Spinner sp = new Spinner(HomeActivity.this);
        sp.setLayoutParams(new LinearLayout.LayoutParams(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.MATCH_PARENT));
        sp.setAdapter(adp);
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage(" Change Jobs per page to : ");
        builder.setView(sp);
        builder.create().show();

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                Log.i("selected", item.toString());
                SharedPreferences.Editor editor = getSharedPreferences("JOB_PER_PAGE", MODE_PRIVATE).edit();
                editor.putString("per_page", item.toString());
                editor.apply();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}
