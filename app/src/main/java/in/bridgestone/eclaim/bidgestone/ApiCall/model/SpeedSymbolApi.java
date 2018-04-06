package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class SpeedSymbolApi {
    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {
        @SerializedName("speed_symbol_id")
        @Expose
        public String speed_symbol_id;

        @SerializedName("speed_symbol_name")
        @Expose
        public String speed_symbol_name;





    }

}
