package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.EngineerDealerSearchAutoAdapter;
import in.bridgestone.eclaim.bidgestone.R;

public class DealerDetailsStepOne extends AppCompatActivity {
    @BindView(R.id.next)
    Button next;

    @BindView(R.id.back)
    Button back;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.dealer_login)
    AutoCompleteTextView actv;


    @BindView(R.id.name)
    TextView Mname;

    @BindView(R.id.email)
    TextView Memail;

    @BindView(R.id.contact)
    TextView Mcontact;

    @BindView(R.id.address)
    TextView Maddress;

    @BindView(R.id.dealer_info)
    RelativeLayout relativeLayout;


    String entity_number;
    String name, email, contact, address, address1, dealer_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealer_info);
        ButterKnife.bind(this);
        setUpview();

    }

    private void setUpview() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Dealer Details");

        final EngineerDealerSearchAutoAdapter adapter1 = new EngineerDealerSearchAutoAdapter(this, android.R.layout.simple_dropdown_item_1line);
        actv.setThreshold(1);
        actv.setAdapter(adapter1);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                entity_number = adapter1.getItem(position).getDealer_entity_no();
                name = adapter1.getItem(position).getDealer_name();
                email = adapter1.getItem(position).getEmail_id();



                contact = adapter1.getItem(position).getDealer_contact_no();
                address = adapter1.getItem(position).getState();
                address1 = adapter1.getItem(position).getCity();
                dealer_id=adapter1.getItem(position).getDealer_id();

                actv.setText(entity_number);


                relativeLayout.setVisibility(View.VISIBLE);

                Mname.setText(name);
                Memail.setText(email);
                Mcontact.setText(contact);
                Maddress.setText(address1 +","+address);





            }
        });


    }


    @Optional
    @OnClick(R.id.next)
    public void submit(View view){
        AppClass.setDealerEntity(entity_number);
        AppClass.setDealer_d(dealer_id);
        Intent intent=new Intent(DealerDetailsStepOne.this,EngineerCustomerRegistrationStepTwo.class);
        startActivity(intent);
    }

    @Optional
    @OnClick(R.id.back)
    public void back(View view){
        Intent intent=new Intent(DealerDetailsStepOne.this,EngneerOTPActivity.class);
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
