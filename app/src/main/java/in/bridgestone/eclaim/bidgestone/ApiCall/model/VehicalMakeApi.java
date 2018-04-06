package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class VehicalMakeApi {
    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {

        @SerializedName("make_and_model_id")
        @Expose
        public String make_and_model_id;


        @SerializedName("model_name")
        @Expose
        public String model_name;

        @SerializedName("make_name")
        @Expose
        public String make_name;


    }

}
