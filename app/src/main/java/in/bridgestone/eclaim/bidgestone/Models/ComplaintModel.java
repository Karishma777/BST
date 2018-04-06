package in.bridgestone.eclaim.bidgestone.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Explicate1 on 3/23/2018.
 */

public class ComplaintModel {


    public String dealer_damage_id;




    public String code_name;



    public String code;


    public String area;


    public String getDealer_damage_id() {
        return dealer_damage_id;
    }

    public void setDealer_damage_id(String dealer_damage_id) {
        this.dealer_damage_id = dealer_damage_id;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
