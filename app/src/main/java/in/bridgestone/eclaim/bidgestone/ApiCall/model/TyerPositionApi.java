package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class TyerPositionApi {
    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {
        @SerializedName("tyre_position_id")
        @Expose
        public String tyre_position_id;

        @SerializedName("tyre_position_name")
        @Expose
        public String tyre_position_name;


        @SerializedName("dot_serial_id")
        @Expose
        public String dot_serial_id;



        @SerializedName("dot_serial_name")
        @Expose
        public String dot_serial_name;




    }

}
