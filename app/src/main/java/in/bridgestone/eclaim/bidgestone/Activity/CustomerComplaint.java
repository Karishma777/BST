package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIClient;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIInterface;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.CustomerComplaintApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.SaveVehicalDetailsApi;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.DamageDetailsAdapter;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.TyerTypeAdapter;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerComplaint extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.next)
    Button button;

    @BindView(R.id.back)
    Button back;

    @BindView(R.id.c_complaint)
    EditText c_complaint;

    @BindView(R.id.judgement)
    Spinner judgement;


    @BindView(R.id.damage)
    AutoCompleteTextView damage;

    @BindView(R.id.damage_details)
    EditText damage_details;


    @BindView(R.id.reason)
    EditText reason;

    @BindView(R.id.wear_ration)
    EditText wear_ration;

    @BindView(R.id.Adjustment_ratio)
    EditText Adjustment_ratio;

    @BindView(R.id.referce_cost)
    EditText referce_cost;


    @BindView(R.id.esitimated_cost)
    EditText esitimated_cost;

    @BindView(R.id.tyer_cost)
    EditText tyer_cost;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String Mc_complaint, Mjudgement, Mdamage, Mdamage_details, Mreason, Mwear_ration, MAdjustment_ratio, Mreferce_cost,
            Mesitimated_cost, Mtyer_cost;
    String customer_reg_number, dealer_entity_number, Complaint_id, TyreType;
    String OTD, RTD;
    int ServiceCharge = 0;

    Double res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_complaint);
        ButterKnife.bind(this);
        setUpview();
    }

    private void setUpview() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Customer Complaint");
        dealer_entity_number = AppClass.getDealerEntity();
        customer_reg_number = AppClass.getCust_reg_number();
        OTD = AppClass.getOTD();
        Log.e("OTD", OTD);

        RTD = AppClass.getRTD();
        Log.e("RTD", RTD);


        TyreType = AppClass.getTyreType();

        Log.e("dealer_entity_number", dealer_entity_number);
        Log.e("customer_reg_number", customer_reg_number);

        judgement.setOnItemSelectedListener(this);


        final DamageDetailsAdapter damageDetailsAdapter = new DamageDetailsAdapter(this, android.R.layout.simple_dropdown_item_1line);
        damage.setThreshold(1);
        damage.setAdapter(damageDetailsAdapter);

        damage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Mtyer_type = damageDetailsAdapter.getItem(position).getCode_name();
                damage.setText(Mtyer_type);


            }
        });


        List<String> categories = new ArrayList<String>();
        categories.add("Select Judgement");
        categories.add("Accept");
        categories.add("Reject");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        judgement.setAdapter(dataAdapter);


        WearRatioCaluculation();



        tyer_cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                RefernceCost();
                lossCost();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Adjustment_ratio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                RefernceCost();
                lossCost();



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void lossCost() {

        Mesitimated_cost = esitimated_cost.getText().toString();
        Mtyer_cost = tyer_cost.getText().toString();
        MAdjustment_ratio = Adjustment_ratio.getText().toString();

        Double t_cost = 0.0;
        Double A_ratio = 0.0;
        Double estim_cost;
        if (!Mtyer_cost.equalsIgnoreCase("")) {
            t_cost = Double.parseDouble(Mtyer_cost);
        }

        if (!MAdjustment_ratio.equalsIgnoreCase("")) {
            A_ratio = Double.parseDouble(MAdjustment_ratio);
        }
        estim_cost = (t_cost * (100 - A_ratio)) / 100;
        String Ad_cost = Double.toString(estim_cost);
        esitimated_cost.setText(Ad_cost);


    }

    private void RefernceCost() {
        Mtyer_cost = tyer_cost.getText().toString();
        MAdjustment_ratio = Adjustment_ratio.getText().toString();
        Mreferce_cost = referce_cost.getText().toString();

        Double t_cost = 0.0;
        Double A_ratio = 0.0;
        Double Adjust_ratio;
        if (!Mtyer_cost.equalsIgnoreCase("")) {
            t_cost = Double.parseDouble(Mtyer_cost);
        }

        if (!MAdjustment_ratio.equalsIgnoreCase("")) {
            A_ratio = Double.parseDouble(MAdjustment_ratio);
        }

        if (TyreType.equals("PSR")) {
            ServiceCharge = 100;
        } else if (TyreType.equals("TBR")) {
            ServiceCharge = 150;

        }

        Log.e("ServiceCharge", String.valueOf(ServiceCharge));


        Adjust_ratio = (((t_cost * A_ratio) * 1.28) + ServiceCharge) / 100;
        String MyAR = Double.toString(Adjust_ratio);

        Log.e("MyAR", MyAR);
        referce_cost.setText(MyAR);


    }

    private void WearRatioCaluculation() {

        Mwear_ration = wear_ration.getText().toString();

        Double Motd = 0.0;
        Double mrtd = 0.0;
        if (!OTD.equalsIgnoreCase("")) {
            Motd = Double.parseDouble(OTD);
        }
        if (!RTD.equalsIgnoreCase("")) {
            mrtd = Double.parseDouble(RTD);
        }


        res = ((Motd - mrtd) / (Motd - 1.6));
        Mwear_ration = Double.toString(res);
        Log.e("Mwear_ration", Mwear_ration);
        wear_ration.setText(Mwear_ration);
    }


    @Optional
    @OnClick(R.id.next)
    public void submit(View view) {

        validation();


    }

    private void validation() {
        Mc_complaint = c_complaint.getText().toString();
        Mdamage = damage.getText().toString();
        Mjudgement = judgement.getSelectedItem().toString();

        Mdamage_details = damage_details.getText().toString();
        Mreason = reason.getText().toString();


        if (Mc_complaint.equalsIgnoreCase("")) {

            c_complaint.setError("Enter Complaint");
            c_complaint.requestFocus();


        } else if (Mdamage.equalsIgnoreCase("")) {

            damage.setError("Enter Damage");
            damage.requestFocus();


        } else if (Mdamage_details.equalsIgnoreCase("")) {

            damage_details.setError("Enter Damage Details");
            damage_details.requestFocus();



        } else if (judgement.getSelectedItem().toString().equalsIgnoreCase("Select Judgement")) {

            Toast.makeText(this, "Select Judgement", Toast.LENGTH_SHORT).show();

        } else if (Mreason.equalsIgnoreCase("")) {

            reason.setError("Enter Reason of Acceptance");
            reason.requestFocus();



        } else if (MAdjustment_ratio.equalsIgnoreCase("")) {
            Adjustment_ratio.setError("Enter Adjustment Ratio");
            Adjustment_ratio.requestFocus();



        } else if (Mtyer_cost.equalsIgnoreCase("")) {

            tyer_cost.setError("Enter Tyre Cost");
            tyer_cost.requestFocus();


        } else if (Mreferce_cost.equalsIgnoreCase("")) {
            referce_cost.setError("Enter Reference Of Adjustment Cost");
            referce_cost.requestFocus();


        } else if (Mesitimated_cost.equalsIgnoreCase("")) {

            esitimated_cost.setError("Enter Estimated Root Cost");
            esitimated_cost.requestFocus();


        } else {
            if (MyUtility.isConnected(this)) {
                CallApi();

            } else {
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.INTERNET_ERROR);
            }
        }
    }

    private void CallApi() {


        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CustomerComplaintApi> call3 = apiInterface.add_complaint_details(customer_reg_number, dealer_entity_number,
                Mc_complaint, Mdamage, Mdamage_details, Mjudgement, Mwear_ration,
                MAdjustment_ratio, Mtyer_cost, Mreferce_cost, Mesitimated_cost, Mreason);
        call3.enqueue(new Callback<CustomerComplaintApi>() {
            @Override
            public void onResponse(Call<CustomerComplaintApi> call, Response<CustomerComplaintApi> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    CustomerComplaintApi model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {
                                Complaint_id = model.data.get(i).complaint_id;
                                Log.e("Complaint_id ", Complaint_id);

                                Intent intent = new Intent(CustomerComplaint.this, PhotosActivity.class);
                                startActivity(intent);

                            }
                            AppClass.setComplaintId(Complaint_id);


                        }


                    } else if (model.responce == 0) {
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<CustomerComplaintApi> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });


    }

    @Optional
    @OnClick(R.id.back)
    public void Back(View view) {
        Intent intent = new Intent(CustomerComplaint.this, CustomerRegStepTwo.class);
        startActivity(intent);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();


                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
