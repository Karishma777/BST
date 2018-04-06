package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
import in.bridgestone.eclaim.bidgestone.ApiCall.model.Engineer_LoginApi;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EngineerInfoActivity extends AppCompatActivity {
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


    String employee_id;
    String name, email, contact, address, address1, engineer_id;


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
        getSupportActionBar().setTitle("Engineer Details");
        employee_id= AppClass.getEmployeeId()
        ;
        getDealerDetails();

    }

    private void getDealerDetails() {


        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Engineer_LoginApi> call3 = apiInterface.get_engineer_info_by_employee_id(employee_id);
        call3.enqueue(new Callback<Engineer_LoginApi>() {
            @Override
            public void onResponse(Call<Engineer_LoginApi> call, Response<Engineer_LoginApi> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    Engineer_LoginApi model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {

                                name = model.data.get(i).employee_name;
                                email = model.data.get(i).engineer_email_id;
                                contact = model.data.get(i).engineer_mobile_no;
                                address = model.data.get(i).place_of_posting;
                                engineer_id = model.data.get(i).engineer_id;
                                employee_id = model.data.get(i).employee_id;
                                AppClass.setEmployeeId(employee_id);
                                AppClass.setEngineerId(engineer_id);
                                Log.e("employee_id",employee_id);


                            }

                            Mname.setText(name);
                            Memail.setText(email);
                            Mcontact.setText(contact);
                            Maddress.setText(address);
                            Mentity.setText(employee_id);


                        }


                    } else {
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<Engineer_LoginApi> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });


    }


    @Optional
    @OnClick(R.id.fab)
    public void submit(View view){
        Intent intent=new Intent(EngineerInfoActivity.this,DealerDetailsStepOne.class);
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
