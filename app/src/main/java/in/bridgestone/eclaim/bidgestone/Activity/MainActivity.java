package in.bridgestone.eclaim.bidgestone.Activity;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIClient;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIInterface;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.Dealer_Login;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.DealerLoginAutoAdapter;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import in.bridgestone.eclaim.bidgestone.Utility.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.dealer_login)
    EditText actv;

    @BindView(R.id.name)
    TextView Mname;

    @BindView(R.id.email)
    TextView Memail;

    @BindView(R.id.contact)
    TextView Mcontact;

    @BindView(R.id.address)
    TextView Maddress;

    @BindView(R.id.cust_login)
    RelativeLayout cust_login;

    String entity_number;
    String dealer_id,otp;
    String back = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpView();


    }

    private void setUpView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Dealer Login");


    /*    final DealerLoginAutoAdapter adapter1 = new DealerLoginAutoAdapter(this, android.R.layout.simple_dropdown_item_1line);
        actv.setThreshold(1);
        actv.setAdapter(adapter1);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                entity_number = adapter1.getItem(position).getDealer_entity_no();

                actv.setText(entity_number);




            }
        });
*/

    }




    @Optional
    @OnClick(R.id.fab)
    public void submit(View view) {

        validation();


    }

    private void validation() {
        entity_number = actv.getText().toString();
        Log.e("entity_number",entity_number);

        if (entity_number.equalsIgnoreCase("")) {
            Toast.makeText(MainActivity.this, "Enter Dealer Entity Number ", Toast.LENGTH_SHORT).show();

        } else {
            CallAPi();


        }
    }

    private void CallAPi() {


        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Dealer_Login> call3 = apiInterface.send_otp_to_dealer(entity_number);
        call3.enqueue(new Callback<Dealer_Login>() {
            @Override
            public void onResponse(Call<Dealer_Login> call, Response<Dealer_Login> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    Dealer_Login model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {


                                otp=model.data.get(i).otp;
                                dealer_id = model.data.get(i).dealer_id;
                                entity_number=model.data.get(i).dealer_entity_no;
                                AppClass.setDealer_d(dealer_id);
                                Log.e("Dealer_id",dealer_id);
                                AppClass.setDealerEntity(entity_number);
                                AppClass.setOTP(otp);


                            }

                            Intent intent = new Intent(MainActivity.this, DealerOTPActivity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Your OTP request send on mail successfully", Toast.LENGTH_SHORT).show();




                        }


                    } else if (model.responce==0){
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

                    Intent i = new Intent(MainActivity.this, SplashScreenActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();


            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }



    @Override
    protected void onResume() {
        super.onResume();
    }


    public void onDestroy() {
        super.onDestroy();
    }

}
