package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/1/2018.
 */

public class Engineer_LoginApi {

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

        @SerializedName("engineer_id")
        @Expose
        public String engineer_id;

        @SerializedName("employee_id")
        @Expose
        public String employee_id;

        @SerializedName("employee_name")
        @Expose
        public String employee_name;


        @SerializedName("place_of_posting")
        @Expose
        public String place_of_posting;

        @SerializedName("engineer_email_id")
        @Expose
        public String engineer_email_id;

        @SerializedName("otp")
        @Expose
        public String otp;

        @SerializedName("engineer_mobile_no")
        @Expose
        public String engineer_mobile_no;







    }
}
