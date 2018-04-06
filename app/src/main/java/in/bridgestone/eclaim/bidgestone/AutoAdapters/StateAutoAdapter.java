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
import in.bridgestone.eclaim.bidgestone.Models.StateModel;
import in.bridgestone.eclaim.bidgestone.R;
import in.bridgestone.eclaim.bidgestone.Utility.MyUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Explicate1 on 3/22/2018.
 */

public class StateAutoAdapter extends ArrayAdapter implements Filterable {
    private ArrayList<StateModel> mInfraList;
    private Context context;

    public StateAutoAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
        mInfraList = new ArrayList<>();
    }



    @Override
    public int getCount() {
        return mInfraList.size();
    }

    @Override
    public StateModel getItem(int position) {
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

                        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<StateApi> call3 = apiInterface.get_states(term);
                        call3.enqueue(new Callback<StateApi>() {
                            @Override
                            public void onResponse(Call<StateApi> call, Response<StateApi> response) {

                                Log.e("State:", new Gson().toJson(response.body()));


                                if (response.body() != null) {


                                    StateApi model = response.body();

                                    if (model.responce == 1) {


                                        if (model.data.size() > 0) {

                                            ArrayList<StateModel> list=new ArrayList<StateModel>();

                                            for (int i = 0; i < model.data.size(); i++) {

                                                StateModel model1 = new StateModel();


                                                model1.setState_name(model.data.get(i).state_name);

                                                list.add(model1);

                                            }

                                            if(!list.isEmpty()){

                                                mInfraList.clear();
                                                mInfraList.addAll(list);
                                                notifyDataSetChanged();
                                            }


                                        }


                                    } else {
                                      //  MyUtility.showAlertMessage(context, MyUtility.FAILED_TO_GET_DATA);
                                    }

                                } else {


                                   // MyUtility.showAlertMessage(context, MyUtility.FAILED_TO_GET_DATA);
                                }


                            }

                            @Override
                            public void onFailure(Call<StateApi> call, Throwable t) {
                                call.cancel();
                              //  MyUtility.showAlertMessage(context, MyUtility.FAILED_TO_GET_DATA);

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
        StateModel contry = mInfraList.get(position);
        TextView countryName = (TextView) view.findViewById(R.id.name);
        countryName.setText(contry.getState_name());

        return view;
    }
}
