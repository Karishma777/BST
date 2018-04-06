package in.bridgestone.eclaim.bidgestone.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIClient;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIInterface;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.AddEclaimApi;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import in.bridgestone.eclaim.bidgestone.Utility.Utility;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EngineerPhotosActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.back)
    Button back;

    @BindView(R.id.next)
    Button next;

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.image1)
    ImageView imageView1;

    @BindView(R.id.image2)
    ImageView imageView2;

    @BindView(R.id.image3)
    ImageView imageView3;

    @BindView(R.id.image4)
    ImageView imageView4;

    @BindView(R.id.image5)
    ImageView imageView5;

    @BindView(R.id.image6)
    ImageView imageView6;

    @BindView(R.id.image7)
    ImageView imageView7;

    List<File> addImages;

    int selectedImage = 0;

    private Uri fileUri;

    private static final int REQUEST_CAMERA = 100;

    private static final int SELECT_FILE = 200;

    String e_claim_id;
    String cust_id, vehical_id, complaint_id,dealer_id,engineer_id;
    private File file1, file2, file3, file4, file5, file6, file7, file8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engin_photos);
        ButterKnife.bind(this);
        addImages = new ArrayList<>();
        setUpview();


    }

    private void setUpview() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Photos");
        cust_id = AppClass.getUserId();
        vehical_id = AppClass.getVehicalId();
        complaint_id = AppClass.getComplaintId();
        dealer_id = AppClass.getDealer_d();
        engineer_id = AppClass.getEngineerId();


        Log.e("c_id", cust_id);
        Log.e("vehical_id", vehical_id);
        Log.e("complint", complaint_id);
        Log.e("dealer_id", dealer_id);
        Addeclaim();
    }

    private void Addeclaim() {


        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AddEclaimApi> call3 = apiInterface.add_e_claim_for_engineer(cust_id,dealer_id,complaint_id,vehical_id,engineer_id);
        call3.enqueue(new Callback<AddEclaimApi>() {
            @Override
            public void onResponse(Call<AddEclaimApi> call, Response<AddEclaimApi> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    AddEclaimApi model = response.body();

                    if (model.responce == 1) {


                        if (model.data.size() > 0) {


                            for (int i = 0; i < model.data.size(); i++) {
                                e_claim_id = model.data.get(i).e_claim_id;
                                AppClass.setEclaimId(e_claim_id);


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
            public void onFailure(Call<AddEclaimApi> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });


    }


    @Optional
    @OnClick(R.id.image)
    public void FirstImage(View view) {
        // TODO submit data to server...
        selectImage(1);

    }

    @Optional
    @OnClick(R.id.image1)
    public void SecondImage(View view) {
        // TODO submit data to server...
        selectImage(2);

    }

    @Optional
    @OnClick(R.id.image2)
    public void ThirdImage(View view) {
        // TODO submit data to server...
        selectImage(3);

    }

    @Optional
    @OnClick(R.id.image3)
    public void FourthImage(View view) {
        // TODO submit data to server...
        selectImage(4);

    }

    @Optional
    @OnClick(R.id.image4)
    public void FifthImage(View view) {
        // TODO submit data to server...
        selectImage(5);

    }


    @Optional
    @OnClick(R.id.image5)
    public void sixthImage(View view) {
        // TODO submit data to server...
        selectImage(6);

    }

    @Optional
    @OnClick(R.id.image6)
    public void seventhImage(View view) {
        // TODO submit data to server...
        selectImage(7);

    }

    @Optional
    @OnClick(R.id.image7)
    public void eightImage(View view) {
        // TODO submit data to server...
        selectImage(8);

    }


    @Optional
    @OnClick(R.id.next)
    public void submit(View view) {
        callApi();


    }

    private void callApi() {


        if (!MyUtility.isConnected(this)) {
            MyUtility.showAlertInternet(this);
            return;
        }

        if (addImages.size()<4){
            MyUtility.showAlertMessage(getApplicationContext(),"Enter Minimum Four Images of tyre");
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Submitting....");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();


        MultipartBody.Part filePart2 = null;
        MultipartBody.Part filePart3 = null;
        MultipartBody.Part filePart1 = null;
        MultipartBody.Part filePart4 = null;
        MultipartBody.Part filePart5 = null;
        MultipartBody.Part filePart6 = null;
        MultipartBody.Part filePart7 = null;
        MultipartBody.Part filePart8 = null;


        if (file1 != null) {

            filePart1 = MultipartBody.Part.createFormData("damage_from_outside", file1.getName(), RequestBody.create(MediaType.parse("image*//*"), file1));
        }

        if (file2 != null) {

            filePart2 = MultipartBody.Part.createFormData("damage_from_inside", file2.getName(), RequestBody.create(MediaType.parse("image*//*"), file2));

        }

        if (file3 != null) {

            filePart3 = MultipartBody.Part.createFormData("close_from_outside", file3.getName(), RequestBody.create(MediaType.parse("image*//*"), file3));
        }


        if (file4 != null) {

            filePart4 = MultipartBody.Part.createFormData("close_from_inside", file4.getName(), RequestBody.create(MediaType.parse("image*//*"), file4));
        }


        if (file5 != null) {

            filePart5 = MultipartBody.Part.createFormData("additional1", file5.getName(), RequestBody.create(MediaType.parse("image*//*"), file5));
        }


        if (file6 != null) {

            filePart6 = MultipartBody.Part.createFormData("additional2", file6.getName(), RequestBody.create(MediaType.parse("image*//*"), file6));
        }


        if (file7 != null) {

            filePart7 = MultipartBody.Part.createFormData("additional3", file7.getName(), RequestBody.create(MediaType.parse("image*//*"), file7));
        }


        if (file8 != null) {

            filePart8 = MultipartBody.Part.createFormData("additional4", file8.getName(), RequestBody.create(MediaType.parse("image*//*"), file8));
        }


        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<JsonObject> call = apiInterface.participant_media(
                toRequestBody(e_claim_id),
                filePart1,
                filePart2,
                filePart3,
                filePart4,
                filePart5,
                filePart6,
                filePart7,
                filePart8);


        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                 Log.e("profile:", new Gson().toJson(response.body()));

                if (dialog.isShowing()) {

                    dialog.dismiss();

                }


                switch (response.code()) {

                    case 200:


                        JsonObject jsonObject = response.body();


                        if (jsonObject != null) {

                            JsonArray array = jsonObject.getAsJsonArray("data");

                            if (array.size() != 0) {

                                Toast.makeText(EngineerPhotosActivity.this, "Claim Successfully Added", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent loginIntent = new Intent(EngineerPhotosActivity.this, PDFActivity.class);
                                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(loginIntent);


                            } else {

                                MyUtility.showAlertMessage(getApplicationContext(), "Data Not Updated");

                            }


                        } else {

                              MyUtility.showAlertMessage(EngineerPhotosActivity.this, MyUtility.FAILED_TO_GET_DATA);
                        }


                        break;


                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                call.cancel();
                if (dialog.isShowing()) {

                    dialog.dismiss();

                }
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });


    }


    public static RequestBody toRequestBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }


    //................OPEN SELECT IMAGE ALERT BOX...........
    private void selectImage(final int number) {

        final CharSequence[] items = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog_custom_title, null);
        TextView title = (TextView) view.findViewById(R.id.alertTitle);
        title.setText("Add Photo!");
        builder.setCustomTitle(view);

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                boolean result = Utility.checkPermission(EngineerPhotosActivity.this);

                if (items[item].equals("Take Photo")) {

                    if (result)
                        cameraIntent(number);

                } else if (items[item].equals("Choose from Gallery")) {

                    if (result)
                        galleryIntent(number);

                } else if (items[item].equals("Cancel")) {

                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }


    //.................TAKE IMAGE FROM CAMERA..............
    private void cameraIntent(int i) {

        selectedImage = i;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = Utility.getOutputMediaFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        //intent.setType("image*//*");

        startActivityForResult(intent, REQUEST_CAMERA);
    }


    //.................CHOOSE IMAGE FROM GALLERY..............
    private void galleryIntent(int i) {
        selectedImage = i;
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Log.e("aa", "Result");
        if (resultCode == RESULT_OK) {

            Log.e("aa1", "rr");

            if (requestCode == REQUEST_CAMERA) {
                Log.e("3", "execute");


                //  Uri selectedImageUri = data.getData();
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), fileUri);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // External sdcard location
                File mediaStorageDir = new File(
                        Environment
                                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                        getString(R.string.app_name)); //Create folder fitkitchen in SD-Card

                // Create the storage directory if it does not exist
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                    }
                }

                String title = String.valueOf(System.currentTimeMillis());
                File destination = new File(mediaStorageDir.getPath(),
                        title + ".jpg");
                FileOutputStream fo;
                try {


                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();

                    // addImages.add(destination);


                    switch (selectedImage) {

                        case 1:
                            Glide.with(this)
                                    .load(destination)
                                    .fitCenter()
                                    .centerCrop()
                                    .into(imageView);
                            file1 = destination;
                            addImages.add(file1);

                            break;

                        case 2:
                            Glide.with(this)
                                    .load(destination)
                                    .fitCenter()
                                    .centerCrop()
                                    .into(imageView1);

                            file2 = destination;
                            addImages.add(file2);


                            break;

                        case 3:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView2);

                            file3 = destination;
                            addImages.add(file3);


                            break;
                        case 4:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView3);

                            file4 = destination;
                            addImages.add(file4);


                            break;
                        case 5:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView4);

                            file5 = destination;
                            addImages.add(file5);


                            break;
                        case 6:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView5);

                            file6 = destination;
                            addImages.add(file6);
                            break;



                        case 7:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView6);

                            file7 = destination;
                            addImages.add(file7);
                            break;


                        case 8:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView7);

                            file8 = destination;
                            addImages.add(file8);


                            break;

                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if (requestCode == SELECT_FILE) {


                Uri selectedImageUri = data.getData();
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();


                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), selectedImageUri);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // External sdcard location
                File mediaStorageDir = new File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                        getString(R.string.app_name));//Create folder fitkitchen in SD-Card

                // Create the storage directory if it does not exist
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                    }
                }

                String title = String.valueOf(System.currentTimeMillis());
                File destination = new File(mediaStorageDir.getPath(),
                        title + ".jpg");

                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();

                    //addImages.add(destination);

                    switch (selectedImage) {
                        case 1:
                            Glide.with(this)
                                    .load(destination)
                                    .fitCenter()
                                    .centerCrop()
                                    .into(imageView);
                            file1 = destination;
                            addImages.add(file1);


                            break;

                        case 2:
                            Glide.with(this)
                                    .load(destination)
                                    .fitCenter()
                                    .centerCrop()
                                    .into(imageView1);

                            file2 = destination;
                            addImages.add(file2);


                            break;

                        case 3:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView2);

                            file3 = destination;
                            addImages.add(file3);


                            break;
                        case 4:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView3);

                            file4 = destination;
                            addImages.add(file4);


                            break;
                        case 5:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView4);

                            file5 = destination;
                            addImages.add(file5);


                            break;
                        case 6:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView5);

                            file6 = destination;
                            addImages.add(file6);
                            break;


                        case 7:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView6);

                            file7 = destination;
                            addImages.add(file7);
                            break;


                        case 8:
                            Glide.with(this)
                                    .load(destination)
                                    .centerCrop()
                                    .into(imageView7);

                            file8 = destination;
                            addImages.add(file8);


                            break;
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        }
    }

    @Optional
    @OnClick(R.id.back)
    public void Back(View view) {
        Intent intent=new Intent(EngineerPhotosActivity.this,CustomerComplaint.class);
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
}
