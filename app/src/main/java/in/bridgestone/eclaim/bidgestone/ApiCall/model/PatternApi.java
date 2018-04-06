package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class PatternApi {
    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {

        @SerializedName("pattern_id")
        @Expose
        public String pattern_id;

        @SerializedName("pattern")
        @Expose
        public String pattern;

        @SerializedName("product_code")
        @Expose
        public String product_code;



        @SerializedName("tyre_price")
        @Expose
        public String tyre_price;



    }

}
