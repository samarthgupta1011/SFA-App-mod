package com.samarthgupta.sfa_app.Activities.CreatingWorkTicket.X_Processes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.samarthgupta.sfa_app.Activities.HomeActivity;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Bill;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Box;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Challan;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Designing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.DieCut;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Dispatch;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Embossing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Ferro;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Foiling;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Lamination;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Packing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Pasting;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Plates;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Printing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Uv;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class Box_Processes extends AppCompatActivity {

    private CheckBox check_design, check_ferro, check_plates, check_printing, check_lamination, check_uv, check_embossing, check_foiling, check_diecut, check_pasting, check_packing, check_dispatch, check_challan, check_bill;
    private EditText et_noOfSets;
    Button bt_proceed_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box__processes);
        addListenerToCheckBox();
    }

    private void addListenerToCheckBox() {
        check_design = (CheckBox) findViewById(R.id.checkBox_design);
        check_ferro = (CheckBox) findViewById(R.id.checkBox_ferro);
        check_plates = (CheckBox) findViewById(R.id.checkBox_plates);
        check_printing = (CheckBox) findViewById(R.id.checkBox_printing);
        et_noOfSets = (EditText) findViewById(R.id.et_noOfsets);
        et_noOfSets.setVisibility(View.GONE); //initially don't want to show.

        check_lamination = (CheckBox) findViewById(R.id.checkBox_lamination);
        check_uv = (CheckBox) findViewById(R.id.checkBox_uv);
        check_embossing = (CheckBox) findViewById(R.id.checkBox_embossing);
        check_foiling = (CheckBox) findViewById(R.id.checkBox_foiling);
        check_diecut = (CheckBox) findViewById(R.id.checkBox_diecut);
        check_pasting = (CheckBox) findViewById(R.id.checkBox_pasting);
        check_packing = (CheckBox) findViewById(R.id.checkBox_packing);
        check_dispatch = (CheckBox) findViewById(R.id.checkBox_dispatch);
        check_challan = (CheckBox) findViewById(R.id.checkBox_challan);
        check_bill = (CheckBox) findViewById(R.id.checkBox_bill);

        bt_proceed_box = (Button) findViewById(R.id.bt_proceed_box);


        final Processes processes = new Processes();

        final Designing designing = new Designing();
        final Ferro ferro = new Ferro();
        final Plates plates = new Plates();
        final Printing printing = new Printing();
        final Lamination lamination = new Lamination();
        final Uv uv = new Uv();
        final Embossing embossing = new Embossing();
        final Foiling foiling = new Foiling();
        final DieCut dieCut = new DieCut();
        final Pasting pasting = new Pasting();
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
        printing.setIsRequired(true);;

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

                            designing.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {

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
                            ferro.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {

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
                            plates.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
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
                            printing.setIsRequired(true);
                            processes.setTotalSets(et_noOfSets.getText().toString());
                        } else if (!((CheckBox) v).isChecked()) {
                            et_noOfSets.setVisibility(View.GONE);
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
                            lamination.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            lamination.setIsRequired(false);
                        }
                    }
                }
        );

        check_uv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            uv.setIsRequired(true);

                        } else if (!((CheckBox) v).isChecked()) {
                            uv.setIsRequired(false);
                        }
                    }
                }
        );

        check_embossing.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            embossing.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            embossing.setIsRequired(false);
                        }
                    }
                }
        );

        check_foiling.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            foiling.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            foiling.setIsRequired(false);
                        }
                    }
                }
        );
        check_diecut.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            dieCut.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            dieCut.setIsRequired(false);
                        }
                    }
                }
        );
        check_pasting.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            pasting.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            pasting.setIsRequired(false);
                        }
                    }
                }
        );
        check_packing.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            packing.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
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
                            dispatch.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
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
                            challan.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
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
                            bill.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            bill.setIsRequired(false);
                        }
                    }
                }
        );
        bt_proceed_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (check_printing.isChecked() && et_noOfSets.getText().toString().length()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Box_Processes.this);
                    builder.setTitle("Enter printing details");
                    builder.setMessage("Please enter number of SETS details");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return;
                }

                bt_proceed_box.setVisibility(View.INVISIBLE);

                final Box box = new Box();

                box.setDesigning(designing);
                box.setFerro(ferro);
                box.setPlates(plates);
                box.setPrinting(printing);
                box.setLamination(lamination);
                box.setUv(uv);
                box.setEmbossing(embossing);
                box.setFoiling(foiling);
                box.setDieCut(dieCut);
                box.setPasting(pasting);
                box.setPacking(packing);
                box.setDispatch(dispatch);
                box.setChallan(challan);
                box.setBill(bill);

                processes.setJobType("Box");
                processes.setBox(box);

                String wt_id = getIntent().getStringExtra("wt_id");
                String total_number = getIntent().getStringExtra("total_number");

                processes.setWtId(wt_id);
                processes.setTotalForms(null);
                processes.setTotalSets(et_noOfSets.getText().toString());
                processes.setTotalNumber(total_number);

                final String BoxProcess = new GsonBuilder().create().toJson(processes);
                Log.i("Boxp", BoxProcess);
                try {
                    JSONObject boxObj = new JSONObject(BoxProcess);
                    String url = baseUrl + "/processes";
                    Volley.newRequestQueue(Box_Processes.this).add(new JsonObjectRequest(Request.Method.POST, url, boxObj, new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                if (response.getBoolean("success")) {
                                    Toast.makeText(Box_Processes.this, "Success", Toast.LENGTH_SHORT).show();
                                    bt_proceed_box.setVisibility(View.VISIBLE);
                                    finish();
                                    Intent intent = new Intent(Box_Processes.this, HomeActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            bt_proceed_box.setVisibility(View.VISIBLE);
                            Toast.makeText(Box_Processes.this, "Network error", Toast.LENGTH_SHORT).show();
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

