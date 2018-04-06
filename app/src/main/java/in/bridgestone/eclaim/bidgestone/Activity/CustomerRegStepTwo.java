package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import in.bridgestone.eclaim.bidgestone.ApiCall.APIClient;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIInterface;

import in.bridgestone.eclaim.bidgestone.ApiCall.model.OTDAPi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.SaveVehicalDetailsApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.VehicalDetailsApi;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.DotSerial;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.PatternAdapter;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.SizeAdapter;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.VehicalMakeAdapter;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.VehicalModelNameAdapter;
import in.bridgestone.eclaim.bidgestone.AutoAdapters.VehicleRegNoAdapter;

import in.bridgestone.eclaim.bidgestone.Models.VehicalDetails;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRegStepTwo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.next)
    Button button;

    @BindView(R.id.tyer_type)
    Spinner tyer_type;

    @BindView(R.id.v_reg_no)
    AutoCompleteTextView v_reg_no;

    @BindView(R.id.vehical_make)
    AutoCompleteTextView vehical_make;

    @BindView(R.id.tyer_brand)
    Spinner tyer_brand;

    @BindView(R.id.oe_rep)
    Spinner OE_REP;

    @BindView(R.id.day)
    Spinner Day;

    @BindView(R.id.month)
    Spinner month;

    @BindView(R.id.year)
    Spinner year_a;

    @BindView(R.id.speed_symbol)
    Spinner Speed;

    @BindView(R.id.dot_serial)
    AutoCompleteTextView dotSerial;

    @BindView(R.id.pattern)
    AutoCompleteTextView Pattern;

    @BindView(R.id.tyer_postion)
    Spinner tyerPostn;


    @BindView(R.id.Axile)
    Spinner Axile;

    @BindView(R.id.inout)
    Spinner inout;


    @BindView(R.id.model_name)
    AutoCompleteTextView model_name;

    @BindView(R.id.tyer_size)
    AutoCompleteTextView tyer_size;

    @BindView(R.id.recyclerview)
    ListView recyclerview;

    @BindView(R.id.otd)
    EditText OTD;

    @BindView(R.id.g1)
    EditText G1;

    @BindView(R.id.g2)
    EditText G2;

    @BindView(R.id.g3)
    EditText G3;

    @BindView(R.id.g4)
    EditText G4;

    @BindView(R.id.g5)
    EditText G5;

    @BindView(R.id.tube)
    EditText Tube;

    @BindView(R.id.odd)
    EditText ood;

    @BindView(R.id.odm)
    EditText odm;

    @BindView(R.id.type_running_serial)
    EditText type_running_serial;

    @BindView(R.id.radioposition)
    RadioGroup radioGroup;

    @BindView(R.id.tyer_postion_details)
    LinearLayout tyer_postion_details;


    String vehical_reg_no, Mtyer_type, Mtyer_brand, MOE_REP, MDay, Mmonth, Myear_a,
            MSpeed, MdotSerial, MPattern, MtyerPostn, Mvehical_make, Mmodel_name, Mtyer_size,
            Mood, Modm, MOTD, MG1, MG2, MG3, MG4, MG5, MTube, MAxile, Minout,MruniingPosition,
            Mtype_running_serial, Mvehical_id, V_reg_number, Product_code;


    public static int res = 0;
    String listStr;
    String cust_id, dealer_entity_no;
    RadioButton selectedType;


    private ArrayList<VehicalDetails> arrayList;
    private ListAdapter filmListAdapter;
    private ArrayList<String> billNamesArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reg_step_two);
        ButterKnife.bind(this);

        setUpview();
    }


    private void setUpview() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Vehicle Details");

        cust_id = AppClass.getUserId();

        dealer_entity_no = AppClass.getDealerEntity();

        Log.e("cust_id", cust_id);

        Log.e("dealer entity", dealer_entity_no);

        OE_REP.setOnItemSelectedListener(this);

        Day.setOnItemSelectedListener(this);

        month.setOnItemSelectedListener(this);

        year_a.setOnItemSelectedListener(this);

        tyerPostn.setOnItemSelectedListener(this);

        tyer_brand.setOnItemSelectedListener(this);

        Axile.setOnItemSelectedListener(this);

        tyer_type.setOnItemSelectedListener(this);

        inout.setOnItemSelectedListener(this);
        Speed.setOnItemSelectedListener(this);


        arrayList = new ArrayList<VehicalDetails>();
        billNamesArray = new ArrayList<String>();


        ArrayAdapter<String> stringArrayAdapter8 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.In_out));
        inout.setAdapter(stringArrayAdapter8);


        ArrayAdapter<String> stringArrayAdapter7 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Axile));
        Axile.setAdapter(stringArrayAdapter7);


        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.OE_REP));
        OE_REP.setAdapter(spinnerCountShoesArrayAdapter);


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Days));
        Day.setAdapter(stringArrayAdapter);


        ArrayAdapter<String> stringArrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Month));
        month.setAdapter(stringArrayAdapter1);

        ArrayAdapter<String> stringArrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Year));
        year_a.setAdapter(stringArrayAdapter2);

        ArrayAdapter<String> stringArrayAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Tyer_Position));
        tyerPostn.setAdapter(stringArrayAdapter3);

        ArrayAdapter<String> stringArrayAdapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.BRAND));
        tyer_brand.setAdapter(stringArrayAdapter4);

        ArrayAdapter<String> stringArrayAdapter5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.TYRE_TYPE));
        tyer_type.setAdapter(stringArrayAdapter5);


        ArrayAdapter<String> stringArrayAdapter6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Speed_Symbol));
        Speed.setAdapter(stringArrayAdapter6);


        final VehicleRegNoAdapter vehicleRegNoAdapter = new VehicleRegNoAdapter(this, android.R.layout.simple_dropdown_item_1line);
        v_reg_no.setThreshold(1);
        v_reg_no.setAdapter(vehicleRegNoAdapter);

        v_reg_no.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                vehical_reg_no = vehicleRegNoAdapter.getItem(position).getVehicle_reg_no();
                v_reg_no.setText(vehical_reg_no);
                getVehicalDetails();


            }
        });


        final VehicalModelNameAdapter vehicalModelNameAdapter = new VehicalModelNameAdapter(this, android.R.layout.simple_dropdown_item_1line);
        model_name.setThreshold(1);
        model_name.setAdapter(vehicalModelNameAdapter);

        model_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Mmodel_name = vehicalModelNameAdapter.getItem(position).getModel_name();

                model_name.setText(Mmodel_name);


            }
        });


        final VehicalMakeAdapter vehicalMakeAdapter = new VehicalMakeAdapter(this, android.R.layout.simple_dropdown_item_1line);
        vehical_make.setThreshold(1);
        vehical_make.setAdapter(vehicalMakeAdapter);

        vehical_make.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Mvehical_make = vehicalMakeAdapter.getItem(position).getMake_name();
                vehicalModelNameAdapter.setSelectdProjectId(Mvehical_make);

                vehical_make.setText(Mvehical_make);
                Log.e("Mvehical_make", Mvehical_make);


            }
        });



        final PatternAdapter patternAdapter = new PatternAdapter(this, android.R.layout.simple_dropdown_item_1line);
        Pattern.setThreshold(1);
        Pattern.setAdapter(patternAdapter);

        Pattern.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MPattern = patternAdapter.getItem(position).getPattern_name();

                AppClass.setPattern(MPattern);

                Product_code=patternAdapter.getItem(position).getProduct_code();
                Log.e("Product_code",Product_code);
                Pattern.setText(MPattern);
                getOTD();


            }
        });


        final SizeAdapter sizeAdapter = new SizeAdapter(this, android.R.layout.simple_dropdown_item_1line);
        tyer_size.setThreshold(1);
        tyer_size.setAdapter(sizeAdapter);

        tyer_size.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Mtyer_size = sizeAdapter.getItem(position).getTyre_size_name();

                patternAdapter.setSelectdProjectId(Mtyer_size);
                tyer_size.setText(Mtyer_size);
                AppClass.setSize(Mtyer_size);
                Log.e("Mtyer_size", Mtyer_size);


            }
        });


        final in.bridgestone.eclaim.bidgestone.AutoAdapters.DotSerial ds = new DotSerial(this, android.R.layout.simple_dropdown_item_1line);
        dotSerial.setThreshold(1);
        dotSerial.setAdapter(ds);

        dotSerial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MdotSerial = ds.getItem(position).getDot_serial_name();

                dotSerial.setText(MdotSerial);


            }
        });

        G1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateRTD();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        G2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateRTD();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        G3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateRTD();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        G4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateRTD();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                   // Toast.makeText(CustomerRegStepTwo.this, rb.getText(), Toast.LENGTH_SHORT).show();

                }
                if (rb.getText().equals("Spare")){
                    MtyerPostn=rb.getText().toString();
                    tyer_postion_details.setVisibility(View.GONE);

                }else
                if (rb.getText().equals("Running")){
                    MtyerPostn=rb.getText().toString();
                    tyer_postion_details.setVisibility(View.VISIBLE);

                }



            }
        });



    }

    private void calculateRTD() {


        int Gone = 0;
        int Gtwo = 0;
        int Gthree = 0;
        int Gfour = 0;
        MG1 = G1.getText().toString();

        MG2 = G2.getText().toString();

        MG3 = G3.getText().toString();

        MG4 = G4.getText().toString();

        MG5 = G5.getText().toString();


        if (!MG1.equalsIgnoreCase("")) {
            Gone = Integer.parseInt(MG1);

        }


        if (!MG2.equalsIgnoreCase("")) {
            Gtwo = Integer.parseInt(MG2);

        }

        if (!MG3.equalsIgnoreCase("")) {
            Gthree = Integer.parseInt(MG3);

        }

        if (!MG4.equalsIgnoreCase("")) {
            Gfour = Integer.parseInt(MG4);

        }


        res = (Gone + Gtwo + Gthree + Gfour) / 4;
        String result = Integer.toString(res);
        G5.setText(result);
        AppClass.setRTD(result);


    }

    private void getOTD() {


        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<OTDAPi> call3 = apiInterface.get_otd(Product_code);
        call3.enqueue(new Callback<OTDAPi>() {
            @Override
            public void onResponse(Call<OTDAPi> call, Response<OTDAPi> response) {

                Log.e("OTD:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    OTDAPi model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {

                                String otd = model.data.get(i).otd;

                                Log.e("otd", otd);

                                OTD.setText(otd);

                                AppClass.setOTD(otd);

                            }


                        }


                    } else {
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<OTDAPi> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.INTERNET_ERROR);

            }
        });


    }


    private void getVehicalDetails() {

        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<VehicalDetailsApi> call3 = apiInterface.search_vehicle_by_reg_no(vehical_reg_no);
        call3.enqueue(new Callback<VehicalDetailsApi>() {
            @Override
            public void onResponse(Call<VehicalDetailsApi> call, Response<VehicalDetailsApi> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    VehicalDetailsApi model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {


                                Mtyer_type = model.data.get(i).tyre_type;

                                Mtyer_brand = model.data.get(i).tyre_brand;

                                MOE_REP = model.data.get(i).oe_rep;

                                MDay = model.data.get(i).date_of_tyre_sale_day;

                                Mmonth = model.data.get(i).date_of_tyre_sale_month;

                                Myear_a = model.data.get(i).date_of_tyre_sale_year;

                                MSpeed = model.data.get(i).speed_symbol;

                                MdotSerial = model.data.get(i).dot_serial;

                                MPattern = model.data.get(i).pattern;

                                MtyerPostn = model.data.get(i).tyre_position;

                                Mvehical_make = model.data.get(i).vehicle_make;

                                Mmodel_name = model.data.get(i).model_name;

                                Mtyer_size = model.data.get(i).tyre_size;

                                Mood = model.data.get(i).odometer_reading_damaged;

                                Modm = model.data.get(i).odometer_reading_mounted;

                                MOTD = model.data.get(i).otd;

                                MG5 = model.data.get(i).rtd;

                                MTube = model.data.get(i).mc_tube_flap_serial;

                                Mtype_running_serial = model.data.get(i).tyre_running_serial;


                            }

                            Pattern.setText(MPattern);

                           dotSerial.setText(MdotSerial);

                           vehical_make.setText(Mvehical_make);

                            model_name.setText(Mmodel_name);

                            tyer_size.setText(Mtyer_size);

                            ood.setText(Modm);

                            odm.setText(Mood);

                            type_running_serial.setText(Mtype_running_serial);

                            Tube.setText(MTube);

                            G5.setText(MG5);

                            OTD.setText(MOTD);


                        }


                    } else {
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<VehicalDetailsApi> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });


    }


    @Optional
    @OnClick(R.id.next)
    public void submit(View view) {
        VehicalValidation();


    }


    private void VehicalValidation() {

        V_reg_number = v_reg_no.getText().toString();
        Log.e("V_reg_number",V_reg_number);

        Mood = ood.getText().toString();
        Log.e("Mood",Mood);


        Modm = odm.getText().toString();
        Log.e("Modm",Modm);

        Mvehical_make = vehical_make.getText().toString();
        Log.e("Mvehical_make",Mvehical_make);


        Mmodel_name = model_name.getText().toString();
        Log.e("Mmodel_name",Mmodel_name);


        MTube = Tube.getText().toString();
        Log.e("MTube",MTube);


        Mtype_running_serial = type_running_serial.getText().toString();

        Log.e("Mtype_running_serial",Mtype_running_serial);


        if (V_reg_number.equalsIgnoreCase("")) {

            v_reg_no.setError("Enter Registration Number");
            v_reg_no.requestFocus();


        } else if (Mood.equalsIgnoreCase("")) {

            ood.setError("Enter Odometer (KM) Reading (Damaged)");
            ood.requestFocus();


        } else if (Modm.equalsIgnoreCase("")) {
            odm.setError("Enter  Odometer (KM) Reading (Mounted)");
            odm.requestFocus();


        } else if (Mvehical_make.equalsIgnoreCase("")) {
            vehical_make.setError("Enter Vehical Make");
            vehical_make.requestFocus();


        } else if (Mmodel_name.equalsIgnoreCase("")) {
            model_name.setError("Enter Vehical Model Name");
            model_name.requestFocus();


        } else if (tyer_type.getSelectedItem().toString().equalsIgnoreCase("Select Tyre Type")) {


            Toast.makeText(this, "Select Tyer Type", Toast.LENGTH_SHORT).show();

        } else if (OE_REP.getSelectedItem().toString().equalsIgnoreCase("Select OE/REP")) {

            Toast.makeText(this, "Select OE/REP", Toast.LENGTH_SHORT).show();

        } else if (Day.getSelectedItem().toString().equalsIgnoreCase("Days")) {

            Toast.makeText(this, "Select Day", Toast.LENGTH_SHORT).show();

        } else if (month.getSelectedItem().toString().equalsIgnoreCase("Month")) {

            Toast.makeText(this, "Select Month", Toast.LENGTH_SHORT).show();

        } else if (year_a.getSelectedItem().toString().equalsIgnoreCase("Year")) {

            Toast.makeText(this, "Select Year", Toast.LENGTH_SHORT).show();

        } else if (tyer_brand.getSelectedItem().toString().equalsIgnoreCase("Select Tyer Brand")) {

            Toast.makeText(this, "Select Tyre Brand", Toast.LENGTH_SHORT).show();

        } else if (Mtyer_size.equalsIgnoreCase("")) {

            tyer_size.setError("Enter Tyre size");
            tyer_size.requestFocus();


        } else if (MPattern.equalsIgnoreCase("")) {
            Pattern.setError("Enter Tyre Pattern");
            Pattern.requestFocus();


        } else if (Speed.getSelectedItem().toString().equalsIgnoreCase("Select Speed Symbol")) {

            Toast.makeText(this, "Select Tyre Speed Symbol", Toast.LENGTH_SHORT).show();

        } else if (MdotSerial.equalsIgnoreCase("")) {
            dotSerial.setError("Enter Tyre Dot Serial");
            dotSerial.requestFocus();


        } else if (MOTD.equalsIgnoreCase("")) {


            OTD.setError("Enter Tyre OTD");
            OTD.requestFocus();


        } else if (Mtype_running_serial.equalsIgnoreCase("")) {


            type_running_serial.setError("Enter  Tyre running serial");
            type_running_serial.requestFocus();


        } else if (MTube.equalsIgnoreCase("")) {


            Tube.setError("Enter Tube Data");
            Tube.requestFocus();


        } else {
            if (MyUtility.isConnected(this)) {

                adddDataSecond();


            } else {

                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.INTERNET_ERROR);
            }
        }


    }

    private void adddDataSecond() {

        VehicalDetails model = new VehicalDetails();
        model.setVehicle_reg_no(vehical_reg_no);
        model.setOdometer_reading_damaged(Mood);
        model.setOdometer_reading_mounted(Modm);
        model.setVehicle_make(Mvehical_make);
        model.setModel_name(Mmodel_name);
        model.setTyre_type(Mtyer_type);
        model.setOe_rep(MOE_REP);
        model.setDate_of_tyre_sale_day(MDay);
        model.setDate_of_tyre_sale_month(Mmonth);
        model.setDate_of_tyre_sale_year(Myear_a);
        model.setTyre_brand(Mtyer_brand);
        model.setTyre_size(Mtyer_size);
        model.setPattern(MPattern);
        model.setSpeed_symbol(MSpeed);
        model.setDot_serial(MdotSerial);
        model.setOTD(MOTD);
        model.setRTD(String.valueOf(res));
        model.setType_running_serial(Mtype_running_serial);
        model.setTyre_position(MtyerPostn);
        model.setAxile(MAxile);
        model.setInout(Minout);
        model.setTube(MTube);
        model.setRunning_tyre_position(MruniingPosition);
        model.setDealer_entity_no(dealer_entity_no);
        arrayList.add(model);


        billNamesArray.add(model.getVehicle_reg_no());
        billNamesArray.add(model.getOdometer_reading_damaged());
        billNamesArray.add(model.getOdometer_reading_mounted());
        billNamesArray.add(model.getVehicle_make());
        billNamesArray.add(model.getModel_name());
        billNamesArray.add(model.getTyre_type());
        billNamesArray.add(model.getOe_rep());
        billNamesArray.add(model.getDate_of_tyre_sale_day());
        billNamesArray.add(model.getDate_of_tyre_sale_month());
        billNamesArray.add(model.getDate_of_tyre_sale_year());
        billNamesArray.add(model.getTyre_brand());
        billNamesArray.add(model.getTyre_size());
        billNamesArray.add(model.getPattern());
        billNamesArray.add(model.getSpeed_symbol());
        billNamesArray.add(model.getDot_serial());
        billNamesArray.add(model.getOTD());
        billNamesArray.add(model.getRTD());
        billNamesArray.add(model.getType_running_serial());
        billNamesArray.add(model.getTyre_position());
        billNamesArray.add(model.getTube());
        billNamesArray.add(model.getAxile());
        billNamesArray.add(model.getInout());
        billNamesArray.add(model.getRunning_tyre_position());
        billNamesArray.add(model.getDealer_entity_no());

        filmListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, billNamesArray);

        recyclerview.setAdapter(filmListAdapter);

        Type listType = new TypeToken<List<VehicalDetails>>() {
        }.getType();

        listStr = new Gson().toJson(arrayList, listType);

        Log.e("JSON:", listStr);

        CallAPi();

    }



    private void CallAPi() {


        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<SaveVehicalDetailsApi> call3 = apiInterface.add_vehicle_details(listStr, cust_id);
        call3.enqueue(new Callback<SaveVehicalDetailsApi>() {
            @Override
            public void onResponse(Call<SaveVehicalDetailsApi> call, Response<SaveVehicalDetailsApi> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    SaveVehicalDetailsApi model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {

                                Mvehical_id = model.data.get(i).vehicle_id;
                                V_reg_number = model.data.get(i).vehicle_reg_no;
                                Mtyer_type = model.data.get(i).tyre_type;
                                AppClass.setVehicalId(Mvehical_id);
                                AppClass.setTyreType(Mtyer_type);

                                Intent intent = new Intent(CustomerRegStepTwo.this, CustomerComplaint.class);
                                startActivity(intent);

                            }


                        }


                    } else if (model.responce == 0) {
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<SaveVehicalDetailsApi> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });


    }

    @Optional
    @OnClick(R.id.back)
    public void Back(View view) {
        Intent intent = new Intent(CustomerRegStepTwo.this, CustomerRegistrationStepOne.class);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {


            case R.id.tyer_type:

                Mtyer_type = tyer_type.getSelectedItem().toString();
                AppClass.setTyreType(Mtyer_type);
                Log.e("Mtyer_type", Mtyer_type);


                break;

            case R.id.oe_rep:


                MOE_REP = OE_REP.getSelectedItem().toString();
                Log.e("MOE_REP", MOE_REP);


                break;

            case R.id.day:

                MDay = Day.getSelectedItem().toString();
                Log.e("MDay", MDay);


                break;


            case R.id.month:

                Mmonth = month.getSelectedItem().toString();
                Log.e("Mmonth", Mmonth);


                break;

            case R.id.year:

                Myear_a = year_a.getSelectedItem().toString();
                Log.e("Myear_a", Myear_a);


                break;

            case R.id.tyer_brand:

                Mtyer_brand = tyer_brand.getSelectedItem().toString();
                Log.e("Mtyer_brand", Mtyer_brand);


                break;


            case R.id.speed_symbol:

                MSpeed = Speed.getSelectedItem().toString();
                Log.e("MSpeed", MSpeed);

                break;


            case R.id.tyer_postion:

                MruniingPosition = tyerPostn.getSelectedItem().toString();
                Log.e("MruniingPosition", MruniingPosition);



                break;

            case R.id.Axile:

                MAxile = Axile.getSelectedItem().toString();

                Log.e("MAxile", MAxile);


                break;

            case R.id.inout:

                Minout = inout.getSelectedItem().toString();
                Log.e("Minout", Minout);


                break;
            default:
                break;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
