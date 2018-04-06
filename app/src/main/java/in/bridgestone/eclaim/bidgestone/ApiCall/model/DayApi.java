package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class DayApi {
    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {

        @SerializedName("day_id")
        @Expose
        public String day_id;



        @SerializedName("day_name")
        @Expose
        public String day_name;




    }

}
