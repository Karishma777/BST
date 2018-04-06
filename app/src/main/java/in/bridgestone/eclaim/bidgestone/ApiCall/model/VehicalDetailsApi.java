package in.bridgestone.eclaim.bidgestone.ApiCall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Explicate1 on 3/23/2018.
 */

public class VehicalDetailsApi {

    @SerializedName("responce")
    @Expose
    public Integer responce;


    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("data")
    public ArrayList<Userdata> data;


    public class Userdata {

        @SerializedName("vehicle_id")
        @Expose
        public String vehicle_id;

        @SerializedName("vehicle_reg_no")
        @Expose
        public String vehicle_reg_no;

        @SerializedName("dealer_entity_no")
        @Expose
        public String dealer_entity_no;

        @SerializedName("customer_reg_no")
        @Expose
        public String customer_reg_no;

        @SerializedName("odometer_reading_mounted")
        @Expose
        public String odometer_reading_mounted;

        @SerializedName("odometer_reading_damaged")
        @Expose
        public String odometer_reading_damaged;

        @SerializedName("vehicle_make")
        @Expose
        public String vehicle_make;

        @SerializedName("model_name")
        @Expose
        public String model_name;

        @SerializedName("tyre_type")
        @Expose
        public String tyre_type;

        @SerializedName("oe_rep")
        @Expose
        public String oe_rep;

        @SerializedName("date_of_tyre_sale_day")
        @Expose
        public String date_of_tyre_sale_day;


        @SerializedName("date_of_tyre_sale_month")
        @Expose
        public String date_of_tyre_sale_month;


        @SerializedName("date_of_tyre_sale_year")
        @Expose
        public String date_of_tyre_sale_year;


        @SerializedName("tyre_brand")
        @Expose
        public String tyre_brand;

        @SerializedName("tyre_size")
        @Expose
        public String tyre_size;

        @SerializedName("pattern")
        @Expose
        public String pattern;


        @SerializedName("speed_symbol")
        @Expose
        public String speed_symbol;

        @SerializedName("dot_serial")
        @Expose
        public String dot_serial;


        @SerializedName("tyre_running_serial")
        @Expose
        public String tyre_running_serial;


        @SerializedName("tyre_position")
        @Expose
        public String tyre_position;


        @SerializedName("otd")
        @Expose
        public String otd;




        @SerializedName("rtd")
        @Expose
        public String rtd;


       ;


        @SerializedName("mc_tube_flap_serial")
        @Expose
        public String mc_tube_flap_serial;




    }




}
