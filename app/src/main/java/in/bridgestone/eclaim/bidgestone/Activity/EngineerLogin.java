package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

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

public class EngineerLogin extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.engg_login)
    EditText engg_login;

    String entity_number, otp, engineer_id, employee_id;
    String back = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer_login2);
        ButterKnife.bind(this);
        setUpview();
    }

    private void setUpview() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Engineer Login");
        entity_number = engg_login.getText().toString();


    }


    @Optional
    @OnClick(R.id.fab)
    public void submit(View view) {
        if (entity_number.equalsIgnoreCase("")) {
            Toast.makeText(EngineerLogin.this, "Enter Engineer Employee Id ", Toast.LENGTH_SHORT).show();

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
        Call<Engineer_LoginApi> call3 = apiInterface.get_engineer_info_by_employee_id(entity_number);
        call3.enqueue(new Callback<Engineer_LoginApi>() {
            @Override
            public void onResponse(Call<Engineer_LoginApi> call, Response<Engineer_LoginApi> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    Engineer_LoginApi model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {


                                otp = model.data.get(i).otp;
                                engineer_id = model.data.get(i).engineer_id;
                                employee_id = model.data.get(i).employee_id;
                                AppClass.setEmployeeId(employee_id);
                                AppClass.setEngineerId(engineer_id);
                                Log.e("employee_id", employee_id);
                                AppClass.setOTP(otp);


                            }

                            Intent intent = new Intent(EngineerLogin.this, EngneerOTPActivity.class);
                            startActivity(intent);
                            Toast.makeText(EngineerLogin.this, "Your OTP request send on mail successfully", Toast.LENGTH_SHORT).show();


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
                Intent i = new Intent(EngineerLogin.this, SplashScreenActivity.class);
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
    public void onBackPressed() {


        int fragments = getFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            // make layout invisible since last fragment will be removed

            if (back.equals("")) {

                Toast.makeText(this, "Press back button again to exit", Toast.LENGTH_LONG).show();
                back = "two";

            } else if (back.equals("two")) {

                finish();
            }


        }


        super.onBackPressed();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    public void onDestroy() {
        super.onDestroy();
    }


}
