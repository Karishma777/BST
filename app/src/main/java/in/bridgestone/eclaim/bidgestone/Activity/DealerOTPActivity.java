package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.R;

public class DealerOTPActivity extends AppCompatActivity {
    @BindView(R.id.next)
    Button button;

    @BindView(R.id.otp)
    EditText otp;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String OTP,Motp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        setUpview();
    }

    private void setUpview() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("OTP Verification");

        OTP= AppClass.getOTP();
        otp.setText(OTP);
    }


    @Optional
    @OnClick(R.id.next)
    public void submit(View view){
        Motp=otp.getText().toString();
        if (Motp.equals(OTP)){

            Intent intent=new Intent(DealerOTPActivity.this,DealerInfoActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(DealerOTPActivity.this, "Wrong OTP", Toast.LENGTH_SHORT).show();



        }



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
}
