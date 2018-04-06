package in.bridgestone.eclaim.bidgestone.AutoAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import in.bridgestone.eclaim.bidgestone.ApiCall.APIClient;
import in.bridgestone.eclaim.bidgestone.ApiCall.APIInterface;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.StateApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.VehicalDetailsApi;
import in.bridgestone.eclaim.bidgestone.Application.AppClass;
import in.bridgestone.eclaim.bidgestone.Models.StateModel;
import in.bridgestone.eclaim.bidgestone.Models.VehicalDetails;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Explicate1 on 3/22/2018.
 */

public class VehicleRegNoAdapter extends ArrayAdapter implements Filterable {
    private ArrayList<VehicalDetails> mInfraList;
    private Context context;

    public VehicleRegNoAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
        mInfraList = new ArrayList<>();
    }



    @Override
    public int getCount() {
        return mInfraList.size();
    }

    @Override
    public VehicalDetails getItem(int position) {
        return mInfraList.get(position);
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint != null){
                    try{
                        //get data from the web
                        // if(!mInfraList.isEmpty()){
                        //    mInfraList.clear();
                        // }
                        String term = constraint.toString();
                        String cust_id= AppClass.getCust_reg_number();

                        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<VehicalDetailsApi> call3 = apiInterface.search_and_get_vehicle_by_reg_no(term,cust_id);
                        call3.enqueue(new Callback<VehicalDetailsApi>() {
                            @Override
                            public void onResponse(Call<VehicalDetailsApi> call, Response<VehicalDetailsApi> response) {

                                Log.e("Data:", new Gson().toJson(response.body()));


                                if (response.body() != null) {


                                    VehicalDetailsApi model = response.body();

                                    if (model.responce == 1) {


                                        if (model.data.size() > 0) {

                                            ArrayList<VehicalDetails> list=new ArrayList<VehicalDetails>();

                                            for (int i = 0; i < model.data.size(); i++) {

                                                VehicalDetails model1 = new VehicalDetails();


                                                model1.setVehicle_reg_no(model.data.get(i).vehicle_reg_no);

                                                list.add(model1);

                                            }

                                            if(!list.isEmpty()){

                                                mInfraList.clear();
                                                mInfraList.addAll(list);
                                                notifyDataSetChanged();
                                            }


                                        }


                                    } else {
                                       // MyUtility.showAlertMessage(context, MyUtility.FAILED_TO_GET_DATA);
                                    }

                                } else {


                                   // MyUtility.showAlertMessage(context, MyUtility.FAILED_TO_GET_DATA);
                                }


                            }

                            @Override
                            public void onFailure(Call<VehicalDetailsApi> call, Throwable t) {
                                call.cancel();
                               // MyUtility.showAlertMessage(context, MyUtility.FAILED_TO_GET_DATA);

                            }
                        });


                        filterResults.values = mInfraList;
                        filterResults.count = mInfraList.size();


                    }catch (Exception e){
                        Log.d("HUS","EXCEPTION "+e);
                    }




                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results != null && results.count > 0){
                    notifyDataSetChanged();
                }else{
                    notifyDataSetInvalidated();
                }
            }
        };

        return myFilter;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.auto_complete_layout,parent,false);

        //get Country
        VehicalDetails contry = mInfraList.get(position);
        TextView countryName = (TextView) view.findViewById(R.id.name);
        countryName.setText(contry.getVehicle_reg_no());

        return view;
    }
}
