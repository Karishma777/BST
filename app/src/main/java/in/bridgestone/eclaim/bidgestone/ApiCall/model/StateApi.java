package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class StateApi {

    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {
        @SerializedName("state_id")
        @Expose
        public String state_id;

        @SerializedName("country_name")
        @Expose
        public String country_name;

        @SerializedName("state_name")
        @Expose
        public String state_name;



    }


}
