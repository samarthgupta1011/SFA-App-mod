package com.samarthgupta.sfa_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Bill;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Binding;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Challan;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Cover;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Creasing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Designing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Dispatch;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Ferro;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Lamination;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Packing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Plates;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Printing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class Cover_Processes extends AppCompatActivity {

    private CheckBox check_design, check_ferro, check_plates, check_printing, check_lamination, check_creasing, check_binding, check_packing, check_dispatch, check_challan, check_bill;
    private EditText et_noOfSets;

    private Button bt_proceed_cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover__processes);
        addListenerToCheckBox();

    }

    private void addListenerToCheckBox() {
        check_design = (CheckBox) findViewById(R.id.checkBox_design);
        check_ferro = (CheckBox) findViewById(R.id.checkBox_ferro);
        check_plates = (CheckBox) findViewById(R.id.checkBox_plates);
        check_printing = (CheckBox) findViewById(R.id.checkBox_printing);
        check_lamination = (CheckBox) findViewById(R.id.checkBox_lamination);
        check_creasing = (CheckBox) findViewById(R.id.checkBox_creasing);
        check_binding = (CheckBox) findViewById(R.id.checkBox_binding);
        check_packing = (CheckBox) findViewById(R.id.checkBox_packing);
        check_dispatch = (CheckBox) findViewById(R.id.checkBox_dispatch);
        check_challan = (CheckBox) findViewById(R.id.checkBox_challan);
        check_bill = (CheckBox) findViewById(R.id.checkBox_bill);

        et_noOfSets = (EditText) findViewById(R.id.et_noOfsets);
        et_noOfSets.setVisibility(View.GONE);

        bt_proceed_cover = (Button) findViewById(R.id.bt_proceed_cover);


        final Processes processes = new Processes();

        final Designing designing = new Designing();
        final Ferro ferro = new Ferro();
        final Plates plates = new Plates();
        final Printing printing = new Printing();
        final Lamination lamination = new Lamination();
        final Creasing creasing = new Creasing();
        final Binding binding = new Binding();
        final Packing packing = new Packing();
        final Dispatch dispatch = new Dispatch();
        final Challan challan = new Challan();
        final Bill bill = new Bill();


        check_design.setChecked(true);
        designing.setIsRequired(true);
        check_ferro.setChecked(true);
        ferro.setIsRequired(true);
        check_plates.setChecked(true);
        plates.setIsRequired(true);
        check_printing.setChecked(true);
        printing.setIsRequired(true);

        check_packing.setChecked(true);
        packing.setIsRequired(true);
        check_dispatch.setChecked(true);
        dispatch.setIsRequired(true);
        check_challan.setChecked(true);
        challan.setIsRequired(true);
        check_bill.setChecked(true);
        bill.setIsRequired(true);


        check_design.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "DESIGN selected", Toast.LENGTH_SHORT).show();
                            designing.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "DESIGN deselected", Toast.LENGTH_SHORT).show();
                            designing.setIsRequired(false);
                        }
                    }
                }
        );
        check_ferro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "FERRO selected", Toast.LENGTH_SHORT).show();
                            ferro.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "FERRO deselected", Toast.LENGTH_SHORT).show();
                            ferro.setIsRequired(false);
                        }
                    }
                }
        );
        check_plates.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "PLATES selected", Toast.LENGTH_SHORT).show();
                            plates.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "PLATES deselected", Toast.LENGTH_SHORT).show();
                            plates.setIsRequired(false);
                        }
                    }
                }
        );

        check_printing.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            et_noOfSets.setVisibility(View.VISIBLE);
                            Toast.makeText(Cover_Processes.this, "PRINTING selected", Toast.LENGTH_SHORT).show();
                            printing.setIsRequired(true);
                            processes.setTotalSets(et_noOfSets.getText().toString());
                        } else if (!((CheckBox) v).isChecked()) {
                            et_noOfSets.setVisibility(View.GONE);
                            Toast.makeText(Cover_Processes.this, "PRINTING deselected", Toast.LENGTH_SHORT).show();
                            printing.setIsRequired(false);
                            processes.setTotalSets(null);
                        }
                    }
                }
        );
        check_lamination.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "LAMINATION selected", Toast.LENGTH_SHORT).show();
                            lamination.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "LAMINATION deselected", Toast.LENGTH_SHORT).show();
                            lamination.setIsRequired(false);
                        }
                    }
                }
        );
        check_creasing.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "CREASING selected", Toast.LENGTH_SHORT).show();
                            creasing.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "CREASING deselected", Toast.LENGTH_SHORT).show();
                            creasing.setIsRequired(false);
                        }
                    }
                }
        );
        check_binding.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "BINDING selected", Toast.LENGTH_SHORT).show();
                            binding.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "BINDING deselected", Toast.LENGTH_SHORT).show();
                            binding.setIsRequired(false);
                        }
                    }
                }
        );
        check_packing.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "PACKING selected", Toast.LENGTH_SHORT).show();
                            packing.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "PACKING deselected", Toast.LENGTH_SHORT).show();
                            packing.setIsRequired(false);
                        }
                    }
                }
        );
        check_dispatch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "DISPATCH selected", Toast.LENGTH_SHORT).show();
                            dispatch.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "DISPATCH deselected", Toast.LENGTH_SHORT).show();
                            dispatch.setIsRequired(false);
                        }
                    }
                }
        );
        check_challan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "CHALLAN selected", Toast.LENGTH_SHORT).show();
                            challan.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "CHALLAN deselected", Toast.LENGTH_SHORT).show();
                            challan.setIsRequired(false);
                        }
                    }
                }
        );
        check_bill.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "BILL selected", Toast.LENGTH_SHORT).show();
                            bill.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Cover_Processes.this, "BILL deselected", Toast.LENGTH_SHORT).show();
                            bill.setIsRequired(false);
                        }
                    }
                }
        );

        bt_proceed_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bt_proceed_cover.setVisibility(View.INVISIBLE);
                final Cover cover = new Cover();
                cover.setDesigning(designing);
                cover.setFerro(ferro);
                cover.setPlates(plates);
                cover.setPrinting(printing);
                cover.setLamination(lamination);
                cover.setCreasing(creasing);
                cover.setBinding(binding);
                cover.setPacking(packing);
                cover.setDispatch(dispatch);
                cover.setChallan(challan);
                cover.setBill(bill);

                processes.setJobType("Cover");
                processes.setCover(cover);

                String wt_id = getIntent().getExtras().getString("wt_id");
                String total_number = getIntent().getStringExtra("total_number");

                processes.setWtId(wt_id);
                processes.setTotalSets(et_noOfSets.getText().toString());
                processes.setTotalForms(null);
                processes.setTotalNumber(total_number);


                final String coverProcess = new GsonBuilder().create().toJson(processes);
                try {

                    //Ticket object to be posted
                    JSONObject coverObj = new JSONObject(coverProcess);

                    String url = baseUrl + "/processes";
                    Volley.newRequestQueue(Cover_Processes.this).add(new JsonObjectRequest(Request.Method.POST,
                            url, coverObj, new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getBoolean("success")) {
                                    Toast.makeText(Cover_Processes.this, "Success", Toast.LENGTH_SHORT).show();
                                    bt_proceed_cover.setVisibility(View.VISIBLE);

                                    Intent intent = new Intent(Cover_Processes.this, HomeActivity.class);
                                    startActivity(intent);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            bt_proceed_cover.setVisibility(View.VISIBLE);
                            Toast.makeText(Cover_Processes.this, "Network error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }));


                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        });


    }
}
