package in.bridgestone.eclaim.bidgestone.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.bridgestone.eclaim.bidgestone.Activity.CustomerRegStepTwo;
import in.bridgestone.eclaim.bidgestone.Activity.CustomerRegistrationStepOne;
import in.bridgestone.eclaim.bidgestone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {



    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);
        return  view;
    }



}
