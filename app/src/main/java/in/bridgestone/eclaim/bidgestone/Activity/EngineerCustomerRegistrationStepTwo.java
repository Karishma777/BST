package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIClient;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIInterface;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.SaveCustomer;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.Search_cust_by_reg_numberApi;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.CustContactAdapter;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.CustEmailAdapter;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.CustRegNoAdapter;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.StateAutoAdapter;
import in.bridgestone.eclaim.bidgestone.Models.CustomerInfoModel;
import in.bridgestone.eclaim.bidgestone.Models.StateModel;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EngineerCustomerRegistrationStepTwo extends AppCompatActivity {
    @BindView(R.id.next)
    Button button;
    @BindView(R.id.back)
    Button back;


    @BindView(R.id.State)
    AutoCompleteTextView Statespinner;

    @BindView(R.id.registration_number)
    AutoCompleteTextView reg_number;

    @BindView(R.id.Customer_Name)
    EditText cust_name;

    @BindView(R.id.contact_number)
    AutoCompleteTextView contact_number;

    @BindView(R.id.email)
    AutoCompleteTextView email;

    @BindView(R.id.city)
    EditText City;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String cont_number, reg_no, c_name, c_state, c_city, cust_email, Cust_id;

    ArrayList<StateModel> states = new ArrayList<>();
    ArrayList<CustomerInfoModel> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng_cust_registration);
        ButterKnife.bind(this);
        setUpview();

    }

    private void setUpview() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Customer Registration");
        final StateAutoAdapter adapter1 = new StateAutoAdapter(this,android.R.layout.simple_dropdown_item_1line);
        Statespinner.setThreshold(1);
        Statespinner.setAdapter(adapter1);

        Statespinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = adapter1.getItem(position).getState_name();
                    Statespinner.setText(name);



            }
        });



        final CustRegNoAdapter adapter = new CustRegNoAdapter(this,android.R.layout.simple_dropdown_item_1line);
        reg_number.setThreshold(1);
        reg_number.setAdapter(adapter);

        reg_number.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reg_no=reg_number.getText().toString();

                reg_no = adapter.getItem(position).getCustomer_reg_no();
                reg_number.setText(reg_no);
                //getCustDetails();




            }
        });



        final CustContactAdapter custContactAdapter = new CustContactAdapter(this,android.R.layout.simple_dropdown_item_1line);
        contact_number.setThreshold(1);
        contact_number.setAdapter(custContactAdapter);

        contact_number.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cont_number = custContactAdapter.getItem(position).getCustomer_contact_no();
                contact_number.setText(cont_number);

                getCustDetailsBycont_number();


            }
        });



        final CustEmailAdapter custEmailAdapter = new CustEmailAdapter(this,android.R.layout.simple_dropdown_item_1line);
        email.setThreshold(1);
        email.setAdapter(custEmailAdapter);

        email.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cust_email = custEmailAdapter.getItem(position).getCustomer_email_id();
                email.setText(cust_email);
                getCustDetailsByEmail();



            }
        });




    }

    private void getCustDetailsByEmail() {


        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Search_cust_by_reg_numberApi> call3 = apiInterface.get_customer_by_email_id(cust_email);
        call3.enqueue(new Callback<Search_cust_by_reg_numberApi>() {
            @Override
            public void onResponse(Call<Search_cust_by_reg_numberApi> call, Response<Search_cust_by_reg_numberApi> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    Search_cust_by_reg_numberApi model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {
                                c_state = model.data.get(i).customer_state;
                                c_city = model.data.get(i).customer_city;
                                c_name = model.data.get(i).customer_name;
                                cont_number = model.data.get(i).customer_contact_no;
                                cust_email = model.data.get(i).customer_email_id;

                                Cust_id=model.data.get(i).customer_id;
                                reg_no=model.data.get(i).customer_reg_no;
                                AppClass.setSession(Cust_id);
                                AppClass.setCust_reg_number(reg_no);

                            }
                            reg_number.setText(reg_no);
                            City.setText(c_city);
                            cust_name.setText(c_name);
                            Statespinner.setText(c_state);
                            contact_number.setText(cont_number);
                            email.setText(cust_email);





                        }


                    } else {
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<Search_cust_by_reg_numberApi> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });




    }

    private void getCustDetailsBycont_number() {

        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Search_cust_by_reg_numberApi> call3 = apiInterface.get_customer_by_mobile_no(cont_number);
        call3.enqueue(new Callback<Search_cust_by_reg_numberApi>() {
            @Override
            public void onResponse(Call<Search_cust_by_reg_numberApi> call, Response<Search_cust_by_reg_numberApi> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    Search_cust_by_reg_numberApi model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {
                                c_state = model.data.get(i).customer_state;
                                c_city = model.data.get(i).customer_city;
                                c_name = model.data.get(i).customer_name;
                                cont_number = model.data.get(i).customer_contact_no;
                                cust_email = model.data.get(i).customer_email_id;

                                Cust_id=model.data.get(i).customer_id;
                                reg_no=model.data.get(i).customer_reg_no;
                                AppClass.setSession(Cust_id);
                                AppClass.setCust_reg_number(reg_no);

                            }
                            reg_number.setText(reg_no);
                            City.setText(c_city);
                            cust_name.setText(c_name);
                            Statespinner.setText(c_state);
                            contact_number.setText(cont_number);
                            email.setText(cust_email);





                        }


                    } else {
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<Search_cust_by_reg_numberApi> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });




    }


    private void getCustDetails() {
        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Search_cust_by_reg_numberApi> call3 = apiInterface.search_customer_by_reg_no(reg_no);
        call3.enqueue(new Callback<Search_cust_by_reg_numberApi>() {
            @Override
            public void onResponse(Call<Search_cust_by_reg_numberApi> call, Response<Search_cust_by_reg_numberApi> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    Search_cust_by_reg_numberApi model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {
                            Log.e("address", "DisplayAddress");


                            for (int i = 0; i < model.data.size(); i++) {
                                String  registration_number = model.data.get(i).customer_reg_no;
                                c_state = model.data.get(i).customer_state;
                                c_city = model.data.get(i).customer_city;
                                c_name = model.data.get(i).customer_name;
                                cont_number = model.data.get(i).customer_contact_no;
                                cust_email = model.data.get(i).customer_email_id;

                            }
                            City.setText(c_city);
                            cust_name.setText(c_name);
                            Statespinner.setText(c_state);
                            Statespinner.setText(c_state);
                            contact_number.setText(cont_number);
                            email.setText(cust_email);





                        }


                    } else {
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<Search_cust_by_reg_numberApi> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });


    }
    @Optional
    @OnClick(R.id.back)
    public void Back(View view) {
        Intent intent=new Intent(EngineerCustomerRegistrationStepTwo.this,DealerOTPActivity.class);
        startActivity(intent);


    }


    @Optional
    @OnClick(R.id.next)
    public void submit(View view) {
        vadlidation();


    }

    private void vadlidation() {


        cont_number=contact_number.getText().toString();
        c_name=cust_name.getText().toString();
        c_state=Statespinner.getText().toString();
        c_city=City.getText().toString();
        cust_email=email.getText().toString();
        reg_no=reg_number.getText().toString();
        if (cont_number.equalsIgnoreCase("")){

            contact_number.setError("Enter Contact");
            contact_number.requestFocus();


        }else if (cust_email.equalsIgnoreCase("")){

            email.setError("Enter Email");
            email.requestFocus();


        }else if (MyUtility.emailPattern.matches(cust_email)){
            email.setError("Enter Valid Email");
            email.requestFocus();

        } else if (c_name.equalsIgnoreCase("")){
            cust_name.setError("Enter Name");
            cust_name.requestFocus();



        } else if (c_state.equalsIgnoreCase("")){
            Statespinner.setError("Enter State");
            Statespinner.requestFocus();




        }else if (c_city.equalsIgnoreCase("")){
            City.setError("Enter City");
            City.requestFocus();

        }else {
            if (MyUtility.isConnected(this)) {

                callApi();

            } else {

                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.INTERNET_ERROR);
            }
        }



    }



    private void callApi() {


        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<SaveCustomer> call3 = apiInterface.add_customer_details(reg_no, c_name,c_state,c_city,cont_number,cust_email);
        call3.enqueue(new Callback<SaveCustomer>() {
            @Override
            public void onResponse(Call<SaveCustomer> call, Response<SaveCustomer> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    SaveCustomer model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {


                                Cust_id=model.data.get(i).customer_id;
                                reg_no=model.data.get(i).customer_reg_no;
                                AppClass.setSession(Cust_id);
                                AppClass.setCust_reg_number(reg_no);
                                Log.e("Customer_id",Cust_id);

                                Intent intent=new Intent(EngineerCustomerRegistrationStepTwo.this,EngVehicalRegStepThree.class);
                                startActivity(intent);

                            }


                        }


                    } else if (model.responce==0){
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<SaveCustomer> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater1 = getMenuInflater();
        inflater1.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout:

                logout();

                return true;

            case android.R.id.home:

                onBackPressed();


                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are your sure?").setTitle("Sign Out");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


AppClass.cleanSharedPreferences();

                Intent i = new Intent(EngineerCustomerRegistrationStepTwo.this, SplashScreenActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();


            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
