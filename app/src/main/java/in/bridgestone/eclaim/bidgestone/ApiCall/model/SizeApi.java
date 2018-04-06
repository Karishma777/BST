package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class SizeApi {
    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {

        @SerializedName("tyre_size_id")
        @Expose
        public String tyre_size_id;



        @SerializedName("tyre_size_name")
        @Expose
        public String tyre_size_name;


        @SerializedName("pattern")
        @Expose
        public String pattern;

        @SerializedName("nsdc")
        @Expose
        public String nsdc;

    }

}
