package in.bridgestone.eclaim.bidgestone.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIClient;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIInterface;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.PDFAPi;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import in.bridgestone.eclaim.bidgestone.Utility.URLListner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PDFActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.webview)
    WebView webView;

    String e_claim_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        ButterKnife.bind(this);
        setUpview();

    }

    private void setUpview() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("E_claim PDF");
        e_claim_id= AppClass.getEclaimId();
        webView.getSettings().setJavaScriptEnabled(true);

        GeneratePGD();
    }

    private void GeneratePGD() {


        if (!MyUtility.isConnected(this)) {

            MyUtility.showAlertInternet(this);

            return;
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PDFAPi> call3 = apiInterface.pdf(e_claim_id);
        call3.enqueue(new Callback<PDFAPi>() {
            @Override
            public void onResponse(Call<PDFAPi> call, Response<PDFAPi> response) {

                Log.e("Data:", new Gson().toJson(response.body()));


                if (response.body() != null) {


                    PDFAPi model = response.body();

                    if (model.responce == 1) {


                        String pdfLink=model.filename;
                        Log.e("pdfLink",pdfLink);

                        if (pdfLink.equals("")){
                            MyUtility.showAlertMessage(getApplicationContext(), "No PDF Available");

                        }else {
                            pdfLink = pdfLink.replace("..", "");

                            String PDF = URLListner.PDF_BASEURLL + pdfLink;

                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(PDF));
                            startActivity(i);


                        }



                    } else if (model.responce == 0) {
                        MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                    }

                } else {


                    MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);
                }


            }

            @Override
            public void onFailure(Call<PDFAPi> call, Throwable t) {
                call.cancel();
                MyUtility.showAlertMessage(getApplicationContext(), MyUtility.FAILED_TO_GET_DATA);

            }
        });


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
