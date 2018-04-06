package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class CustomerComplaintApi {
    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {

        @SerializedName("complaint_id")
        @Expose
        public String complaint_id;



        @SerializedName("customer_reg_no")
        @Expose
        public String customer_reg_no;


        @SerializedName("dealer_entity_no")
        @Expose
        public String dealer_entity_no;


        @SerializedName("customer_complaint")
        @Expose
        public String customer_complaint;


        @SerializedName("damage")
        @Expose
        public String damage;

        @SerializedName("damage_details")
        @Expose
        public String damage_details;

        @SerializedName("judgement")
        @Expose
        public String judgement;

        @SerializedName("wear_ration")
        @Expose
        public String wear_ration;


        @SerializedName("adjustment_ratio")
        @Expose
        public String adjustment_ratio;


        @SerializedName("tyre_cost")
        @Expose
        public String tyre_cost;

        @SerializedName("ref_adjustment_cost")
        @Expose
        public String ref_adjustment_cost;

        @SerializedName("root_cause")
        @Expose
        public String root_cause;



    }

}
