package com.samarthgupta.sfa_app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.samarthgupta.sfa_app.DataInterface;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.JobTicket;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Bill;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Binding;
import com.samarthgupta.sfa_app.POJO.WT_Processes.CentrePin;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Challan;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Cover;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Creasing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Designing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Dispatch;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Ferro;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Finishing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Folding;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Gathering;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Lamination;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Packing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Perfect;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Plates;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Printing;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Processes;
import com.samarthgupta.sfa_app.POJO.WT_Processes.Sewing;
import com.samarthgupta.sfa_app.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;
import static com.samarthgupta.sfa_app.POJO.GlobalAccess.jobTicket;

public class Cover_Processes extends AppCompatActivity {

    private CheckBox check_design,check_ferro, check_plates, check_printing, check_lamination, check_creasing, check_binding,check_packing, check_dispatch, check_challan, check_bill ;
    private EditText et_noOfSets ;

    private Button bt_proceed_cover ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover__processes);
        addListenerToCheckBox() ;
    }

    private void addListenerToCheckBox() {
        check_design = (CheckBox)findViewById(R.id.checkBox_design) ;
        check_ferro = (CheckBox)findViewById(R.id.checkBox_ferro) ;
        check_plates = (CheckBox)findViewById(R.id.checkBox_plates) ;
        check_printing = (CheckBox)findViewById(R.id.checkBox_printing);
        check_lamination = (CheckBox)findViewById(R.id.checkBox_lamination) ;
        check_creasing = (CheckBox)findViewById(R.id.checkBox_creasing) ;
        check_binding = (CheckBox)findViewById(R.id.checkBox_binding) ;
        check_packing= (CheckBox)findViewById(R.id.checkBox_packing) ;
        check_dispatch = (CheckBox)findViewById(R.id.checkBox_dispatch) ;
        check_challan = (CheckBox)findViewById(R.id.checkBox_challan) ;
        check_bill = (CheckBox)findViewById(R.id.checkBox_bill) ;

        et_noOfSets = (EditText)findViewById(R.id.et_noOfsets) ;
        et_noOfSets.setVisibility(View.GONE);

        bt_proceed_cover = (Button)findViewById(R.id.bt_proceed_cover) ;


        final Processes processes = new Processes();

        final Designing designing = new Designing() ;
        final Ferro ferro = new Ferro() ;
        final Plates plates = new Plates();
        final Printing printing = new Printing() ;
        final Lamination lamination = new Lamination() ;
        final Creasing creasing = new Creasing() ;
        final Binding binding = new Binding();
        final Packing packing = new Packing() ;
        final Dispatch dispatch = new Dispatch() ;
        final Challan challan = new Challan() ;
        final Bill bill = new Bill() ;


check_design.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    Toast.makeText(Cover_Processes.this, "DESIGN selected", Toast.LENGTH_SHORT).show();
                    designing.setIsRequired(true);
                }else if ( !((CheckBox) v).isChecked()){
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
                        if(((CheckBox)v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "FERRO selected", Toast.LENGTH_SHORT).show();
                            ferro.setIsRequired(true);
                        }else if ( !((CheckBox) v).isChecked()){
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
                        if(((CheckBox)v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "PLATES selected", Toast.LENGTH_SHORT).show();
                            plates.setIsRequired(true);
                        }else if ( !((CheckBox) v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "PLATES deselected", Toast.LENGTH_SHORT).show();
                            plates.setIsRequired(false);
                        }
                    }
                }
        );

        check_printing.setOnClickListener(
                new  View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if (((CheckBox)v).isChecked()){
                            et_noOfSets.setVisibility(View.VISIBLE);
                            Toast.makeText(Cover_Processes.this, "PRINTING selected", Toast.LENGTH_SHORT).show();
                            printing.setIsRequired(true);
                            processes.setTotalSets(et_noOfSets.getText().toString());
                        }
                        else if ( !((CheckBox) v).isChecked()){
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
                        if(((CheckBox)v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "LAMINATION selected", Toast.LENGTH_SHORT).show();
                            lamination.setIsRequired(true);
                        }else if ( !((CheckBox) v).isChecked()){
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
                        if(((CheckBox)v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "CREASING selected", Toast.LENGTH_SHORT).show();
                            creasing.setIsRequired(true);
                        }else if ( !((CheckBox) v).isChecked()){
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
                        if(((CheckBox)v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "BINDING selected", Toast.LENGTH_SHORT).show();
                            binding.setIsRequired(true);
                        }else if ( !((CheckBox) v).isChecked()){
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
                        if(((CheckBox)v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "PACKING selected", Toast.LENGTH_SHORT).show();
                            packing.setIsRequired(true);
                        }else if ( !((CheckBox) v).isChecked()){
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
                        if(((CheckBox)v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "DISPATCH selected", Toast.LENGTH_SHORT).show();
                            dispatch.setIsRequired(true);
                        }else if ( !((CheckBox) v).isChecked()){
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
                        if(((CheckBox)v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "CHALLAN selected", Toast.LENGTH_SHORT).show();
                            challan.setIsRequired(true);
                        }else if ( !((CheckBox) v).isChecked()){
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
                        if(((CheckBox)v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "BILL selected", Toast.LENGTH_SHORT).show();
                            bill.setIsRequired(true);
                        }else if ( !((CheckBox) v).isChecked()){
                            Toast.makeText(Cover_Processes.this, "BILL deselected", Toast.LENGTH_SHORT).show();
                            bill.setIsRequired(false);
                        }
                    }
                }
        );

        bt_proceed_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Cover cover = new Cover() ;
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

                Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
                DataInterface client = retrofit.create(DataInterface.class);
                Call<Processes> call = client.postTicket(processes);
                call.enqueue(new Callback<Processes>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(Call<Processes> call, Response<Processes> response) {
                        Toast.makeText(Cover_Processes.this, "Job ticket created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Cover_Processes.this,HomeActivity.class );
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Processes> call, Throwable t) {
                        Toast.makeText(Cover_Processes.this,"Error",Toast.LENGTH_LONG);
                    }
                });
            }
        });


    }
}