package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIClient;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIInterface;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.Dealer_Login;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealerInfoActivity extends AppCompatActivity {
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.name)
    TextView Mname;

    @BindView(R.id.email)
    TextView Memail;

    @BindView(R.id.contact)
    TextView Mcontact;

    @BindView(R.id.address)
    TextView Maddress;

    @BindView(R.id.entity)
    TextView Mentity;


    String entity_number;
    String name, email, contact, address, address1, dealer_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_info);
        ButterKnife.bind(this);
        setUpview();
    }

    private void setUpview() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Dealer Details");
        entity_number= AppClass.getDealerEntity();
        getDealerDetails();

    }

    private void getDealerDetails() {


        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Dealer_Login> call3 = apiInterface.get_dealer_by_entity_no(entity_number);
        call3.enqueue(new Callback<Dealer_Login>() {
            @Override
            public void onResponse(Call<Dealer_Login> call, Response<Dealer_Login> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    Dealer_Login model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {

                                name = model.data.get(i).dealer_name;
                                email = model.data.get(i).email_id;
                                contact = model.data.get(i).dealer_contact_no;
                                address = model.data.get(i).state;
                                address1 = model.data.get(i).city;
                                dealer_id = model.data.get(i).dealer_id;
                                entity_number=model.data.get(i).dealer_entity_no;
                                AppClass.setDealer_d(dealer_id);
                                Log.e("Dealer_id",dealer_id);
                                AppClass.setDealerEntity(entity_number);



                            }

                            Mname.setText(name);
                            Memail.setText(email);
                            Mcontact.setText(contact);
                            Maddress.setText(address1 + "," + address);
                            Mentity.setText(entity_number);


                        }


                    } else {
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<Dealer_Login> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });


    }


    @Optional
    @OnClick(R.id.fab)
    public void submit(View view){
        Intent intent=new Intent(DealerInfoActivity.this,CustomerRegistrationStepOne.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                onBackPressed();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
