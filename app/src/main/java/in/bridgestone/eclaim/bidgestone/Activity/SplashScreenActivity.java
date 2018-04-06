package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.R;

public class SplashScreenActivity extends AppCompatActivity {
    @BindView(R.id.imgsplash)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        Glide.with(this)
                .load(R.drawable.adhesiv)
                .into(imageView);
        goTomain();



    }

    private void goTomain() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                if (AppClass.getDealer_d().length()>0) {

                    Intent loginIntent = new Intent(SplashScreenActivity.this, CustomerRegistrationStepOne.class);
                    startActivity(loginIntent);
                    finish();

                }else if (AppClass.getEmployeeId().length()>0){
                    Intent loginIntent = new Intent(SplashScreenActivity.this, EngineerCustomerRegistrationStepTwo.class);
                    startActivity(loginIntent);
                    finish();

                }else {
                    Intent loginIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }


            }
        }, 3000);

    }
}
