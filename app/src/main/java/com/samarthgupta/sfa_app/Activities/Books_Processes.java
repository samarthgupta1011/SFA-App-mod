package com.samarthgupta.sfa_app.Activities;

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
import com.samarthgupta.sfa_app.POJO.WT_Processes.Bill;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Book;
import com.samarthgupta.sfa_app.POJO.WT_Processes.CentrePin;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Challan;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Designing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Dispatch;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Ferro;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Finishing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Folding;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Gathering;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Packing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Perfect;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Plates;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Printing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Sewing;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;

public class Books_Processes extends AppCompatActivity {
    private CheckBox check_printing, check_folding, check_design, check_ferro, check_plates, check_gathering, check_sewing, check_perfect, check_cpin, check_finishing, check_packing, check_dispatch, check_challan, check_bill;
    private EditText et_noOfSets, et_noOfForms;
    Button bt_proceed_Books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books__processes);
        addListenerToCheckBox();
    }

    private void addListenerToCheckBox() {


        check_design = (CheckBox) findViewById(R.id.checkBox_design);
        check_ferro = (CheckBox) findViewById(R.id.checkBox_ferro);
        check_plates = (CheckBox) findViewById(R.id.checkBox_plates);
        check_printing = (CheckBox) findViewById(R.id.checkBox_printing);
        check_folding = (CheckBox) findViewById(R.id.checkBox_folding);
        check_gathering = (CheckBox) findViewById(R.id.checkBox_gathering);
        check_sewing = (CheckBox) findViewById(R.id.checkBox_sewing);
        check_perfect = (CheckBox) findViewById(R.id.checkBox_perfect);
        check_cpin = (CheckBox) findViewById(R.id.checkBox_cpin);
        check_finishing = (CheckBox) findViewById(R.id.checkBox_finishing);
        check_packing = (CheckBox) findViewById(R.id.checkBox_packing);
        check_dispatch = (CheckBox) findViewById(R.id.checkBox_dispatch);
        check_challan = (CheckBox) findViewById(R.id.checkBox_challan);
        check_bill = (CheckBox) findViewById(R.id.checkBox_bill);

        et_noOfSets = (EditText) findViewById(R.id.et_noOfsets); //on checking printing activity. for entering no. of sets
        et_noOfForms = (EditText) findViewById(R.id.et_noOfForms); //on checking folding activity. for entering no. of forms


        et_noOfSets.setVisibility(View.GONE); // initially don't want to show
        et_noOfForms.setVisibility(View.GONE);  // initially don't want to show

        bt_proceed_Books = (Button) findViewById(R.id.bt_proceed);

        final Processes processes = new Processes();

        //making new Objects of each processes.
        final Designing designing = new Designing();
        final Ferro ferro = new Ferro();
        final Plates plates = new Plates();
        final Printing printing = new Printing();
        final Gathering gathering = new Gathering();
        final Folding folding = new Folding();
        final Sewing sewing = new Sewing();
        final Perfect perfect = new Perfect();
        final CentrePin centrePin = new CentrePin();
        final Finishing finishing = new Finishing();
        final Packing packing = new Packing();
        final Dispatch dispatch = new Dispatch();
        final Challan challan = new Challan();
        final Bill bill = new Bill();


        check_design.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "DESIGN selected", Toast.LENGTH_SHORT).show();

                            designing.setIsRequired(true);


                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "DESIGN deselected", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Books_Processes.this, "FERRO selected", Toast.LENGTH_SHORT).show();
                            ferro.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "FERRO deselected", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Books_Processes.this, "PLATES selected", Toast.LENGTH_SHORT).show();
                            plates.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "PLATES deselected", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Books_Processes.this, "PRINTING selected", Toast.LENGTH_SHORT).show();
                            printing.setIsRequired(true);
                            processes.setTotalSets(et_noOfSets.getText().toString()); //what if printing is checked and noOfSets not Entered?
                        } else if (!((CheckBox) v).isChecked()) {
                            et_noOfSets.setVisibility(View.GONE);
                            Toast.makeText(Books_Processes.this, "PRINTING deselected", Toast.LENGTH_SHORT).show();
                            printing.setIsRequired(false);
                            processes.setTotalSets(null);
                        }
                    }
                }
        );

        check_gathering.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "GATHERING selected", Toast.LENGTH_SHORT).show();
                            gathering.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "GATHERING deselected", Toast.LENGTH_SHORT).show();
                            gathering.setIsRequired(false);
                        }
                    }
                }
        );

        check_folding.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            et_noOfForms.setVisibility(View.VISIBLE);
                            Toast.makeText(Books_Processes.this, "FOLDING selected", Toast.LENGTH_SHORT).show();
                            folding.setIsRequired(true);
                            processes.setTotalForms(et_noOfForms.getText().toString());//what is folding checked and noOfForms not entered?
                        } else if (!((CheckBox) v).isChecked()) {
                            et_noOfForms.setVisibility(View.GONE);
                            Toast.makeText(Books_Processes.this, "FOLDING deselected", Toast.LENGTH_SHORT).show();
                            folding.setIsRequired(false);
                            processes.setTotalForms(null);
                        }
                    }
                }
        );

        check_sewing.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "SEWING selected", Toast.LENGTH_SHORT).show();
                            sewing.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "SEWING deselected", Toast.LENGTH_SHORT).show();
                            sewing.setIsRequired(false);
                        }
                    }
                }
        );
        check_perfect.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "PERFECT selected", Toast.LENGTH_SHORT).show();
                            perfect.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "PERFECT deselected", Toast.LENGTH_SHORT).show();
                            perfect.setIsRequired(false);
                        }
                    }
                }
        );

        check_cpin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "C. PIN selected", Toast.LENGTH_SHORT).show();
                            centrePin.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "C. PIN deselected", Toast.LENGTH_SHORT).show();
                            centrePin.setIsRequired(false);
                        }
                    }
                }
        );

        check_finishing.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "FINISHING selected", Toast.LENGTH_SHORT).show();
                            finishing.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "FINISHING deselected", Toast.LENGTH_SHORT).show();
                            finishing.setIsRequired(false);
                        }
                    }
                }
        );

        check_packing.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "PACKING selected", Toast.LENGTH_SHORT).show();
                            packing.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "PACKING deselected", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Books_Processes.this, "DISPATCH selected", Toast.LENGTH_SHORT).show();
                            dispatch.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "DISPATCH deselected", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Books_Processes.this, "CHALLAN selected", Toast.LENGTH_SHORT).show();
                            challan.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "CHALLAN deselected", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Books_Processes.this, "BILL selected", Toast.LENGTH_SHORT).show();
                            bill.setIsRequired(true);
                        } else if (!((CheckBox) v).isChecked()) {
                            Toast.makeText(Books_Processes.this, "BILL deselected", Toast.LENGTH_SHORT).show();
                            bill.setIsRequired(false);
                        }
                    }
                }
        );

        bt_proceed_Books.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                bt_proceed_Books.setVisibility(View.INVISIBLE);


                final Book book = new Book();
                book.setDesigning(designing);
                book.setFerro(ferro);
                book.setPlates(plates);
                book.setPrinting(printing);
                if (printing.equals(true) && et_noOfSets.getText().toString()==null ){
                    Log.i("error", et_noOfSets.getText().toString()) ;
                    bt_proceed_Books.setVisibility(View.VISIBLE);
                    AlertDialog dialog = new AlertDialog.Builder(Books_Processes.this).setTitle("Error").setMessage("Enter Number of Sets").setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create();
                    dialog.show();

                }
                book.setFolding(folding);
                book.setGathering(gathering);
                book.setSewing(sewing);
                book.setPerfect(perfect);
                book.setCentrePin(centrePin);
                book.setFinishing(finishing);
                book.setPacking(packing);
                book.setDispatch(dispatch);
                book.setChallan(challan);
                book.setBill(bill);

                processes.setJobType("Book");
                processes.setBook(book);

                String wt_id = getIntent().getExtras().getString("wt_id") ;
                String total_number = getIntent().getStringExtra("total_number");

                processes.setWtId(wt_id);
                processes.setTotalForms(et_noOfForms.getText().toString());
                processes.setTotalSets(et_noOfSets.getText().toString());
                processes.setTotalNumber(total_number);

                final String bookProcess = new GsonBuilder().create().toJson(processes);
                Log.d("POST", bookProcess);
                try {


                    JSONObject bookObj = new JSONObject(bookProcess);

                    String url = baseUrl + "/processes";
                    Volley.newRequestQueue(Books_Processes.this).add(new JsonObjectRequest(Request.Method.POST,
                            url, bookObj, new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                if(response.getBoolean("success")){
                                    Toast.makeText(Books_Processes.this, "Success", Toast.LENGTH_SHORT).show();
                                    bt_proceed_Books.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(Books_Processes.this,HomeActivity.class) ;
                                    startActivity(intent);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                        }
                    }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            bt_proceed_Books.setVisibility(View.VISIBLE);
                            Toast.makeText(Books_Processes.this, "Network error", Toast.LENGTH_SHORT).show();
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