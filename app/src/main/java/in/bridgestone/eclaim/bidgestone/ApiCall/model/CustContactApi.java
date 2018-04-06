package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/23/2018.
 */

public class CustContactApi {
    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {

        @SerializedName("customer_id")
        @Expose
        public String customer_id;

        @SerializedName("customer_reg_no")
        @Expose
        public String customer_reg_no;

        @SerializedName("customer_contact_no")
        @Expose
        public String customer_contact_no;
    }
}

