package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class ComplaintApi {
    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {

        @SerializedName("dealer_damage_id")
        @Expose
        public String dealer_damage_id;



        @SerializedName("code_name")
        @Expose
        public String code_name;


        @SerializedName("code")
        @Expose
        public String code;


        @SerializedName("area")
        @Expose
        public String area;



    }

}
