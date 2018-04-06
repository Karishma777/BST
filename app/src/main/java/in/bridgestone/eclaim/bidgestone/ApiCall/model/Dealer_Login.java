package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/1/2018.
 */

public class Dealer_Login {

    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("success")
    @Expose
    public String success;


    @SerializedName("data")
    @Expose
    public ArrayList<DealerData> data;

    public class DealerData{

        @SerializedName("dealer_id")
        @Expose
        public String dealer_id;

        @SerializedName("partners_name")
        @Expose
        public String partners_name;

        @SerializedName("branch")
        @Expose
        public String branch;


        @SerializedName("region")
        @Expose
        public String region;

        @SerializedName("dealer_entity_no")
        @Expose
        public String dealer_entity_no;

        @SerializedName("category")
        @Expose
        public String category;

        @SerializedName("address1")
        @Expose
        public String address1;

        @SerializedName("address2")
        @Expose
        public String address2;

        @SerializedName("dealer_name")
        @Expose
        public String dealer_name;

        @SerializedName("dealer_contact_person")
        @Expose
        public String dealer_contact_person;

        @SerializedName("dealer_contact_no")
        @Expose
        public String dealer_contact_no;

        @SerializedName("dealer_phone_no")
        @Expose
        public String dealer_phone_no;

        @SerializedName("email_id")
        @Expose
        public String email_id;

        @SerializedName("fax_no")
        @Expose
        public String fax_no;

        @SerializedName("state")
        @Expose
        public String state;

        @SerializedName("city")
        @Expose
        public String city;

        @SerializedName("pin")
        @Expose
        public String pin;

        @SerializedName("otp")
        @Expose
        public String otp;

    }
}
