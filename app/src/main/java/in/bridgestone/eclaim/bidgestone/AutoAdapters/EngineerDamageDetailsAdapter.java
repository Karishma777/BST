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
import in.bridgestone.eclaim.bidgestone.ApiCall.model.ComplaintApi;
import in.bridgestone.eclaim.bidgestone.Models.ComplaintModel;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Explicate1 on 3/22/2018.
 */

public class EngineerDamageDetailsAdapter extends ArrayAdapter implements Filterable {
    private ArrayList<ComplaintModel> mInfraList;
    private Context context;

    public EngineerDamageDetailsAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
        mInfraList = new ArrayList<>();
    }



    @Override
    public int getCount() {
        return mInfraList.size();
    }

    @Override
    public ComplaintModel getItem(int position) {
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
                        String term = constraint.toString();

                        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<ComplaintApi> call3 = apiInterface.get_damage_details_for_engineer(term);
                        call3.enqueue(new Callback<ComplaintApi>() {
                            @Override
                            public void onResponse(Call<ComplaintApi> call, Response<ComplaintApi> response) {

                                Log.e("Data:", new Gson().toJson(response.body()));


                                if (response.body() != null) {


                                    ComplaintApi model = response.body();

                                    if (model.responce == 1) {


                                        if (model.data.size() > 0) {

                                            ArrayList<ComplaintModel> list=new ArrayList<ComplaintModel>();

                                            for (int i = 0; i < model.data.size(); i++) {

                                                ComplaintModel model1 = new ComplaintModel();


                                                model1.setCode_name(model.data.get(i).code_name);

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
                            public void onFailure(Call<ComplaintApi> call, Throwable t) {
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
        ComplaintModel contry = mInfraList.get(position);
        TextView countryName = (TextView) view.findViewById(R.id.name);
        countryName.setText(contry.getCode_name());

        return view;
    }
}
