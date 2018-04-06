package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import in.bridgestone.eclaim.bidgestone.R;

public class LoginActivity extends AppCompatActivity {
   @BindView(R.id.dealer_login)
    TextView dealer_login;

    @BindView(R.id.engineer_login)
    TextView engineer_login;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }
    @Optional
    @OnClick(R.id.dealer_login)
    public void submit(View view){
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);

    }
    @Optional
    @OnClick(R.id.engineer_login)
    public void submitengin(View view){
        Intent intent=new Intent(LoginActivity.this,EngineerLogin.class);
        startActivity(intent);

    }


    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
